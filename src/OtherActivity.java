/** OtherActivity class (Sub-class of Activity Class)
 * @author Brayden Cowell
 * A class for representing and comparing other activities.
 */

public class OtherActivity extends Activity 
{
    public String location;
    
    public OtherActivity(String type, String title, Time startingTime, Time endingTime, String location, String comment)
    {
        super(type, title, startingTime, endingTime, comment);
        this.location = location;
    }
	
    /**
     * Get the type of Activity Type
     */
    public String getActivityType()
    {
        return "Other";
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
    
    /**
    * Overload Check for equality of two other activities
    */
    public boolean equals(OtherActivity other)
    {
	if (other == null)
            return false;
	else 
            return title.equals(other.title) &&
            startingTime.equals(other.startingTime) &&
            endingTime.equals(other.endingTime) &&
            location.equals(other.location) &&
            comment.equals(other.comment);
    }
	
}
