package cl.freedom.digitalfactoryapp.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.freedom.digitalfactoryapp.R
import cl.freedom.digitalfactoryapp.database.UserViewModel
import cl.freedom.digitalfactoryapp.functions.AES
import cl.freedom.digitalfactoryapp.global.GlobalData
import cl.freedom.digitalfactoryapp.model.entities.User
import cl.freedom.digitalfactoryapp.ui.MainActivity
import java.lang.Boolean

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var userViewModel: UserViewModel? = null
    var btnSignIn: Button? = null
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    val secretKey = "nadielosabra!!!!"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        findViews()
        events()
        addUser()
    }

    private fun addUser() {
        Log.d("Entre", "wena")
        val prefs = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val previouslyStarted = prefs.getBoolean("firstTime", false)
        if (!previouslyStarted) {
            Log.d("Entre", "wena2")
            val edit = prefs.edit()
            edit.putBoolean("firstTime", Boolean.TRUE)
            edit.commit()
            Log.d("Entre", "insercion")
            val encryptedString = AES.encrypt(GlobalData.user.password, secretKey)
            val user = User(GlobalData.user.email, encryptedString)
            userViewModel!!.insert(user)
        }
    }

    private fun findViews() {
        etEmail = findViewById(R.id.edit_email)
        etPassword = findViewById(R.id.edit_password)
        btnSignIn = findViewById(R.id.button_sign_in)
    }

    private fun events() {
        btnSignIn!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_sign_in -> try {
                goToLogin()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @Throws(Exception::class)
    private fun goToLogin() {
        val email = etEmail!!.text.toString()
        val password = etPassword!!.text.toString()
        if (email.isEmpty()) {
            etEmail!!.error = "El email es requerido"
        } else if (password.isEmpty()) {
            etPassword!!.error = "La contraseña es requerida"
        } else {
            userViewModel!!.getUser(email)!!.observe(this, Observer<User?> { user: User? ->
                if (user != null) {
                    val decryptedString = AES.decrypt(user.password, secretKey)
                    if (decryptedString == password) {
                        Toast.makeText(this@MainActivity, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
                        val i = Intent(this@MainActivity, DashboardActivity::class.java)
                        startActivity(i)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Problemas en los datos de acceso.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Problemas en los datos de acceso", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}