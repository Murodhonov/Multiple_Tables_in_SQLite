package com.example.multipletablesinsqlite.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.R
import com.example.multipletablesinsqlite.databinding.RvItemBinding
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter1(var context: Context, private val list: List<Customer>, val rvClick: rv_click) :
    RecyclerView.Adapter<RvAdapter1.Vh>() {
    inner class Vh(var itemRv: RvItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("DiscouragedPrivateApi")
        fun onBind(user: Customer, position: Int) {
            itemRv.rvTv1.text = user.name

            itemRv.root.setOnClickListener {
                rvClick.onClick(user, position)
            }
        }
    }

    interface rv_click {
        fun onClick(user: Customer, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}