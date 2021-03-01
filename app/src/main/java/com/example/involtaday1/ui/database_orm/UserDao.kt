package com.example.involtaday1.ui.database_orm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM InvoltaORM ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("DELETE FROM InvoltaORM WHERE id = :id")
    suspend fun deleteByID(id: Int)

    @Query("DELETE FROM InvoltaORM WHERE `values` LIKE :search ")
    suspend fun deleteByValue(search: String)

    @Query("SELECT * FROM InvoltaORM WHERE `values` LIKE :search ")
    fun searchByValue(search: String): LiveData<List<User>>
}

