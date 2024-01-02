package com.kirdevelopment.worship47compose.data.remote

import com.kirdevelopment.worship47compose.data.remote.dto.SongDto
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Worship47Api {

    // получение токена авторизации
    @POST("/get-token/")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): String

    // получение первых песен
    @GET("/songs-ro/")
    fun getFirstSongs(@Header("Authorization") token: String): SongDto

    // получение песен на заданной странице
    @GET("/songs-ro/?page=/")
    fun getNextSongs(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): SongDto

    // обновление песен от заданной даты
    @GET("/api/songs-ro/")
    fun updateSongs(@Header("Authorization") token: String,
                        @Query("page") page: Int,
                        @Query("update_from") date: String): SongDto
}