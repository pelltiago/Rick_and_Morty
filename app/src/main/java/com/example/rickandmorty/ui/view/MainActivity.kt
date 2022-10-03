package com.example.rickandmorty.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.viewmodel.CharactersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailFrameLayout: FrameLayout? = findViewById(R.id.detailFrameLayout)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val charactersViewModel: CharactersViewModel by viewModels()

        charactersViewModel.onCreate()
        initSearchView(charactersViewModel)
        setButtonListeners(charactersViewModel)


        charactersViewModel.charactersResponseModel.observe(this, Observer
        {
            if (it != null) {
                binding.frameLayout.visibility = View.VISIBLE
                binding.nextBtn.alpha = 1f
                binding.prevBtn.alpha = 1f
                binding.nextBtn.isClickable = true
                binding.prevBtn.isClickable = true
                setupRecyclerView(
                    recyclerView,
                    setOnClickListeners(detailFrameLayout, it.results),
                    it
                )
                binding.infoPage.text = "  ${charactersViewModel.actualPage} / ${charactersViewModel.maxPage}  "
            } else {
                binding.frameLayout.visibility = View.INVISIBLE
                binding.nextBtn.isClickable = false
                binding.prevBtn.isClickable = false
                binding.nextBtn.alpha = 0.5f
                binding.prevBtn.alpha = 0.5f
                binding.infoPage.text = "  /  "

            }
        })
    }

    private fun setButtonListeners(charactersViewModel: CharactersViewModel) {
        binding.nextBtn.setOnClickListener {
            charactersViewModel.onClickNextButton()
        }

        binding.prevBtn.setOnClickListener {
            charactersViewModel.onClickPrevButton()

        }
    }


    private fun setOnClickListeners(
        detailFrameLayout: FrameLayout?,
        charactersList: List<Results>,
    ): View.OnClickListener {

        val onClickListener = View.OnClickListener { itemView ->

            val id = itemView.tag.toString().toInt()

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

    private fun initSearchView(
        charactersViewModel: CharactersViewModel
    ) {
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                charactersViewModel.searchCharacter(query, 1)
                if (query !== "") {
                    binding.searchview.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "") {
                    onQueryTextSubmit(newText)
                }
                return false
            }
        })
    }
}

