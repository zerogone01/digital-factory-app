package cl.freedom.digitalfactoryapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import cl.freedom.digitalfactoryapp.daos.UserDao
import cl.freedom.digitalfactoryapp.model.entities.User

internal class UserRepository(application: Application) {
    private var userDao: UserDao? = null
    private var userLiveData: LiveData<User?>? = null
    var db: AppRoomDatabase?
    fun getUser(email: String?): LiveData<User?>? {
        userDao = db!!.userDao()
        userLiveData = userDao!!.getUser(email)
        return userLiveData
    }

    fun insert(user: User?) {
        AppRoomDatabase.databaseWriteExecutor.execute(Runnable {
            userDao = db!!.userDao()
            userDao!!.insert(user)
        })
    }

    init {
        db = AppRoomDatabase.getDatabase(application)
    }
}