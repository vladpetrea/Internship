package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ChuckNorisDatabaseModel::class],
    version = 1
)
abstract class ExampleRoomDatabase : RoomDatabase() {

    abstract fun getChuckDao(): ChuckDao

}

