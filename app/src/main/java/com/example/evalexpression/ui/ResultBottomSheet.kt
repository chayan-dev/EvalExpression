package com.example.evalexpression.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evalexpression.databinding.FragmentResultBottomSheetBinding
import com.example.evalexpression.ui.MainViewModel
import com.example.evalexpression.ui.ResultAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResultBottomSheet : BottomSheetDialogFragment() {

  private lateinit var binding: FragmentResultBottomSheetBinding
  private lateinit var adapter: ResultAdapter
  val viewModel : MainViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    binding = FragmentResultBottomSheetBinding.inflate(inflater, container, false)

    adapter = ResultAdapter(listOf())
    binding.rvResult.layoutManager = LinearLayoutManager(context)
    binding.rvResult.adapter = adapter

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.expressionsValues.observe(viewLifecycleOwner) { result->
      adapter.data = result
      adapter.notifyDataSetChanged()
    }

  }

  override fun onDestroy() {
    super.onDestroy()
    viewModel.resetBottomSheetData()
  }

}