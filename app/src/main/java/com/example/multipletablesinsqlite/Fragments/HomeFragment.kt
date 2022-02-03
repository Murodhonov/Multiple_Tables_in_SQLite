package com.example.multipletablesinsqlite.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.multipletablesinsqlite.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root =  inflater.inflate(R.layout.fragment_home, container, false)

        root.employee.setOnClickListener {
            findNavController().navigate(R.id.employeeFragment)
        }
        root.customer.setOnClickListener {
            findNavController().navigate(R.id.customerFragment)
        }
        root.order.setOnClickListener {
            findNavController().navigate(R.id.orderFragment)
        }

        return root
    }


}