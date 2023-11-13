package ru.stan.a65.presentation.ui.fragmentWorkManager

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import ru.stan.a65.App
import ru.stan.a65.presentation.worker.CashingDataWorker
import javax.inject.Inject


class WorkManagerViewModel @Inject constructor(
    val contex: Application
) : ViewModel() {

    internal val progressWorkInfoItems: LiveData<List<WorkInfo>>

    fun testNotify() {
        (contex as App).notificationService.createNotification()
    }

    fun startService() {
        CashingDataWorker.start()
    }

    fun stopService() {
        CashingDataWorker.stop()
    }

    init {
        progressWorkInfoItems =
            CashingDataWorker.getWorkManager()
                .getWorkInfosByTagLiveData(CashingDataWorker.TAG_PROGRESS)
        CashingDataWorker.start()
    }


}
