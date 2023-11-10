package ru.stan.a65.data.firebase

import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.FirebaseDatabase
import ru.stan.a65.domain.model.ForumItem

class FirebaseUtils(
    private val dbFirebase: FirebaseDatabase,
    internal val crashlytics: FirebaseCrashlytics,
    internal val authUtils: AuthUtils
) {
    val forumReference = dbFirebase.getReference(FORUM_CHILD)


    fun getFirebaseRecyclerOptions() =
        FirebaseRecyclerOptions.Builder<ForumItem>()
            .setQuery(forumReference, ForumItem::class.java)
            .build()


    companion object {

        private const val FORUM_CHILD = "Forum"
        private var INSTANCE: FirebaseUtils? = null
        private val LOCK = Any()
        fun getInstance(
            firebaseUtils: FirebaseUtils
        ): FirebaseUtils {
            INSTANCE?.let { firebaseInstance ->
                return firebaseInstance
            }

            synchronized(LOCK) {
                INSTANCE?.let { firebaseInstance ->
                    return firebaseInstance
                }
                INSTANCE = FirebaseUtils(
                    firebaseUtils.dbFirebase,
                    firebaseUtils.crashlytics,
                    firebaseUtils.authUtils
                )
                return FirebaseUtils(
                    firebaseUtils.dbFirebase,
                    firebaseUtils.crashlytics,
                    firebaseUtils.authUtils
                )
            }
        }

    }
}