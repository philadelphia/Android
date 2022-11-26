package com.example.myapplication.ui.fragment.customeview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LineFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LineFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_line, container, false)
    }
}