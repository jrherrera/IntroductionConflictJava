package introduction.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import introduction.mappers.utils.HttpClientExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static introduction.mappers.utils.Constants.MESSAGE_JSON;

/**
 *
 * @author jherrera
 */
public class RestService {

    private static final Logger log = LoggerFactory.getLogger(RestService.class);
    
    public static String get(String uri) throws Exception {

        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            content = new StringBuilder();
            content.append(in.readLine());
        }
        con.disconnect();

        String result = content.toString();

        log.info(String.format("Result of RestService for uri %s - Result: %s", uri, result));
        return result;
    }

    public static String send(String uri, String json, String httpRequest) throws Exception {
        String result = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(httpRequest);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            log.info(String.format("Message to send %s", json));

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(json);
            out.flush();
            out.close();

            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                content = new StringBuilder();
                content.append(in.readLine());
            }
            con.disconnect();

            result = content.toString();

        } catch(Exception e) {
            //log.info(String.format("Exception posting message. Error: %s", e.getMessage()));
            throw new Exception(e);
        }
        log.info(String.format("Result of RestService for uri %s - Result: %s", uri, result));
        return result;
    }

    public static String put(String uri, String json) throws Exception {
        return send(uri, json, "PUT");
    }

    public static String post(String uri, String json) throws Exception {
        return send(uri, json, "POST");
    }
}
