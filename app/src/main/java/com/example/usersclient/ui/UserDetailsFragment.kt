package com.example.usersclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.usersclient.data.repositories.User
import com.example.usersclient.databinding.FragmentUserDetailsBinding


class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding
    private val args : UserDetailsFragmentArgs by navArgs()
    private val usersViewModel by activityViewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Glide.with(requireContext()).load(args.user.avatar).into(binding.userAvatarImageView)
        binding.userNameTextView.text = args.user.first_name + " " + args.user.last_name
        binding.emailTextView.text = args.user.email

        val user = User(
            args.user.id,
            args.user.avatar,
            args.user.first_name,
            args.user.last_name,
            args.user.email
        )

        binding.deleteButton.setOnClickListener{
            deleteUser(user)
            val action =
                UserDetailsFragmentDirections.actionUserDetailsFragmentToUsersFragment()
            findNavController().navigate(action)
        }
        binding.updateButton.setOnClickListener {
            val action =
                UserDetailsFragmentDirections.actionUserDetailsFragmentToUpdateUserFragment(
                    user
                )
            findNavController().navigate(action)
        }
    }

    private fun deleteUser(user: User){
        usersViewModel.deleteUser(user.toUserEntity())

    }
}