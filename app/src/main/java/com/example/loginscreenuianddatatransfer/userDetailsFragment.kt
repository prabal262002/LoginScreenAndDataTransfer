package com.example.loginscreenuianddatatransfer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.loginscreenuianddatatransfer.R
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginscreenuianddatatransfer.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var binding: FragmentUserDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_details, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = registerDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepo(dao)

        val factory = userDetailsFactory(repository, application)

        userDetailsViewModel =
            ViewModelProvider(this, factory)[UserDetailsViewModel::class.java]

        binding.userDelailsLayout = userDetailsViewModel

        binding.lifecycleOwner = this

//        userDetailsViewModel.navigateto.observe(this, Observer { hasFinished ->
//            if (hasFinished == true) {
//                val action = FragmentUserDetailsDirections.actionUserDetailsFragmentToLoginFragment()
//                NavHostFragment.findNavController(this).navigate(action)
//                userDetailsViewModel.doneNavigating()
//            }
//        })

        initRecyclerView()

        return binding.root

    }


    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        userDetailsViewModel.users.observe(viewLifecycleOwner, Observer {
            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        })

    }

}