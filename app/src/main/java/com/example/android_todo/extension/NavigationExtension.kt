package com.example.android_todo.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(navDirections: NavDirections) = this.findNavController().navigate(navDirections)
