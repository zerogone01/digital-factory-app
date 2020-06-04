package cl.freedom.digitalfactoryapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User(@field:ColumnInfo(name = "email") var email: String?, @field:ColumnInfo(name = "password") var password: String?) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}