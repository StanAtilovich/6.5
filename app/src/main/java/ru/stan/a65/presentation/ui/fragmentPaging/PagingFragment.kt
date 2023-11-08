package ru.stan.a65.presentation.ui.fragmentPaging

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.stan.a65.databinding.FragmentPagingBinding

class PagingFragment : Fragment() {
    private val viewModel: PagingViewModel by viewModels()

    private var _binding: FragmentPagingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvAdapter = CharacterPagingListAdapter()
        binding.rvPagingCharacters.layoutManager =
            LinearLayoutManager(activity?.applicationContext)
        binding.rvPagingCharacters.adapter = rvAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters.collect {
                rvAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}