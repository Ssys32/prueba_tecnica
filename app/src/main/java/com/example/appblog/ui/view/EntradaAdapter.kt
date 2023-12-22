package com.example.appblog.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appblog.R
import com.example.appblog.data.Entrada
import com.example.appblog.databinding.EntradasRowBinding

class EntradaAdapter(
    private val context: Context,
    private var entradaList: List<Entrada>,
    private val itemClickListener: onItemClickListener
) : RecyclerView.Adapter<EntradaViewHolder<*>>() {

    interface onItemClickListener {
        fun onItemClick(entrada: Entrada)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntradaViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.entradas_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EntradaViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(entradaList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return entradaList.size
    }

    inner class MainViewHolder(itemView: View) : EntradaViewHolder<Entrada>(itemView) {
        val binding = EntradasRowBinding.bind(itemView)
        override fun bind(item: Entrada, position: Int) {
            binding.itemTitle.text = item.titulo
            binding.itemAuthor.text = item.autor
            binding.itemContent.text = item.contenido
            binding.itemDate.text = item.fecha
            itemView.setOnClickListener { itemClickListener.onItemClick(item) }
        }
    }

}