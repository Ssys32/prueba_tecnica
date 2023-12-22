package com.example.appblog.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class EntradaViewHolder<T> (view : View) : RecyclerView.ViewHolder(view){

    abstract fun bind(item: T, position: Int)
}