package com.example.multipletablesinsqlite.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.multipletablesinsqlite.Adapters.RvAdapter1
import com.example.multipletablesinsqlite.DB.MyDbHelper
import com.example.multipletablesinsqlite.Models.Customer
import com.example.multipletablesinsqlite.R
import com.example.multipletablesinsqlite.databinding.Bottomsheet1Binding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_customer.view.*

class CustomerFragment : Fragment() {
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_customer, container, false)

        val db = MyDbHelper(root.context)

        root.add_image1.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(root.context, R.style.SheetDialog)

            val itemView = LayoutInflater.from(context).inflate(R.layout.bottomsheet1, null, false)
            val binding3 = Bottomsheet1Binding.bind(itemView)

            binding3.customerAdd.setOnClickListener {

                val name = binding3.customerEdt.text.toString().trim()
                if (name.isNotEmpty()) {

                    val customer = Customer(name)

                    db.addCustomer(customer)
                    onStart()

                    Toast.makeText(context, "Customer saved !!", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.hide()


                } else {
                    Toast.makeText(context, "Enter name first", Toast.LENGTH_SHORT).show()
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

        root.rv1.adapter = RvAdapter1(root.context,db.getAllCustomer(),object : RvAdapter1.rv_click{
            override fun onClick(user: Customer, position: Int) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun editClick(user: Customer, position: Int) {
                db.editCustomer(user)
                onStart()
            }

        })
    }
}