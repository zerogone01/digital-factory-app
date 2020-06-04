package cl.freedom.digitalfactoryapp.retrofit.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Indicator {
    @SerializedName("codigo")
    @Expose
    var codigo: String? = null
    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("unidad_medida")
    @Expose
    var unidadMedida: String? = null
    @SerializedName("fecha")
    @Expose
    var fecha: String? = null
    @SerializedName("valor")
    @Expose
    var valor: Double? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param fecha
     * @param codigo
     * @param unidadMedida
     * @param valor
     * @param nombre
     */
    constructor(codigo: String?, nombre: String?, unidadMedida: String?, fecha: String?, valor: Double?) : super() {
        this.codigo = codigo
        this.nombre = nombre
        this.unidadMedida = unidadMedida
        this.fecha = fecha
        this.valor = valor
    }

}