package com.alikazi.codetest.ee.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.alikazi.codetest.ee.R

class MessagesAdapter(private val context: Context?) : RecyclerView.Adapter<MessagesAdapter.MessageItemViewHolder>(){

    companion object {
        const val VIEW_TYPE_SENT = 0
        const val VIEW_TYPE_RECEIVED = 1
    }

    private var messagesList = ArrayList<Any>()
    private var inflater = LayoutInflater.from(context)

    fun addSentMessage(messageSent: MessageSent) {
        messagesList.add(messageSent)
        notifyItemInserted(messagesList.size)
    }

    fun addReceievedMessage(messageReceived: MessageReceived) {
        messagesList.add(messageReceived)
        notifyItemInserted(messagesList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemViewHolder {
        val view = inflater.inflate(R.layout.item_message, parent, false)
        return MessageItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageItemViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_SENT -> {
                val sentMessage = messagesList[position] as MessageSent
                holder.messageText.text = sentMessage.text
                alignMessageToRight(holder.messageContainer)

            }
            VIEW_TYPE_RECEIVED -> {
                val receivedMessage = messagesList[position] as MessageReceived
                holder.messageText.text = receivedMessage.text
            }
        }
    }

    private fun alignMessageToRight(target: CardView) {
        val layoutParams = RelativeLayout.LayoutParams(target.layoutParams.width, target.layoutParams.height)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END)
        target.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = messagesList.size

    override fun getItemViewType(position: Int) = when(messagesList[position]) {
        is MessageSent -> VIEW_TYPE_SENT
        is MessageReceived -> VIEW_TYPE_RECEIVED
        else -> super.getItemViewType(position)
    }

    class MessageItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageContainer: CardView = view.findViewById(R.id.messageContainer)
        val messageText: TextView = view.findViewById(R.id.messageText)
        val messageTimestamp: TextView = view.findViewById(R.id.messageTimestamp)
    }

}