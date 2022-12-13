package com.pp.library_database.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface UserDao {

    /**
     * 加入一条数据，如果主键值一样就替换，如果不一样就添加
     * Insert a User in the database. If the User already exists, replace it.
     *
     * @param user the task to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("DELETE FROM user where name = :username")
    fun delete(username: String)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User where name = :username")
    fun findUser(username: String): User

    /**
     * 表中有数据就返回第一条数据
     *
     * @return
     */
    @Query("SELECT * FROM User")
    fun findSingleUser(): User

}
