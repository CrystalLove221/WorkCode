
/**
 * @author Tyler Hogue
 * @version 2018.2.23
 *
 */
public class EventFinder {
    
    
    /**
     * constructor to set up instances of finder
     */
    public EventFinder() {
        //empty
    }
    
    /**
     * @param target event to be found in the list
     * @param events list of events to look in
     * @return index of matching event
     * (or index to insert new event)
     */
    public int find(HistoricEvent target, HistoricEvent[] events) {
        int start = 0;
        int end = events.length - 1;
        
        return find(target, events, start, end);
    }
    
    /**
     * @param target The event to look for
     * @param events a list of events
     * @param start the first event in List to
     * look at
     * @param end the last event in list to look
     * at
     * @return index target is located
     */
    
    public int find(HistoricEvent target, HistoricEvent[] events,
            int start, int end) {
        
        int mid = (start + end) / 2;
        
        if (start == end) {
            return mid; // return index of last event compared
        }
        
        else if (target.compareTo(events[mid]) == 1) {
            return find(target, events, mid + 1, end);
        }
        
        else if (target.compareTo(events[mid]) == -1) {
            return find(target, events, start, mid - 1);
        }
        
        return mid; // return when target is found
        
    }
}