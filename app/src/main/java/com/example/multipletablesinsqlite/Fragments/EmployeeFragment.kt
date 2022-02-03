package com.example.multipletablesinsqlite.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.multipletablesinsqlite.Adapters.RvAdapter2
import com.example.multipletablesinsqlite.DB.MyDbHelper
import com.example.multipletablesinsqlite.Models.Employee
import com.example.multipletablesinsqlite.R
import com.example.multipletablesinsqlite.databinding.Bottomsheet2Binding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_employee.view.*

class EmployeeFragment : Fragment() {
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_employee, container, false)

        val db = MyDbHelper(root.context)

        root.add_image2.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(root.context, R.style.SheetDialog)

            val itemView = LayoutInflater.from(context).inflate(R.layout.bottomsheet2, null, false)
            val binding2 = Bottomsheet2Binding.bind(itemView)

            binding2.employeeAdd.setOnClickListener {

                val name = binding2.employeeEdt.text.toString().trim()
                if (name.isNotEmpty()) {

                    val employee = Employee(name)

                    db.addEmployee(employee)

                    binding2.employeeEdt.text.clear()
                    Toast.makeText(context, "Employee saved !!", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.hide()
                    onStart()

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
        root.rv2.adapter = RvAdapter2(root.context,db.getAllEmployee(),object : RvAdapter2.rv_click{
            override fun onClick(user: Employee, position: Int) {
                Toast.makeText(context, "Employee clicked", Toast.LENGTH_SHORT).show()
            }

            override fun editClick(user: Employee, position: Int) {
                db.editEmployee(user)
                onStart()
            }

        })
    }
}