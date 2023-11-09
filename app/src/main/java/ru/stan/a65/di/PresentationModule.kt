package ru.stan.a65.di

import dagger.Module
import dagger.Provides
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.domain.usecase.GetCharacterUseCase
import ru.stan.a65.presentation.ui.fragmentCharacter.MainViewModel
import ru.stan.a65.presentation.ui.fragmentCharacter.MainViewModelFactory
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListCharactersViewModel
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListViewModelFactory

@Module
class PresentationModule {

    @Provides
    fun provideCharacterListViewModel(
        characterListUseCase: GetCharacterListUseCase):
            ListCharactersViewModel{
      return ListCharactersViewModel(
          characterListUseCase
      )
    }

    @Provides
    fun provideMainViewModel(
        characterUseCase: GetCharacterUseCase
    ): MainViewModel{
        return MainViewModel(
            characterUseCase
        )
    }

    @Provides
    fun provideCharacterListViewModelFactory(listCharactersViewModel: ListCharactersViewModel):ListViewModelFactory{
        return ListViewModelFactory(listCharactersViewModel)
    }

    @Provides
    fun provideMainViewModelFactory(mainViewModel: MainViewModel):MainViewModelFactory{
        return MainViewModelFactory(mainViewModel)
    }
}