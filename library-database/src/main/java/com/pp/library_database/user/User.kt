package com.pp.library_database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * user table
 */
@Entity(tableName = "user", indices = [Index(value = ["name"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var name: String? = null,
    @ColumnInfo var password: String? = null
)