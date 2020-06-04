package cl.freedom.digitalfactoryapp.data

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.freedom.digitalfactoryapp.global.MyApp.Companion.context
import cl.freedom.digitalfactoryapp.retrofit.AppClient
import cl.freedom.digitalfactoryapp.retrofit.AppClient.Companion.instance
import cl.freedom.digitalfactoryapp.retrofit.AppService
import cl.freedom.digitalfactoryapp.retrofit.response.Indicator
import cl.freedom.digitalfactoryapp.retrofit.response.ResponseIndicatorsResume
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndicatorRepository {
    private var allIndicators: LiveData<MutableList<Indicator?>?>
    var appClient: AppClient?
    var appService: AppService

    fun getAllIndicators(): LiveData<MutableList<Indicator?>?> {
        val data = MutableLiveData<MutableList<Indicator?>>()
        val call = appService.allIndicatorResume

        call!!.enqueue(object : Callback<ResponseIndicatorsResume?> {
            override fun onResponse(call: Call<ResponseIndicatorsResume?>, response: Response<ResponseIndicatorsResume?>) {
                if (response.isSuccessful) {
                    data.setValue(response.body()!!.indicators)
                } else {
                    Toast.makeText(context, "Algo ha ido mal", Toast.LENGTH_LONG)
                }
            }
            override fun onFailure(call: Call<ResponseIndicatorsResume?>, t: Throwable) {
                Toast.makeText(context, "Algo ha ido mal", Toast.LENGTH_LONG)
            }
        })
        return data
    }

    init {
        appClient = instance
        appService = appClient!!.appService
        allIndicators = getAllIndicators()
    }
}