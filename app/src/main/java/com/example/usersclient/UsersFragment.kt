package com.example.usersclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.usersclient.data.local.UserEntity
import com.example.usersclient.data.local.UsersDatabase
import com.example.usersclient.data.repositories.User
import com.example.usersclient.databinding.FragmentUsersBinding
import com.example.usersclient.ui.UsersListAdapter
import com.example.usersclient.ui.UsersViewModel
import com.example.usersclient.ui.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var usersListAdapter: UsersListAdapter
    private val usersViewModel by activityViewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater)

        return binding.root
    }



    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usersViewModel.usersListSateFlow.onEach {
            when (it) {
                is ViewState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
                is ViewState.Success -> {

                }

                is ViewState.Error -> {
                    Toast.makeText(requireContext(), it.result, Toast.LENGTH_LONG).show()
                }
            }
        }.launchIn(lifecycleScope)

        binding.reloadButton.setOnClickListener {
            usersViewModel.getUsers()
        }

        usersListAdapter = UsersListAdapter(requireContext()) {
            val user = it
            val action = UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(user)
            findNavController().navigate(action)
        }


        lifecycle.coroutineScope.launch {
            usersViewModel.loadAllUsers().collect {
                usersListAdapter.usersList.clear()
                for (element in it) {
                    usersListAdapter.usersList.add(
                        element.entityToUser()
                    )
                }
                usersListAdapter.notifyDataSetChanged()
            }
        }

        binding.usersRecyclerView.adapter = usersListAdapter
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.usersRecyclerView.layoutManager = gridLayoutManager

    }


}