package edu.yale.cpsc112_assignment3.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;




import android.content.Context;
import android.content.SharedPreferences;

public class EntryItemSource {
	
	private static final String PREFKEY = "notes";
	private SharedPreferences notePrefs;
	
	public SharedPreferences returnPrefs()
	{
		return notePrefs;
	}
	
	public EntryItemSource(Context context)
	{
		notePrefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
		
	}
	
	public List<EntryItem> findAll()
	{
		
		/*
		List<NoteItem> noteList = new ArrayList<NoteItem>();
		NoteItem note = NoteItem.getNew();
		noteList.add(note);
		return noteList;
		 */
		Map<String, ?> entriesMap = notePrefs.getAll();
		
		SortedSet<String> keys = new TreeSet<String>(entriesMap.keySet());
		
		List<EntryItem> entryList = new ArrayList<EntryItem>();
		for(String key: keys)
		{
			EntryItem entry = new EntryItem();
			entry.setKey(key);
			entry.setValue((String) entriesMap.get(key));
			entryList.add(entry);
			
		}
		
		return entryList;
	}
	
	public boolean update(EntryItem entry)
	{
		SharedPreferences.Editor editor = notePrefs.edit();
		editor.putString(entry.getKey(), entry.getValue());
		editor.commit();
		
		return true;
	}
	
	public boolean remove(EntryItem entry)
	{
		if(notePrefs.contains(entry.getKey()))
		{
			SharedPreferences.Editor editor = notePrefs.edit();
			editor.remove(entry.getKey());
			editor.commit();
		}
		return true;
	}

}
