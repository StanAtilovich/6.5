package ru.stan.a65.presentation.ui.fragmentWorkManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.stan.a65.databinding.FragmentWorkManagerBinding


class WorkManagerFragment : Fragment() {


    private val viewModel: WorkManagerViewModel by viewModels {
        WorkManagerViewModelFactory()
    }

    private var _binding: FragmentWorkManagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkManagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAdd.setOnClickListener { viewModel.onBtnAdd() }
        binding.btnUpdate.setOnClickListener { viewModel.onUpdateBtn() }
        binding.btnDelete.setOnClickListener { viewModel.onDeleteBtn() }
        //  binding.btnNotify.setOnClickListener {
        //      viewModel.testNotify()
        //  }
        binding.btnNotify.setOnClickListener {
            viewModel.startService()
        }
        binding.btnStop.setOnClickListener {
            viewModel.stopService()
        }



        lifecycleScope.launch {
            viewModel.characters.collect {
                binding.textView.text = it.joinToString(separator = "\r\n")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}

