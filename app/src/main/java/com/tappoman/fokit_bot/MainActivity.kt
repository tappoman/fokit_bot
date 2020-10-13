package com.tappoman.fokit_bot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elbekD.bot.Bot
import twitter4j.Paging
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder



class MainActivity : AppCompatActivity() {

    private val dataIds =  DataIds()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main()
    }

    private fun main() {
        val statuses = getTweets()
        val bot = Bot.createPolling("fokit", dataIds.telegramBotToken)
        bot.onCommand("/start") { msg, _ ->
            bot.sendMessage(msg.chat.id, "Hello World!")
        }
        bot.onCommand("/twitter") { msg, _ ->
            for (status: Status in statuses)
            bot.sendMessage(msg.chat.id, status.text)
        }
        bot.start()
    }

    private suspend fun getTweets(): List<Status> {

        val cb = ConfigurationBuilder()
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(dataIds.twitterApiKey)
            .setOAuthConsumerSecret(dataIds.twitterApiSecretKey)
            .setOAuthAccessToken(dataIds.twitterAccessToken)
            .setOAuthAccessTokenSecret(dataIds.twitterAccessTokenSecret)
        val tf = TwitterFactory(cb.build())
        val twitter: Twitter = tf.instance
        val paging = Paging(1, 100)
        val statuses: List<Status> = twitter.getUserTimeline("fok_it", paging)
        for (status: Status in statuses)
            println("jeejee"+status)
        return statuses
    }
}