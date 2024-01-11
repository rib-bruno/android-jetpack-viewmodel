package me.dio.urlshortener.data.datasource.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import me.dio.urlshortener.data.dao.ShortenedUrlDao
import me.dio.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.urlshortener.data.model.ShortenedUrlModel
import me.dio.urlshortener.domain.ShortenedUrl
import javax.inject.Inject

//
class UrlShortenerLocalDataSource @Inject constructor(
    private val dao: ShortenedUrlDao
) : UrlShortenerDataSource.Local {

    override fun getAll(): Flow<List<ShortenedUrl>> = dao.getAll().map { items ->
        items.map { item ->
            ShortenedUrl(
                original = item.original,
                url = item.url,
            )
        }
    }
    override suspend fun add(shortenedUrl: ShortenedUrl) = withContext(Dispatchers.IO) {
       val model = ShortenedUrlModel(
           original = shortenedUrl.original,
           url = shortenedUrl.url,
       )
        dao.insert(model)
    }
}