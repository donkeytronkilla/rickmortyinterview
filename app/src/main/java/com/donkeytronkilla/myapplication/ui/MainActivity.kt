package com.donkeytronkilla.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.donkeytronkilla.myapplication.R
import com.donkeytronkilla.myapplication.api.RickMortyApi
import com.donkeytronkilla.myapplication.databinding.ActivityMainBinding
import com.donkeytronkilla.myapplication.model.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapater = CharacterAdapter()
    private val viewModel by viewModels<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.apply {
            recyclerCharacter.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerCharacter.adapter = adapater
        }

        lifecycleScope.launchWhenStarted {
            viewModel.charactersPagingData.collectLatest { charactersPagingData ->
                adapater.submitData(charactersPagingData)
            }
        }
    }
}