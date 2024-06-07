package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.repository.chat.ChatRepository
import com.bkcoding.garagegurufyp_user.repository.garage.GarageRepository
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    private val garageRepository: GarageRepository,
    val userPreferences: UserPreferences
): ViewModel() {

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var getRequestResponse by mutableStateOf<List<Request>?>(null)

    fun getRequest() = viewModelScope.launch {
        isLoading = true
        garageRepository.getRequest().collect{
            if(it is Result.Success){
                isLoading = false
                getRequestResponse = it.data
            }else if(it is Result.Failure){
                isLoading = false
                error = it.exception.message ?: "Error"
            }
        }
    }
}