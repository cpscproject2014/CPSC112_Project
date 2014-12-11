package edu.yale.cpsc112_assignment3;

import java.util.List;

import edu.yale.cpsc112_assignment3.data.EntryItem;
import edu.yale.cpsc112_assignment3.data.EntryItemSource;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity2 extends ListActivity {
	private EntryItemSource entrySource;
	List<EntryItem> entriesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#669900"));     
        ab.setBackgroundDrawable(colorDrawable);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		entrySource = new EntryItemSource(this);
		
		refreshDisplay();
	}
	private void refreshDisplay() {
		entriesList = entrySource.findAll();
		
		ArrayAdapter<EntryItem> adapter = new ArrayAdapter<EntryItem>(this, android.R.layout.simple_list_item_1, entriesList);
		setListAdapter(adapter);
		
		/*for(EntryItem entry: entriesList)
		{
			System.out.println("Name: " + entry.getKey() + " Amount: " + entry.getValue());
		}
		*/
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

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
}