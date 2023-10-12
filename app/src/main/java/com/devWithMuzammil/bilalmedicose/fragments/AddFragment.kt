package com.devWithMuzammil.bilalmedicose.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.devWithMuzammil.bilalmedicose.fragments.AddFragmentBinding


class AddFragment : Fragment() {
    private lateinit var binding:AddFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        // Create and set the adapter for the Spinner
        val items = arrayOf("Item 1", "Item 2", "Item 3")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerVolume.adapter = adapter

        // Other setup and logic for your Fragment

        return view
    }

}