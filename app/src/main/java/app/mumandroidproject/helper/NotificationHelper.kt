package app.mumandroidproject.helper

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat


/**
 * Created by CodingHome on 4/20/18.
 */
class NotificationHelper private constructor() {

    companion object {

        private val NOTIFY_ID = 0
        @TargetApi(Build.VERSION_CODES.O)
        @SuppressLint("WrongConstant")
        fun sendMsg(context: Context, title: String, textContent: String, rid: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                val name = title
                val description = textContent
                val importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(context.packageName, name, importance)
                channel.description = description
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
            val mBuilder = NotificationCompat.Builder(context, context.packageName)
                    .setSmallIcon(rid)
                    .setContentTitle(textContent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(NOTIFY_ID, mBuilder.build())

        }
    }

}
