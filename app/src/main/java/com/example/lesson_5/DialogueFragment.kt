package com.example.lesson_5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson_5.databinding.FragmentDialogueBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogueFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDialogueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPer()
    }

    private fun setupPer() {
        binding.btnSettings.setOnClickListener {
            startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
                Uri.parse("package:${requireActivity().packageName}")
            ))
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}