package com.example.involtaday1.ui.database_orm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM InvoltaORM ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}