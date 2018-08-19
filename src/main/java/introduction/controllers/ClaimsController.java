package introduction.controllers;

import introduction.mappers.details.MessageDetail;
import introduction.mappers.details.ClaimDetailMiddleend;
import introduction.mappers.details.OrdersListMiddleend;
import introduction.mappers.json.OrdersList;
import introduction.services.ClaimsService;

import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author jherrera
 */
@RestController
public class ClaimsController {

    private static final Logger log = LoggerFactory.getLogger(ClaimsController.class);

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping(value = "/claims/{claimId}/detail")
    public ClaimDetailMiddleend getClaimDetail(@PathVariable("claimId") String claimId, @RequestParam("access_token") String accessToken) throws Exception {
        ClaimDetailMiddleend claimDetailMiddleend = ClaimsService.getClaimDetail(claimId, accessToken);
        return claimDetailMiddleend;
    }

    @GetMapping(value = "/orders/sellers/{sellerId}")
    @ResponseBody
    public OrdersListMiddleend getOrdersDetail(@PathVariable("sellerId") String claimId, @RequestParam("access_token") String accessToken) throws Exception {
        OrdersListMiddleend ordersListMiddleend = ClaimsService.getOrdersDetail(claimId, accessToken);
        return ordersListMiddleend;
    }

    @PostMapping(value = "/claims/{claimId}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClaimDetailMiddleend createMessage(@PathVariable("claimId") String claimId,
                                              @RequestParam("access_token") String accessToken,
                                              @RequestBody MessageDetail messageBody) throws Exception {

        //*Estoy tirando un 500. Cambiarlo por 400*//
        Preconditions.checkNotNull(messageBody.getMessage(), "Parameter message can't be null");
        ClaimDetailMiddleend claimDetailMiddleend = ClaimsService.postMessage(claimId, messageBody.getMessage(), accessToken);
        return claimDetailMiddleend;
    }

    @PostMapping(value = "/claims/{claimId}/open_dispute")
    public ClaimDetailMiddleend openDispute(@PathVariable("claimId") String claimId,
                                         @RequestParam("access_token") String accessToken) throws Exception {

        ClaimDetailMiddleend claimDetailMiddleend = ClaimsService.openDispute(claimId, accessToken);

        return claimDetailMiddleend;
    }
}

//TODO: considerar exceptions. apicalls que rompan y demas.

//curl -H "Content-Type: application/json" -i -XGET "http://localhost:8080/claims/1023241276/detail?access_token=APP_USR-8070606397579233-081819-54ed35567db3b9279dba5886607f0607-344672346";
//curl -H "Content-Type: application/json" -i -XGET "http://localhost:8080/orders/sellers/344672346?access_token=APP_USR-8070606397579233-081819-54ed35567db3b9279dba5886607f0607-344672346";
//curl -H "Content-Type: application/json" -i -XPOST -d '{"message":"Prueba de mensajeee"}' "http://localhost:8080/claims/1023241276/messages?access_token=APP_USR-8070606397579233-081819-54ed35567db3b9279dba5886607f0607-344672346";
//curl -H "Content-Type: application/json" -i -XPOST "http://localhost:8080/claims/1023241276/open_dispute?access_token=APP_USR-8070606397579233-081819-54ed35567db3b9279dba5886607f0607-344672346";