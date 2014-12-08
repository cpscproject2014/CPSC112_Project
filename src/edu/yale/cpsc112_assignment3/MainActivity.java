package edu.yale.cpsc112_assignment3;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
	EditText editRecipient, editAmount, editWhatsItFor, editRecipientName, editYourName;
	Button buttonSend;
	
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#669900"));     
        ab.setBackgroundDrawable(colorDrawable);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
        editRecipient = (EditText) findViewById(R.id.editRecipient);
        editAmount = (EditText) findViewById(R.id.editAmount);
        editWhatsItFor = (EditText) findViewById(R.id.editWhatsItFor);
        editRecipientName = (EditText) findViewById(R.id.editRecipientName);
        editYourName = (EditText) findViewById(R.id.editYourName);
        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        
        
        //The following describes what happens when you click the button
        buttonSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				final SmsManager smsManager = SmsManager.getDefault();
				final String Recipient = editRecipient.getText().toString();
				String Amount = editAmount.getText().toString();
				String WhatsItFor = editWhatsItFor.getText().toString();
				String RecipientName = editRecipientName.getText().toString();
				String YourName = editYourName.getText().toString();
				
				boolean sendText = true;
				
				Calendar cal = Calendar.getInstance();
				long millis = 1000 * 60;
			    long sendTime = cal.getTimeInMillis() + millis;
			    cal.setTimeInMillis(sendTime);
			    
				
			    try{
			    	Double.parseDouble(Amount);
			    } catch(Exception e){
			    	Toast.makeText(MainActivity.this, "ERROR: Please enter an amount that isn't $0.00",Toast.LENGTH_LONG).show();
			    	sendText = false;
			    }
		    	if ((Amount.equals("") || Double.parseDouble(Amount) == 0) && sendText) { 
		    		Toast.makeText(MainActivity.this, "ERROR: Please enter an amount that isn't $0.00",Toast.LENGTH_LONG).show();
		    		sendText = false;
		    	 }
		    	if (Recipient.equals("") && sendText) {
		    	    Toast.makeText(MainActivity.this, "ERROR: Please select a recipient.", Toast.LENGTH_LONG).show();
		    	    sendText = false;
		    	}
		    	if (RecipientName.equals("") && sendText) {
		    	    Toast.makeText(MainActivity.this, "ERROR: Please enter the recipient's name.", Toast.LENGTH_LONG).show();
		    	    sendText = false;
		    	}
		    	if (YourName.equals("") && sendText) {
		    	    Toast.makeText(MainActivity.this, "ERROR: Please enter your name.", Toast.LENGTH_LONG).show();
		    	    sendText = false;
		    	}
		    	if(sendText) {
		    		// Put texting code HERE
	    			String smsMessage = new String("Hey "+ RecipientName + "! You owe "+ YourName +" $" + Amount +". It's for \"" + WhatsItFor + ".\"");
	    			if(WhatsItFor.equals("")){
	    				smsMessage = "Hey "+ RecipientName + "! You owe "+ YourName +" $" + Amount +".";
	    			}
	    		
	    			if(Double.parseDouble(Amount) < 0) {
	    				Amount = Amount.substring(1,Amount.length());
	    				smsMessage = "Thanks " + RecipientName + ", for paying me back $" + Amount + " for \"" + WhatsItFor + ".\"";
	    				if(WhatsItFor.equals("")){
		    				smsMessage = "Hey "+ RecipientName + "! You owe "+ YourName +" $" + Amount +".";
	    				}
	    			}
		    		final String sms = smsMessage;
		    		final Timer mytimer = new Timer(true);
		    		final TimerTask mytask = new TimerTask() {
		    		public void run() {
		    			smsManager.sendTextMessage(Recipient, null, sms, null, null);
		    			}
		            };
		            mytimer.schedule(mytask, 60000L);
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
	    	  openBalances();
	         return true;
	      }
	      if(id == R.id.action_about)
	      {
	    	  openAbout();
	      }
	      return super.onOptionsItemSelected(item);
	   }
	   public void openBalances(){
		   Intent intent = new Intent(this, MainActivity2.class);
		   startActivity(intent);
	   }
	   
	   public void openAbout(){
		   Intent intent = new Intent(this, MainActivity3.class);
		   startActivity(intent);
	   }
}