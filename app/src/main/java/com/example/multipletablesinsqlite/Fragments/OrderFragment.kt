package com.example.multipletablesinsqlite.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.multipletablesinsqlite.Adapters.CustomSpinner
import com.example.multipletablesinsqlite.Adapters.CustomSpinner2
import com.example.multipletablesinsqlite.Adapters.RvAdapter3
import com.example.multipletablesinsqlite.DB.MyDbHelper
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.Models.Employee
import com.example.multipletablesinsqlite.Models.Orders
import com.example.multipletablesinsqlite.R
import com.example.multipletablesinsqlite.databinding.Bottomsheet1Binding
import com.example.multipletablesinsqlite.databinding.Bottomsheet3Binding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet3.*
import kotlinx.android.synthetic.main.bottomsheet3.view.*
import kotlinx.android.synthetic.main.fragment_order.view.*
import javax.crypto.CipherSpi

class OrderFragment : Fragment() {
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_order, container, false)

        val db = MyDbHelper(root.context)

        root.add_image3.setOnClickListener {

            val list = ArrayList<Customer>()
            list.addAll(db.getAllCustomer())

            val list2 = ArrayList<Employee>()
            list2.addAll(db.getAllEmployee())

            list.add( 0, Customer("Mijozlar ro'yhati"))
            list2.add(0, Employee("Ishchilar ro'yhati"))

            val bottomSheetDialog = BottomSheetDialog(root.context, R.style.SheetDialog)

            val itemView = LayoutInflater.from(context).inflate(R.layout.bottomsheet3, null, false)

            itemView.spinner_customer.adapter = CustomSpinner(list)
            itemView.spinner_employee.adapter = CustomSpinner2(list2)

            itemView.order_add.setOnClickListener {

                val pos1 = itemView.spinner_customer.selectedItemPosition
                val pos2 = itemView.spinner_employee.selectedItemPosition

                if (pos1 != 0 && pos2 != 0) {

                    db.addOrder(Orders(list[pos1],list2[pos2]))
                    Toast.makeText(context, "Saved !!", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.hide()
                    onStart()

                } else {
                    Toast.makeText(context, "Not selected", Toast.LENGTH_SHORT).show()
                }

            }

            bottomSheetDialog.setContentView(itemView)
            bottomSheetDialog.show()
        }

        return root
    }

    override fun onStart() {
        super.onStart()

        val db = MyDbHelper(root.context)

        root.rv3.adapter = RvAdapter3(root.context,db.getAllOrder(),object : RvAdapter3.rv_click{
            override fun onClick(user: Orders, position: Int) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun editClick(user: Orders, position: Int) {
                val list = ArrayList<Customer>()
                list.addAll(db.getAllCustomer())

                val list2 = ArrayList<Employee>()
                list2.addAll(db.getAllEmployee())

                var index1 = 0
                var index2 = 0

                list.add( 0, Customer("Mijozlar ro'yhati"))
                list2.add(0, Employee("Ishchilar ro'yhati"))

                for (i in list.indices){
                    if (list[i].id == user.id  && list[i].name == user.customer?.name){
                         index1 = i
                    }
                }

                for (i in list2.indices){
                    if (list2[i].id == user.id  && list2[i].name == user.customer?.name){
                        index2 = i
                    }
                }

                val bottomSheetDialog = BottomSheetDialog(root.context, R.style.SheetDialog)

                val itemView = LayoutInflater.from(context).inflate(R.layout.bottomsheet3, null, false)

                itemView.spinner_customer.adapter = CustomSpinner(list)
                itemView.spinner_employee.adapter = CustomSpinner2(list2)

                itemView.spinner_customer.setSelection(index1)
                itemView.spinner_employee.setSelection(index2)

                itemView.order_add.setOnClickListener {

                    val pos1 = itemView.spinner_customer.selectedItemPosition
                    val pos2 = itemView.spinner_employee.selectedItemPosition

                    if (pos1 != 0 && pos2 != 0) {

                        db.editOrder(Orders(list[pos1],list2[pos2]))
                        Toast.makeText(context, "Saved !!", Toast.LENGTH_SHORT).show()
                        bottomSheetDialog.hide()
                        onStart()

                    } else {
                        Toast.makeText(context, "Not selected", Toast.LENGTH_SHORT).show()
                    }

                }

                bottomSheetDialog.setContentView(itemView)
                bottomSheetDialog.show()
            }

            override fun delete(user: Orders) {
                db.deleteOrder(user)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                onStart()
            }
        })
    }
}