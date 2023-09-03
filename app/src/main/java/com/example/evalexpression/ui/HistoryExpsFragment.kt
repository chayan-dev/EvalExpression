package com.example.evalexpression.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evalexpression.R
import com.example.evalexpression.databinding.FragmentHistoryExpsBinding
import com.example.evalexpression.db.entity.SavedExpr
import com.example.evalexpression.models.ExprBody

class HistoryExpsFragment : Fragment() {

  private lateinit var binding: FragmentHistoryExpsBinding
  private lateinit var adapter: HistoryExprsAdapter
  private val viewModel : HistoryViewModel by activityViewModels()
  private val mainViewModel : MainViewModel by activityViewModels()
  private lateinit var allExpressionList: List<SavedExpr>

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    binding = FragmentHistoryExpsBinding.inflate(inflater, container, false)

    adapter = HistoryExprsAdapter(listOf()){ pos->
      mainViewModel.setEvaluationResultFromDB(allExpressionList[pos],pos)
      findNavController().navigate(R.id.action_historyExpsFragment_to_resultBottomSheet)
    }
    binding.rvExprs.layoutManager = LinearLayoutManager(context)
    binding.rvExprs.adapter = adapter

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.title.text = viewModel.currentDate

    viewModel.getExpressionsOnDate().observe(viewLifecycleOwner) { result->
      Log.d("getExpressionsOnDate", result.toString())
      allExpressionList = result

      val exprsList = mutableListOf<String>()
      for (i in result){
        var exprsText=""
        for (j in i.values){
          exprsText=exprsText.plus("${j.first} \n")
        }
        exprsText=exprsText.dropLast(2)
        Log.d("expsText", exprsText)
        exprsList.add(exprsText)
      }
      Log.d("expsList", exprsList.toString())
      adapter.data = exprsList
      adapter.notifyDataSetChanged()
    }

  }


}