package ru.stan.a65.presentation.ui.fragmentWorkManager

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.stan.a65.App
import ru.stan.a65.data.local.entity.CharacterDbModel
import ru.stan.a65.presentation.worker.CashingDataWorker


class WorkManagerViewModel(
    val contex: Application
) : ViewModel() {

 //   private var characterDao = (contex as App).db.characterDao()
        //
        //   private var _characters = MutableStateFlow<List<CharacterDbModel>>(mutableListOf())
 //   val characters = _characters.asStateFlow()
        //
        //   fun onBtnAdd() {
 //       var size = _characters.value.size
                //       size++
                //       viewModelScope.launch {
 //           characterDao.insertCharacterItem(
 //               CharacterDbModel(
                    //                   id = 0, name = "Potter ${characters.value.size}",
 //                   "Slytherin", "image.url.png"
 //               )
 //           )
 //           updateTextView()
                    //       }
                //   }
        //
        //
        //   fun onDeleteBtn() {
 //       viewModelScope.launch {
 //           characters.value.lastOrNull()?.let {
 //               characterDao.deleteAll()
                //           }
                //       }
                //       updateTextView()
                //   }
        //
        //   fun onUpdateBtn() {
 //       viewModelScope.launch {
 //           characters.value.lastOrNull()?.let {
 //               characterDao.insertCharacterItem(it.copy(name = "Albus"))
                //           }
                //       }
                //       updateTextView()
                //   }
        //
        //   private fun updateTextView() {
 //       viewModelScope.launch {
 //           _characters.value = characterDao.getAllCharacters()
            //       }
            //   }

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
        CashingDataWorker.start()
    }


}
