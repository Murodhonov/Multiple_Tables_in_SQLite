package com.example.multipletablesinsqlite.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.Models.Employee
import com.example.multipletablesinsqlite.R
import com.example.multipletablesinsqlite.databinding.RvItemBinding

class RvAdapter2(var context: Context, private val list: List<Employee>, val rvClick: rv_click) :
    RecyclerView.Adapter<RvAdapter2.Vh>() {
    inner class Vh(var itemRv: RvItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: Employee, position: Int) {
            itemRv.rvTv1.text = user.name

            itemRv.root.setOnClickListener {
                rvClick.onClick(user, position)
            }
        }
    }

    interface rv_click {
        fun onClick(user: Employee, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}