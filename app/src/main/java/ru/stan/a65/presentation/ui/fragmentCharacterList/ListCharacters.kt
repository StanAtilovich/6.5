package ru.stan.a65.presentation.ui.fragmentCharacterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.stan.a65.databinding.FragmentListCharactersBinding
import javax.inject.Inject

@AndroidEntryPoint
class ListCharacters : Fragment() {

    @Inject
    lateinit var VMFactory: ListViewModelFactory
    private val viewModel: ListCharactersViewModel by viewModels {
        VMFactory
    }

    private var _binding: FragmentListCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcAdapter = CharacterListAdapter()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerCharacterList.adapter = rcAdapter



        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onlySlizerin.collect {
                rcAdapter.submitList(viewModel.characterList.value)
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                //  binding.progressBar.isVisible = it is ProgressState.Loading
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}