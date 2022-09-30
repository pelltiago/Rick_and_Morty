package com.example.rickandmorty.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(intent.extras?.getString("image"))
            .into(binding.characterImageView)

        binding.nameTextView.text = intent.extras?.getString("name")
        binding.statusTextView.text = intent.extras?.getString("status")
        binding.genderTextView.text = intent.extras?.getString("gender")
        binding.speciesTextView.text = intent.extras?.getString("species")
        binding.typeTextView.text = intent.extras?.getString("type")

    }
}