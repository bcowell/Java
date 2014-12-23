 /** Activity Class
 * @author Brayden Cowell (0844864)
 * @date Due Friday, November 7th, 2014
 * A Super class that reduces re-written code for the three sub-classes (HomeActivity, SchoolActivity, and OtherActivity)
 * @info - Assignment 2 - Object Oriented Programming CIS*2430
 */
 
public class Activity 
{
    protected String type;          // Type of activity
    protected String title;        // short description for the activity
    protected Time startingTime;   // starting time of the activity
    protected Time endingTime;     // ending time of the activity
    protected String comment;      // additional details about the activity
    protected String location;    // location of the activity
    
    /**
    * Check the validity for a potential activity
    */
    public static boolean valid(Time startingTime, Time endingTime)
    {
	return (startingTime != null) && (endingTime != null);
    }

    /**
    * Set a new value for title
    */  
    public void setTitle(String title)
    {
	this.title = title;
    }
	
    /**
    * Set a new value for starting time
    */
    public void setStartingTime(Time startingTime)
    {
	if (startingTime == null)
        {
            System.out.println("Invalid starting time");
            System.exit(0);  
	} 
        else 
            this.startingTime = startingTime; 
    }
	
    /**
    * Set a new value for ending time
    */
    public void setEndingTime(Time endingTime) 
    {
	if (endingTime == null) 
        {
            System.out.println("Invalid ending time");
            System.exit(0);
	}
		this.endingTime = endingTime;
    }
	
    /**
    * Set a new value for comment
    */
    public void setComment(String comment)
    {
        this.comment = comment;
    }
	
    /**
    * Get the value of title
    */
    public String getTitle() 
    {
        return title;
    }
	
    /**
    * Get the value of starting time
    */
    public Time getStartingTime()
    {
        return startingTime;
    }
	
    /**
    * Get the value of ending time
    */
    public Time getEndingTime()
    {
        return endingTime;
    }
	
    /**
    * Get the value of comment
    */
    public String getComment()
    {
        return comment;
    }
    
    /**
    * Show the content of a school activity in a string
    */
    public String toString()
    {
        return title + ": " + startingTime + " to " + endingTime + ", " + comment;	
    }
    
    /**
    * Create a school activity with all the required fields
    */
    public Activity(String Type, String title, Time startingTime, Time endingTime, String comment)
    {
	if (valid(startingTime, endingTime))
        {
            this.type = type;
            this.title = title;
            this.startingTime = startingTime;
            this.endingTime = endingTime;
            this.comment = comment;
        } 
                
        else
        {			
            System.out.println("Invalid times for Activity");
            System.exit(0);
        }
    }

    /**
     * Get the type of Activity Type
     */
    public String getActivityType()
    {
        return "Activity";
    }
    
    /**
    * Set a new value for Activity Type
    */
    public void setActivityType(String type)
    {
        this.type = type;
    }
    
    
    /**
    * Check for equality of two school activities
    */
    public boolean equals(Activity other)
    {
	if (other == null)
            return false;
        
	else 
            return title.equals(other.title) &&
            startingTime.equals(other.startingTime) &&
            endingTime.equals(other.endingTime) &&
            comment.equals(other.comment);
    }
    
    /**
    * Set a new value for location
     */
    public void setLocation(String location)
    {
	this.location = location;
    }
	
    /**
    * Get the value of location
     */
    public String getLocation()
    {
	return location;
    }
    
     public String FileStartTime()
    {
        return startingTime.getYear() + ", " + startingTime.getMonth() + ", " + startingTime.getDay() + ", " + startingTime.getHour() + ", " + startingTime.getMinute();
    }
    
    public String FileEndTime()
    {
        return endingTime.getYear() + ", " + endingTime.getMonth() + ", " + endingTime.getDay() + ", " + endingTime.getHour() + ", " + endingTime.getMinute();
    }
 
}
