package com.example.happymall.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.happymall.R
import com.example.happymall.data.util.Utils.validateLoginRequest
import com.example.happymall.databinding.FragmentLoginBinding
import com.example.happymall.presentation.viewmodel.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.loginUsername.setText("mor_2314")
        binding.loginPassword.setText("83r5^_")

        binding.loginButton.setOnClickListener {

            val username = binding.loginUsername.editableText.toString()
            val password = binding.loginPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if (result.successful) {
                binding.loginProgress.visibility = View.VISIBLE
                binding.loginButton.isEnabled = false

                viewModel.loginUser(username, password)

                viewModel.successful.observe(viewLifecycleOwner) { successful ->
                    if (successful == true) {
                        binding.loginProgress.visibility = View.INVISIBLE
                        binding.loginButton.isEnabled = true
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        viewModel.navigated()
                    } else if (successful == false) {
                        binding.loginProgress.visibility = View.INVISIBLE
                        binding.loginButton.isEnabled = true
                        Snackbar.make(
                            binding.loginButton,
                            "${viewModel.error.value}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        viewModel.navigated()
                    }
                }
            } else {
                Snackbar.make(binding.loginButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }

        }


        binding.loginSignup.setOnClickListener {
            Toast.makeText(requireContext(), "Comming soon...", Toast.LENGTH_SHORT).show()
        }

    }


}