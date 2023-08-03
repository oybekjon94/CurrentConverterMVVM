package com.example.currentconvertermvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.currentconvertermvvm.databinding.ActivityMainBinding
import com.example.currentconvertermvvm.main.MainViewModel
import com.example.currentconvertermvvm.utils.ConvertEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "TAG"

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertRateBtn.setOnClickListener {
            val fromCurrency = binding.fromCurrency.selectedItem as String
            val toCurrency = binding.toCurrency.selectedItem as String
            Log.d(TAG,"onCreate: ${fromCurrency.substring(0,3)}")
            Log.d(TAG,"onCreate: ${toCurrency.substring(0,3)}")
            viewModel.getConvertRate(fromCurrency.substring(0,3), toCurrency.substring(0,3), binding.amountEt.text.toString())
        }


        lifecycleScope.launch {
            viewModel.conversion.collectLatest {
                when (it) {
                    ConvertEvent.Empty -> Unit
                    is ConvertEvent.Error -> {
                        binding.progressBar.isVisible = false
                    }
                    ConvertEvent.Loading -> binding.progressBar.isVisible = true
                    is ConvertEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.resultTv.text = "${getFormatted(it.result.result)}${it.result.query.to}"
                    }
                }
            }
        }
    }
    private fun getFormatted(amount:Double):String{
        return String.format("%.2f",amount)
    }
}