/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 *
 * @author dansc
 */
public class APIController {
    protected ObjectMapper mapper = new ObjectMapper();
    public Date lastUpdated = new Date();
    
    public APIController() {
        lastUpdated = new Date();
        lastUpdated.setYear(1970);
    }
    
    public int put(Object o, String endpoint) {
        System.out.println("[INFO] Posting resource to: " + endpoint);
        try {
            String data = mapper.writeValueAsString(o);
            URL url = new URL(endpoint);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("PUT");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            
            try (OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream())) {
                out.write(data);
            }
            int responseCode = http.getResponseCode();
            http.disconnect();
            return responseCode;
        } catch (Exception e) {
            System.out.println("[ERR] " + e.getMessage());
        }
        return -1;
    }
    
    public int delete(String endpoint) {
        System.out.println("[INFO] Deleting resource from: " + endpoint);
        try {
            URL url = new URL(endpoint);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("DELETE");
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.connect();
            int responseCode = http.getResponseCode();
            http.disconnect();
            return responseCode;
        } catch (Exception e) {
            
        }
        return -1;
    }
    
    public int post(Object o, String endpoint) {
        System.out.println("[INFO] Posting resource to: " + endpoint);
        try {
            String data = mapper.writeValueAsString(o);
            URL url = new URL(endpoint);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            
            OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());
            out.write(data);
            out.close();

            int responseCode = http.getResponseCode();
            http.disconnect();
            return responseCode;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    } 
}