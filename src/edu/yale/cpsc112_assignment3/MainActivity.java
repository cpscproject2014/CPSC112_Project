package edu.yale.cpsc112_assignment3;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import edu.yale.cpsc112_assignment3.data.EntryItem;
import edu.yale.cpsc112_assignment3.data.EntryItemSource;
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
	private EntryItemSource entrySource;
	
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#669900"));     
        ab.setBackgroundDrawable(colorDrawable);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
        entrySource = new EntryItemSource(this);
      	final List<EntryItem> entries = entrySource.findAll();      
      	
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
				
				//These change what the user enters in the Textviews to variables.
				final SmsManager smsManager = SmsManager.getDefault();
				final String Recipient = editRecipient.getText().toString();
				String Amount = editAmount.getText().toString();
				String WhatsItFor = editWhatsItFor.getText().toString();
				String RecipientName = editRecipientName.getText().toString();
				String YourName = editYourName.getText().toString();
				
				boolean sendText = true;
				//These lines tell you how many digits the user placed after the decimal point
				String decimals = new String();
				int numberOfDecimals = 0;
				if (Amount.indexOf('.') != -1){
					decimals = Amount.substring(Amount.indexOf('.') + 1, Amount.length());
					numberOfDecimals = decimals.length();
				}
				
				/*
				 * This part of the code gets the current time and adds the delay time to it.
				 * Used http://www.tutorialspoint.com/java/util/java_util_calendar.htm
				 * It gets the current time in milliseconds, adds on 1 minute of milliseconds to the current time,
				 * and sets sendTime equal to that time.
				 */
				Calendar cal = Calendar.getInstance();
				long millis = 1000 * 60;
			    long sendTime = cal.getTimeInMillis() + millis;
			    cal.setTimeInMillis(sendTime);
			    
				//These are all the checks that need to occur in order for the app to send a text message.
			    //If at any point, one of the checks fails, sendText will be set equal to false, and the text message won't send.
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
		    	
		    	//This makes sure that the user doesn't enter a dollar value like 3.141592653.
		    	if ((numberOfDecimals > 2) && sendText) { 
		    		Toast.makeText(MainActivity.this, "ERROR: Dollar amounts cannot have more than two decimals.",Toast.LENGTH_LONG).show();
		    		sendText = false;
		    	 }
		    	
		    	//This part just adds a 0 if the user enters something like 4.5 for the amount owed. This part will convert it
		    	//to 4.50.
		    	if(numberOfDecimals == 1) {
		    		Amount = "" + Amount + "0";
		    	}
		    	
		    	/*
		    	 * These checks make sure that the user enters vital information, such as the username, recipient name,
		    	 * and recipient phone number. WhatsItFor was not deemed vital, because sometimes the sender will not want
		    	 * to put in a reason for charging somebody every time they use this app. Sometimes it may be obvious what the
		    	 * recipient is being charged for.
		    	 */
		    	if (Recipient.equals("") && sendText) {
		    	    Toast.makeText(MainActivity.this, "ERROR: Please enter the recipient's phone number.", Toast.LENGTH_LONG).show();
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
		    	
		    	//If all the checks work, the app will send a text message.
		    	if(sendText) {
		    		// Here is the texting code.
	    			String smsMessage = new String("Hey "+ RecipientName + "! You owe "+ YourName +" $" + Amount +". It's for " + WhatsItFor + ".");
		    		
	    			//Add entry
		    		EntryItem entry = EntryItem.getNew();
		    		//Create unique ID because without it, any entry that uses the same key will overwrite previous values
		    		//The UUID ensures that no values will be overwritten, and since 
		    		UUID uuid = UUID.randomUUID();
		    		entry.setKey(Recipient + uuid);
		    		entry.setValue(Recipient + ":" + Amount);
		    		entrySource.update(entry);
		    		
	    			//This is the adjusted message, if the user doesn't include anything in WhatsItFor.
	    			if(WhatsItFor.equals("")){
	    				smsMessage = "Hey "+ RecipientName + "! You owe "+ YourName +" $" + Amount +".";
			    		
	    				//Add entry
			    		entry = EntryItem.getNew();
			    		//Create unique ID because without it, any entry that uses the same key will overwrite previous values
			    		//The UUID ensures that no values will be overwritten, and since 
			    		uuid = UUID.randomUUID();
			    		entry.setKey(Recipient + uuid);
			    		entry.setValue(Recipient + ":" + Amount);	
			    		entrySource.update(entry);
	    				
	    			}
	    			
	    			/*
	    			 * We decided that the way you delete entries from the data file when somebody does pay you back is that
	    			 * you subtract the amount you've been paid back. This is done by entering a negative number into the
	    			 * editAmount Textview. When you click send, the app will subtract this much money for the total that the 
	    			 * recipient owes you. Then it sends out a text message saying "Thanks for paying me back..." Here is the
	    			 * code that deals with constructing that message.
	    			 */
	    			if(Double.parseDouble(Amount) < 0) {
	    				//This line removes the negative sign.
	    				Amount = Amount.substring(1,Amount.length());
	    				smsMessage = "Thanks " + RecipientName + ", for paying me back $" + Amount + " for " + WhatsItFor + ".";
	    				if(WhatsItFor.equals("")){
		    				smsMessage = "Thanks "+ RecipientName + ", for paying me back $" + Amount +".";
	    				}
	    			}
	    			
	    			//This section of code is for the delay function. 
		    		final String sms = smsMessage;
		    		//Used http://developer.android.com/reference/java/util/Timer.html
		    		final Timer mytimer = new Timer(true);
		    		final TimerTask mytask = new TimerTask() {
		    		public void run() {
		    			//This line of code sends a text message.
		    			//Used http://developer.android.com/reference/android/telephony/SmsManager.html
		    			smsManager.sendTextMessage(Recipient, null, sms, null, null);
		    			}
		            };
		            
		            //This sets the time that the task is completed to 1 minute after the user presses send.
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