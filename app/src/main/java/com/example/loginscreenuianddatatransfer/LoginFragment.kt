package com.example.loginscreenuianddatatransfer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.loginscreenuianddatatransfer.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private lateinit var loginviewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = registerDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepo(dao)
        val factory = loginViewModelFactory(repository,application)
        loginviewModel = ViewModelProvider(this,factory)[loginviewModel::class.java]
        binding.myLoginViewModel = loginviewModel
        binding.lifecycleOwner = this

        loginviewModel.navigatetoUserDetails.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                loginviewModel.doneNavigatingUserDetails()
            }
        })

        loginviewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                loginviewModel.donetoast()
            }
        })

        return binding.root
    }
    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action =LoginFragment.action
        NavHostFragment.findNavController(this).navigate(action)

    }
}