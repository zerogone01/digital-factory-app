package cl.freedom.digitalfactoryapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cl.freedom.digitalfactoryapp.daos.UserDao
import cl.freedom.digitalfactoryapp.model.entities.User
import java.util.concurrent.Executors

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getDatabase(context: Context): AppRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(AppRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AppRoomDatabase::class.java, "app_database")
                                .addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                databaseWriteExecutor.execute {
                    /* UserDao userDao = INSTANCE.userDao();
                userDao.deleteAll();*/Log.d("Entre", "Borrado")
                }
            }
        }
    }
}