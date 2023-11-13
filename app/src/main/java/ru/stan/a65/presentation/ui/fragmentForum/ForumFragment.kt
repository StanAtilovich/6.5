package ru.stan.a65.presentation.ui.fragmentForum


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import ru.stan.a65.App
import ru.stan.a65.databinding.FragmentForumBinding
import ru.stan.a65.presentation.ui.Activities.MainActivity
import javax.inject.Inject

class ForumFragment : Fragment() {
    @Inject
    lateinit var VMFactory: ForumViewModelFactory

    private val viewModel: ForumViewModel by viewModels {
VMFactory
    }
    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ForumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.injectForumFragment(this)
        super.onCreate(savedInstanceState)
    }

    private val openDocumentLauncher = registerForActivityResult(
        object : ActivityResultContracts.OpenDocument() {
            override fun createIntent(context: Context, input: Array<String>): Intent {
                val intent = super.createIntent(context, input)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                return intent
            }
        }
    ) { uri ->
        uri?.let {
            onImageSelected(it)
        }
    }

    private fun onImageSelected(uri: Uri) {
        Log.d("stan555", "$uri")
        val storage = Firebase.storage.getReference("")
        storage.putFile(uri)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.edText.hint = "заебись"
                } else {
                    binding.edText.hint = "хуйово"
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSend.setOnClickListener {
            val text = binding.edText.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.sendTextToFirebaseDb(
                    text,

                    )
                binding.edText.setText("")
            }
        }
        binding.edText.addTextChangedListener(
            viewModel.TextWatcherForEditText(binding.btnSend)
        )


        setRecyclerView()

    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(getMainActivity())
        adapter = viewModel.getRecyclerAdapter()
        layoutManager.stackFromEnd = true
        binding.rvData.adapter = adapter
        binding.rvData.layoutManager = layoutManager

        adapter.registerAdapterDataObserver(
            MyScrollBottomObserver(
                layoutManager,
                binding.rvData,
                adapter
            )
        )
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()

    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun getMainActivity() = (requireActivity() as MainActivity)

}