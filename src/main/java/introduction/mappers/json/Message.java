package introduction.mappers.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jherrera
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    
    //String attachments;
    @JsonProperty("sender_role")
    String senderRole;
    @JsonProperty("receiver_role")
    String receiverRole;
    @JsonProperty("message")
    String message;
//  @JsonProperty("stage")
//  String stage;
    @JsonProperty("date_created")
    String dateCreated;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public String getReceiverRole() {
        return receiverRole;
    }
}