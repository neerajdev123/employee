package com.example.employee.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.employee.model.Address
import com.example.employee.model.Company
import com.example.employee.model.Employee
import com.example.employee.model.Geo

@Database(entities = arrayOf(Employee::class), version = 2, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun getEmployeeDAO() : EmployeeDAO

    companion object {

        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabaseClient(context: Context): EmployeeDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(
                        context,
                        EmployeeDatabase::class.java,
                        "EMPLOYEE_DATABASE"
                    )
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }

}