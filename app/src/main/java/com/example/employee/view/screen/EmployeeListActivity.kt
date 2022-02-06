package com.example.employee.view.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employee.R
import com.example.employee.model.Employee
import com.example.employee.repository.EmployeeRepository
import com.example.employee.view.adapter.EmployeeAdapter
import com.example.employee.view.listener.ItemClickedListener
import com.example.employee.viewmodel.EmployeeViewModel
import com.example.employee.viewmodel.EmployeeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class EmployeeListActivity : AppCompatActivity(), ItemClickedListener {

    lateinit var viewModel: EmployeeViewModel
    lateinit var employees : MutableList<Employee>
    val adapter = EmployeeAdapter(this)
    private val TAG = EmployeeListActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        initViewModel()
    }

    private fun initUI(){
        toolbar.title = "Employee List"
        setSupportActionBar(toolbar)
        recyclerview.adapter = this.adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, EmployeeViewModelFactory(EmployeeRepository())).get(
            EmployeeViewModel::class.java
        )
        //load list on data fetch
        viewModel.employeeList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            employees = it as MutableList<Employee>
            loadList()
            saveDataInDB()
        })

        //load local data on api data fetch error
        viewModel.errorMessage.observe(this, Observer {
            Log.e(TAG,"error in api : " + it)
            Log.d(TAG, "Loading from local db")
            fetchOfflineData()
        })

       getData()
    }

    private fun getData(){
        progress_load.visibility = View.VISIBLE
        viewModel.getAllEmployees()
    }

    private fun saveDataInDB(){
        viewModel.insertEmployeeData(this.applicationContext,employees)
    }

    private fun loadList(){
        progress_load.visibility = View.GONE
        adapter.setEmployeeData(employees)
    }

    private fun fetchOfflineData(){
        viewModel.getAllEmployeesOffline(this.applicationContext).observe(this, Observer {
            employees = it as MutableList<Employee>
            loadList()
        })
    }

    override fun onItemClicked(employee: Employee) {
        // Navigate to details page
        Log.d(TAG, "Clicked - " + employee.name)
        intent = Intent(this,EmployeeDetailActivity::class.java)
        intent.putExtra("employee_details", employee)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)
        val searchView = menu?.findItem(R.id.action_search)!!
            .actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // filter recycler view when query submitted
                filter(query.toString())
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                // filter recycler view when text is changed
                filter(query.toString());
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun filter(text: String) {
        val filteredEmployees: java.util.ArrayList<Employee> = ArrayList()

        for (employee in employees) {
            if(employee.name != null && employee.company != null && employee.company?.name != null) {
                if (employee.name!!.toUpperCase()
                        .contains(text.toUpperCase()) || employee.company?.name!!.toUpperCase().contains(text.toUpperCase())
                ) {
                    filteredEmployees.add(employee)
                }
            }
        }

        //pass filtered list to adapter
        adapter.filterList(filteredEmployees)
    }
}
