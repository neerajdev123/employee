package com.example.employee.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employee.model.Employee

@Dao
interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertEmployees(employees: List<Employee>)

    @Query("SELECT * FROM Employee")
    fun getAllEmployees(): LiveData<List<Employee>>
}