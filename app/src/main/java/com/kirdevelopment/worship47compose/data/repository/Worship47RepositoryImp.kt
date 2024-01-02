package com.kirdevelopment.worship47compose.data.repository

import com.kirdevelopment.worship47compose.data.remote.Worship47Api
import com.kirdevelopment.worship47compose.data.remote.dto.SongDto
import com.kirdevelopment.worship47compose.domain.repository.Worship47Repository
import javax.inject.Inject

class Worship47RepositoryImp @Inject constructor(
    private val api: Worship47Api
): Worship47Repository {
    override suspend fun login(username: String, password: String): String {
        return api.login(username, password)
    }

    override suspend fun getFirstSongs(token: String): SongDto {
        return api.getFirstSongs(token)
    }

    override suspend fun getNextSongs(token: String, page: Int): SongDto {
        return api.getNextSongs(token, page)
    }

    override suspend fun updateSongs(token: String, page: Int, updateFrom: String): SongDto {
        return api.updateSongs(token, page, updateFrom)
    }
}