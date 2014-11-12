package edu.yale.cpsc112_assignment3;

import edu.yale.cpsc112_assignment3.CPSC112_Assignment3;
import edu.yale.cpsc112_assignment3.R;
import edu.yale.cpsc112_assignment3.TextViewPrintStream;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity
{

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);//make t be the textbox where our program will output data
 
      TextView t = (TextView) findViewById(R.id.textOut);
      
      //make it so the scroll bar on t will work
      t.setMovementMethod(new ScrollingMovementMethod());
      
      //make it so when we write System.out.println in our code, it goes to t
      System.setOut(new TextViewPrintStream(this, t));
      
      //call initNum to generate a random number at the beginning of the program
      CPSC112_Assignment3.makeMySecret();
      
      //make editText be the textbox where the user can enter in data
      EditText editText = (EditText) findViewById(R.id.textIn);
      editText.setOnEditorActionListener(new OnEditorActionListener() {
          @Override
          public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
              boolean handled = false;
              if (actionId == EditorInfo.IME_ACTION_DONE) {
                  //the below is the only line you have to understand
                  //we get to this point if the user clicked on the 'Done' key on the keyboard when typing text into the textbox
                  //it calls the processGuess method inside our CPSC112.java file, sending whatever the user typed into the textbox as a String parameter to the method
                  TextView t = (TextView) findViewById(R.id.textOut);
                  if (CPSC112_Assignment3.isGameOver(v.getText().toString())) {
                     System.out.println("Let's play again!!");
                     CPSC112_Assignment3.makeMySecret();
                  }
                  v.setText("");
                  handled = true;
              }
              return handled;
          }
      });
      System.out.print("");
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
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
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
}
