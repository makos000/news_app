/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.news_app.app.authentication.splash

import androidx.compose.runtime.mutableStateOf
import com.example.news_app.app.AppViewModel
import com.example.news_app.app.NEWS_SCREEN
import com.example.news_app.app.SPLASH_SCREEN
import com.example.news_app.data.firebaseauth.AccountService
//import com.example.news_app.data.firebaseauth.LogService
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val accountService: AccountService
) : AppViewModel() {
  val showError = mutableStateOf(false)

  fun onAppStart(openAndPopUp: (String, String) -> Unit) {

    showError.value = false
    if (accountService.hasUser) openAndPopUp(NEWS_SCREEN, SPLASH_SCREEN)
    else createAnonymousAccount(openAndPopUp)
  }

  private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
    launchCatching(snackbar = false) {
      try {
        accountService.createAnonymousAccount()
      } catch (ex: FirebaseAuthException) {
        showError.value = true
        throw ex
      }
      openAndPopUp(NEWS_SCREEN, SPLASH_SCREEN)
    }
  }
}
