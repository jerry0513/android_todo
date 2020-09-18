package com.example.android_todo.ui

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        requireActivity().currentFocus?.windowToken?.let {
            imm.hideSoftInputFromWindow(it, 0)
        }
    }

    override fun onDestroyView() {
        hideProgressBar()
        hideKeyboard()
        super.onDestroyView()
    }
}
