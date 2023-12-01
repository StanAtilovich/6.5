# 6.5 HARRY POTTER APP
Приложение сожержит 5 вкладок: Получаем иноформацию с 2 серверов и чат(Firebase). Также произвел миграцию jetpack compos.


1)Загружаем из сервера список персонажей из Гарри Поттера с помощью библиотеки Retrofit и отображаем в UI в соответствии с Clean architecture


2)Исследуем технологию локального хранения данных в базе Room 


3)Выполнил задачи:
Фрагменты и низкоуровневая навигация
Передавча параметров в фрагмент через Bundle
ЖЦ фрагментов
Jetpack Navigation
SafeArgs для передачи параметров
setupActionBarWithNavController
Bottom navigation
onSupportNavigateUp()
Знакомство с RecyclerView
Recyclerview.Adapter
ItemTouchHelper и DiffUtil
ListAdapter
Двухсторонний dataBinding
runCatching


4)Задачи:
Подключение Firebase
Crashlytics
Tools - Firebase 
DrawerLayout,NavigationView
AlertDialog.Builder().setView().show()
Firebase.auth.createUser.sendVerification
Рефакторинг
Firebase.auth.signInWithEmailAndPasword()
Firebase.auth.sendPasswordResetEmail(email)
signInWithCredential(credential) 
task.exception.message
linkWithCredential()
googleAccount.signOut()
Миграция в AuthUI
Добавление фрагмента
Отправка данных в Firebase.database
Получение данных
Подготовка layout-а для FirebaseRecyclerAdapter
FirebaseRecyclerAdapter
Выбор картинки
Firebase.storage...putFile(uri)


5)Андройд архитектура:
Clean architecture
ViewModel
BindingAdapter
viewModelScope
double check при инстанциации
Паттерн Repository
Архитектура Firebase
Notifications
Permissions
Foreground services
WorkManager_theory
WorkManager_practice
FirebaseMessaging push notifications
SwipeRefreshLayout


6)backend with paging
Подготовка фрагмента
Знакомство с Paging
Применение paging
Внедрение зависимостей(Теория)
Dagger практика
Workmanager(Подготовка)
Зависимости Worker вручную
Зависимости Worker через Dagger
Dagger singletone
Dagger практика
Hilt
Hilt и HiltWorker


7)Поигрался с основами @Composable функции и осуществляем миграцию макетов(layout) из xml в jetpack compos
