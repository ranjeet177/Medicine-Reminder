package com.example.medicalandroidapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.SyncStateContract.Constants;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
NotificationManager nm;
private MediaPlayer mp;
 @Override
 public void onReceive(Context context, Intent intent) {
	 //Log.i(Constants.LOG_TAG, "DealAlarmReceiver invoked, starting DealService in background");
     //context.startService(new Intent(context, DealService.class));
     
	 Toast.makeText(context, "Time is up Take Medicine!!!!.",
			Toast.LENGTH_LONG).show();
	 
	    Vibrator vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(2000);
		       
		 mp = MediaPlayer.create(context,R.raw.b);
         mp.start();
         
         NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

         String MyText = "Reminder";
         Notification mNotification = new Notification(R.drawable.ic_launcher, MyText, System.currentTimeMillis() );
         //The three parameters are: 1. an icon, 2. a title, 3. time when the notification appears

         String MyNotificationTitle = "Medicine!";
         String MyNotificationText  = "Don't forget to take your medicine!";

         Intent MyIntent = new Intent(Intent.ACTION_VIEW);
         PendingIntent StartIntent = PendingIntent.getActivity(context.getApplicationContext(),0,MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         //A PendingIntent will be fired when the notification is clicked. The FLAG_CANCEL_CURRENT flag cancels the pendingintent

         mNotification.setLatestEventInfo(context.getApplicationContext(), MyNotificationTitle, MyNotificationText, StartIntent);

         int NOTIFICATION_ID = 1;
         notificationManager.notify(NOTIFICATION_ID , mNotification);  
         
		//AudioManager amanager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		//amanager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND, 0);
		//NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		//Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		//Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), soundUri);
         //r.play();
        
		//Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //if (alarmUri == null) {
          //  alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      //  }
        //Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
       // ringtone.play();
        
       // Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
        //r.play();
        
		//Intent in = new Intent(context, AlarmReceiver.class);
	   // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//PendingIntent Sender = PendingIntent.getActivity(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
       // Notification notif1 = new Notification(R.drawable.ic_launcher, "Wake up alarm", System.currentTimeMillis());
       // notif1.setLatestEventInfo(context, "Please Take Your Medicine", "Wake Up...", Sender);
       // notif1.flags = Notification.FLAG_INSISTENT;
       // nm.notify(1, notif1); 
        
		//Notification notif1 = new Notification(R.drawable.ic_launcher, "Please Take Your Medicine", System.currentTimeMillis());
		

		//mp = MediaPlayer.create(this, resId);
		//mp.start();
		 
		 
  //Toast.makeText(arg0, "Alarm received!", Toast.LENGTH_LONG).show();
	// nm = (NotificationManager) context.getSystemService(
	       // Context.NOTIFICATION_SERVICE);
 // NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	 
	    
	  //  Intent in = new Intent(context, AlarmReceiver.class);
	//    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 //   PendingIntent Sender = PendingIntent.getActivity(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
	 //   nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	 //   Notification notif1 = new Notification(R.drawable.ic_launcher, "Wake up alarm", System.currentTimeMillis());
	 //   notif1.setLatestEventInfo(context, "Hanuman Chalisa", "Wake Up...", Sender);
	 //   notif1.flags = Notification.FLAG_INSISTENT;
	  //  notif1.sound = (Uri)intent.getParcelableExtra("Ringtone");
	  //  nm.notify(1, notif1);  
	    
	  //  NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	  //  Intent notificationIntent = new Intent(this, YourActivity.class);
	   // PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
	    //Notification note  = new Notification(R.drawable.icon, "Your Text to Show", System.currentTimeMillis());
	   // note.sound = Uri.parse("file:///sdcard/notification/ringer.mp3");
	   // int nid = 123456789;
//	   / manager.notify(nid, note);
 }

}
