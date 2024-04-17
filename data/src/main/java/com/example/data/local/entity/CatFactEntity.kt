package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_facts")
data class CatFactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val fact: String,
    val length: Int
)
