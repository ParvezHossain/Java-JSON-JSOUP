/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parvez.JSOUP.HTML;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Parvez
 */
public class HTML {

    private static Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN
            = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,15}";
    private static Pattern patrn = Pattern.compile(DOMAIN_NAME_PATTERN);

    public static String getDomainName(String url) {

        String domainName = "";
        matcher = patrn.matcher(url);

        if (matcher.find()) {
            domainName = matcher.group(0).toLowerCase().trim();
        }

        return domainName;
    }

    public static void main(String[] args) {

        Document document;
        try {
            String query = "Devoxx Morocco";
            String url = "https://facebook.com";
            document = Jsoup.connect(url).timeout(5000).get();

            String title = document.title();
            System.out.println("Title: " + title + "\n\n");
            Elements elements = document.getAllElements();
            System.out.println("ALl Elements...");
            System.out.println(elements + "\n\n\n");

            System.out.println("Get All Attributes by Style");
            Elements elements2 = document.getElementsByAttribute("style");
            System.out.println(elements2);

            System.out.println("Get All Attributes value by Text");
            Elements text = document.getElementsByAttributeValue("type", "text");
            System.out.println(text + " \n\n");

            System.out.println("Meta informations: ");
            String description = document.select("meta[name=description]").first().attr("content");
            System.out.println("Description : " + description + "\n\n");

            System.out.println("Get Keywords: ");
            String keywords = document.select("meta[name=keywords]").first()
                    .attr("content");
            System.out.println(keywords + " \n\n");

            Elements links = document.select("a[href]");

            System.out.println("Parse all Web-Links");
            links.stream().map((link) -> {
                System.out.println("link : " + link.attr("href"));
                return link;
            }).forEachOrdered((link) -> {
                System.out.println("text : " + link.text());
            });

            System.out.println("\n\n");
            System.out.println("..........Get Domain......Performing Google Search...........");
//            Elements domain = document.select("a[href]");
//            Set<String> result = new HashSet<>();
//            System.out.println("All Web Links");
//            for (Element link : domain) {
//                String attr1 = link.attr("href");
//                String attr2 = link.attr("class");
//
//                if (!attr2.startsWith("_Zkb") && attr1.startsWith("/url?q=")) {
//                    result.add(getDomainName(attr1));
//                }
//                result.forEach((string) -> {
//                    System.out.println(string);
//                });
//            }

            System.out.println("\n\n");
            Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            images.stream().map((image) -> {
                System.out.println("\nsrc : " + image.attr("src"));
                return image;
            }).map((image) -> {
                System.out.println("height : " + image.attr("height"));
                return image;
            }).map((image) -> {
                System.out.println("width : " + image.attr("width"));
                return image;
            }).forEachOrdered((image) -> {
                System.out.println("alt : " + image.attr("alt"));
            });
        } catch (IOException ex) {
            System.out.println(ex.fillInStackTrace().getMessage());
            Logger.getLogger(HTML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
