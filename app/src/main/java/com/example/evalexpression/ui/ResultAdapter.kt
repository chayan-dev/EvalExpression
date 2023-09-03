package com.example.evalexpression.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evalexpression.R
import com.example.evalexpression.databinding.ItemResultBinding

class ResultAdapter(
  var data: List<Pair<String,String>>
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>(){

  private lateinit var binding: ItemResultBinding

  inner class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ResultViewHolder {
    return ResultViewHolder(
      parent.context.getSystemService(LayoutInflater::class.java).inflate(
        R.layout.item_result,
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
    binding= ItemResultBinding.bind(holder.itemView)

    binding.apply {
      val item = data[position]
      exp.text = item.first
      result.text = item.second
    }
  }
}