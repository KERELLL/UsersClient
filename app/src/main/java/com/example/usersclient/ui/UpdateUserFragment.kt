package com.example.usersclient.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.usersclient.R
import com.example.usersclient.data.repositories.User
import com.example.usersclient.databinding.FragmentUpdateUserBinding


class UpdateUserFragment : Fragment() {

    private lateinit var binding: FragmentUpdateUserBinding
    private val args : UpdateUserFragmentArgs by navArgs()
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usersViewModel = ViewModelProvider(this)[UsersViewModel::class.java]

        binding.emailEditText.setText(args.user.email)
        binding.firstNameEditText.setText(args.user.first_name)
        binding.lastNameEditText.setText(args.user.last_name)

        binding.updateBtn.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser(){
        val email = binding.emailEditText.text.toString()
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        if(inputCheck(email, firstName, lastName)){
            val updateUser = User(args.user.id, args.user.avatar, firstName, lastName, email)
            usersViewModel.updateUser(updateUser.toUserEntity())
            findNavController().navigate(R.id.usersFragment)
        }
    }

    private fun inputCheck(email : String, firstName: String, lastName: String): Boolean{
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }

}