package com.example.evalexpression.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evalexpression.R
import com.example.evalexpression.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

  private lateinit var binding: FragmentHistoryBinding
  private lateinit var adapter: HistoryDateAdapter
  val viewModel : HistoryViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    binding = FragmentHistoryBinding.inflate(inflater, container, false)

    adapter = HistoryDateAdapter(listOf()){ date ->
      viewModel.setDate(date)
      findNavController().navigate(R.id.action_historyFragment_to_historyExpsFragment)
    }
    binding.rvDate.layoutManager = LinearLayoutManager(context)
    binding.rvDate.adapter = adapter

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.getSavedDates().observe(viewLifecycleOwner) { result->
      Log.d("getSavedDates", result.toString())
      adapter.data = result
      adapter.notifyDataSetChanged()
    }

  }

}