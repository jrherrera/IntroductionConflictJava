package introduction;

import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jherrera
 */
@RestController
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/ping")
    //public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    public String ping() {        
        return "pong";
    }
    
    @GetMapping(value = "/claims/{claimId}/detail")
    public FrontendDetail getDetail(@PathVariable("claimId") String claimId) {
        
        log.info("Greeting frontend detail with claimId: " + claimId);
        
        return new FrontendDetail("Reclamo test numero: " + claimId,
                "Tengo un problema con mi producto",
                "Lista de mensajes");
    }
    
    /**
     *
     * @param actionName
     * @param action
     * @return
     */
    @PostMapping(value = "/claims/{action}/actions")
    public Actions executeAction(@PathVariable("action") String actionName, @RequestBody Actions action) {
        log.info("Execute action: " + action.getName());
        return action;
    }
}


/*
curl -i -XGET "http://localhost:8080/ping";
curl -i -XGET "http://localhost:8080/claims/123456/detail";
curl -i -XPOST "http://localhost:8080/claims/message/actions";
*/