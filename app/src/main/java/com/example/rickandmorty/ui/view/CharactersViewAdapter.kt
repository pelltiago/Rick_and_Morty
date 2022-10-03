package com.example.rickandmorty.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Results

class CharactersViewAdapter(private val values: List<Results>, private val onClickListener: View.OnClickListener) :
        RecyclerView.Adapter<CharactersViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val characterResult = values[position]

            holder.characterImageView.let {
                Glide.with(holder.itemView.context)
                    .load(characterResult.image)
                    .into(it)
            }

            with(holder.itemView) {
                tag = position
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val characterImageView: ImageView = view.findViewById(R.id.characterImageView)
        }
    }
