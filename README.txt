PayMeBack
Julie Chang, Anthony Amadeo, Andrew Schmidt, Nicole Tsai, Megan Wilson
CPSC112

(1)    What is the app you built?
This app keeps track of money owed to you by friends and sends them a gentle text reminder in 24 hours to pay you back! It is a flexible method of tracking money when a friend cannot Venmo you the money or does not have cash on hand.  


(2)    How is it supposed to work?
On the homescreen, there are five input functions-- Your name, Recipient name, Recipient number, dollar amount recipient owes, and notes.  When you fill out these fields appropriately and press send, the a text message will be sent to the recipient  number that says, “Hey [Recipient name] You owe [Your name] [dollar amount]. It’s for [notes].” This text message will be sent in 24 hours-- for the purpose of our demo, we just set it for one minute.

When the text is sent, the app will also store the information in a text file with the Recipient’s name and the amount owed to the user. If this is filled out over time, a sum of the amount owed to the user will be summed. For example, if John owes Jessica $10.00 for apples and then $20.00 for oranges, the app will save the amount owed as $30. If John returns the $30, then Jessica can delete this amount owed from the app itself. The amount John owed to Jessica will $0.00.

(3)    What actually works?
We have a working home screen that accounts for errors in the user inputs. For example, if the person does not put in a recipient name, the app will say “ERROR: Please select a recipient.” We have several error messages that account for most issues regarding the input field in the main screen. Right now, we have seven different errors accounted for, which should catch most errors.

If there is no error, a toast that says “Submitted!” will pop-up and the app will send a delayed text with the string “Hey [Recipient name] You owe [Your name] [dollar amount]. It’s for [notes].”  The receiver will see this as a text from the user’s phone number. NOTE: This does not work in an emulator. This only works on an actual phone.

The app can store data in the phone and then comb through that data to sum up balance sheets for each individual. The app can take and process entries. Deciding what type of data-storing system would be best for our app was a very time-consuming process that took several re-writes of the code. The first attempt at persistent data storage was to create a SQLite database and store values in there.  This turned out to be too complicated and beyond the scope of the project, so it was soon abandoned.  The next attempt was by using .txt files to store a list of all the entries and then another text file with all the people that have a balance.  This method involved handling exceptions on nearly every line, a method that was very very very briefly discussed in class (even though it’s actually super valuable in a real coding setting).  You can look at the DatabaseInteraction.class file to see the horrible mess that became.  Final, looking through Lynda.com tutorials, we stumbled across the Shared Preferences persistent storage method, a method that allowed for easy storage and access of key value pairs, exactly what was needed.  

The storage works currently by adding a unique key value pair in the form of “Name + Unique ID: $ Amount”.  The key value pairs are then sorted alphanumerically in the Who Owes You? activity.  There is currently no way to remove values.

To delete entry, right now we would have to enter the negative amount returned. For example, if John returned the $30.00 that he owed Jessica for the fruits, then Jessica will have to input -$30.00 under John’s name for now. Of course, this would be refined for the future, where the user can just delete the amount owed from the Amount Owed? screen.

We also created three .xml files-- one for the main screen, one for what the amount owed screen would look like, and one for an about page. We were also able to have a customized interface with a green background and a green action bar. We successfully created a menu that the user can switch onto different pages for. We also have an extra “About page” xml that we wrote, but was not included into the menu yet. We also customized the interface with various fields the user can input and spacing it into an aesthetically pleasing menu page. We also created a nifty icon!

(4) What we can do in the future?

- Make the balance sheets for each individual interactive, where the user can just delete them by clicking an X instead of entering a negative number.
- We could, like venmo, interface with facebook also so you can connect with your friends online and create a network
- We could have something display “past activity” for each individual. 
- We could allow for the user to select the recipient using his or her contacts list in the phone
- We could have a customizable text delay time, as well as a way to prevent a text message from being sent
- Allow the app to automatically sum the amount owed to the person