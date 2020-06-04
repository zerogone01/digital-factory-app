package cl.freedom.digitalfactoryapp.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cl.freedom.digitalfactoryapp.model.entities.User

@Dao
interface UserDao {
    // allowing the insert of the same word multiple times by passing a
// conflict resolution strategy
    @Insert
    fun insert(user: User?)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * from user_table where email = :email LIMIT 1")
    fun getUser(email: String?): LiveData<User?>?
}