package com.alikazi.codetest.ee.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alikazi.codetest.ee.R
import com.alikazi.codetest.ee.utils.Injector
import com.alikazi.codetest.ee.viewmodels.MessageViewModel
import com.alikazi.codetest.ee.viewmodels.RequestResponseModels
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var messagesAdapter: MessagesAdapter
    private var messageViewModel: MessageViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageViewModel = ViewModelProviders
            .of(this, Injector.provideViewModelFactory())
            .get(MessageViewModel::class.java)

        messageViewModel?.messageReceived?.observe(this, Observer {
            if (it.text.isNotEmpty() && it.text.isNotBlank()) {
                messagesAdapter.addReceievedMessage(it)
            }
        })
        messagesAdapter = MessagesAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            mainFragmentRecyclerView.adapter = messagesAdapter
            mainFragmentButtonSend.setOnClickListener {
                if (mainFragmentEditTextMessage.text.isNotEmpty() &&
                        mainFragmentEditTextMessage.text.isNotBlank()) {
                    val trimmedText = mainFragmentEditTextMessage.text.toString().trim()
                    val messageSent = MessageSent(trimmedText)
                    val messageRequest = RequestResponseModels.MessageRequest(messageSent)
                    messageViewModel?.sendMessageToServer(messageRequest)
                    messagesAdapter.addSentMessage(messageSent)
                    mainFragmentEditTextMessage.text.clear()
                }
            }
        }

    }
}
