/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parvez.Comments;

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
public class CommentView {

    public static void main(String[] args) {
        System.out.println("Connection Establising...");
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/comments");
            System.out.println("Connection Established!");
            try {
                URLConnection connection = url.openConnection();
                System.out.println("Connection Opened Successfully...");

                connection.setConnectTimeout(10000);
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                System.out.println("Input Stream Reader Created...");
                BufferedReader br = new BufferedReader(reader);
                StringBuilder jsonString = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    jsonString.append(line);
//                    System.out.println(line);
                }

                ObjectMapper mapper = new ObjectMapper();
                List<Comment> comments = mapper.readValue(jsonString.toString(), new TypeReference<List<Comment>>() {
                });

                comments.stream().forEach(comment -> {
                    System.out.println("Post ID : " + comment.getPostId());
                    System.out.println("ID : " + comment.getId());
                    System.out.println("Name : " + comment.getName());
                    System.out.println("Email : " + comment.getEmail());
                    System.out.println("Body : " + comment.getBody());
                    System.out.println("\n\n");

                });

            } catch (IOException ex) {
                Logger.getLogger(CommentView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.getCause().getMessage());
            Logger.getLogger(CommentView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
