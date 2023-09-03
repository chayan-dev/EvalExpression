package com.example.evalexpression.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evalexpression.R
import com.example.evalexpression.databinding.ItemDateBinding

class HistoryDateAdapter (
  var data: List<String>,
  val onClick:(date: String) -> Unit
) : RecyclerView.Adapter<HistoryDateAdapter.HistoryDateViewHolder>(){

  private lateinit var binding: ItemDateBinding

  inner class HistoryDateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): HistoryDateViewHolder {
    return HistoryDateViewHolder(
      parent.context.getSystemService(LayoutInflater::class.java).inflate(
        R.layout.item_date,
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onBindViewHolder(holder: HistoryDateViewHolder, position: Int) {
    binding= ItemDateBinding.bind(holder.itemView)

    binding.apply {
      date.text = data[position]
      root.setOnClickListener {
        onClick(data[position])
      }
    }
  }
}