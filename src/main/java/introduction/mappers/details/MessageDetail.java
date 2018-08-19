package introduction.mappers.details;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jherrera
 */
public class MessageDetail {
    
    private String subject;
    private String message;

    /*public MessageDetail(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }*/

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subjext) {
        this.subject = subjext;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}