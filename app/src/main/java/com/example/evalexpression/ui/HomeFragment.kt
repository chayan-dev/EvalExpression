package com.example.evalexpression.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.evalexpression.R
import com.example.evalexpression.databinding.FragmentHomeBinding
import com.example.evalexpression.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  lateinit var binding: FragmentHomeBinding
  val viewModel by activityViewModels<MainViewModel>()

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
      if(binding.inputEt.text?.isBlank()==true){
        Toast.makeText(context,"Enter expressions", Toast.LENGTH_SHORT).show()
      }else{
        viewModel.solveAction(binding.inputEt.text.toString())
        findNavController().navigate(R.id.action_homeFragment_to_resultBottomSheet)
      }
    }

    binding.history.setOnClickListener {
      findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
    }


  }

}