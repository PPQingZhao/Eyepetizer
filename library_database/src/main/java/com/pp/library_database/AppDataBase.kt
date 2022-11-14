package com.pp.library_database

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pp.library_database.user.User
import com.pp.library_database.user.UserDao
import kotlinx.coroutines.*

/**
 * 数据库
 */
@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private const val TAG = "AppDataBase"

        // 数据库名称
        private const val DB_NAME = "Eyepetizer.db"

        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        val instance by lazy {
            if (context == null) {
                throw RuntimeException("you should call init(context) at first")
            }

            val v1_to_v2= object :Migration(1,2){
                override fun migrate(database: SupportSQLiteDatabase) {
                    // do nothing
                }
            }
            Room.databaseBuilder(context!!, AppDataBase::class.java, DB_NAME)
                .addMigrations(v1_to_v2)
                .build()
        }

        private fun init(ctx: Context) {
            Log.v(TAG, "start init $DB_NAME")
            if (context != null) {
                throw RuntimeException("Do not initialize AppDataBase again")
            }
            context = ctx
            instance
            Log.v(TAG, "$DB_NAME init succeed")
        }

        /**
         * 监听 app 生命周期,用于初始化数据库
         */
        internal class ProcessDatabaseInitializer(private val ctx: Context) :
            DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {

                owner.lifecycleScope.launch(Dispatchers.IO) {
                    // 数据库初始化
                    init(ctx)
                }
                // 初始化完成，移除监听
                ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
            }
        }
    }

}



