package com.example.employee.view.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.employee.R
import com.example.employee.databinding.ActivityEmployeeDetailBinding
import com.example.employee.model.Employee

class EmployeeDetailActivity : AppCompatActivity() {

    lateinit var employee : Employee
    lateinit var binding : ActivityEmployeeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_employee_detail)
        getIntentDetails()
        initUI()
    }

    private fun getIntentDetails(){
        employee = intent.extras?.get("employee_details") as Employee
        binding.employee = employee
    }

    private fun initUI(){
        binding.toolbarDetail.title = "Employee Details"
        setSupportActionBar( binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            super.onBackPressed()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }


}
