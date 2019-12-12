# GymInventory
Assignment Instructions: Make an application which is used to keep stock of all gym inventory.
The first activity has a list of 3 gym items eg (Treadmill, Dumbell, ExcerciseBike) displayed in a ListView and another ListView below the first displaying the current inventory eg ("5 Dumbells(15lbs) ")with both lists using an ArrayAdapter, once clicked a  second activity opens which allows the user to insert the quantity of the items being inserted. When the user inserts an item the app navigates back to the first application displaying the added item in the inventory List. 
- App consists of 2 ListViews
- SharedPreferences
- Two activities

Functionality: User clicks on what item they want to purchase from the top list view. The user is taken to a second activity that allows them to enter the number of units they wish to purchase. After the user clicks save the application goes back to the main activity where the type of item along with the number of units wanting to be purchased is diplayed on the bottom list view.

Implementation: Used two list views to display the both arrays of information on the maina ctivity. Implemented onClickListener to each item so that when clicked ot goes to the second activity bringning with it the name of the item using intents. Set the text view of the second application to the name of the clicked item using getIntent. and return to the first activity after the user has entered a number. This number is added to the end of the string of the item name and returned to the first activity using intents calling the finish() method. In the first method onActivityResult adds the new string to an array list of purchased goods. Then the application updates the second list view. For saving the data the application converts the array list to a string and saves it using shared preferances. When the application is restarted the application convertts from string to array list.
