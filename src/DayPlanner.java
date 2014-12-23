import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** DayPlanner class
 *
 * @author Fei Song
 * @author Brayden Cowell (0844864)
 * @version 2.0 (Revised by Brayden Cowell)
 * @date Due Friday, November 7th, 2014
 * A class that adds and searches activities in an array list.
 * @info Assignment 2 - Object Oriented Programming CIS*2430
 */

public class DayPlanner
{
        
    public static final int MAX_SIZE = 20;

    public static final String[] types = new String[]{"home", "school", "other", "h", "s", "o"};

    /**
     * One combined ArrayList for home, school, and other activities and it's actual size
     */
    ArrayList<Activity> list = new ArrayList<>(MAX_SIZE);
    
    /**
     * HashMap for index of key words in titles
     */
    HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    /* Size of the Activities arraylist */
    int listSize = 0; 

    /*
    * Create a time object for the valid input
    */
    private Time getTime(String line)
    {
        String[] tokens = line.split("[ ,\n]+");
        if (tokens.length != 5)
            return (null);
	for (int i = 0; i < 5; i++ )
            if (!tokens[i].matches("[-+]?[0-9]+"))
		return null;
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        int hour = Integer.parseInt(tokens[3]);
        int minute = Integer.parseInt(tokens[4]);
        if (Time.timeOK(year, month, day, hour, minute))
            return new Time(year, month, day, hour, minute);
	else
            return (null);
    }
	

    /*
    * Add a valid home activity
    */
    private void addHomeActivity(HomeActivity home)
    {
        if (list.size() == MAX_SIZE)
                {
            System.out.println("List is full: Activity not added");
        }

        else
        { 
            list.add(home);
            listSize++;
        }
    }
 
    /*
    * Add a valid school activity
    */
    private void addSchoolActivity(SchoolActivity school)
    {
        if (list.size() == MAX_SIZE)
        {
            System.out.println("List is full: Activity not added");
        }
        else
        { 
            list.add(school);
            listSize++;
        }
    }
    
    /*
    * Add a valid other activity
    */
    private void addOtherActivity(OtherActivity other)
    {
        if (list.size() == MAX_SIZE)
        {
            System.out.println("List is full: Activity not added");
        }
        else
        { 
            list.add(other);
            listSize++;
        }
    }

    /** 
    * Create an activity from the input and add it to the appropriate list
    */
    public void addActivity(Scanner input)
    {
        String type = "";
	do 
        {
            System.out.print( "Enter an activity type (home, school, or other)> " );
            type = input.nextLine();
	} while (!matchedKeyword(type, types));
		
	System.out.print("Enter a title> ");
	String title = input.nextLine();
		
	Time startingTime = null;
        do
        {
            System.out.print( "Enter a starting time (year, month, day, hour, minute)> " );
            startingTime = getTime( input.nextLine());
	} while (startingTime == null);

	Time endingTime = null;
        do
        {
            System.out.print( "Enter an ending time (year, month, day, hour, minute)> " );
            endingTime = getTime( input.nextLine());
	} while (endingTime == null);

	String location = "";
	if (type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o"))
        {
            System.out.print( "Enter a location> ");
            location = input.nextLine();
	}
		
	System.out.print( "Enter a line of comment> ");
	String comment = input.nextLine();
	
        if (type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o"))
        {
            addOtherActivity(new OtherActivity(type, title, startingTime, endingTime, location, comment));
        }
        
        else if (type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h"))
        {
            addHomeActivity(new HomeActivity(type, title, startingTime, endingTime, comment));
        }
        
        else if (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s"))
        {
            addSchoolActivity(new SchoolActivity(type, title, startingTime, endingTime, comment));
        }
    }

    /* 
    * Check if a keyword is on a list of tokens
    */
    private boolean matchedKeyword(String keyword, String[] tokens)
    {
	for (int i = 0; i < tokens.length; i++) 
            if (keyword.equalsIgnoreCase(tokens[i]))
		return true;
	return false;
    }

    /*
    * Check if all keywords are in a string 
    */
    private boolean matchedKeywords(String[] keywords, String title)
    {
	String[] tokens = title.split( "[ ,\n]+" );
	for (int i = 0; i < keywords.length; i++) 
            if (!matchedKeyword(keywords[i], tokens))
		return false;
	return true;
    }

   /*
    * Search for all home activities that satisfy a search request
    */
    private void searchHomeActivities(Time startingTime, Time endingTime, String[] keywords)
    {
        for (int i = 0; i < listSize; i++) 
            if ((startingTime == null || list.get(i).getStartingTime().compareTo(startingTime) >= 0) &&
		(endingTime == null || list.get(i).getEndingTime().compareTo(endingTime) <= 0) &&
		(keywords == null || matchedKeywords(keywords, list.get(i).getTitle())))
		System.out.println(list.get(i)); 		
    }

    /*
    * Search for all school activities that satisfy a search request
    */
    private void searchSchoolActivities(Time startingTime, Time endingTime, String[] keywords)
    {
        for (int i = 0; i < listSize; i++) 
            if ((startingTime == null || list.get(i).getStartingTime().compareTo(startingTime) >= 0) &&
		(endingTime == null || list.get(i).getEndingTime().compareTo(endingTime) <= 0) &&
		(keywords == null || matchedKeywords(keywords, list.get(i).getTitle())))
		System.out.println(list.get(i)); 		
    }

    /*
     * Search for all other activities that satisfy a search request
    */
    private void searchOtherActivities(Time startingTime, Time endingTime, String[] keywords) {
	for (int i = 0; i < listSize; i++) 
            if ((startingTime == null || list.get(i).getStartingTime().compareTo(startingTime) >= 0) &&
		(endingTime == null || list.get(i).getEndingTime().compareTo(endingTime) <= 0) &&
		(keywords == null || matchedKeywords(keywords, list.get(i).getTitle())))
		System.out.println(list.get(i)); 		
	}

    /**
    * Gather a search request and find the matched activities in the related list(s)
    */ 
    public void searchActivities(Scanner input)
    {
	String type = "";
	do 
        {
            System.out.print("Enter an activity type (home, school, or other)> ");
            type = input.nextLine();
	} while (!type.isEmpty() && !matchedKeyword(type, types));
		
	Time startingTime = null;
	do 
        {
            System.out.print("Enter a starting time (year, month, day, hour, minute)> ");
            String line = input.nextLine();
            if (line.isEmpty()) 
		break;
            else
		startingTime = getTime(line);
            
	} while (startingTime == null);

	Time endingTime = null;
	do
        {
            System.out.print("Enter an ending time (year, month, day, hour, minute)> ");
            String line = input.nextLine();
            if (line.isEmpty()) 
                break;
            else
                endingTime = getTime(line);
            
	} while (endingTime == null);

 	System.out.print("Enter title keywords: ");
	String[] keywords = null;
	String line = input.nextLine();
	if (!line.isEmpty())
            keywords = line.split("[ ,\n]+");

	// search for matched activities
	System.out.println("Matched activities: ");
	if (type.isEmpty() || type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h"))
            searchHomeActivities(startingTime, endingTime, keywords);

        else if (type.isEmpty() || type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s"))
            searchSchoolActivities(startingTime, endingTime, keywords);

        else if (type.isEmpty() || type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o"))
            searchOtherActivities(startingTime, endingTime, keywords);
    }
	
    /**
     * Read saved activities from file
     */
    private void readFile(String fileName)
    {
        Scanner inputStream = null;
        DayPlanner planner = new DayPlanner();
        String a;
        
        String type = null;          // Type of activity
        String title = null ;        // short description for the activity
        Time startingTime = null;   // starting time of the activity
        Time endingTime = null;     // ending time of the activity
        String comment = null;      // additional details about the activity
        String location = null;    // location of the activity
        fileName = "savedActivities.txt";
        
        /* Take name of file to import from - leave blank for none */

        
        /* Read Activities from file */
        try
        {
            inputStream = new Scanner (new FileInputStream(fileName));
            while(inputStream.hasNextLine())
            {
                a = inputStream.nextLine();

                if(a.equals(""))
                {
                    type = null;
                    title = null;
                    startingTime = null;
                    endingTime = null;
                    comment = null;
                    location = null;
                }
                
                else
                {
                    String[] parse = a.split(" ");
                    String element = parse[0];
                    /* parse[1] is the = sign so we skip that and take the information we need */

                    /* Parse information inside quotations - Code source http://stackoverflow.com/questions/1473155/how-to-get-data-between-quotes-in-java */
                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(a); 
                    while (m.find()) /* End of borrowed code - Code source http://stackoverflow.com/questions/1473155/how-to-get-data-between-quotes-in-java */
                    {
                        if(element.equals("type"))
                        {
                            type = m.group(1);
                        }

                        if(element.equals("title"))
                        {
                            title = m.group(1);
                        }

                        if(element.equals("start"))
                        {
                            startingTime = planner.getTime(m.group(1));
                        }

                        if(element.equals("end"))
                        {
                            endingTime = planner.getTime(m.group(1));
                        }

                        if(element.equals("location"))
                        {
                            location = m.group(1);
                        }

                        if(element.equals("comment"))
                        {
                            comment = m.group(1);  
                        }
                    }
                    

                    /* If next line is seperated by a space, then clear all values */

                    if(type.equalsIgnoreCase("home") && title != null && startingTime != null && endingTime != null && comment!= null)
                    {
                        addHomeActivity (new HomeActivity(type, title, startingTime, endingTime, comment));
                        System.out.println("HomeActivity loaded from file.");
                    }

                    if (type.equalsIgnoreCase("school") && title != null && startingTime != null && endingTime != null && comment!= null)
                    {
                        addSchoolActivity (new SchoolActivity(type, title, startingTime, endingTime, comment));
                        System.out.println("SchoolActivity loaded from file.");
                    }

                    if (type.equalsIgnoreCase("other") && title != null && startingTime != null && endingTime != null && location != null && comment != null)
                    {
                        addOtherActivity (new OtherActivity(type, title, startingTime, endingTime, location, comment));
                        System.out.println("OtherActivity loaded from file.");
                    } 
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("File not found or cannot be read beacuse there may be an error. Please read ReadMe.txt.");
            System.exit(0);
        }
        inputStream.close(); 
    }
    
    /**
     * Store all activities in the Array List into the same text file
     */
    private void writeFile()
    {
        PrintWriter outputStream = null;
        String fileName = "savedActivities.txt";
        
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(fileName));
        }
        
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error writing to " + fileName);
        }
        
        /* Write Activities to file */
        for(int i = 0; i < list.size(); i++)
        {
            outputStream.printf("type = \"%s\"\n", list.get(i).getActivityType());
            outputStream.printf("title = \"%s\"\n", list.get(i).getTitle());
            outputStream.printf("start = \"%s\"\n", list.get(i).FileStartTime());
            outputStream.printf("end = \"%s\"\n", list.get(i).FileEndTime());
                    
            if(list.get(i).getActivityType().equalsIgnoreCase("Other"))
            {
                outputStream.printf("location = \"%s\"\n", list.get(i).getLocation());
            }
            outputStream.printf("comment = \"%s\"\n", list.get(i).getComment());
            
            if (i+1 == list.size()) /* At last activity to write */
            {
                outputStream.close();
            }
            
            else
            {
                outputStream.printf("%n");
            }
        }
        
    }
    
    
    /**
    * After adding an activity to the array list - we then have to index it's title to the Hash Map
    */
    public void UpdateHashMap(String title)
    {
        String[] parse = null;
        String temp;
        /* Go through the ArrayList */
        for(int i = 0; i < list.size(); i++)
        {      
            temp = list.get(i).getTitle(); /* Grab the temp title in list */
            
            if(temp.equalsIgnoreCase(title))/* if the title you want to add already exists don't add another */
            {
                System.out.println(title + " already exists in the HashMap index.");
                break;
            }
            
            else
            {
                parse = title.split(" "); /* Parse them, so we only have single words */
                /* Add keyword to HashMap */
                for (int j = 0; j < parse.length; j++) /* Take each word and put it into the hashmap at index */
                {
                    String key = parse[j];
                    ArrayList<Integer> entry = map.get(key);
                    if(entry == null)
                    {
                        entry = new ArrayList<Integer>();
                        map.put(key, entry);
                    }
                    entry.add(i);
                }
            }
        }
    }
    
    /**
     * Creates the initial Hash Map from the saved activities on the file
     */
    public void MakeHashMap()
    {
       String[] parse = null;
       String title = null;
        
        /* Go through the ArrayList */
        for(int i = 0; i < list.size(); i++)
        {      
            title = list.get(i).getTitle(); /* Grab all the titles */
            parse = title.split(" "); /* Parse them, so we only have single words */
            
            /* Add keyword to HashMap */
            for (int j = 0; j < parse.length; j++) /* Take each word and put it into the hashmap at index */
            {
                String key = parse[j];
                ArrayList<Integer> entry = map.get(key);
                if(entry == null)
                {
                    entry = new ArrayList<Integer>();
                    map.put(key, entry);
                }
                entry.add(i);
            }
        } 
    }
    
    /**
     * MAIN
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        DayPlanner planner = new DayPlanner();
        String command;
        String fileName = "";
        
        if(args.length == 1) /* Check if user entered filename into command line */
        {
            fileName = args[0];
        }
        
        else /* If they didn't enter a file name print error and exit */
        {
            System.out.println("Invalid amount of arguments. Please type file.txt after the run command.");
            System.exit(0);
        }
        
        planner.readFile(fileName); /* Load saved activities from file specified */
        planner.MakeHashMap();  /* Create the HashMap from the newly loaded Array List */
        
	do  /* Command Loop */
        {
            System.out.print("Enter add, search, or quit> ");
            command = input.nextLine();
            if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a"))
                planner.addActivity(input);
            else if (command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s"))
		planner.searchActivities(input);			
	} while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q"));
                
        planner.writeFile();  /* After program is finished store activity data on file */
    }
}
