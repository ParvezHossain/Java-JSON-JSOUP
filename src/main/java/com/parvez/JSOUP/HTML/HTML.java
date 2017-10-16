/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parvez.JSOUP.HTML;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Parvez
 */
public class HTML {

    public static void main(String[] args) {

        Document document;
        try {
            String url = "https://www.oracle.com/index.html";
            document = Jsoup.connect(url).timeout(5000).get();

            /*
            If you're using Netbeans and if in console shows this message 
            "this line is too long, please switch to wrapped mode to see the whole line"
            Then press ctrl+r and the string/text will be unqrapped 
             */
            String title = document.title();
            System.out.println("Title: " + title + "\n\n");

            System.out.println("Read the whole body text");
            String text = document.body().text();
            System.out.println(text + "\n\n");

            Elements elements = document.getAllElements();
            System.out.println("ALl Elements...");
            System.out.println(elements + "\n\n\n");

            System.out.println("Get All Attributes by Style");
            Elements elements2 = document.getElementsByAttribute("style");
            System.out.println(elements2 + " \n\n");

            System.out.println("Get All Attributes value by Text");
            Elements text2 = document.getElementsByAttributeValue("type", "text");
            System.out.println(text2 + " \n\n");

            System.out.println("Meta informations: ");
            String description = document.select("meta[name=description]").first().attr("content");

            System.out.println("Description : " + description + "\n\n");
            System.out.println("Get Keywords: ");

            String keywords
                    = document.select("meta[name=keywords]").first()
                            .attr("content");
            System.out.println(keywords + " \n\n");

            Elements links = document.select("a[href]");
            Elements resultLinks = document.select("h3.r > a");
            resultLinks.forEach((resultLink) -> {
                System.out.println(resultLink + "\n\n'n");
            });

            System.out.println("Image with src ending with .png" + "\n\n\n");
            Elements pngs = document.select("img[src$=.png]");
            System.out.println(pngs + "\n\n'n");

            System.out.println("Read the OuterHTML data..." + "\n\n'n");
            String outerHtml = links.outerHtml();
            System.out.println(outerHtml + "\n\n'n");

            System.out.println("Read the InnerHTML data..." + "\n\n'n");
            String innerHtml = links.html();
            System.out.println(innerHtml + "\n\n'n");

            System.out.println("Parse all Web-Links");
            links.stream().map((link) -> {
                System.out.println("Absolute URL link : " + link.attr("abs:href"));
                return link;
            }).forEachOrdered((link) -> {
                System.out.println("Text : " + link.text());
            });

            System.out.println("\n\n");
            Elements domain = document.select("a[href]");
            domain.stream().map((Element link) -> {
                String attr1 = link.attr("href");
                System.out.println(attr1);
                return link;
            }).map((link) -> link.attr("class")).forEachOrdered(System.out::println);

            System.out.println("\n\n\n\n");
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
