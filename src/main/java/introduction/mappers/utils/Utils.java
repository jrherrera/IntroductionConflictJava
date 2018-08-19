package introduction.mappers.utils;

import introduction.mappers.json.Claims;
import introduction.mappers.json.Player;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static Long getCollectorId(Claims claim, String resource) throws Exception {
        List<Player> players = claim.getPlayers();

        String respondentType;

        switch (resource) {
            case "order":
                respondentType = "seller";
                break;
            case "shipping":
                respondentType = "sender";
                break;
            case "payment":
                respondentType = "collector";
                break;
            default:
                throw new Exception("invalid claim V1 resource");
        }

        Long collectorId = players.stream()
                .filter(player -> player.getType().equals(respondentType) && player.getRole().equals("respondent"))
                .mapToLong(user -> Long.parseLong(String.valueOf(user.getUserId())))
                .findAny()
                .getAsLong();

        return collectorId;
    }

    public static Long getPayerId(Claims claim, String resource) throws Exception {
        List<Player> players = claim.getPlayers();

        String complainantType;

        switch (resource) {
            case "order":
                complainantType = "buyer";
                break;
            case "shipping":
                complainantType = "receiver";
                break;
            case "payment":
                complainantType = "payer";
                break;
            default:
                throw new Exception("invalid claim V1 resource");
        }

        Long payerId = players.stream()
                .filter(player -> player.getType().equals(complainantType) && player.getRole().equals("complainant"))
                .mapToLong(user -> Long.parseLong(String.valueOf(user.getUserId())))
                .findAny()
                .getAsLong();

        return payerId;
    }

    public static String parseDateString(String dateTime) {
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS-SS:SS").parseDateTime(dateTime).toDate();
        Locale espanol = new Locale("es","ES");
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM", espanol);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String getMillestone(String status, String stage) throws Exception {

        String millestine;
        if ( stage.equals("claim") && status.equals("opened") ) {
            millestine = "Contactate con tu comprador para resolver el reclamo";
        } else if ( stage.equals("dispute") && status.equals("opened") ) {
            millestine = "Reclamo en mediacion";
        } else {
            millestine = "Reclamo cerrado";
        }

        return millestine;
    }

}
