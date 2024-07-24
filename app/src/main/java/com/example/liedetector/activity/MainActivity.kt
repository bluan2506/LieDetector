package com.example.liedetector.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.liedetector.R
import com.example.liedetector.base.BaseActivity
import com.example.liedetector.databinding.ActivityMainBinding
import com.example.liedetector.fragment.FingerFragment
import kotlin.random.Random

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var isSpinning = false
    private val maxDuration = 3000L
    private val minStepDuration = 20L
    private val maxStepDuration = 100L
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arrayString = arrayOf(
            getString(R.string.truth),
            getString(R.string.not_tell),
            getString(R.string.lie)
        )
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = arrayString.size - 1
        binding.numberPicker.displayedValues = arrayString
        binding.numberPicker.value = 0

        binding.btnStart.setOnClickListener {
            if (!isSpinning) {
                startDeceleratingScroll()
            }
        }

        binding.viewPager.adapter = ViewPagerAdapter(this)
    }

    class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> FingerFragment()
                2 -> FingerFragment()
                else -> FingerFragment()
            }
        }
    }

    private fun startDeceleratingScroll() {
        if (isRunning) return
        isRunning = true

        val totalCalls = 3 * 20
        val minInterval = 20L // Minimum interval in milliseconds
        val maxInterval = 100L // Maximum interval in milliseconds
        val duration = 3000L // Total duration in milliseconds

        val startTime = System.currentTimeMillis()
        var currentCall = 0

        val runnable = object : Runnable {
            override fun run() {
                val elapsedTime = System.currentTimeMillis() - startTime
                val progress = elapsedTime.toFloat() / duration

                val currentInterval = (minInterval + (progress * (maxInterval - minInterval))).toLong()

                binding.numberPicker.smoothScroll(true, 1) // Call your method here
                currentCall++

                if (currentCall < totalCalls) {
                    handler.postDelayed(this, currentInterval)
                } else {
                    isRunning = false
                }
            }
        }

        handler.post(runnable)
    }

    private fun startSpinning() {
        isSpinning = true
        val startTime = System.currentTimeMillis()
        val runnable = object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
                val progress = elapsedTime.toFloat() / maxDuration

                binding.numberPicker.value = (binding.numberPicker.value + 1) % (binding.numberPicker.maxValue + 1)

                if (elapsedTime >= maxDuration) {
                    stopSpinning()
                } else {
                    val stepDuration = ((progress * (maxStepDuration - minStepDuration)) + minStepDuration).toLong()
                    handler.postDelayed(this, stepDuration)
                }
            }
        }

        handler.post(runnable)
    }

    private fun stopSpinning() {
        isSpinning = false
        val value = Random.nextInt(binding.numberPicker.minValue, binding.numberPicker.maxValue + 1)
        binding.numberPicker.smoothScroll(true, value)
        Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show()
    }
}