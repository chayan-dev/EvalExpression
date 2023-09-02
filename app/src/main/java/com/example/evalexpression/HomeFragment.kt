package com.example.evalexpression

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.evalexpression.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

  lateinit var binding: FragmentHomeBinding
  val viewModel : MainViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnSolve.setOnClickListener {
      viewModel.solveAction(binding.inputEt.text.toString())
    }


  }

}