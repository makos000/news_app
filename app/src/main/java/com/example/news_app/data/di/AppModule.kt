package com.example.news_app.data.di


import com.example.authexample.firebaseauthrepo.data.AuthRepository
import com.example.authexample.firebaseauthrepo.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth) : AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}