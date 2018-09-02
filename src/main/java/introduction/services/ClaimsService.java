package introduction.services;

import introduction.mappers.details.OrdersListMiddleend;
import introduction.mappers.json.*;
import introduction.mappers.details.ClaimDetailMiddleend;
import introduction.mappers.details.MessageDetail;
import introduction.utils.RestService;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static introduction.mappers.utils.Constants.*;
import static introduction.mappers.utils.StringToObjectMapper.*;
import static introduction.mappers.utils.Utils.*;

/**
 *
 * @author jherrera
 */
public class ClaimsService {

    //vendedor : 344672346	TETE3864003	qatest2015
    //comprador: 344672287	TT114983	qatest6065
    private static final Logger log = LoggerFactory.getLogger(ClaimsService.class);

    private static final List<String> ACTIONS = Arrays.asList("send_message", "opend_dispute");

    public static OrdersListMiddleend getOrdersDetail(String claimId, String accessToken) throws Exception {

        String stringOrdersList = RestService.get(String.format(ORDERS_URI, claimId, accessToken));

        OrdersList ordersList = mapperOrdersListToObject(stringOrdersList);
        OrdersListMiddleend ordersListMiddleend = new OrdersListMiddleend();
        List<OrdersListMiddleend.Orders> orderResult = new ArrayList();

        for (OrderResult order : ordersList.getResults()){
            List<String> claimsIds = new ArrayList<>();
            List<String> claimsList = new ArrayList<>();
            List<Map<String, String>> itemsList = new ArrayList<>();

            order.getMediations().forEach( claim -> {
                claimsList.add(String.format("/frotend/claim/%s",claim.getId()));
            });

            for (OrderItems odr: order.getOrderItems()) {
                HashMap<String, String> it = new HashMap<>();
                it.put("title",odr.getItem().getTitle());

                String stringItem = RestService.get(String.format(ITEM_URI, odr.getItem().getId()));
                Item item = mapperItemToObject(stringItem);
                it.put("link",item.getThumbnail());

                itemsList.add(it);
            }

            orderResult.add(ordersListMiddleend.new Orders(claimsList,itemsList));
        }

        ordersListMiddleend.setOrders(orderResult);
        return ordersListMiddleend;

    }

    public static ClaimDetailMiddleend getClaimDetail(String claimId, String accessToken) throws Exception {

        String messages = RestService.get(String.format(MESSAGES_URI, claimId, accessToken));
        List<Message> listMessages = mapperMessageToObject(messages);

        String claim = RestService.get(String.format(CLAIM_URI, claimId, accessToken));
        Claims claimObject = mapperClaimsToObject(claim);

        Long payerId = getPayerId(claimObject, claimObject.getResource());
        Long collectorId = getCollectorId(claimObject, claimObject.getResource());

        String payer = RestService.get(String.format(USER_URI, payerId));
        String collector = RestService.get(String.format(USER_URI, collectorId));

        Users payerObject = mapperUsersToObject(payer);
        Users collectorObject = mapperUsersToObject(collector);

        List<MessageDetail> messageDetail = new ArrayList<>();
        listMessages.forEach(mess -> {
            String subject = mess.getSenderRole() == "complainant" ? payerObject.getNickname() : collectorObject.getNickname();
            subject = String.format("%s  -  %s",subject, parseDateString(mess.getDateCreated()));
            MessageDetail text = new MessageDetail();
            text.setMessage(mess.getMessage());
            text.setSubject(subject);
            messageDetail.add(text);
        });

        String milestone = getMillestone(claimObject.getStatus(), claimObject.getStage());
        ClaimDetailMiddleend claimDetailMiddleend = new ClaimDetailMiddleend(String.format("Reclamo n.º %s", claimId), messageDetail, milestone, ACTIONS);
        return claimDetailMiddleend;
    }

    public static ClaimDetailMiddleend postMessage(String claimId, String message, String accessToken) throws Exception {

        String claim = RestService.get(String.format(CLAIM_URI, claimId, accessToken));
        Claims claimObject = mapperClaimsToObject(claim);
        if ( !(claimObject.getStage().equals("claim")
                && claimObject.getStatus().equals("opened")) ) {
            throw new Exception("Invalid status to post message");
        }

        String json = String.format(MESSAGE_JSON, message);
        RestService.post(String.format(MESSAGES_URI, claimId, accessToken), json);
        ClaimDetailMiddleend claimDetailMiddleend = getClaimDetail(claimId, accessToken);
        return claimDetailMiddleend;
    }

    public static ClaimDetailMiddleend openDispute(String claimId, String accessToken) throws Exception {

        String claim = RestService.get(String.format(CLAIM_URI, claimId, accessToken));
        Claims claimObject = mapperClaimsToObject(claim);

        if ( !(claimObject.getStage().equals("claim")
                && claimObject.getStatus().equals("opened")) ) {
            throw new Exception("Invalid status to open dispute");
        }

        RestService.put(String.format(CLAIM_URI, claimId, accessToken), STAGE_DISPUTE_JSON);
        ClaimDetailMiddleend claimDetailMiddleend = getClaimDetail(claimId, accessToken);
        return claimDetailMiddleend;
    }
}