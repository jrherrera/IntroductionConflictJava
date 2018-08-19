package introduction.mappers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import introduction.mappers.json.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StringToObjectMapper {

    public static Users mapperUsersToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Users user = objectMapper.readValue(json, Users.class);
        return user;
    }

    public static Claims mapperClaimsToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Claims claim = objectMapper.readValue(json, Claims.class);
        return claim;
    }

    public static Item mapperItemToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Item item = objectMapper.readValue(json, Item.class);
        return item;
    }

    public static List<Message> mapperMessageToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> messages = Arrays.asList(objectMapper.readValue(json, Message[].class));
        return messages;
    }

    public static OrdersList mapperOrdersListToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OrdersList ordersList = objectMapper.readValue(json, OrdersList.class);
        return ordersList;
    }

}
