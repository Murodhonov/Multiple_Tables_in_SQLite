package com.example.multipletablesinsqlite.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.R
import kotlinx.android.synthetic.main.spinner_item.view.*

class CustomSpinner(private val list: ArrayList<Customer>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val itemView: View = p1 ?: LayoutInflater.from(p2?.context).inflate(R.layout.spinner_item, p2, false)

        itemView.tv_spinner.text = list[p0].name

        return itemView
    }

}