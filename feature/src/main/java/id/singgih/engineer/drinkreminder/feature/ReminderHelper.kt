package id.singgih.engineer.drinkreminder.feature

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.app.NotificationChannel
import android.graphics.Color
import android.os.Build






/**
 * Created by singgihrs on 9/28/17.
 */

class ReminderHelper(private var context: Context) {

    private val ID_DRINK_REMINDER = 1

    private val NOTIFICATION_CHANNEL_ID = "drink_reminder_notification_channel"

    private var notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context)

    private val resultIntent: Intent = Intent(context, DrinkReminderActivity::class.java)

    private val resultPendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    private var notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun getIconBitmap(): Bitmap? = BitmapFactory.decodeResource(context.resources, R.drawable.ic_logo_transparent)

    fun createNotification() {
        setupNotificationChannel(notificationManager)
        notificationBuilder.setContentIntent(resultPendingIntent)
        notificationBuilder.setSmallIcon(R.drawable.ic_logo_transparent)
        notificationBuilder.setLargeIcon(getIconBitmap())
        notificationBuilder.setContentTitle(context.getString(R.string.app_name))
        notificationBuilder.setContentText(context.getString(R.string.drinking_reminder))
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)

        notificationManager.notify(ID_DRINK_REMINDER, notificationBuilder.build())
    }

    private fun setupNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, context.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.description = context.getString(R.string.drinking_reminder)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

}