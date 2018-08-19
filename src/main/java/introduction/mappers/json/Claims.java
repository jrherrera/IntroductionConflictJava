package introduction.mappers.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 *
 * @author jherrera
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Claims {
    
    private Long id;
    private String status;
    private String stage;
    private String resource;
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getResource() {
        return resource;
    }

    
}
