package com.rogok.glideapp.api

import com.rogok.glideapp.models.User
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

//https://jsonplaceholder.typicode.com/photos?_page=3&_limit=3

interface JsonPlaceholderApi {

    @GET("photos")
    suspend fun getUsers(
        @Query("_page")
        pageNumber: Int = 0,
        @Query("_limit")
        itemsNumber: Int = 10

    ): ArrayList<User>


}