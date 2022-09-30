package com.example.rickandmorty.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        nameTextView.text = arguments?.getString("name")

        val genderTextView: TextView = view.findViewById(R.id.genderTextView)
        genderTextView.text = arguments?.getString("gender")

        val statusTextView: TextView = view.findViewById(R.id.statusTextView)
        statusTextView.text = arguments?.getString("status")

        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        dateTextView.text = arguments?.getString("date")

        val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        typeTextView.text = arguments?.getString("type")

        val imageView: ImageView = view.findViewById(R.id.imgChar)
        Glide.with(this)
            .load(arguments?.getString("image"))
            .into(imageView)

        return view
    }

}