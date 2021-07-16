package com.arade.doordash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arade.bottomsheet.showBottomSheetDialog
import com.arade.doordash.databinding.*
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback by lazy {
        object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                binding.bottomSheetState.text = when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> "STATE_EXPANDED"
                    BottomSheetBehavior.STATE_COLLAPSED -> "STATE_COLLAPSED"
                    BottomSheetBehavior.STATE_DRAGGING -> "STATE_DRAGGING"
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> "STATE_HALF_EXPANDED"
                    BottomSheetBehavior.STATE_HIDDEN -> "STATE_HIDDEN"
                    BottomSheetBehavior.STATE_SETTLING -> "STATE_SETTLING"
                    else -> null
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.openSmallBottomSheet.setOnClickListener {
            showBottomSheetDialog(
                titleText = "Small item",
                subTitleText = "This is demo for small item",
                viewResId = R.layout.small_item,
                callback = bottomSheetCallback
            )
        }
        binding.openLargeBottomSheet.setOnClickListener {

            showBottomSheetDialog(
                titleText = "Large item",
                subTitleText = "This is demo for large item",
                body = LargeItemBinding.inflate(layoutInflater).root,
                callback = bottomSheetCallback
            )
        }
    }
}