package introduction.mappers.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jherrera
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    private String role;
    private String type;
    @JsonProperty("user_id")
    private Long userId;

    public String getRole() {
        return role;
    }

    public String getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }

}
