package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chuckNorisButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                //ChuckNorisRepositoryOkHttp().retrieveRandomChuckJoke()

                ChuckNorisRepositoryRetrofit().apply {
                    //retrieveRandomChuckJokeWithQueryParams()
                    retrieveChuckJokeParsed()
                }
            }
        }
    }
}