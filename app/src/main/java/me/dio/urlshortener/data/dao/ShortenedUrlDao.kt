package me.dio.urlshortener.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


//TODO IMPLEMENTAR DAO
@Dao
interface  ShortenedUrlDao {
    @Insert
    fun insert(url: ShortenedUrlModel)

    @Query("SELECT * FROM url ORDER BY id DESC")
    fun getAll(): Flow<List<ShortenedUrlModel>>
}