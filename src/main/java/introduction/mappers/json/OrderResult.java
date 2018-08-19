package introduction.mappers.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResult {

    private List<OrderMediations> mediations;
    @JsonProperty("order_items")
    //@JsonIgnore
    private List<OrderItems> OrderItems;

    public List<OrderMediations> getMediations() {
        return mediations;
    }

    public void setMediations(List<OrderMediations> mediations) {
        this.mediations = mediations;
    }

    public List<OrderItems> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        OrderItems = orderItems;
    }
}
