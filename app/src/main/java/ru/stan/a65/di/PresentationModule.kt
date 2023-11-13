package ru.stan.a65.di

import dagger.Module

@Module
class PresentationModule {
  //  @Provides
  //  fun provideMainViewModel(
  //      characterUseCase: GetCharacterUseCase
  //  ): MainViewModel{
  //      return MainViewModel(
  //          characterUseCase
  //      )
  //  }
 //   @Provides
 //   fun provideCharacterListViewModel(
 //       characterListUseCase: GetCharacterListUseCase):
 //           ListCharactersViewModel{
 //     return ListCharactersViewModel(
 //         characterListUseCase
 //     )
 //   }



//    @Provides
//    fun provideCharacterListViewModelFactory(
//        listCharactersViewModel: ListCharactersViewModel):ListViewModelFactory{
//        return ListViewModelFactory(listCharactersViewModel)
//    }

//   @Provides
//   fun provideMainViewModelFactory(
//       mainViewModel: MainViewModel):
//           MainViewModelFactory{
//       return MainViewModelFactory(mainViewModel)
//   }

  //  @Provides
  //  fun provideWorkManagerViewModel(
  //      application: Application)
  //  :WorkManagerViewModel{
  //      return WorkManagerViewModel(application)
  //  }

 //  @Provides
 //  fun provideWorkManagerViewModelFactory(
 //      workManagerViewModel: WorkManagerViewModel
 //  ):WorkManagerViewModelFactory{
 //      return WorkManagerViewModelFactory(workManagerViewModel)
 //  }

  //  @Provides
  //  fun provideCasherDataWorkerFactory(
  //      uploadDataUseCase: UploadListUseCase,
  //      cashDataUseCase: CashCharacterListUseCase
  //  ):CasherDataWorkerFactory{
  //      return CasherDataWorkerFactory(
  //          uploadDataUseCase,cashDataUseCase)
  //  }

  //  @Provides
  //  fun providePagingViewModel(
  //      getPagerForCharactersUseCase: GetPagerForCharactersUseCase
  //  ):PagingViewModel{
  //      return PagingViewModel(getPagerForCharactersUseCase)
  //  }

  //  @Provides
  //  fun providePagingViewModelFactory(
  //      pagingViewModel: PagingViewModel
  //  ):PagingViewModelFactory{
  //      return PagingViewModelFactory(pagingViewModel)
  //  }
}