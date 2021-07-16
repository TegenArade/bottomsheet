package com.arade.bottomsheet


import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnNextLayout
import com.arade.bottomsheet.databinding.BsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * This extension function is used to show bottom sheet from any view which a parent activity.
 * @param titleText : Title text for the bottom sheet content
 * @param subTitleText: subtitle/description for  the bottom sheet content
 * @param viewResId: is layout resourceID which will be inflated in to the body of the bottom sheet
 * @param configuration: optional configuration property  to overide the default behaviors of the bottom sheet
 * @param callback :  this callback is used to get the current state of the bottom sheet on the view (if it is sliding/ expanded collapsed etc ...)
 */
fun AppCompatActivity.showBottomSheetDialog(
    titleText: String,
    subTitleText: String,
    viewResId: Int,
    configuration: BottomSheetConfiguration = BottomSheetConfiguration(),
    callback: BottomSheetBehavior.BottomSheetCallback? = null,
) {
    showBottomSheetDialog(
        titleText = titleText,
        subTitleText = subTitleText,
        body = layoutInflater.inflate(viewResId, null),
        configuration = configuration,
        callback = callback,
    )
}

/**
 * This extension function is used to show bottom sheet from any view which a parent activity.
 * @param titleText : Title text for the bottom sheet content
 * @param subTitleText: subtitle/description for  the bottom sheet content
 * @param body: a view which is going to be added to the bottom sheet body container
 * @param configuration: optional configuration property  to override the default behaviors of the bottom sheet
 * @param callback :  this callback is used to get the current state of the bottom sheet on the view (if it is sliding/ expanded collapsed etc ...)
 */
fun AppCompatActivity.showBottomSheetDialog(
    titleText: String,
    subTitleText: String,
    body: View,
    configuration: BottomSheetConfiguration = BottomSheetConfiguration(),
    callback: BottomSheetBehavior.BottomSheetCallback? = null,
) {

    BsBottomSheetBinding.inflate(layoutInflater).apply {
        setBody(body, configuration.body)
        setHeader(this@showBottomSheetDialog, titleText, subTitleText, configuration.header)
        BottomSheetDialog(this@showBottomSheetDialog).apply {
            cancelButton.setOnClickListener {
                dismiss()
            }
            setBehaviour(
                configuration = configuration.behavior,
                bottomSheetContentContainer.rootView,
                this@showBottomSheetDialog,
                callback
            )
            setContentView(root)
            show()
        }
    }
}

/**
 * Setting the body of the bottom sheet
 */
private fun BsBottomSheetBinding.setBody(
    body: View,
    configuration: BottomSheetConfiguration.BodyConfiguration
) {
    bottomSheetContentContainer.addView(body)
}

/**
 * Setting the header of the bottom sheet
 */
private fun BsBottomSheetBinding.setHeader(
    activity: AppCompatActivity,
    titleText: String,
    subTitleText: String,
    configuration: BottomSheetConfiguration.HeaderConfiguration
) {
    title.text = titleText
    subTitle.text = subTitleText
    configuration.apply {
        headerSubTitleAppearanceResId?.let { resId -> subTitle.setTextAppearance(activity, resId) }
        headerTitleAppearanceResId?.let { resId -> title.setTextAppearance(activity, resId) }
    }
}

/**
 * We set the bottom sheet behaviour here
 */
private fun BottomSheetDialog.setBehaviour(
    configuration: BottomSheetConfiguration.BehaviourConfiguration,
    rootView: View,
    activity: AppCompatActivity,
    callback: BottomSheetBehavior.BottomSheetCallback? = null
) {
    behavior.halfExpandedRatio = configuration.peekRatio
    behavior.isHideable = configuration.isHideable
    behavior.skipCollapsed = configuration.skipCollapsed
    dismissWithAnimation = configuration.dismissWithAnimation
    setCancelable(configuration.cancelable)
    setCanceledOnTouchOutside(configuration.cancelableOnTOuchOutside)
    //This will make sure if the height of the bottom sheet is less than half expanded height it just skip this step
    rootView.doOnNextLayout {
        if (it.height > behavior.peekHeight) {
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }
    behavior.setPeekHeight(activity.getPeekHeight(configuration.peekRatio), true)
    callback?.let { behavior.addBottomSheetCallback(it) }
    behavior.saveFlags = BottomSheetBehavior.SAVE_ALL
}

private fun AppCompatActivity.getPeekHeight(peekRatio: Float): Int =
    (DisplayMetrics().apply {
        this@getPeekHeight.windowManager
            .defaultDisplay
            .getMetrics(this)
    }.heightPixels * peekRatio).toInt()



