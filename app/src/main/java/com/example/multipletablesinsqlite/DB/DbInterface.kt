package com.example.multipletablesinsqlite.DB

import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.Models.Employee
import com.example.multipletablesinsqlite.Models.Orders

interface DbInterface {

    fun addCustomer(customer: Customer)
    fun editCustomer(customer: Customer):Int
    fun deleteCustomer(customer: Customer)
    fun getAllCustomer():List<Customer>
    fun getCustomerById(id:Int):Customer

    fun addEmployee(employee: Employee)
    fun editEmployee(employee: Employee):Int
    fun deleteEmployee(employee: Employee)
    fun getAllEmployee():List<Employee>
    fun getEmployeeById(id:Int):Employee

    fun addOrder(orders: Orders)
    fun editOrder(orders: Orders):Int
    fun deleteOrder(orders: Orders)
    fun getAllOrder():List<Orders>
}