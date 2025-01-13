package pw.edu.pl.pap.api.data

import de.jensklingenberg.ktorfit.Ktorfit
import pw.edu.pl.pap.api.ServiceCreator


class ConfigServiceCreator(baseUrl: String) : ServiceCreator() {
    private val ktorfit = Ktorfit.Builder().baseUrl(baseUrl).httpClient(httpClient).build()

    fun createConfigApi(): ConfigApi {
        return ktorfit.createConfigApi()
    }
}