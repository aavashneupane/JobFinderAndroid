package com.aavash.jobfinder.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aavash.jobfinder.MapsActivity
import com.aavash.jobfinder.R

class ExtraFragment : Fragment() {

    private lateinit var webview:WebView
    private lateinit var btnMap:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_extra, container, false)
        webview=root.findViewById(R.id.webview)
        btnMap=root.findViewById(R.id.btnMap)



        btnMap.setOnClickListener {
            val intent=Intent(context, MapsActivity::class.java)
            context?.startActivity(intent)
        }

        webview.loadUrl("https://softwarica.edu.np/about-softwarica/")

        return root



    }



}