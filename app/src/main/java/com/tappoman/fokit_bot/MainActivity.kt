package com.tappoman.fokit_bot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elbekD.bot.Bot


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun main() {
            val token = ""
            val bot = Bot.createPolling("fokit",token)
            bot.onCommand("/start") { msg, _ ->
                bot.sendMessage(msg.chat.id, "Hello World!")
            }
            bot.start()
        }

        main()
    }
}
