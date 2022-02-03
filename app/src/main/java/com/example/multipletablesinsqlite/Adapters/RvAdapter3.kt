package com.example.multipletablesinsqlite.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.Models.Employee
import com.example.multipletablesinsqlite.Models.Orders
import com.example.multipletablesinsqlite.R
import com.example.multipletablesinsqlite.databinding.RvItem2Binding
import com.example.multipletablesinsqlite.databinding.RvItemBinding

class RvAdapter3(var context: Context, private val list: List<Orders>, val rvClick: rv_click) :
    RecyclerView.Adapter<RvAdapter3.Vh>() {
    inner class Vh(var itemRv: RvItem2Binding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("DiscouragedPrivateApi")
        fun onBind(user: Orders, position: Int) {

            itemRv.rvTv11.text = user.customer?.name
            itemRv.rvTv22.text = user.employee?.name
            itemRv.rvTv33.text = user.date

            itemRv.more2.setOnClickListener { it ->
                val popupMenu = PopupMenu(context, it)
                popupMenu.inflate(R.menu.menu1)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.edit_menu -> {
                            rvClick.editClick(user, position)
                            true
                        }
                        R.id.delete_menu -> {
                            rvClick.delete(user)
                            true
                        }
                        else -> true
                    }
                }
                try {
                    val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true
                    val menu = popup.get(popupMenu)
                    menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(menu, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    popupMenu.show()
                }
            }

            itemRv.root.setOnClickListener {
                rvClick.onClick(user, position)
            }
        }
    }

    interface rv_click {
        fun onClick(user: Orders, position: Int)
        fun editClick(user: Orders, position: Int)
        fun delete(user:Orders)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}