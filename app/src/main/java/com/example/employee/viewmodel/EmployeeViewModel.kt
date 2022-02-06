package com.example.employee.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employee.model.Employee
import com.example.employee.repository.EmployeeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeViewModel (private val employeeRepository: EmployeeRepository) : ViewModel() {

    val employeeList = MutableLiveData<List<Employee>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllEmployees() {
        val response = employeeRepository.getAllEmployees()
        response.enqueue(object : Callback<List<Employee>> {
            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                employeeList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }

    fun insertEmployeeData(appContext : Context, employee : List<Employee>){
        employeeRepository.insertData(appContext,employee)
    }

    fun getAllEmployeesOffline(appContext: Context) = employeeRepository.getAllEmployeesOffline(appContext)
}