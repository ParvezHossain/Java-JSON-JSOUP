/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parvez.Users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parvez
 */
public class UserView {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            try {
                URLConnection connection = url.openConnection();
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(reader);
                StringBuilder jsonBuilder = new StringBuilder();

                String line;
//                jsonBuilder.append(line);

                while ((line = br.readLine()) != null) {
                    jsonBuilder.append(line);
//                    System.out.println(line);
                }

                ObjectMapper mapper = new ObjectMapper();
                List<User> userList = mapper.readValue(jsonBuilder.toString(), new TypeReference<List<User>>() {
                });

                userList.stream().forEach(users -> {

                    System.out.println("User I'd: " + users.getId());
                    System.out.println("User Name: " + users.getName());
                    System.out.println("User UserName: " + users.getUsername());
                    System.out.println("User Email: " + users.getEmail());
                    System.out.println("User Address: " + users.getAddress());
                    System.out.println("User Phone: " + users.getPhone());
                    System.out.println("User Website: " + users.getWebsite());
                    System.out.println("User Company: " + users.getCompany());
                });

            } catch (IOException ex) {
                System.out.println(ex.fillInStackTrace().getMessage());
                Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.fillInStackTrace().getMessage());
            Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
