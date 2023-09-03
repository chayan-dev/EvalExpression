package com.example.evalexpression.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evalexpression.R
import com.example.evalexpression.databinding.ItemDateBinding
import com.example.evalexpression.databinding.ItemHistoryExpBinding

class HistoryExprsAdapter (
  var data: List<String>,
  val onClick:(pos: Int) -> Unit
) : RecyclerView.Adapter<HistoryExprsAdapter.HistoryExprsViewHolder>(){

  private lateinit var binding: ItemHistoryExpBinding

  inner class HistoryExprsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): HistoryExprsViewHolder {
    return HistoryExprsViewHolder(
      parent.context.getSystemService(LayoutInflater::class.java).inflate(
        R.layout.item_history_exp,
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onBindViewHolder(holder: HistoryExprsViewHolder, position: Int) {
    binding= ItemHistoryExpBinding.bind(holder.itemView)

    binding.apply {
      exp.text = data[position]
      root.setOnClickListener {
        onClick(position)
      }
    }
  }
}