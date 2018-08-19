package introduction.mappers.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import introduction.mappers.json.OrderResult;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersList {
    private List<OrderResult> results;

    public List<OrderResult> getResults() {
        return results;
    }

    public void setResults(List<OrderResult> results) {
        this.results = results;
    }
}