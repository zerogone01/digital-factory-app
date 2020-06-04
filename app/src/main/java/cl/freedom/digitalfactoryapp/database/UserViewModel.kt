package cl.freedom.digitalfactoryapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.freedom.digitalfactoryapp.model.entities.User

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: UserRepository
    private var userLiveData: LiveData<User?>? = null
    fun getUser(email: String?): LiveData<User?>? {
        userLiveData = mRepository.getUser(email)
        return userLiveData
    }

    fun insert(user: User?) {
        mRepository.insert(user)
    }

    init {
        mRepository = UserRepository(application)
    }
}