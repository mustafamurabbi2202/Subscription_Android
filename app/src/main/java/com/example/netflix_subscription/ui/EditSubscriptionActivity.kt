package com.example.netflix_subscription.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.netflix_subscription.databinding.ActivityEditSubscriptionBinding
import com.example.netflix_subscription.databinding.BottomSheetAmountInputBinding
import com.example.netflix_subscription.databinding.BottomSheetAppSelectionBinding
import com.example.netflix_subscription.databinding.BottomSheetCategorySelectionBinding
import com.example.netflix_subscription.databinding.BottomSheetFrequencySelectionBinding
import com.example.netflix_subscription.viewmodel.EditSubscriptionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class EditSubscriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditSubscriptionBinding
    private val viewModel: EditSubscriptionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityEditSubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.subscription.observe(this) { subscription ->
            binding.apply {
                tvAppName.text = subscription.appName
                tvAppNameValue.text = subscription.appName
                tvAmountValue.text = "$${String.format("%.2f", subscription.amount)}"
                tvCategoryValue.text = subscription.category
                tvStartDateValue.text = subscription.startDate
                tvFrequencyValue.text = subscription.frequency
                switchActive.isChecked = subscription.isActive

                // Set app icon
                ivAppIcon.setImageResource(subscription.appIcon)
            }
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            // Back button
            ivBack.setOnClickListener { finish() }

            // Save button
            tvSave.setOnClickListener {
                Toast.makeText(this@EditSubscriptionActivity, "Subscription saved!", Toast.LENGTH_SHORT).show()
                finish()
            }

            // App selection
            layoutApp.setOnClickListener { showAppSelectionBottomSheet() }

            // Amount input
            layoutAmount.setOnClickListener { showAmountInputBottomSheet() }

            // Category selection
            layoutCategory.setOnClickListener { showCategorySelectionBottomSheet() }

            // Start date picker
            layoutStartDate.setOnClickListener { showDatePickerBottomSheet() }

            // Frequency selection
            layoutFrequency.setOnClickListener { showFrequencySelectionBottomSheet() }

            // Active toggle
            switchActive.setOnCheckedChangeListener { _, isChecked ->
                viewModel.toggleActive(isChecked)
            }

            // Delete button
            btnDelete.setOnClickListener {
                Toast.makeText(this@EditSubscriptionActivity, "Subscription deleted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAppSelectionBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = BottomSheetAppSelectionBinding.inflate(layoutInflater)

        val adapter = AppSelectionAdapter { app ->
            viewModel.updateApp(app)
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.recyclerViewApps.adapter = adapter

        viewModel.apps.observe(this) { apps ->
            adapter.submitList(apps)
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun showAmountInputBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = BottomSheetAmountInputBinding.inflate(layoutInflater)

        var currentAmount = ""

        bottomSheetBinding.apply {
            // Number button click listeners
            btn0.setOnClickListener { appendNumber("0", bottomSheetBinding) }
            btn1.setOnClickListener { appendNumber("1", bottomSheetBinding) }
            btn2.setOnClickListener { appendNumber("2", bottomSheetBinding) }
            btn3.setOnClickListener { appendNumber("3", bottomSheetBinding) }
            btn4.setOnClickListener { appendNumber("4", bottomSheetBinding) }
            btn5.setOnClickListener { appendNumber("5", bottomSheetBinding) }
            btn6.setOnClickListener { appendNumber("6", bottomSheetBinding) }
            btn7.setOnClickListener { appendNumber("7", bottomSheetBinding) }
            btn8.setOnClickListener { appendNumber("8", bottomSheetBinding) }
            btn9.setOnClickListener { appendNumber("9", bottomSheetBinding) }

            btnDone.setOnClickListener {
                val amount = etAmount.text.toString().toDoubleOrNull() ?: 0.0
                viewModel.updateAmount(amount)
                bottomSheetDialog.dismiss()
            }
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun appendNumber(number: String, binding: BottomSheetAmountInputBinding) {
        val current = binding.etAmount.text.toString()
        binding.etAmount.setText(current + number)
    }

    private fun showCategorySelectionBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = BottomSheetCategorySelectionBinding.inflate(layoutInflater)

        val adapter = CategorySelectionAdapter { category ->
            viewModel.updateCategory(category.name)
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.recyclerViewCategories.adapter = adapter

        viewModel.categories.observe(this) { categories ->
            adapter.submitList(categories)
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun showDatePickerBottomSheet() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)
                viewModel.updateStartDate(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showFrequencySelectionBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = BottomSheetFrequencySelectionBinding.inflate(layoutInflater)

        val adapter = FrequencySelectionAdapter { frequency ->
            viewModel.updateFrequency(frequency)
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.recyclerViewFrequencies.adapter = adapter

        viewModel.frequencies.observe(this) { frequencies ->
            adapter.submitList(frequencies)
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }
}