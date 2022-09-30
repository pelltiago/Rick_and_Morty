package com.example.rickandmorty.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.viewmodel.CharactersViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailFrameLayout: FrameLayout? = findViewById(R.id.detailFrameLayout)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val charactersViewModel: CharactersViewModel by viewModels()
        var maxPageNumber: Int?
        charactersViewModel.onCreate(pageNumber.toString())
        charactersViewModel.charactersResponseModel.observe(this, Observer {
            if (it != null) {
                maxPageNumber = it.info?.pages
                setupRecyclerView(recyclerView, setOnClickListeners(charactersViewModel, detailFrameLayout, it.results, maxPageNumber), it)
            }
        })
    }

    private fun setOnClickListeners(
        charactersViewModel: CharactersViewModel,
        detailFrameLayout: FrameLayout?,
        charactersList: List<Results>,
        maxPageNumber: Int?
    ): View.OnClickListener {

        binding.nextBtn.setOnClickListener {
            if (pageNumber < maxPageNumber!!) {
                pageNumber++
                charactersViewModel.onCreate(pageNumber.toString())
            }
        }

        binding.prevBtn.setOnClickListener {
            if (pageNumber > 1) {
                pageNumber--
                charactersViewModel.onCreate(pageNumber.toString())
            }
        }

        val onClickListener = View.OnClickListener { itemView ->
            val id = (itemView.tag.toString().toInt() - 20 * (pageNumber - 1)) - 1

            if (detailFrameLayout != null) {
                val fragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", id.toString().toInt())
                        putString("name", charactersList[id].name)
                        putString("image", charactersList[id].image)
                        putString("gender", charactersList[id].gender)
                        putString("status", charactersList[id].status)
                        putString("species", charactersList[id].species)
                        putString("type", charactersList[id].type)
                    }
                }
                supportFragmentManager.beginTransaction().replace(R.id.detailFrameLayout, fragment)
                    .commit()
            } else {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("id", id.toString().toInt())
                    putExtra("name", charactersList[id].name)
                    putExtra("image", charactersList[id].image)
                    putExtra("gender", charactersList[id].gender)
                    putExtra("status", charactersList[id].status)
                    putExtra("species", charactersList[id].species)
                    putExtra("type", charactersList[id].type)
                }
                this.startActivity(intent)
            }
        }
        return onClickListener
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        charactersResponseModel: CharactersResponseModel
    ) {
        recyclerView.adapter =
            CharactersViewAdapter(charactersResponseModel.results, onClickListener)
    }
}

