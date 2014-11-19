package edu.yale.cpsc112_assignment3;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
        
        //The following describes what happens when you click the button
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	/*
//<<<<<<< Updated upstream
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//=======
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      if (id == R.id.action_settings)
      {
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
   
   
>>>>>>> Stashed changes*/
}
