package com.abhay.innobuzzassignment.RoomImpl


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.Exception

@Database(entities = [PostModel::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        const val DATABASE_NAME = "post_database"

        fun getPostDatabase(applicationContext: Context):PostDatabase{
            var postDatabase : PostDatabase? = null
            try {
                postDatabase =  Room.databaseBuilder(applicationContext,PostDatabase::class.java, DATABASE_NAME).build()
            }catch (e:Exception){
                e.printStackTrace()
            }
            return postDatabase!!
        }
    }

}
