package ru.stan.a65.presentation.ui.fragmentWorkManager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.CashCharacterListUseCase
import ru.stan.a65.domain.usecase.UploadListUseCase
import ru.stan.a65.presentation.worker.CashingDataWorker

class CasherDataWorkerFactory(
    private val uploadDataUseCase: UploadListUseCase,
    private val cashDataUseCase: CashCharacterListUseCase
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return CashingDataWorker(
            appContext,
            workerParameters,
            uploadDataUseCase,
            cashDataUseCase
        )
    }
}