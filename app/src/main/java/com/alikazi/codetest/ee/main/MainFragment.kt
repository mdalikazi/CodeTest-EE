package com.alikazi.codetest.ee.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alikazi.codetest.ee.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var messagesAdapter = MessagesAdapter(activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            mainFragmentRecyclerView.adapter = messagesAdapter
            mainFragmentButtonSend.setOnClickListener {
                mainFragmentEditTextMessage.text
            }
        }

    }
}