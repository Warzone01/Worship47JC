package com.kirdevelopment.worship47compose.domain.repository

import com.kirdevelopment.worship47compose.data.remote.dto.SongData
import com.kirdevelopment.worship47compose.data.remote.dto.SongDto

interface Worship47Repository {
    suspend fun login(username: String, password: String): String
    suspend fun getFirstSongs(token: String): SongDto
    suspend fun getNextSongs(token: String, page: Int): SongDto
    suspend fun updateSongs(token: String, page: Int, updateFrom: String): SongDto
}