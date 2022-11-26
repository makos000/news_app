package com.example.news_app.app.authentication.login

//import com.example.news_app.data.firebaseauth.LogService
import androidx.compose.runtime.mutableStateOf
import com.example.news_app.app.AppViewModel
import com.example.news_app.app.LOGIN_SCREEN
import com.example.news_app.app.NEWS_SCREEN
import com.example.news_app.app.SIGN_UP_SCREEN
import com.example.news_app.app.authentication.common.ext.isValidEmail
import com.example.news_app.app.authentication.common.snackbar.SnackbarManager
import com.example.news_app.data.firebaseauth.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.news_app.R.string as AppText


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    val userState = accountService.currentUser.map { UserState(it.isAnonymous) }


    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            //openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
            openAndPopUp(NEWS_SCREEN, LOGIN_SCREEN)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }


    fun onSignUpClick(openAndPopUp: (String, String) -> Unit)= openAndPopUp(SIGN_UP_SCREEN,LOGIN_SCREEN)

    fun onSignOutClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signOut()
            openAndPopUp(NEWS_SCREEN, LOGIN_SCREEN)
        }
    }

    fun onDeleteMyAccountClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.deleteAccount()
            openAndPopUp(NEWS_SCREEN, LOGIN_SCREEN)
        }
    }

}

