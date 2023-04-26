package com.pico.make_it_so.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Singleton
    @Provides
    fun provideAuth(): FirebaseAuth {
        val firebaseAuth = Firebase.auth
//        firebaseAuth.useEmulator("10.0.2.2", 9099)
        return firebaseAuth
    }

    @Singleton
    @Provides
    fun provideFirestoreDb(): FirebaseFirestore {
        // 10.0.2.2 is the special IP address to connect to the 'localhost' of
        // the host computer from an Android emulator.
        val firestore = Firebase.firestore
//        firestore.useEmulator("10.0.2.2", 8080)
//
//        firestore.firestoreSettings = firestoreSettings {
//            isPersistenceEnabled = false
//        }
        return firestore
    }
}