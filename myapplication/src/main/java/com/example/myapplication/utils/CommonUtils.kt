package com.delta.ams.common.utils

import android.content.Context
import android.content.res.Configuration
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 *
 * @author V.Wenju.Tian
 */
object CommonUtils {
    fun <T : View?> findView(view: View, resId: Int): T {
        val mview = view.findViewById<View>(resId)
        return mview as T
    }

    /**
     * 隐藏键盘操作
     *
     * @param context
     * 上下文
     * @param view
     * 点击
     */
    fun hideKeyBoard(context: Context, view: View) {
        view.setOnClickListener { v -> // TODO Auto-generated method stub
            val imm = context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    /**
     * 判断String是否为纯数字
     *
     * @param str
     * @return
     */
    fun isNumeric(str: String): Boolean {
        for (i in 0 until str.length) {
            println(str[i])
            if (!Character.isDigit(str[i])) {
                return false
            }
        }
        return true
    }

    /**
     * 保留两位小数
     *
     * @param editText
     */
    fun setEditTextPoint(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                var s = s
                if (s.toString().contains(".")) {
                    if (s.length - 1 - s.toString().indexOf(".") > 2) {
                        Log.e("tag", "----" + s.length + "--" + s.toString()
                                + "--" + editText.text.toString() + "--"
                                + s.toString().indexOf("."))
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 2)
                        editText.setText(s)
                        Log.e("tag", s.length.toString() + "--" + s.toString() + "--"
                                + editText.text.toString() + "--"
                                + s.toString().indexOf("."))
                        editText.setSelection(s.length)
                    }
                }
                if (s.toString().trim { it <= ' ' }.substring(0) == ".") {
                    s = "0$s"
                    editText.setText(s)
                    editText.setSelection(2)
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim { it <= ' ' }.length > 1) {
                    if (s.toString().substring(1, 2) != ".") {
                        editText.setText(s.subSequence(0, 1))
                        editText.setSelection(1)
                        return
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
    }

    /**
     * 判断屏幕是否是竖屏
     *
     * @param context
     * @return
     */
    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
}