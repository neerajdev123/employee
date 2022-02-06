package com.example.employee.repository

import android.content.Context
import com.example.employee.api.EmployeeService
import com.example.employee.api.RetrofitBuilder
import com.example.employee.model.Employee
import com.example.employee.room.EmployeeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class EmployeeRepository {

    fun getAllEmployees() : Call<List<Employee>> {
        val employeeService = RetrofitBuilder.getInstance("http://www.mocky.io/v2/").create(EmployeeService::class.java)
        return employeeService.getEmployees()
    }

    fun insertData(context: Context, employees : List<Employee>) {

        val employeeDatabase = initializeDB(context)

        CoroutineScope(Dispatchers.IO).launch {
            employeeDatabase.getEmployeeDAO().insertEmployees(employees)
        }

    }

    fun getAllEmployeesOffline(context: Context) = initializeDB(context).
    getEmployeeDAO().getAllEmployees()

    private fun initializeDB(context: Context) : EmployeeDatabase {
        return EmployeeDatabase.getDatabaseClient(context)
    }
}