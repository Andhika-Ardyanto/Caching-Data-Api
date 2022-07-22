package com.example.caching_data_api.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var id: Int,
    var username: String,
    var avatar: String
)
