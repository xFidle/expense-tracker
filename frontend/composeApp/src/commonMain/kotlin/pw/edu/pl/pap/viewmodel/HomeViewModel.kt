package pw.edu.pl.pap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pw.edu.pl.pap.apiclient.ApiClient
import pw.edu.pl.pap.data.Home

class HomeViewModel(private val apiClient: ApiClient) : ViewModel() {
    private val _homeInfo = MutableStateFlow<Home?>(null)
    val homeInfo: StateFlow<Home?> = _homeInfo

    fun fetchHomeInfo() {
        viewModelScope.launch {
            try {
                val homeData = apiClient.getHome("herkules1@gmail.com")
                _homeInfo.value = homeData
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}