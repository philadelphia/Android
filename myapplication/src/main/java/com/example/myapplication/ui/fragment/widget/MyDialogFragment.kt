package com.example.myapplication.ui.fragment.widget

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R

/**
 * A simple [Fragment] subclass.
 */
class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.fragment_dialog, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setTitle("Title")
                .setPositiveButton("YES") { dialog, id -> Toast.makeText(this@MyDialogFragment.context, "你选择了确定", Toast.LENGTH_SHORT).show() }
                .setNegativeButton("NO") { dialog, id -> Toast.makeText(this@MyDialogFragment.context, "你选择了NO", Toast.LENGTH_SHORT).show() }
        return builder.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.fragment_dialog, container)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Toast.makeText(this.activity, "dismissing", Toast.LENGTH_SHORT)
    }
}