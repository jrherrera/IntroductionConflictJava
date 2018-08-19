package introduction.mappers.utils;

public class Constants {
    /* Links */
    public static final String CLAIM_URI = "https://api.mercadolibre.com/v1/claims/%s?access_token=%s";
    public static final String MESSAGES_URI = "https://api.mercadolibre.com/v1/claims/%s/messages?access_token=%s";
    public static final String USER_URI = "https://api.mercadolibre.com/users/%s";
    public static final String ITEM_URI  = "https://api.mercadolibre.com/items/%s";
    public static final String ORDERS_URI = "https://api.mercadolibre.com/orders/search?seller=%s&access_token=%s";

    /* JSON */

    public static final String MESSAGE_JSON = "{\"message\":\"%s\", \"receiver_role\": \"complainant\"}";
    public static final String STAGE_DISPUTE_JSON = "{\"stage\":\"dispute\"}";

}
