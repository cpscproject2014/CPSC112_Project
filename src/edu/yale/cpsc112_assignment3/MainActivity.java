package edu.yale.cpsc112_assignment3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast; 
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;


public class MainActivity extends Activity {
	EditText editRecipient, editAmount, editWhatsItFor;
	Button buttonSend;

    protected void onCreate1(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list_view);
    }
	
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#669900"));     
        ab.setBackgroundDrawable(colorDrawable);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editRecipient = (EditText) findViewById(R.id.editRecipient);
        editAmount = (EditText) findViewById(R.id.editAmount);
        editWhatsItFor = (EditText) findViewById(R.id.editWhatsItFor);
        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        
        
        //The following describes what happens when you click the button
        buttonSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				final SmsManager smsManager = SmsManager.getDefault();
				final String Recipient = editRecipient.getText().toString();
				String Amount = editAmount.getText().toString();
				String WhatsItFor = editWhatsItFor.getText().toString();
				
		    	 if (Amount.equals("")) { 
		    		Toast.makeText(MainActivity.this, "ERROR: Please enter an amount greater than $0.00",Toast.LENGTH_LONG).show();
		    	 }
		    		else if (Recipient.equals("")) {
		    	    Toast.makeText(MainActivity.this, "ERROR: Please select at least one recipient.", Toast.LENGTH_LONG).show();
		    	}
		    		else{
		    		// Put texting code HERE
		    			final String sms = new String("Hey there! You owe whoever sent you this text $" + Amount +". It's for " + WhatsItFor + ".");
		    		
		    		final Timer mytimer = new Timer(true);
		    		final TimerTask mytask = new TimerTask() {
		    			public void run() {
		                    smsManager.sendTextMessage(Recipient, null, sms, null, null);
		                }
		            };
		            mytimer.schedule(mytask, 86400000L);
		    		Toast.makeText(MainActivity.this, "Submitted!",Toast.LENGTH_LONG).show();
		    		
			}
		    	 }});
        } 	
   




	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      
      if (id == R.id.action_settings)
      {
    	  openSettings();
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
   public void openSettings(){
	   Intent intent = new Intent(this, MainActivity2.class);
	   startActivity(intent);
   
   }
   
}