package com.example.multipletablesinsqlite.Models

class Orders {
    var id: Int? = null
    var customer: Customer? = null
    var employee: Employee? = null
    var date: String? = null

    constructor()


    constructor(id: Int?, customer: Customer?, employee: Employee?, date: String?) {
        this.id = id
        this.customer = customer
        this.employee = employee
        this.date = date
    }

    constructor(customer: Customer?, employee: Employee?) {
        this.customer = customer
        this.employee = employee
    }

    override fun toString(): String {
        return "Orders(id=$id, customer=$customer, employee=$employee, date=$date)"
    }
}