import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.bookClub.data.auth.AuthApi
import com.jetbrains.bookClub.data.auth.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authApi: AuthApi
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                authApi.login(email, password)
                    .onSuccess { response ->
                        _loginState.value = LoginState.Success(response)
                    }
                    .onFailure { error ->
                        _loginState.value = LoginState.Error(error.message ?: "Login failed")
                    }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val response: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}