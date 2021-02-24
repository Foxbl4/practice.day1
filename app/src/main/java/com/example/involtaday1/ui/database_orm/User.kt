package com.example.involtaday1.ui.database_orm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InvoltaORM")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val values: String)

