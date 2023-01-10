package com.bcaf.bcafretrofit.database

import android.content.Context
import androidx.room.*
import com.bcaf.bcafretrofit.database.dao.DummyDao
import com.bcaf.bcafretrofit.model.PostDummyData

@Database(version = 1 , entities=[PostDummyData::class])
@TypeConverters(ListStringConverter::class)
abstract class DummyDatabase : RoomDatabase() {

    abstract fun dummyDao(): DummyDao

    companion object{
    var instance :DummyDatabase?=null
    fun getInstance(ctx :Context):DummyDatabase{

        if (instance==null){
            instance= Room.databaseBuilder(ctx.applicationContext,DummyDatabase::class.java,"dummy db")
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance!!
    }

    }


}