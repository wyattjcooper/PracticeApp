# PracticeApp
Practice with Android Studio.
Currently implements a number picker with a text field that will change if you pick the correct random number.  Now will write your correct pick to a Firebase and has an editable text field with a button that when pressed, will send the value in the text field to Firebase.  Then, a different text field on the screen will listen to Firebase and update itself to that value.  
Now, when a name is entered in the text field and a button is pressed, the app sends the user to a new page where they can enter a date and some text.  The name, date, and text they entered will be pushed to Firebase as a child of "entries" with a unique ID.  
