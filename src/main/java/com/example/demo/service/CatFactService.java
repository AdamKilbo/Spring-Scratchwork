package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

@Service
public class CatFactService {
    static String apiUrl = "https://catfact.ninja/fact";
    static int timeout = 5000;

    private HttpURLConnection setupAPI() throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(timeout);
        con.setReadTimeout(timeout);
        con.setRequestProperty("Content-Type", "application/json");
        return con;
    }

    private static class FullResponseBuilder {
        public static String getFullResponse(HttpURLConnection con) throws IOException {
            StringBuilder fullResponseBuilder = new StringBuilder();

            // read status and message
            fullResponseBuilder.append(con.getResponseCode())
                    .append(" ")
                    .append(con.getResponseMessage())
                    .append("\n");

            // read headers
            con.getHeaderFields().entrySet().stream()
                    .filter(entry -> entry.getKey() != null)
                    .forEach(entry -> {
                        fullResponseBuilder.append(entry.getKey()).append(": ");
                        List headerValues = entry.getValue();
                        Iterator it = headerValues.iterator();
                        if (it.hasNext()) {
                            fullResponseBuilder.append(it.next());
                            while (it.hasNext()) {
                                fullResponseBuilder.append(", ").append(it.next());
                            }
                        }
                        fullResponseBuilder.append("\n");
                    });
            // read response content

            return fullResponseBuilder.toString();
        }
    }

    public String getCatFact() {
        HttpURLConnection con;
        try {
            con = setupAPI();
        } catch (Exception e) {
            System.out.println("couldn't set up url: " + e);
            return e.toString();
        }

        try {
            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            Reader streamReader = null;

            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }

//            System.out.println("status: " + streamReader.toString());
//            System.out.println("fact: " + content);
//            String response = FullResponseBuilder.getFullResponse(con);

            con.disconnect();

            JSONObject jsonObj = new JSONObject(content.toString());
            String catFact = jsonObj.getString("fact");
            return catFact;

        } catch (Exception e) {
            System.out.println("some error getting facts: " + e);
            return e.toString();
        }
    }
}
