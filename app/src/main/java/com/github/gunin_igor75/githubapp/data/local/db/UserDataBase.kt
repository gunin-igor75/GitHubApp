package com.github.gunin_igor75.githubapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.gunin_igor75.githubapp.data.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "UserDataBase"
        private var INSTANCE: UserDataBase? = null
        private val lock = Any()

        fun getInstance(context: Context): UserDataBase {
            INSTANCE?.let { return it }

            synchronized(lock) {
                INSTANCE?.let { return it }
                val dataBase = Room.databaseBuilder(
                    context = context,
                    klass = UserDataBase::class.java,
                    name = DB_NAME,
                ).build()

                INSTANCE = dataBase
                return dataBase
            }
        }
    }
}