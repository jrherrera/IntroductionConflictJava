package introduction.mappers.details;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class OrdersListMiddleend {

    private List<Orders> orders;

    public OrdersListMiddleend(List<Orders> orders) {
        this.orders = orders;
    }

    public OrdersListMiddleend() {

    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public class Orders {

        public Orders(List<String> claimsList, List<Map<String, String>> itemsList) {
            this.claimsList = claimsList;
            this.itemsList = itemsList;
        }

        @JsonProperty("claims_list")
        private List<String> claimsList;
        @JsonProperty("items_list")
        private List<Map<String, String>> itemsList;

        public Orders() {

        }

        public List<String> getClaimsList() {
            return claimsList;
        }

        public void setClaimsList(List<String> claimsList) {
            this.claimsList = claimsList;
        }

        public List<Map<String, String>> getItemsList() {
            return itemsList;
        }

        public void setItemsList(List<Map<String, String>> itemsList) {
            this.itemsList = itemsList;
        }
    }

}
