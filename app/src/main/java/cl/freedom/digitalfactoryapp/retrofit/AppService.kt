package cl.freedom.digitalfactoryapp.retrofit

import cl.freedom.digitalfactoryapp.retrofit.response.ResponseIndicatorsResume
import retrofit2.Call
import retrofit2.http.GET

interface AppService {
    @get:GET("api")
    val allIndicatorResume: Call<ResponseIndicatorsResume?>?
}