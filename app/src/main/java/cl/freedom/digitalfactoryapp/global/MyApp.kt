package cl.freedom.digitalfactoryapp.global

import android.app.Application
import android.content.Context

class MyApp : Application() {
    //Se creará solo una vez, cuando se cree la aplicación
    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        var instance: MyApp? = null
            private set

        val context: Context?
            get() = instance
    }
}