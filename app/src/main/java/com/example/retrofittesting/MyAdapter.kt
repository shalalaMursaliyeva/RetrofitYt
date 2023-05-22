package com.example.retrofittesting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.retrofittesting.model.DataFile

class MyAdapter(val context: Context, val list: List<DataFile>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var userId : TextView
        init{
            tvTitle = itemView.findViewById(R.id.tvTitle)
            userId = itemView.findViewById(R.id.userId)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title.toString()
        holder.userId.text = list[position].userId.toString()

    }
}