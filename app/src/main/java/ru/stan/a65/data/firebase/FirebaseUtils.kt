package ru.stan.a65.data.firebase

import android.app.Application
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.stan.a65.presentation.ForumItem
import ru.stan.a65.presentation.MainActivity

class FirebaseUtils() {
    lateinit var authUtils: AuthUtils
    val dbFirebase = Firebase.database
    val forumReference = dbFirebase.getReference(FORUM_CHILD)


    val crashlytics = Firebase.crashlytics

    fun getFirebaseRecyclerOptions() =
        FirebaseRecyclerOptions.Builder<ForumItem>()
            .setQuery(forumReference, ForumItem::class.java)
            .build()


    fun initAuthUtils(mainActivity: MainActivity) {
        authUtils = AuthUtils(mainActivity)
    }

    companion object {

        private const val FORUM_CHILD = "Forum"
        private var INSTANCE: FirebaseUtils? = null
        private val LOCK = Any()
        fun getInstance(application: Application): FirebaseUtils {
            INSTANCE?.let { firebaseInstance ->
                return firebaseInstance
            }

            synchronized(LOCK) {
                INSTANCE?.let { firebaseInstance ->
                    return firebaseInstance
                }
                INSTANCE = FirebaseUtils()
                return FirebaseUtils()
            }
        }

    }
}