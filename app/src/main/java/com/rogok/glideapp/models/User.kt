package com.rogok.glideapp.models


import com.google.gson.annotations.SerializedName

data class User(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)