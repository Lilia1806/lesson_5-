package com.example.lesson_5

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.lesson_5.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPermissions()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        for (permission in isGranted) {
            when {
                permission.value -> fileChooseContract.launch("image/*")
                !shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    findNavController().navigate(R.id.action_mainFragment_to_dialogueFragment)
                }
            }
        }
    }

    private fun setupPermissions() {
        binding.btn.setOnClickListener {
            if (hasPermissionCheckAndRequest(
                    requestPermissionLauncher,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                )
            ) {
                fileChooseContract.launch("image/*")
            }
        }
    }

    private val fileChooseContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            if (imageUri != null) {
                binding.imag.setImage(imageUri.toString())
                uri = imageUri
            }
        }
}