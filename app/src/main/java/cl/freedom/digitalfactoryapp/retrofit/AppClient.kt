package cl.freedom.digitalfactoryapp.retrofit

import cl.freedom.digitalfactoryapp.global.GlobalData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppClient {
    val appService: AppService
    private val retrofit: Retrofit



    companion object {
        //Singleton
        var instance: AppClient? = null
            get() {
                if (field == null) {
                    field = AppClient()
                }
                return field
            }
            private set
    }

    init {
        retrofit = Retrofit.Builder().baseUrl(GlobalData.API_INDICADOR_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        appService = retrofit.create(AppService::class.java)
    }
}