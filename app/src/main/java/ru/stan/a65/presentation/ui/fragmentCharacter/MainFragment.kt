package ru.stan.a65.presentation.ui.fragmentCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.launch
import ru.stan.a65.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.character.collect {
                binding.tvHouse.text = it.hogwartsHouse
                binding.imageCharacter.load(it.imageUrl)

            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                binding.progressBar.isVisible = it is ProgressState.Loading
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}