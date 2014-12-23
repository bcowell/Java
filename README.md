Brayden Cowell <br>
bcowell.uoguelph.ca <br>
Tuesday, Dec. 23rd, 2014
Java Code Examples
------------
- Assignment 2 Description
<img src="https://cloud.githubusercontent.com/assets/10282189/5541266/c86b9194-8aa3-11e4-924b-ea385d9b72a5.jpg">
<img src="https://cloud.githubusercontent.com/assets/10282189/5541265/c863a10a-8aa3-11e4-97af-a380393a1e87.jpg">

- Help
- ----------------------------------
To Load the File: Compile and run with an extra argument like: "savedActivites.txt"
---------------------------------------------------------------
Assumptions
-----------------------------------------------------------------
The file to save activities can be called what ever you want as long as it's a .txt.
Make sure to put the file inside of the A2 directory 
(So that you can see Folders (build, dist, nbproject, src) and files (savedActivites.txt)).
Note: There is only one file to load from and save to and as an example I have created a savedActivites.txt
Basic Layout of a file (Done using Notepad++ because Notepad doesn't organize properly all the time):
type = "Home"
title = "Call mom"
start = "2014, 11, 12, 9, 30"
end = "2014, 11, 12, 9, 45"
comment = "Ask about Christmas Break"

type = "School"
title = "Java class"
start = "2009, 10, 23, 12, 30"
end = "2009, 10, 23, 13, 20"
comment = "About inheritance"

type = "Other"
title = "lunch at Tim Horton's"
start = "2009, 10, 23, 1, 30"
end = "2009, 10, 23, 2, 23"
location = "Tim Horton near Stone Road Mall"
comment = "Get together with friends"
-----------------------------------------------------------------
Limitations
-----------------------------------------------------------------
Make sure there are blank lines separating the activities in the input text file.
Note: It doesn't matter if the last line has a newline after it or not.

I didn't fully finish the HashMap searching, however I did create the HashMap index and properly updated it after adding new
activities.
-----------------------------------------------------------------
Borrowed Code
---------------------------------------------------------------
I borrowed some code to partially parse some information between quotations.
Source: http://stackoverflow.com/questions/1473155/how-to-get-data-between-quotes-in-java
It is clearly labelled in my source-code under the function readFile in DayPlanner.java.

I also used Fei Song's example Assignment 1 - DayPlanner and built everything off of it after importing a few of my old functions.
New Functions I created:

/**
* Read saved activities from file
*/
private void readFile(String fileName)

/**
* Store all activities in the Array List into the same text file
*/
private void writeFile()

/**
* Creates the initial Hash Map from the saved activities on the file
*/
public void MakeHashMap()

/**
* After adding an activity to the array list - we then have to index it's title to the Hash Map
*/
public void UpdateHashMap(String title)

Also after creating the superclass for Home, School, and Other Activity very little had to remain in those subclasses.
The one Class I did not touch was your Time.java so all credit goes to Fei Song for that class.

--------------------------------------------------------------------
Example Outputs
--------------------------------------------------------------------
After loading file with this on it:

type = "Home"
title = "Call mom"
start = "2014, 11, 12, 9, 30"
end = "2014, 11, 12, 9, 45"
comment = "Ask about Christmas Break"

type = "School"
title = "Java class"
start = "2009, 10, 23, 12, 30"
end = "2009, 10, 23, 13, 20"
comment = "About inheritance"

type = "Other"
title = "lunch at Tim Horton's"
start = "2009, 10, 23, 1, 30"
end = "2009, 10, 23, 2, 23"
location = "Tim Horton near Stone Road Mall"
comment = "Get together with friends"

Program reads:
HomeActivity loaded from file.
School Activity loaded from file.
OtherActivity loaded from file.

Do a quick empty search:
Program reads:
Enter add, search, or quit> s
Enter an activity type (home, school, or other)> 
Enter a starting time (year, month, day, hour, minute)> 
Enter an ending time (year, month, day, hour, minute)> 
Enter title keywords: 
Matched activities: 
Call mom: 9:30, 11/12/2014 to 9:45, 11/12/2014, Ask about Christmas Break
Java class: 12:30, 10/23/2009 to 13:20, 10/23/2009, About inheritance
lunch at Tim Horton's: 1:30, 10/23/2009 to 2:23, 10/23/2009, Get together with friends
Enter add, search, or quit> 

Now let's add another activity:
Enter add, search, or quit> add
Enter an activity type (home, school, or other)> school
Enter a title> Math Midterm
Enter a starting time (year, month, day, hour, minute)> 2014 11 21 11 20
Enter an ending time (year, month, day, hour, minute)> 2014 11 21 1 50
Enter a line of comment> Make sure to study

Now if we do a search for math. The following shows up:
Enter add, search, or quit> search
Enter an activity type (home, school, or other)> 
Enter a starting time (year, month, day, hour, minute)> 
Enter an ending time (year, month, day, hour, minute)> 
Enter title keywords: math
Matched activities: 
Math Midterm: 11:20, 11/21/2014 to 1:50, 11/21/2014, Make sure to study

After quiting:
Enter add, search, or quit> quit

The file has now been overwritten with the new information.
The file now has:

type = "Home"
title = "Call mom"
start = "2014, 11, 12, 9, 30"
end = "2014, 11, 12, 9, 45"
comment = "Ask about Christmas Break"

type = "School"
title = "Java class"
start = "2009, 10, 23, 12, 30"
end = "2009, 10, 23, 13, 20"
comment = "About inheritance"

type = "Other"
title = "lunch at Tim Horton's"
start = "2009, 10, 23, 1, 30"
end = "2009, 10, 23, 2, 23"
location = "Tim Horton near Stone Road Mall"
comment = "Get together with friends"

type = "School"
title = "Math Midterm"
start = "2014, 11, 21, 11, 20"
end = "2014, 11, 21, 1, 50"
comment = "Make sure to study"
-------------------------------------------------------------
End of example output
-------------------------------------------------------------
