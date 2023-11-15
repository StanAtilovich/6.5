package ru.stan.a65.presentation.ui.fragmentCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var VMfactory: MainViewModelFactory
    private val viewModel: MainViewModel by viewModels {
        VMfactory
    }
    private var composeView: ComposeView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        composeView = ComposeView(container!!.context).apply {
            setContent { MainFragmentCompose(viewModel = viewModel) }
        }
        return composeView as ComposeView
    }


}