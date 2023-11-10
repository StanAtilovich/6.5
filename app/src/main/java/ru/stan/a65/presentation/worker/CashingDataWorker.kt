package ru.stan.a65.presentation.worker

import android.app.NotificationManager
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.stan.a65.App
import ru.stan.a65.R
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.CashCharacterListUseCase
import ru.stan.a65.domain.usecase.UploadListUseCase

class CashingDataWorker(
    context: Context,
    params: WorkerParameters,
   private val uploadDataUseCase : UploadListUseCase,
   private val cashDataUseCase : CashCharacterListUseCase
) : CoroutineWorker(context, params) {


    override suspend fun doWork(): Result {
        makeNotification("Start")
        (0..100 step 10).forEach {
            delay(100)
            setProgress(workDataOf(PROGRESS to it))
        }
        return withContext(Dispatchers.IO) {
            return@withContext try {
                cashDataUseCase(
                    uploadDataUseCase()
                )
                makeNotification("Finish")
                Result.success()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                makeNotification("Error")
                Result.failure()
            }
        }
    }

    private fun makeNotification(notificationContent: String) {
        App.INSTANCE.notificationService.showNewNotification(
            notificationIcon = R.drawable.sorting_hat_icon,
            notificationContentText = notificationContent,
            notificationTitle = "кеширование фореграунд",
            channelImportance = NotificationManager.IMPORTANCE_MAX
        )

    }

    companion object {
        private val workManager = WorkManager.getInstance(App.INSTANCE)
        fun getWorkManager() = workManager
        private fun getRequest() =
            OneTimeWorkRequestBuilder<CashingDataWorker>()
                .addTag(TAG_PROGRESS)
                .build()


        fun start() {
            workManager.beginUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                getRequest()
            )
                .enqueue()
        }

        fun stop() = workManager.cancelUniqueWork(WORK_NAME)


        private const val WORK_NAME = "Cashing data into room db work"

        const val PROGRESS = "progress"
        const val TAG_PROGRESS = "tag_progress"
    }

}

