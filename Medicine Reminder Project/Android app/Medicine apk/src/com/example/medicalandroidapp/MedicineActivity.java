package com.example.medicalandroidapp;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MedicineActivity extends Activity implements OnDateSetListener {
	private static final int RQS_1 = 1;
	private static final int REQUEST_CODE = 123456;
	private static final int REQUEST_CODE2 = 547896;
	private static final int REQUEST_CODE3 = 5475869;
	//EditText txt1;
	Spinner sp;
	TextView tv1,tv2,tv3;
	String msg;
	MedicineList arr[];
	MedicineList sobj=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicine);
		//txt1=(EditText)findViewById(R.id.TextView02);
		
		sp=(Spinner)findViewById(R.id.spinner1);
		Intent it=getIntent();
		msg=it.getStringExtra("msg");
		String strMedicine[] = msg.split(",");
		String Medicine = strMedicine[0];
		String Time = strMedicine[1];
		String Alarmtime = strMedicine[2];
		String str[]= Medicine.split(":");  //msg.split(":");
		String str1[] = Time.split(":");
		String str2[] = Alarmtime.split(":");
 		int len=(str.length-1);
		arr=new MedicineList[len];
		int i=0;
		int l = 0;
		for(int j=1;j<str.length;j++)
		{
			MedicineList obj=new MedicineList();
			//obj.subid=str[j];
			obj.Mdicinename=str[j];
			arr[i]=obj;
			i++;
		}
		ArrayAdapter<MedicineList> ar=new ArrayAdapter<MedicineList>(this,android.R.layout.simple_spinner_item,arr);
		sp.setAdapter(ar);
		
		tv1=(TextView)findViewById(R.id.textView3);
		tv2=(TextView)findViewById(R.id.textView4);
		tv3=(TextView)findViewById(R.id.textView5);
		
		tv1.setText(str1[1] + ":" + str1[2] + "-" + str1[3]);
		tv2.setText(str1[4] + ":" + str1[5] + "-" + str1[6]);
		tv3.setText(str1[7] + ":" + str1[8] + "-" + str1[9]);
		
	    String time1 = str2[1];
	    int timeint1 = Integer.parseInt(time1.toString());
	    String time1Min = str2[2];
	    int timeintMin1 = Integer.parseInt(time1Min.toString());
	    
	    String time2 = str2[3];
	    int timeint2 = Integer.parseInt(time2.toString());
	    String time2Min = str2[4];
	    int timeintMin2 = Integer.parseInt(time2Min.toString());
	    
	    String time3 = str2[5];
	    int timeint3 = Integer.parseInt(time3.toString());
	    String time3Min = str2[6];
	    int timeintMin3 = Integer.parseInt(time3Min.toString());
		
		//Setting First Alarm
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		//Calendar calendar2 = Calendar.getInstance();
	   
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        Calendar firstTurn = Calendar.getInstance();
        Calendar secondTurn = Calendar.getInstance();
        Calendar thirdTurn = Calendar.getInstance();

        // set times
        firstTurn.set(Calendar.HOUR_OF_DAY, timeint1);
        firstTurn.set(Calendar.MINUTE, timeintMin1);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        secondTurn.set(Calendar.HOUR_OF_DAY, timeint2);
        secondTurn.set(Calendar.MINUTE, timeintMin2);
        PendingIntent alarmIntent2 = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE2, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        thirdTurn.set(Calendar.HOUR_OF_DAY, timeint3);
        thirdTurn.set(Calendar.MINUTE, timeintMin3);
        PendingIntent alarmIntent3 = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE3, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmMgr.cancel(alarmIntent);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, secondTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent2);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, thirdTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent3);
       // setRepeating
      		Toast.makeText(this, "Alarm set For " + timeint1 + ":" + timeintMin1 + " Time",
				Toast.LENGTH_LONG).show();
      		
      		Toast.makeText(this, "Alarm set For " + timeint2 + ":" + timeintMin2 + " Time",
				Toast.LENGTH_LONG).show();
      		
      		Toast.makeText(this, "Alarm set For " + timeint3 + ":" + timeintMin3 + " Time",
    				Toast.LENGTH_LONG).show();
		//onTimeSet();
	}
	
	public void onTimeSet() {
		
		TextView tv1=(TextView)findViewById(R.id.textView3);
		int i = Integer.parseInt(tv1.getText().toString());
		TextView tv2=(TextView)findViewById(R.id.textView4);
		int j = Integer.parseInt(tv2.getText().toString());
		TextView tv3=(TextView)findViewById(R.id.textView5);
		int k = Integer.parseInt(tv3.getText().toString());
		
		  Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		 
		  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		//  alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
		  
		   Calendar calNow = Calendar.getInstance();
		   
		   Calendar firstTurn = Calendar.getInstance();
	        Calendar secondTurn = Calendar.getInstance();
	        Calendar thirdTurn = Calendar.getInstance();
	        
	        firstTurn.set(Calendar.HOUR_OF_DAY, i);
	        firstTurn.set(Calendar.MINUTE, 0);
	        PendingIntent alarmIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		   alarmManager.cancel(alarmIntent);
		   alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
		   //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calSet2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
		  // alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calSet3.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
		  // intent.putExtra("Ringtone",getResources().getResourceName(R.raw.ringtone));
		  	  // if(calSet.compareTo(calNow) <= 0){
		    //Today Set time passed, count to tomorrow
		   // calSet.add(Calendar.DATE, 6);
		   //}
		   
		  // setAlarm(calSet);
		   //Toast.makeText(AlarmReceiver.class, "Start Alarm", Toast.LENGTH_LONG).show();
		   Toast.makeText(this, "Alarm set in " + i + " Time",
					Toast.LENGTH_LONG).show();
		  }
	
	private void setAlarm(Calendar targetCal){

		 // textAlarmPrompt.setText(
		  //  "\n\n***\n"
		   // + "Alarm is set@ " + targetCal.getTime() + "\n"
		    //+ "***\n");
		  
		  Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		  alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
		  
		 }
		  

  	public void f1(View view)
	{
		sobj=(MedicineList)sp.getSelectedItem();
		//sobj.subid
		
		MyTask m=new MyTask();
		//Toast.makeText(getApplicationContext(), "http://192.168.43.2:8060/myapp/roll?id="+sobj.subid, Toast.LENGTH_SHORT).show();
		
		//m.execute(new String[]{"http://192.168.43.2:8060/myapp/roll?id="+sobj.subid});
		
		
	}
	private class MyTask extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			String output=null;
			for(String url : urls)
			{
				output=getOutputFromUrl(url);
			}
			return output;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
		//	super.onPostExecute(result);
			
			//Intent obj=new Intent(getApplicationContext(),SelectXMLActivity.class);
			
		} 
		
	}
	private String getOutputFromUrl(String url){
		String output=null;
		try
		{
			DefaultHttpClient ct=new DefaultHttpClient();
			HttpGet hg=new HttpGet(url);
			HttpResponse hr=ct.execute(hg);
			HttpEntity he=hr.getEntity();
			output=EntityUtils.toString(he);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			Log.d("SS", ex.toString());
		}
		return output;
	}

	public void f2(View view)
	{
		finish();
	}
	public void f3(View view)
	{
		DatePickerDialog dp=new DatePickerDialog(this, this, 2013, 3, 16);
		dp.show();
		
	}
	@Override
	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		//txt1.setText(arg1+"-"+(arg2+1)+"-"+arg3);
		
	}

}
class MedicineList implements Serializable
{
	//public String subid;
	public String Mdicinename;
	//public String attenddate;
	public String toString()
	{
		return Mdicinename;
	}
}