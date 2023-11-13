package ru.stan.a65.presentation.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import ru.stan.a65.domain.usecase.CashCharacterListUseCase
import ru.stan.a65.domain.usecase.UploadListUseCase
import javax.inject.Inject

class CasherDataWorkerFactory @Inject constructor(
    private val uploadDataUseCase: UploadListUseCase,
    private val cashDataUseCase: CashCharacterListUseCase
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {

        return CashingDataWorker(
            appContext,
            workerParameters,
            uploadDataUseCase,
            cashDataUseCase
        )
    }
}