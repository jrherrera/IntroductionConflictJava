package introduction.mappers.details;

import java.util.List;

/**
 *
 * @author jherrera
 */
public class ClaimDetailMiddleend {

    private final String title;
    private final List<MessageDetail> messages;
    private final String milestone;
    private final List actions;

    public ClaimDetailMiddleend(String title, List<MessageDetail> messages, String milestone, List actions) {
        this.title = title;
        this.messages = messages;
        this.milestone = milestone;
        this.actions = actions;
    }

    public String getTitle() {
        return title;
    }
    
    public List<MessageDetail> getMessages() {
        return messages;
    }
    
    public String getMilestone() {
        return milestone;
    }
    
    public List getActions() {
        return actions;
    }
    
}