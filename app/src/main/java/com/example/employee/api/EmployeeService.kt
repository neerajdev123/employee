package com.example.employee.api

import com.example.employee.model.Employee
import retrofit2.Call
import retrofit2.http.GET


interface EmployeeService {

    @GET("5d565297300000680030a986")
    fun getEmployees(): Call<List<Employee>>
}