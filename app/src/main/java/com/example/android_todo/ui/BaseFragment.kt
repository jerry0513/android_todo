package com.example.android_todo.ui

import android.view.WindowManager
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun showProgressBar() {
        val flag = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        requireActivity().window.setFlags(flag, flag)
        (activity as MainActivity).progressBar.show()
    }

    fun hideProgressBar() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        (activity as MainActivity).progressBar.hide()
    }

    override fun onDestroyView() {
        hideProgressBar()
        super.onDestroyView()
    }
}
