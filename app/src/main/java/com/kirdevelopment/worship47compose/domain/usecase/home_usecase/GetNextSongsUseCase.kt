package com.kirdevelopment.worship47compose.domain.usecase.home_usecase

import com.kirdevelopment.worship47compose.common.Resource
import com.kirdevelopment.worship47compose.data.remote.dto.SongDto
import com.kirdevelopment.worship47compose.domain.repository.Worship47Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNextSongsUseCase @Inject constructor(
    private val repository: Worship47Repository
) {
    operator fun invoke(token: String, page: Int): Flow<Resource<SongDto>> = flow {
        try {
            emit(Resource.Loading())
            val songs = repository.getNextSongs(token, page)
            emit(Resource.Success(songs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Неизвестная ошибка"))
        } catch (e: IOException) {
            emit(Resource.Error("Нет подключения к серверу, проверьте интернет соединение"))
        }
    }
}