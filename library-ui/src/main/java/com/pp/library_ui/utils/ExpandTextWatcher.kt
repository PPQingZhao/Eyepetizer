package com.pp.library_ui.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.View
import android.widget.TextView

class ExpandTextWatcher(
    private val textView: TextView,
    private val expandText: String,
    private val closeText: String,

    ) : TextWatcher {
    var mContent: CharSequence? = null
    var changed = false
    var expandMode = MODE_NONE
    private val maxLines: Int

    init {
        maxLines = textView.maxLines
    }

    companion object {
        const val MODE_NONE = -1
        const val MODE_EXPAND = 0
        const val MODE_CLOSE = 1
        const val SCALE = 1.1f
    }

    @SuppressLint("RestrictedApi")
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        if (null == s) {
            mContent = s
            return
        }

        if (!s.endsWith(expandText) && !s.endsWith(closeText)) {
            mContent = s
            changed = true
        }
        Log.e("ExpandTextWatcher", "content: ${mContent}")
    }

    override fun afterTextChanged(s: Editable?) {
        Log.e("ExpandTextWatcher", "Editable: ${s}")
        if (!changed) {
            return
        }
        if (mContent == null) {
            return
        }
        val lineCount = textView.lineCount
        Log.e(
            "ExpandTextWatcher",
            "lineCount: ${lineCount} maxlines:${maxLines} height:${textView.height}"
        )
        if (lineCount == 0) {
            return
        }
        // 内容不足 maxLines
        if (expandMode == MODE_NONE && maxLines >= lineCount) {
            return
        }
        textView.removeTextChangedListener(this)
        // 状态:展开  ->添加   "收起"
        if (expandMode == MODE_EXPAND) {

            val endLineWidth = textView.layout.getLineWidth(lineCount - 1)
            val expandMeasurePaint = Paint()
            expandMeasurePaint.textSize = textView.textSize * SCALE
            val closeValueWidth = expandMeasurePaint.measureText(" $closeText")
            val newLine =
                (textView.width - textView.paddingRight - textView.paddingLeft - endLineWidth) < closeValueWidth

            val contentBuilder = SpannableStringBuilder(mContent)
                .append(if (newLine) "\n" else " ")
                .append(closeText)
            val start = contentBuilder.length - closeText.length
            val end = contentBuilder.length
            contentBuilder.setSpan(
                RelativeSizeSpan(SCALE),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            contentBuilder.setSpan(
                ForegroundColorSpan(Color.RED),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            contentBuilder.setSpan(object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false
                    ds.setColor(Color.RED)
                }

                override fun onClick(widget: View) {
                    textView.maxLines = maxLines
                    textView.setText(mContent)
                    expandMode = MODE_CLOSE
                }
            }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            textView.setText(contentBuilder)
        } else {
            val expandValue = "... ${expandText}测"

            // 获取收起状态时,最后一行最后一个字符的索引
            val endCharIndex = textView.layout.getLineVisibleEnd(maxLines - 1)
            val contentMeasurePaint = Paint()
            contentMeasurePaint.textSize = textView.textSize
            val expandMeasurePaint = Paint()
            expandMeasurePaint.textSize = textView.textSize * SCALE
            val subEndIndex = calcSubEndIndex(
                mContent!!,
                expandValue,
                endCharIndex,
                contentMeasurePaint,
                expandMeasurePaint
            )

            val substring = mContent?.substring(0, subEndIndex)

            val contentBuilder = SpannableStringBuilder(substring)
                .append("... ")
                .append(expandText)

            val start = subEndIndex + 4
            val end = start + expandText.length

            contentBuilder.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    textView.maxLines = Int.MAX_VALUE
                    textView.setText(mContent)
                    expandMode = MODE_EXPAND
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.setColor(Color.RED)
                    ds.isUnderlineText = false
                }
            }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            contentBuilder.setSpan(
                RelativeSizeSpan(SCALE),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            contentBuilder.setSpan(
                ForegroundColorSpan(Color.RED),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.setText(contentBuilder)
        }

        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.addTextChangedListener(this)
    }

    private fun calcSubEndIndex(
        mContent: CharSequence,
        expandValue: String,
        endCharIndex: Int,
        contentMeasurePaint: Paint,
        expandMeasurePaint: Paint
    ): Int {

        if (mContent.length < expandValue.length) {
            return 0
        }
        // expandValue 占用宽度大小
        val expandValueWidth = expandMeasurePaint.measureText(expandValue)
        Log.e("ExpandTextWatcher", "${expandValueWidth}")
        var subIndex = endCharIndex
        val endStringBuilder = StringBuilder()
        // 从位置endCharIndex往前遍历字符串,计算出字节大小（expandValueByteSize） 位置
        while (subIndex >= 0) {
            endStringBuilder.insert(0, mContent[subIndex])
            val endStringWidth = contentMeasurePaint.measureText(endStringBuilder.toString())
            Log.e("ExpandTextWatcher", "${mContent[subIndex]}  $endStringWidth")
            if (endStringWidth > expandValueWidth) {
                break
            }
            subIndex--
        }
        return subIndex
    }
}