import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * EventManager manages a list of events and a separate list of volunteers.
 * 
 * IMPLEMENT THE METHODS OF THIS CLASS
 * 
 * It also provides methods for adding matches between events and volunteers
 * and for displaying the events and volunteers that exist.
 * 
 * An EventManager instance manages the list of events and volunteers.
 */
public class EventManager {
	
	/** the list of events */
	private List<Event> eventList;

	/** the list of volunteers */
	private List<Volunteer> volunteerList;

	/**
	 * Constructor for an EventManager instance
	 */
	public EventManager(){
		this.eventList = new ArrayList<Event>();
		this.volunteerList = new ArrayList<Volunteer>();
	}
	
	/**
	 * Adds an event to the list of events or returns false if the details for the event are not valid.
	 * This maintains the event list in sorted order (sort is ascending by name only).
	 * 
	 * Tip: Collections.sort can be used after a new event is added.
	 * 
	 * The following conditions result in no event being added and false being returned
	 * <ul>
	 * <li>name is null or an empty string "".</li>
	 * <li>date is not an integer in range 1 to 30, inclusive.</li>
	 * <li>the event name already exists (duplicate event names are not allowed)</li>
	 * <li>the volunteer limit is less than one</li>
	 * </ul>
	 * 
	 * @param name the name of a new event
	 * @param dateStr the string for the date of this event
	 * @param limitStr the string for the volunteers limit in this event
	 * @return true if arguments have valid format and added event successfully, otherwise false
	 */
	public boolean addEvent(String name, String dateStr, String limitStr){
		int dateStart = Integer.parseInt(dateStr);
		int volnlimit = Integer.parseInt(limitStr);
		
		
		//if name null return
		if(name == null){
			return false;
		}
		name = name.toLowerCase();
		
		
		//return false if start date out of range
		if(dateStart > 30 || dateStart <= 0 || volnlimit < 0)
		return false;
		
		//check for duplicates
		if(findEvent(name) != null){
			return false;
		}
			
		//if passed checks add new event
		Event e = new Event(name,dateStart, volnlimit);
		eventList.add(e);
		return true;
		
	}
	
	
		
	/**
	 * Adds a new volunteer to the list of volunteers or returns false.
	 * Maintains the volunteer list in sorted order.  
	 * 
	 * Tip: Collections.sort can be used after a new volunteer is added.
	 * 
	 * <ul>
	 * <li>Name must not be null or empty string</li>
	 * <li>Volunteer name must not be a duplicate.</li>
	 * </ul>
	 *
	 * @param name the name of a new volunteer
	 * @param availableDatesStrAry a String array that has date strings
	 */
	public boolean addVolunteer(String name, String[] availableDatesStrAry){
		//if name null return
		if(name == null){
			return false;
		}
		name = name.toLowerCase();
		
		//check for duplicates
		if(findVolunteer(name) != null){
			return false;
		}
		
		//check if available  dates are in the right range
		for(int i = 0; i < availableDatesStrAry.length; i++){
			if(Integer.parseInt(availableDatesStrAry[i]) < 1 || Integer.parseInt(availableDatesStrAry[i]) > 30){
				return false;
			}
		}
		
		//create new Array list to add
		List<Integer> L = new ArrayList<Integer>();
		for(int i = 0; i < availableDatesStrAry.length; i++){
			
			L.add(Integer.parseInt(availableDatesStrAry[i]));
		}
		
		Volunteer v = new Volunteer(name, L);
		volunteerList.add(v);
		return true;
	}
	
	/** 
	 * USED ONLY IF AN EVENT NEEDS TO BE REMOVED WHILE READ FROM FILE
	 * 
	 * Iterates through the event list and remove the event if event exists. 
	 * This method must also remove all the event-volunteer matches corresponding to this event.
	 * 
	 * @param name the name of the event to be removed
	 * @return true if the event existed and removed successfully, otherwise false
	 */
	public boolean removeEvent(String name) {
		//TODO: implement this method
		return true;
	}
	
	/**
	 * Iterates through the volunteer list and removes the volunteer if volunteer exists. 
	 * Also removes all the event-volunteer matches corresponding to this volunteer
	 * 
	 * @param name the name of the volunteer to be removed
	 * @return true if volunteer existed and removed successfully, otherwise false
	 */
	public boolean removeVolunteer(String name){
		// TODO: implement this method
		return false;
	}
	
	/**
	 * Given the event name,check if the event exists in the event list. 
	 * 
	 * @param name the name of the event to be found
	 * @return event if the event exists, otherwise null.
	 */
	public Event findEvent(String name){
		//check for duplicates
		for(int i = 0; i < eventList.size(); i ++){
			if(eventList.get(i).getName().trim().toLowerCase().equals(name.trim().toLowerCase())){
				return eventList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Return the volunteer with the given name.
	 * 
	 * @param name the name of the volunteer
	 * @return volunteer if the volunteer exists, otherwise null.
	 */
	public Volunteer findVolunteer(String name){
		for(int i = 0; i < volunteerList.size(); i ++){
		if(volunteerList.get(i).getName().trim().toLowerCase().equals(name.trim().toLowerCase())){
			return volunteerList.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * This method is used to create a match between an event and a volunteer.
	 * 
	 * <ol>
	 * <li>Find the event and the volunteer from their names.</li>
	 * <li>If either is null, return false.</li>
	 * <li>If event has not reached volunteer limit and volunteer has the event's date in its availability list, then
	 *     <ol><li>add the volunteer node to the event's adjacency list</li>
	 *     <li>add the event to the volunteer's list</li>
	 *     <li>set the availability date for the volunteer to false</li>
	 *     </ol>
	 * </li>
	 * <li>return true if all is well</li>
	 * </ol> 
	 * 
	 * @param eventName the name of an event to be matched to a volunteer
	 * @param volunteerName the name of a volunteer to be matched to a event
	 * @return true if the match is created, otherwise false.
	 */
	public boolean createMatch(String eventName, String volunteerName){
		if(eventName == null || volunteerName == null){
			return false;
		}
		
		// Initialize 
	//	boolean available = false;
	//	List<Integer> L = new ArrayList<Integer>();
		
		// Find the event and volunteer
		Event e = findEvent(eventName);
		Volunteer v = findVolunteer(volunteerName);
		
		// If it cannot be found stop the method, return false
		if (e == null || v == null)
			return false;
		
		/*
		// Because we cannot access the available dates and the graphnode needs a List
		// parameter, we will parse through all dates and create a new list of available
		// dates for the volunteer
		for (int i = 1; i <= 30; i++)
		{
			if (v.isAvailable(i))
				available = true;
			else
				available = false;
			
			if (available)
				L.add(i);
			
		} */
		
		
		
		// Check event that is has not reached volunteer limit 
		// and volunteer is available that day
		if (e.isBelowLimit() && v.isAvailable(e.getDate()))
		{
		// Create a new volunteer graphNode
		GraphNode gNode = v;
		
		// Add volunteer node to event's adj list
		e.addAdjacentNode(gNode);
		
		// Add event to volunteer's adj list
		v.addAdjacentNode(e);
		
		v.setUnavailable(e.getDate());
		
		return true;
		}
		
		return false;
	}
	
	/**
	 * Given the event and volunteer, remove the match between them if it exists.
	 * Return true if the match is found and removed.
	 * 
	 * If a match is found:
	 * 
	 * <ul>
	 * <li>remove the volunteer from the event's volunteer list</li>
	 * <li>remove the event from volunteer's event list</li>
	 * <li>set the event's date to available for the volunteer</li>
	 * <li>return true if all is well</li>
	 * </ul>
	 * 
	 * @param eventName the name of an event to be removed from match
	 * @param volunteer the name of a volunteer to be removed from match
	 * @return true if the match existed and removed successfully, otherwise false.
	 */
	public boolean removeMatch(String eventName, String volunteerName){
		
		return true;
	}
	
	/**
	 * This method is used to display all the events along 
	 * with corresponding matches with the volunteers.
	 * Check sample files for exact format of the display.
	 * 
	 * Utilize formats defined in the Resource class
	 * to display in correct format.
	 * 
	 * Resource.STR_ERROR_DISPLAY_EVENT_FAILED
	 * Resource.STR_DISPLAY_ALL_EVENTS_PRINT_FORMAT
	 */
	public void displayAllEvents(){
		System.out.println(Resource.STR_DISPLAY_ALL_EVENTS_PRINT_FORMAT);

		Iterator itr = eventList.iterator();
		
		if (!itr.hasNext())
			System.out.println(Resource.STR_ERROR_DISPLAY_EVENT_FAILED);
		else
		{
			while (itr.hasNext())
			{
				Event e = (Event) itr.next();
				System.out.println("-Name: " + e.getName());
				System.out.println(" Date: " + e.getDate());
				System.out.println(" Maximum number of volunteers: " + e.getLimit());
				System.out.println(" Matched Volunteer(s): ");
				e.getAdjacentNodes();
			}
		}
	}
	
	/**	 
	 * This method is used to display all the volunteers along 
	 * with corresponding matches with the events.
	 * Check sample files for exact format of the display.
	 * 
	 * Utilize formats defined in the resource file for 
	 * display in the correct format.
	 * 
	 * Resource.STR_ERROR_DISPLAY_VOLUNTEER_FAILED
	 * Resource.STR_DISPLAY_ALL_VOLUNTEERS_PRINT_FORMAT
	 */
	public void displayAllVolunteers(){
		
		System.out.println(Resource.STR_DISPLAY_ALL_VOLUNTEERS_PRINT_FORMAT);

		Iterator itr = volunteerList.iterator();
		
		if (!itr.hasNext())
			System.out.println(Resource.STR_ERROR_DISPLAY_VOLUNTEER_FAILED);
		else
		{
			while (itr.hasNext())
			{
				Volunteer v = (Volunteer) itr.next();
				System.out.println("-Name: " + v.getName());
				System.out.print(" Available: ");
				for (int i = 1; i < 30; i++)
				{
					if (v.isAvailable(i))
					{
						System.out.print(i +",");
					}
				}
				System.out.println();
				System.out.println(" Matched event(s):");
				System.out.println(v.getAdjacentNodes());
			}
		}
	}
	
	/**
	 * This is helper method to create a string for
	 * writing all the volunteers in a file.
	 * 
	 * (Example)
	 * <pre>
	 * v;Mingi;5,23,30
	 * v;Sonu;1,2,3,4,5
	 * </pre>
	 * 
	 * @return a single string object containing all the volunteers 
	 * in the format needed to be printed in the file.
	 */
	public String toStringAllVolunteers(){
		// TODO: implement this method
		return null;
	}
	
	/**
	 * This is helper method to create a string for
	 * writing all the events in a file.
	 * 
	 * (Example)
	 * e;Field trip;7
	 * e;Birthday;23;Mingi,Sonu
	 * 
	 * @return string containing all the events in the format
	 * needed to be printed in the file.
	 */
	public String toStringAllEvents(){
		// TODO: implement this method
		return null;
	}
}
