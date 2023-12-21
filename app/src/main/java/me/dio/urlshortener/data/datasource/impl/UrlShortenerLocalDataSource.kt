package me.dio.urlshortener.data.datasource.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import me.dio.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.urlshortener.domain.ShortenedUrl
import javax.inject.Inject

class UrlShortenerLocalDataSource @Inject constructor(
    // TODO fazer private val dao: ShortenedUrlDao
) : UrlShortenerDataSource.Local {
    private val urls = MutableStateFlow(emptyList<ShortenedUrl>())

    override fun getAll(): Flow<List<ShortenedUrl>> = urls.onStart {
        delay(2_000)
    }

    override fun add(shortenedUrl: ShortenedUrl) {
        urls.update { prevValue ->
            val urls = setOf(shortenedUrl) + prevValue.toSet()
            urls.toList()
        }
    }
}