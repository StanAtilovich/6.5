package ru.stan.a65.presentation.ui.FragmentWorkManager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import ru.stan.a65.App


private const val TAG = "HatWorkManager555"

class HatWork(
    context: Context,
    parems: WorkerParameters
) : Worker(context, parems) {

    override fun doWork(): Result {
        Log.d(TAG, "doWork: ")
        return try {
            applicationContext
            val startIndex = inputData.getInt(KEY_START_STUDENT, 1)
            (startIndex..100).forEach {
                Thread.sleep(3_000)
                val house = arrayListOf<String>(
                    "Grifindor",
                    "Slytherin",
                    "Ravenclaw",
                    "Hufflepuff"
                ).random()
                Log.d(TAG, "Student: $it, goes to $house ")
            }
            val outputString = "work done!"
            Result.success(workDataOf(KEY_OUT_PUT to outputString))
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure()
        }


    }

    override fun onStopped() {
        super.onStopped()
        Log.d(TAG, "onStopped: ")

    }

    companion object {
        private fun getInputData(value: Int): Data {
            val builder = Data.Builder()
            builder.putInt(KEY_START_STUDENT, value)
            return builder.build()
        }

        private fun getRequest(startIndex: Int = 1) = OneTimeWorkRequestBuilder<HatWork>()
            .setInputData(getInputData(startIndex))
            .build()


        fun start(startIndex: Int = 1) {
            workManager.beginUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                getRequest(startIndex)
            )
                .enqueue()
        }
        fun stop() = workManager.cancelUniqueWork(WORK_NAME)

        const val KEY_START_STUDENT = "Key start student"
        const val WORK_NAME = "Hat sorting work"
        const val KEY_OUT_PUT = "Key output string"
        private val workManager = WorkManager.getInstance(App.INSTANCE)
    }

}

