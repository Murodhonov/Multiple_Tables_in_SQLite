package com.example.multipletablesinsqlite.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.multipletablesinsqlite.DB.Const.CUSTOMER_ID
import com.example.multipletablesinsqlite.DB.Const.CUSTOMER_NAME
import com.example.multipletablesinsqlite.DB.Const.CUSTOMER_TABLE
import com.example.multipletablesinsqlite.DB.Const.DB_NAME
import com.example.multipletablesinsqlite.DB.Const.DB_VERSION
import com.example.multipletablesinsqlite.DB.Const.EMPLOYEE_ID
import com.example.multipletablesinsqlite.DB.Const.EMPLOYEE_NAME
import com.example.multipletablesinsqlite.DB.Const.EMPLOYEE_TABLE
import com.example.multipletablesinsqlite.DB.Const.ORDERS_ID
import com.example.multipletablesinsqlite.DB.Const.ORDERS_TABLE
import com.example.multipletablesinsqlite.DB.Const.ORDER_CUSTOMER_ID
import com.example.multipletablesinsqlite.DB.Const.ORDER_DATE
import com.example.multipletablesinsqlite.DB.Const.ORDER_EMPLOYEE_ID
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.Models.Employee
import com.example.multipletablesinsqlite.Models.Orders
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyDbHelper(context: Context)
    :SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),DbInterface{
    override fun onCreate(db: SQLiteDatabase?) {

        val queryCustomer = "CREATE TABLE $CUSTOMER_TABLE($CUSTOMER_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,$CUSTOMER_NAME TEXT NOT NULL)"
        db?.execSQL(queryCustomer)

        val queryEmployee = "CREATE TABLE $EMPLOYEE_TABLE($EMPLOYEE_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,$EMPLOYEE_NAME TEXT NOT NULL)"
        db?.execSQL(queryEmployee)

        val queryOrder = "CREATE TABLE $ORDERS_TABLE($ORDERS_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,$ORDER_CUSTOMER_ID INTEGER NOT NULL,$ORDER_EMPLOYEE_ID INTEGER NOT NULL,$ORDER_DATE TEXT NOT NULL, FOREIGN KEY($ORDER_CUSTOMER_ID) REFERENCES $CUSTOMER_TABLE($CUSTOMER_ID), FOREIGN KEY($ORDER_EMPLOYEE_ID) REFERENCES $EMPLOYEE_TABLE ($EMPLOYEE_ID) )"
        db?.execSQL(queryOrder)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addCustomer(customer: Customer) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(CUSTOMER_NAME,customer.name)

        db.insert(CUSTOMER_TABLE,null,cv)
        db.close()
    }

    override fun editCustomer(customer: Customer): Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(CUSTOMER_ID,customer.id)
        cv.put(CUSTOMER_NAME,customer.name)

        return db.update(CUSTOMER_TABLE,cv,"$CUSTOMER_ID = ?", arrayOf(customer.id.toString()))
    }

    override fun deleteCustomer(customer: Customer) {
        val db = this.writableDatabase

        db.delete(CUSTOMER_TABLE,"$CUSTOMER_ID = ?", arrayOf(customer.id.toString()))

        db.close()
    }

    @SuppressLint("Recycle")
    override fun getAllCustomer(): ArrayList<Customer> {
        val list = ArrayList<Customer>()

        val query = "SELECT * FROM $CUSTOMER_TABLE"

        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {

                val customer = Customer()
                customer.id = cursor.getInt(0)
                customer.name = cursor.getString(1)

                list.add(customer)
            }while (cursor.moveToNext())
        }
        return list
    }

    @SuppressLint("Recycle")
    override fun getCustomerById(id: Int): Customer {
        val db = this.readableDatabase
        val cursor = db.query(
            CUSTOMER_TABLE, arrayOf(
                CUSTOMER_ID,
                CUSTOMER_NAME
            ), "$CUSTOMER_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        return Customer(
            cursor.getInt(0),
            cursor.getString(1)
        )
    }

    override fun addEmployee(employee: Employee) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(EMPLOYEE_NAME,employee.name)

        db.insert(EMPLOYEE_TABLE,null,cv)
        db.close()
    }

    override fun editEmployee(employee: Employee): Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(EMPLOYEE_ID,employee.id)
        cv.put(EMPLOYEE_NAME,employee.name)

        return db.update(EMPLOYEE_TABLE,cv,"$EMPLOYEE_ID = ?", arrayOf(employee.id.toString()))
    }

    override fun deleteEmployee(employee: Employee) {
        val db = this.writableDatabase

        db.delete(EMPLOYEE_TABLE,"$EMPLOYEE_ID = ?", arrayOf(employee.id.toString()))

        db.close()
    }

    @SuppressLint("Recycle")
    override fun getAllEmployee(): ArrayList<Employee> {
        val list = ArrayList<Employee>()

        val query = "SELECT * FROM $EMPLOYEE_TABLE"

        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {

                val employee = Employee()
                employee.id = cursor.getInt(0)
                employee.name = cursor.getString(1)

                list.add(employee)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun getEmployeeById(id: Int): Employee {
        val db = this.readableDatabase
        val cursor = db.query(
            EMPLOYEE_TABLE, arrayOf(
                EMPLOYEE_ID,
                EMPLOYEE_NAME
            ), "$EMPLOYEE_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        return Employee(
            cursor.getInt(0),
            cursor.getString(1)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun addOrder(orders: Orders) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(ORDER_CUSTOMER_ID,orders.customer?.id)
        cv.put(ORDER_EMPLOYEE_ID,orders.employee?.id)
        val simpleDateFormat = SimpleDateFormat("dd//MM//yyyy hh:mm:ss").format(Date())
        cv.put(ORDER_DATE,simpleDateFormat)

        db.insert(ORDERS_TABLE,null,cv)
        db.close()

    }

    override fun editOrder(orders: Orders): Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(ORDERS_ID,orders.id)
        cv.put(ORDER_CUSTOMER_ID,orders.customer?.id)
        cv.put(ORDER_EMPLOYEE_ID,orders.employee?.id)
        cv.put(ORDER_DATE,orders.date)

        return db.update(ORDERS_TABLE,cv,"$ORDERS_ID = ?", arrayOf(orders.id.toString()))
    }

    override fun deleteOrder(orders: Orders) {
        val db = this.writableDatabase

        db.delete(ORDERS_TABLE,"$ORDERS_ID = ?", arrayOf(orders.id.toString()))

        db.close()
    }

    @SuppressLint("Recycle")
    override fun getAllOrder(): List<Orders> {
        val list = ArrayList<Orders>()
        val query = "select * from $ORDERS_TABLE"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val orders = Orders()
                orders.id = cursor.getInt(0)
                orders.customer = getCustomerById(cursor.getInt(1))
                orders.employee = getEmployeeById(cursor.getInt(2))
                orders.date = cursor.getString(3)
                list.add(orders)
            }while (cursor.moveToNext())
        }

        return list
    }


}