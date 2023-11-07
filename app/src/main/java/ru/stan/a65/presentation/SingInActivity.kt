package ru.stan.a65.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import ru.stan.a65.data.firebase.AuthUtils
import ru.stan.a65.databinding.ActivitySingInBinding

class SingInActivity : AppCompatActivity() {


    private val singInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            FirebaseAuthUIActivityResultContract(),
            this::onSingInResult
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySingInBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onStart() {
        super.onStart()
        singInLauncher.launch(
            AuthUtils.getIntentForSingIn()
        )
    }

    fun onSingInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                "Error sing in! ${result.idpResponse?.error}",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }



}