
package com.example.news_app.data.di

import com.example.news_app.data.firebaseauth.AccountService
import com.example.news_app.data.firebaseauth.impl.AccountServiceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
  @Provides
  fun auth(): FirebaseAuth = Firebase.auth

  @Provides
  fun firestore(): FirebaseFirestore = Firebase.firestore


  @Provides
  fun provideAccountService(auth: FirebaseAuth): AccountService {
  return AccountServiceImpl(auth)
}
}
