package com.example.gestionipn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestionipn.utils.Time
import com.example.gestionipn.data.Message
import com.example.gestionipn.R
import com.example.gestionipn.utils.BotResponse
import com.example.gestionipn.utils.Constants.RECEIVE_ID
import com.example.gestionipn.utils.Constants.SEND_ID
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessagingAdapter
    private val botlist = listOf("Gestion")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()
        clickEvents()

        customMessage("Hola soy un bot llamado ${botlist[0]},me puedes pregunta sobre los examenes excribiendo: " +
                "Examen\n")
    }
    private fun sendMessage(){
        val message = et_message.text.toString()
        val timeStamp =Time.timeStamp()
        if (message.isEmpty()){
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID,timeStamp))
            rv_messages.scrollToPosition((adapter.itemCount-1))

            botResponse(message)
        }
    }

    private fun botResponse(message: String){
        val timeStamp = Time.timeStamp()
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponses(message)

                adapter.insertMessage(Message(message, RECEIVE_ID,timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }
    private fun clickEvents() {

        //Send a message
        btn_send.setOnClickListener {
            sendMessage()
        }

        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun customMessage(message: String){
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount-1)

            }
        }
    }
}