package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val context: Context,
              private val list: ArrayList<ColorData>,
              private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>()
{

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view1: View = view.findViewById(R.id.view1)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.view1.setBackgroundColor(data.colorHex.toInt())
        holder.textView.text = data.colorName
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }
}