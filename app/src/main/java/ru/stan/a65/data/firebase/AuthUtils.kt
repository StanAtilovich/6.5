package ru.stan.a65.data.firebase

import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.stan.a65.R
import ru.stan.a65.presentation.MainActivity
import ru.stan.a65.presentation.SingInActivity

class AuthUtils(
    private val mainActivity : MainActivity
) {

    private val auth = Firebase.auth
    private val authUi = AuthUI.getInstance()


    private fun isDoneAuth(): Boolean =
        auth.currentUser != null


    private val singInActivityClass = SingInActivity::class.java
    fun singUpIn() {
        if (!isDoneAuth()) {
            val intent = Intent(mainActivity, singInActivityClass)
            mainActivity.startActivity(intent)
            mainActivity.finish()
        }
    }

    fun singOut() {
        authUi.signOut(mainActivity)
    }

    fun getUserName() = auth.currentUser?.displayName ?: ANONYMOUS

    companion object {
        fun getIntentForSingIn() = AuthUI.getInstance().createSignInIntentBuilder()
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


