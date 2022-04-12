package com.oguzhancetin.androidprojecttemplate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhancetin.androidprojecttemplate.adapter.UserListAdapter
import com.oguzhancetin.androidprojecttemplate.data.NetworkResult
import com.oguzhancetin.androidprojecttemplate.data.UserViewModel
import com.oguzhancetin.androidprojecttemplate.databinding.FragmentMainBinding
import com.oguzhancetin.androidprojecttemplate.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var adapter: UserListAdapter
    override fun getVB(): FragmentMainBinding {
        return FragmentMainBinding.inflate(LayoutInflater.from(requireContext()), null, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserListAdapter(requireContext(), listOf<User>(), ::onClickUserCard)
        binding.rcUser.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcUser.also {
            it.addItemDecoration(
                DividerItemDecoration(
                    it.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        binding.rcUser.adapter = adapter
        fetchUserList()


    }

    private fun fetchUserList() {
        viewModel.fetchUserList()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlowUserList.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            withContext(Dispatchers.Main){
                                binding.progressBar.visibility = View.VISIBLE
                            }


                        }
                        is NetworkResult.Error -> {
                            withContext(Dispatchers.Main) {
                                binding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                        is NetworkResult.Success -> {
                                withContext(Dispatchers.Main) {

                                    binding.progressBar.visibility = View.INVISIBLE
                                    it.data?.let { users ->
                                        adapter.refreshList(users)
                                    }
                                }

                        }
                    }

                }
            }
        }

    }

    private fun onClickUserCard(user: User) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToUserDetailFragment(
                user.userName
            )
        )
    }


}