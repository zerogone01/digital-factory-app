package cl.freedom.digitalfactoryapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import cl.freedom.digitalfactoryapp.R
import cl.freedom.digitalfactoryapp.ui.DashboardActivity
import cl.freedom.digitalfactoryapp.ui.fragment.IndicatorListFragment

class DashboardActivity : AppCompatActivity() {

    //aplicar dagger
    //aplicar pruebas unitarias con mockito y roboelectric (covertura 80%)
    private var mIndicatorListFragment: IndicatorListFragment? = null
    private var profileName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mIndicatorListFragment = IndicatorListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_dashboard, mIndicatorListFragment!!)
        transaction.commit()
        profileName = intent.getStringExtra("Username")
        val btnCloseSession = findViewById<Button>(R.id.button_close_session)
        btnCloseSession.setOnClickListener {
            Toast.makeText(this@DashboardActivity, "Cerrando sesión", Toast.LENGTH_SHORT).show()
            val i = Intent(this@DashboardActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_edit, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        setTitle("Hola "+ profileName)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mIndicatorListFragment!!.filterAdapter(newText)
                return false
            }
        })
        return true
    }
}