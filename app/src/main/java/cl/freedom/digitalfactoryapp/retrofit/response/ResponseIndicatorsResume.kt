package cl.freedom.digitalfactoryapp.retrofit.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class ResponseIndicatorsResume {
    @SerializedName("version")
    @Expose
    var version: String? = null
    @SerializedName("autor")
    @Expose
    var autor: String? = null
    @SerializedName("fecha")
    @Expose
    var fecha: String? = null
    @SerializedName("uf")
    @Expose
    var uf: Indicator? = null
    @SerializedName("ivp")
    @Expose
    var ivp: Indicator? = null
    @SerializedName("dolar")
    @Expose
    var dolar: Indicator? = null
    @SerializedName("dolar_intercambio")
    @Expose
    var dolarIntercambio: Indicator? = null
    @SerializedName("euro")
    @Expose
    var euro: Indicator? = null
    @SerializedName("ipc")
    @Expose
    var ipc: Indicator? = null
    @SerializedName("utm")
    @Expose
    var utm: Indicator? = null
    @SerializedName("imacec")
    @Expose
    var imacec: Indicator? = null
    @SerializedName("tpm")
    @Expose
    var tpm: Indicator? = null
    @SerializedName("libra_cobre")
    @Expose
    var libraCobre: Indicator? = null
    @SerializedName("tasa_desempleo")
    @Expose
    var tasaDesempleo: Indicator? = null
    @SerializedName("bitcoin")
    @Expose
    var bitcoin: Indicator? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    constructor(version: String?, autor: String?, fecha: String?, uf: Indicator?, ivp: Indicator?, dolar: Indicator?, dolarIntercambio: Indicator?, euro: Indicator?, ipc: Indicator?, utm: Indicator?, imacec: Indicator?, tpm: Indicator?, libraCobre: Indicator?, tasaDesempleo: Indicator?, bitcoin: Indicator?) {
        this.version = version
        this.autor = autor
        this.fecha = fecha
        this.uf = uf
        this.ivp = ivp
        this.dolar = dolar
        this.dolarIntercambio = dolarIntercambio
        this.euro = euro
        this.ipc = ipc
        this.utm = utm
        this.imacec = imacec
        this.tpm = tpm
        this.libraCobre = libraCobre
        this.tasaDesempleo = tasaDesempleo
        this.bitcoin = bitcoin
    }

    val indicators: MutableList<Indicator?>
        get() {
            val indicators: MutableList<Indicator?> = ArrayList()
            indicators.add(uf)
            indicators.add(ivp)
            indicators.add(dolar)
            indicators.add(dolarIntercambio)
            indicators.add(euro)
            indicators.add(ipc)
            indicators.add(utm)
            indicators.add(imacec)
            indicators.add(tpm)
            indicators.add(libraCobre)
            indicators.add(tasaDesempleo)
            indicators.add(bitcoin)
            return indicators
        }

}