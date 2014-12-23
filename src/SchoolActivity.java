/** SchoolActivity class (Sub-class of Activity Class)
 *
 * @author Brayden Cowell
 * A class for representing and comparing school activities.
 */

public class SchoolActivity extends Activity
{
    public SchoolActivity(String type, String title, Time startingTime, Time endingTime, String comment)
    {
        super(type, title, startingTime, endingTime, comment);
    }
    
    /**
     * Get the type of Activity Type
     */
    public String getActivityType()
    {
        return "School";
    }
     
}
