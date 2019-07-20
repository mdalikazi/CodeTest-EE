package com.alikazi.codetest.ee.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alikazi.codetest.ee.R
import com.alikazi.codetest.ee.utils.InactivityHelper
import com.alikazi.codetest.ee.utils.Injector
import com.alikazi.codetest.ee.viewmodels.MessageViewModel
import com.alikazi.codetest.ee.viewmodels.RequestResponseModels
import com.google.android.material.snackbar.Snackbar
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
                mainFragmentRecyclerView.smoothScrollToPosition(messagesAdapter.itemCount)
                InactivityHelper.getInstance(activity).startFourSecondsCountdown()
            }
        })

        messageViewModel?.networkErrors?.observe(this, Observer {
            Snackbar.make(mainFragmentContainer, R.string.generic_network_error, Snackbar.LENGTH_LONG).show()
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
                if (validateTextFields()) {
                    val trimmedText = mainFragmentEditTextMessage.text.toString().trim()
                    val messageSent = MessageSent(trimmedText)
                    val messageRequest = RequestResponseModels.MessageRequest(messageSent)
                    messageViewModel?.sendMessageToServer(messageRequest)
                    messagesAdapter.addSentMessage(messageSent)
                    mainFragmentEditTextMessage.text.clear()
                    mainFragmentRecyclerView.smoothScrollToPosition(messagesAdapter.itemCount)
                }
            }
        }
    }

    private fun validateTextFields(): Boolean {
        if (mainFragmentNumberEditText.text.isBlank() &&
                mainFragmentNumberEditText.text.isEmpty()) {
            mainFragmentNumberEditText.requestFocus()
            mainFragmentNumberEditText.error = getString(R.string.validation_number_error_empty)
            return false
        } else if (mainFragmentNumberEditText.length() < 10) {
            mainFragmentNumberEditText.requestFocus()
            mainFragmentNumberEditText.error = getString(R.string.validation_number_error_less_than_10_chars)
        } else if (mainFragmentEditTextMessage.text.isBlank() &&
            mainFragmentEditTextMessage.text.isEmpty()) {
            return false
        }
        mainFragmentNumberEditText.error = null
        return true
    }

}
