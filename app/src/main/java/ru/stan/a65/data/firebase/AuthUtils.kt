package ru.stan.a65.data.firebase

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import ru.stan.a65.R

class AuthUtils(
    internal val auth : FirebaseAuth,
    internal val authUi : AuthUI
) {


    fun getUserName(): String? {
        val user = auth.currentUser
        return if (user != null) {
            user.displayName
        } else ANONYMOUS
    }

    companion object {
        fun getIntentForSingIn() =
            AuthUI.getInstance().createSignInIntentBuilder()
            .setLogo(R.drawable.harry)
            .setAvailableProviders(
                listOf(
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.PhoneBuilder().build()
                )
            )
            .build()

        private const val ANONYMOUS = "Аноним!!!!"
    }
}


