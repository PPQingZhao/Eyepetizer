package com.pp.library_ui.utils

//noinspection SuspiciousImport,SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import androidx.lifecycle.MutableLiveData

class AppTheme : Theme {

    val windowBackground = MutableLiveData<Int>()
    val colorPrimary = MutableLiveData<Int>()
    val colorAccent = MutableLiveData<Int>()
    val textColor = MutableLiveData<Int>()
    val textColorSecondary = MutableLiveData<Int>()
    val textColorHint = MutableLiveData<Int>()
    val editTextColor = MutableLiveData<Int>()
    val themeTint = MutableLiveData<Int>()
    val indicatorNormalColor = MutableLiveData<Int>()
    val indicatorSelectedColor = MutableLiveData<Int>()
    val dividerColor = MutableLiveData<Int>()

    @SuppressLint("ResourceType", "Recycle")
    override fun setTheme(theme: Resources.Theme) {

        val attrMap = mapOf(
            themeTint to com.pp.library_ui.R.attr.themeTint,
            windowBackground to R.attr.windowBackground,
            colorPrimary to R.attr.colorPrimary,
            colorAccent to R.attr.colorAccent,
            textColor to R.attr.textColor,
            textColorSecondary to R.attr.textColorSecondary,
            textColorHint to R.attr.textColorHint,
            editTextColor to R.attr.editTextColor,
            indicatorNormalColor to com.pp.library_ui.R.attr.indicatorNormalColor,
            indicatorSelectedColor to com.pp.library_ui.R.attr.indicatorSelectedColor,
            dividerColor to com.pp.library_ui.R.attr.dividerColor,
        )

        val attrArr = attrMap.values.toIntArray()
        val typedArray = theme.obtainStyledAttributes(attrArr)

        attrMap.keys.forEachIndexed { index, mutableLiveData ->
            try {
                mutableLiveData.value = typedArray.getColor(index, Color.TRANSPARENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}