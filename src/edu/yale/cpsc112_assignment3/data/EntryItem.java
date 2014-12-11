package edu.yale.cpsc112_assignment3.data;


public class EntryItem {
	//Defines the EntryItem object, which contains a key value pair.
	
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public static EntryItem getNew() {
		
		String key = new String("TEST");
		
		EntryItem entry = new EntryItem();
		entry.setKey(key);
		entry.setValue("");
		return entry;
		
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
}
