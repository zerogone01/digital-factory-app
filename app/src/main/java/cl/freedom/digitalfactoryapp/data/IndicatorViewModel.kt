package cl.freedom.digitalfactoryapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.freedom.digitalfactoryapp.retrofit.response.Indicator

class IndicatorViewModel(application: Application) : AndroidViewModel(application) {
    private val indicatorRepository: IndicatorRepository
    val indicators: LiveData<MutableList<Indicator?>?>

    init {
        indicatorRepository = IndicatorRepository()
        indicators = indicatorRepository.getAllIndicators()
    }
}