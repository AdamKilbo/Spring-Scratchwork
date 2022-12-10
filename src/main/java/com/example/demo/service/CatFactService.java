package com.example.demo.service;

import com.example.demo.repository.CatFactRepository;
import com.example.demo.repository.entities.CatFact;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CatFactService {
    static String limit = "5"; // number of cat facts
    static String apiUrl = "https://catfact.ninja/facts?limit=" + limit;
    static int timeout = 5000;

    @Autowired
    private CatFactRepository catFactRepository;

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

    public CatFact getCatFact() {
        List<CatFact> facts = catFactRepository.findAll();
        if (facts.size() == 0) {
            try {
                facts = fillRepo();
            } catch (Exception e) {
                return null;
            }
        }
        catFactRepository.delete(facts.get(0));
        return facts.get(0);
    }

    // fills the repo with facts, returns list of them
    private List<CatFact> fillRepo() throws Exception{

        HttpURLConnection con;
        try {
            con = setupAPI();
        } catch (Exception e) {
            System.out.println("couldn't set up api: " + e);
            throw e;
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

            List<CatFact> catFacts = new ArrayList<>();
            JSONObject jsonObj = new JSONObject(content.toString());
            JSONArray facts = jsonObj.getJSONArray("data");
            for (int i = 0; i < facts.length(); i++) {
                JSONObject obj = facts.getJSONObject(i);
                String fact = obj.getString("fact");
                int length = obj.getInt("length");

                catFacts.add(new CatFact(fact, length));
            }
            catFactRepository.saveAll(catFacts);

            return catFacts;

        } catch (Exception e) {
            System.out.println("some error getting facts: " + e);
            throw e;
        }
    }
}
