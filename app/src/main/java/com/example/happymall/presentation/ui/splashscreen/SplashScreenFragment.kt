package com.example.happymall.presentation.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.happymall.R
import com.example.happymall.presentation.viewmodel.splashscreen.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            if (viewModel.isLogin) {
                //navigate to the home fragment
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                //navigate to the login fragment
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 2000L)
    }

}