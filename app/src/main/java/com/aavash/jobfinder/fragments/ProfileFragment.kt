package com.aavash.jobfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aavash.jobfinder.R
import com.aavash.jobfinder.db.UserDB
import com.aavash.jobfinder.userRepository.UserRepository


class ProfileFragment : Fragment() {
    private lateinit var tvFirstName:TextView
    private lateinit var tvEmail: TextView

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root: View = inflater.inflate(R.layout.fragment_profile, container, false)
        tvFirstName = root.findViewById(R.id.tvFirstName)
        tvEmail = root.findViewById(R.id.tvemailProfile)

        val userdao = context?.let {
            UserDB.getInstance(it)
                    .getUserDAO()
        }
        val repo = UserRepository(userdao!!)

        profileViewModel =
                ViewModelProvider(this, ProfileViewModelFactory(repo)).get(ProfileViewModel::class.java)

        profileViewModel.getLogggedInUser()

        profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
           // tvFirstName.text = user.firstname
            tvEmail.text = user.email
        })

        return root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}