package com.example.myapplication

import androidx.room.*

@Dao
interface ChuckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<ChuckNorisDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(data: ChuckNorisDatabaseModel)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Update
    suspend fun update(lastMinuteProduct: ChuckNorisDatabaseModel)

    @Query("SELECT * FROM $TABLE_NAME where id = :id")
    suspend fun queryAfterId(id: String): ChuckNorisDatabaseModel

}