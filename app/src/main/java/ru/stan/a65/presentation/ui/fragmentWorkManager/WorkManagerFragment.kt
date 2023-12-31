package ru.stan.a65.presentation.ui.fragmentWorkManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.WorkInfo
import dagger.hilt.android.AndroidEntryPoint
import ru.stan.a65.databinding.FragmentWorkManagerBinding
import ru.stan.a65.presentation.worker.CashingDataWorker
import javax.inject.Inject


@AndroidEntryPoint
class WorkManagerFragment : Fragment() {
    @Inject
    lateinit var VMFactory: WorkManagerViewModelFactory

    private val viewModel: WorkManagerViewModel by viewModels {
        VMFactory

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

        binding.btnNotify.setOnClickListener {
            viewModel.startService()
        }

        binding.btnStop.setOnClickListener {
            viewModel.stopService()
        }
        viewModel.progressWorkInfoItems.observe(viewLifecycleOwner) { listOfWorkInfo ->
            if (!listOfWorkInfo.isNullOrEmpty()) {
                listOfWorkInfo.forEach { workInfo ->
                    if (workInfo.state == WorkInfo.State.RUNNING) {
                        val progressValue = workInfo.progress.getInt(CashingDataWorker.PROGRESS, 0)
                        binding.progressBar.progress = progressValue
                    }
                    binding.progressBar.isVisible = !workInfo.state.isFinished
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

