package edu.yale.cpsc112_assignment3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText editRecipient, editAmount;
	Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editRecipient = (EditText) findViewById(R.id.editRecipient);
        editAmount = (EditText) findViewById(R.id.editAmount);
        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        
        buttonSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String Recipient = editRecipient.getText().toString();
				String Amount = editAmount.getText().toString();
				
		    	 if (Amount.equals("")) { 
		    		Toast.makeText(MainActivity.this, "ERROR: Please enter an amount greater than $0.00",Toast.LENGTH_LONG).show();
		    	 }
		    		else if (Recipient.equals("")) {
		    	    Toast.makeText(MainActivity.this, "ERROR: Please select at least one recipient.", Toast.LENGTH_LONG).show();
		    	}
		    		else{
		    		// Put texting code HERE
		    		sendSms(Recipient, Amount);
		    		Toast.makeText(MainActivity.this, "Submitted!",Toast.LENGTH_LONG).show();
			}
		    	 }});
        }  	
   


    protected void sendSms(String Recipient, String Amount) {
		// TODO Auto-generated method stub
		SmsManager manager = SmsManager.getDefault();
		String message = new String ("Hey there! You owe Blank " + Amount + ".");
		manager.sendTextMessage(Recipient, null, message, null, null);
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // ANDREW - add the action bar from selected menu xml file
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
	

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      // Handles action bar item clicks
	  // Any items added to the action bar should also be added here in the form of:
	  // case R.id.[item id]:
	  // 	[method called];
	  // 	return true;
      switch(item.getItemId())
      {
      case R.id.action_settings:
    	  //method to be called when item is clicked
    	  openSettings();
    	  return true;
      default:
    	  return super.onOptionsItemSelected(item);
      }
   }
   //Here I define the method that will open MainActivity2
   public void openSettings()
	{
	   //First, create an intent with initial context "this" and destination "MainActivity2"
		Intent openSettings = new Intent(this, MainActivity2.class);
		startActivity(openSettings);
		
	}
   

}
