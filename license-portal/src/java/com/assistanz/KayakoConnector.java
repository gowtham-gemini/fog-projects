
package com.assistanz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;
import com.kayako.api.configuration.Configuration;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.customfield.*;
import com.kayako.api.ticket.TicketPriority;
import com.kayako.api.configuration.Configuration;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.ticket.TicketPost;
import com.kayako.api.user.User;
import com.kayako.api.ticket.TicketPriority;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import com.kayako.api.department.Department;

public class KayakoConnector {
    
    String apiKey;
    String secretKey; 
    String baseURL;
    String salt = "0123456789"; 
    String signature;
    
    public KayakoConnector(String apiKey, String secretKey, String url) {
        
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseURL = url;
    }
    
    public String createTicket(String departmentName, String priority, String instanceId, String subject, String description, String fullName, String email) throws IOException, GeneralSecurityException { 
        
        String priorityId = null;
        String DepartmentId = null;
        
        if("Low".equals(priority)) {
             priorityId = "1";
        } else if("Normal".equals(priority)) {
            priorityId = "2";
        } else if("High".equals(priority)) {
            priorityId = "3";
        } else if("Urgent".equals(priority)) {
            priorityId = "4";
        }
        
        if("General".equals(departmentName)) {
            DepartmentId = "1";
        } else if("General".equals(departmentName)) {
            DepartmentId = "2";
        } else if("Level 1 Support".equals(departmentName)) {
            DepartmentId = "3";
        } else if("Level 2 Support".equals(departmentName)) {
            DepartmentId = "4";
        } else if("Level 3 Support".equals(departmentName)) {
            DepartmentId = "5";
        }
        
        signature = generateHmacSHA256Signature(salt, secretKey);
        
        QueryString qs = new QueryString("subject", subject);
        qs.add("fullname", fullName);
        qs.add("email", email);
        qs.add("contents", description);
        qs.add("departmentid", DepartmentId);
        qs.add("ticketstatusid", "1");
        qs.add("ticketpriorityid", priorityId);
        qs.add("tickettypeid", "1");
        qs.add("autouserid", "1");
        qs.add("apikey", apiKey);
        qs.add("salt", salt);
        qs.add("signature", signature);
        String url = baseURL + "?e=/Tickets/Ticket/" + qs;
        String response = generateConnection(url, qs);
        return response;
    }
    
    public String generateConnection(String connectUrl, QueryString qs) throws MalformedURLException, IOException {
        
        URL url = new URL(connectUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Apikey", apiKey);
        urlConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(qs.toString());
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    public static String generateHmacSHA256Signature(String data, String key) throws GeneralSecurityException {
        
        byte[] hmacData = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hmacData = mac.doFinal(data.getBytes("UTF-8"));
            return new BASE64Encoder().encode(hmacData);
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }   
    }
    
    public Ticket searchTicket(String Id) {
        
        Configuration config = Configuration.init(baseURL, apiKey, secretKey);
        config.setDateTimeFormat("yyyy-MM-dd");
        config.setDateFormat("MM/dd/yyyy");
        Configuration.setConfiguration(config);
        
        Ticket  ticket = null;
        try {
            int ticketId = Integer.parseInt(Id);
            ticket = Ticket.get(ticketId);
        } catch(Exception e) {
            System.out.print(e);
        }
        return ticket;
    }

    public ArrayList getListofTickets(String currentUserEmail) throws KayakoException {
        
        Configuration config = Configuration.init(baseURL, apiKey, secretKey);
        config.setDateTimeFormat("yyyy-MM-dd");
        config.setDateFormat("MM/dd/yyyy");
        Configuration.setConfiguration(config);
        
        ArrayList<Ticket> tickets = null;
        try {
            ArrayList<String> arrayList = new ArrayList();
            arrayList.add("email");
            tickets  = Ticket.search(currentUserEmail, arrayList); 
        } catch(Exception e) {
            System.out.print(e);
        }
        return tickets;
    }
    
    public ArrayList getTicketPriority () {
        
        Configuration config = Configuration.init(baseURL, apiKey, secretKey);
        config.setDateTimeFormat("yyyy-MM-dd");
        config.setDateFormat("MM/dd/yyyy");
        Configuration.setConfiguration(config);
        
        ArrayList<RawArrayElement> list = null;
        try {
            RawArrayElement priorities = TicketPriority.getAll();
            list = priorities.getComponents();
        }
        catch(Exception e) {
            System.out.print(e);
        }
        return list;
    }
    
    public ArrayList getTicketDepartments() { 
        
        Configuration config = Configuration.init(baseURL, apiKey, secretKey);
        config.setDateTimeFormat("yyyy-MM-dd");
        config.setDateFormat("MM/dd/yyyy");
        Configuration.setConfiguration(config);
        
        ArrayList<RawArrayElement> departmentlist = null;
        try {
            RawArrayElement departments = Department.getAll();
            departmentlist = departments.getComponents();
        } catch(Exception e) {
            System.out.print(e);
        }
        return departmentlist;
    }
    
    void addPosts(String ticketId, String posts, String mailId) throws KayakoException {
    
        Configuration config = Configuration.init(baseURL, apiKey, secretKey);
        config.setDateTimeFormat("yyyy-MM-dd");
        config.setDateFormat("MM/dd/yyyy");
        Configuration.setConfiguration(config);
    
        Ticket ticket = null;
        try  {
            ticket = Ticket.get(ticketId);
            ArrayList<User> users = User.search(mailId);
            User user = users.get(0);
            TicketPost post = TicketPost.createNew(ticket, posts, user);
            post.create();
        } catch(Exception e) {
            System.out.print(e);
        }
    }
    
    void editPriority(String ticketId, String priority) throws KayakoException {
        
        Configuration config = Configuration.init(baseURL, apiKey, secretKey);
        config.setDateTimeFormat("yyyy-MM-dd");
        config.setDateFormat("MM/dd/yyyy");
        Configuration.setConfiguration(config);
        
        int id = 0;
        if("Low".equals(priority)) {
             id = 1;
        } else if("Normal".equals(priority)) {
            id = 2;
        } else if("High".equals(priority)) {
            id = 3;
        } else if("Urgent".equals(priority)) {
            id = 4;
        }
        
        Ticket ticket = null;
        TicketPriority priorityInstance = null;
        try  {
            ticket = Ticket.get(ticketId);
            priorityInstance = TicketPriority.get(id);
            ticket.setPriority(priorityInstance);
            ticket.update();
        } catch(Exception e) {
            System.out.print(e);
        }
    }
}