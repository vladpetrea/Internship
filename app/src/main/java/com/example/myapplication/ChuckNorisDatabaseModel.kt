package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "chuck_noris_joke"

@Entity(tableName = TABLE_NAME)
data class ChuckNorisDatabaseModel(
    @ColumnInfo
    @PrimaryKey
    var id: String = "",
    @ColumnInfo
    var joke: String = "",
    @ColumnInfo
    var createdAt: String = ""
)