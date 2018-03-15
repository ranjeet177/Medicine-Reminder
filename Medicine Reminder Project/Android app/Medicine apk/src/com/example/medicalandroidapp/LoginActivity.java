package com.example.medicalandroidapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
EditText t1,t2;	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	t1=(EditText)findViewById(R.id.UserNameEditText);
	t2=(EditText)findViewById(R.id.PasswordEditText);
	
	
}
public void validateLogin(View view)
{
	String username=t1.getText().toString();
	String password=t2.getText().toString();
	MyTask m=new MyTask();
	//Toast.makeText(getApplicationContext(), "http://192.168.103.1:8050/myapp/store?u="+username+"&p="+password+"", Toast.LENGTH_SHORT).show();
	Toast.makeText(getApplicationContext(), "http://192.168.0.1:8050/myapp/store?u="+username+"&p="+password+"", Toast.LENGTH_SHORT).show();
	
	m.execute(new String[]{"http://192.168.0.1:8050/myapp/store?u="+username+"&p="+password+""});

	
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
		
		if(result.trim().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Todays Dont Have Any Medicine..... "+result, Toast.LENGTH_SHORT).show();
		}
		else if(!result.trim().equals("0"))
		{
			//Intent obj=new Intent(getApplicationContext(),SelectXMLActivity.class);
			Intent obj=new Intent(getApplicationContext(),MedicineActivity.class);
			obj.putExtra("msg", result);
			//obj.putExtra("msg1",result);
			startActivity(obj);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Login is Failed... try again "+result, Toast.LENGTH_SHORT).show();
		}
		
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
public void clearContents(View view)
{
	t1.setText("");
	t2.setText("");
	t1.requestFocus();
	
}
}
