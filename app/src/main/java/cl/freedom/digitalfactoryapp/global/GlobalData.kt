package cl.freedom.digitalfactoryapp.global

import cl.freedom.digitalfactoryapp.model.entities.User

object GlobalData {
    const val API_INDICADOR_BASE_URL = "https://www.mindicador.cl/"
    @JvmField
    var user = User("user@digitalfactory.cl", "digitalFactory")
}