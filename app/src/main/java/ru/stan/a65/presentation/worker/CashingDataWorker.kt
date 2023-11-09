package ru.stan.a65.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.stan.a65.App
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.CashCharacterListUseCase
import ru.stan.a65.domain.usecase.UploadListUseCase

class CashingDataWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    val repo = CharacterRepositoryImpl(App.INSTANCE, CharacterMapper(),CharacterDatabase.getInstance(App.INSTANCE).characterDao())
    val uploadDataUseCase = UploadListUseCase(repo)
    val cashDataUseCase = CashCharacterListUseCase(repo)

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                cashDataUseCase(
                    uploadDataUseCase()
                )
                Result.success()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                Result.failure()
            }
        }
    }

    companion object {
        private val workManager = WorkManager.getInstance(App.INSTANCE)
        private fun getRequest() = OneTimeWorkRequestBuilder<CashingDataWorker>()
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


    }

}