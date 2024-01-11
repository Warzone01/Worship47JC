package com.kirdevelopment.worship47compose.domain.usecase.login_usecase

import com.kirdevelopment.worship47compose.common.Resource
import com.kirdevelopment.worship47compose.domain.repository.Worship47Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Worship47Repository
) {
    operator fun invoke(username: String, password: String): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val token = repository.login(username, password)
            emit(Resource.Success(token))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Неизвестная ошибка"))
        } catch (e: IOException) {
            emit(Resource.Error("Нет подключения к серверу, проверьте интернет соединение"))
        }
    }
}