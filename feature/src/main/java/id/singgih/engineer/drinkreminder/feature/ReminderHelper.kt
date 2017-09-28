package id.singgih.engineer.drinkreminder.feature

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.graphics.BitmapFactory
import android.graphics.Bitmap




/**
 * Created by singgihrs on 9/28/17.
 */

class ReminderHelper(private var context: Context) {

    private val ID_DRINK_REMINDER = 1

    private var notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context)

    private var resultIntent: Intent = Intent(context, DrinkReminderActivity::class.java)

    private var resultPendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    private var notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun getIconBitmap(): Bitmap? = BitmapFactory.decodeResource(context.resources, R.drawable.ic_logo_transparent)

    fun createNotification() {
        notificationBuilder.setContentIntent(resultPendingIntent)
        notificationBuilder.setSmallIcon(R.drawable.ic_logo_transparent)
        notificationBuilder.setLargeIcon(getIconBitmap())
        notificationBuilder.setContentTitle(context.getString(R.string.app_name))
        notificationBuilder.setContentText(context.getString(R.string.drinking_reminder))
        notificationBuilder.setAutoCancel(true)

        notificationManager.notify(ID_DRINK_REMINDER, notificationBuilder.build())
    }

}