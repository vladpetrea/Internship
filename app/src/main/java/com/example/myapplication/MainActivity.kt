package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val CHUCK_DATABASE_TAG = "chuck_database"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Room.databaseBuilder(
            applicationContext,
            ExampleRoomDatabase::class.java,
            CHUCK_DATABASE_TAG
        ).build()

        val dao = database.getChuckDao()

        val listOfViewpagerItems = listOf(
            ExampleModel(
                "image 1",
                0,
                "https://files.worldwildlife.org/wwfcmsprod/images/HERO_Penguins_Antarctica/story_full_width/9de57cats0_Medium_WW267491.jpg"
            ),
            ExampleModel(
                "image 2",
                1,
                "https://files.worldwildlife.org/wwfcmsprod/images/HERO_Penguins_Antarctica/story_full_width/9de57cats0_Medium_WW267491.jpg"
            ),
            ExampleModel(
                "image 3",
                2,
                "https://files.worldwildlife.org/wwfcmsprod/images/HERO_Penguins_Antarctica/story_full_width/9de57cats0_Medium_WW267491.jpg"
            ), ExampleModel(
                "image 4",
                3,
                "https://files.worldwildlife.org/wwfcmsprod/images/HERO_Penguins_Antarctica/story_full_width/9de57cats0_Medium_WW267491.jpg"
            ),
            ExampleModel(
                "image 5",
                4,
                "https://files.worldwildlife.org/wwfcmsprod/images/HERO_Penguins_Antarctica/story_full_width/9de57cats0_Medium_WW267491.jpg"
            )
        )


        binding.viewpager.adapter = ExampleAdapter().apply {
            submitList(
                listOfViewpagerItems
            )
        }


        binding.viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                binding.indicator.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicator.onPageSelected(position)
            }
        })
        binding.indicator.setPageSize(
            listOfViewpagerItems.size
        )
        binding.indicator.notifyDataChanged()

        binding.chuckNorisButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                //ChuckNorisRepositoryOkHttp().retrieveRandomChuckJoke()


                ChuckNorisRepositoryRetrofit().apply {
                    val response = retrieveChuckJokeParsed()
                    dao.insertOne(
                        ChuckNorisDatabaseModel(
                            id = response.id,
                            joke = response.value,
                            createdAt = response.createdAt
                        )
                    )
                    //retrieveChuckJokeParsed()
                }
            }
        }
    }
}