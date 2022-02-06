package com.example.employee.view.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employee.R
import com.example.employee.model.Employee
import com.example.employee.view.listener.ItemClickedListener

class EmployeeAdapter (private val cellClickListener: ItemClickedListener) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var employees = mutableListOf<Employee>()

    fun setEmployeeData(employees: List<Employee>) {
        this.employees = employees.toMutableList()
        notifyDataSetChanged()
    }

    fun filterList(employees: List<Employee>){
        this.employees = employees.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list, parent, false)

        return EmployeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.profileName.text = employee.name
        holder.company.text = employee.company?.name

            Glide.with(holder.itemView.context).load(employee.profileImage)
                .placeholder(R.drawable.ic_person_outline_black_24dp)
                .error(R.drawable.ic_person_outline_black_24dp)
                .fallback(R.drawable.ic_person_outline_black_24dp).centerCrop()
                .into(holder.profileImage)

        //set item click listener
        holder.itemView.setOnClickListener {
            cellClickListener.onItemClicked(employee)
        }
    }

    class EmployeeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.img_picture)
        val profileName : TextView = itemView.findViewById(R.id.txt_name)
        val company : TextView = itemView.findViewById(R.id.txt_company_name)
    }
}