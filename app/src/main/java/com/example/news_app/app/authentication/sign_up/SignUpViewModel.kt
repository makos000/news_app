
package com.example.news_app.app.authentication.sign_up


import androidx.compose.runtime.mutableStateOf
import com.example.news_app.app.AppViewModel
import com.example.news_app.app.navbar.NEWS_SCREEN
import com.example.news_app.app.navbar.SIGN_UP_SCREEN
import com.example.news_app.app.authentication.common.ext.isValidEmail
import com.example.news_app.app.authentication.common.ext.isValidPassword
import com.example.news_app.app.authentication.common.ext.passwordMatches
import com.example.news_app.app.authentication.common.snackbar.SnackbarManager
import com.example.news_app.data.firebaseauth.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.news_app.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val accountService: AccountService
) : AppViewModel() {
  var uiState = mutableStateOf(SignUpUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onRepeatPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(repeatPassword = newValue)
  }

  fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    if (!password.isValidPassword()) {
      SnackbarManager.showMessage(AppText.password_error)
      return
    }

    if (!password.passwordMatches(uiState.value.repeatPassword)) {
      SnackbarManager.showMessage(AppText.password_match_error)
      return
    }

    launchCatching {
      accountService.linkAccount(email, password)
     // openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
      openAndPopUp(NEWS_SCREEN, SIGN_UP_SCREEN)
    }
  }
}
