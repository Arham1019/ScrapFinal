package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Main M = new Main();
        M.extractingurl("http://books.toscrape.com/catalogue/category/books_1/index.html");
    }
    public void concatingurl(String[] names,int i) {
        String url = new String("https://books.toscrape.com/catalogue/category/books/");
        String[] parts = names;
        String href="/index.html";
        String url2 = null;
        Document URL=null;
        for ( i = 0; i < names.length; i++) {
            parts[i] = names[i];
        }
        for ( i = 0; i < names.length; i++) {
            url2 = url + parts[i];
            if (i==0) { //System.out.println(" "+url + parts[i]); not to use the first url
            }
            else {
                  if (url2.endsWith(parts[i]))
                  {
                      url2=url2+href;
                      try {
                          URL= (Document) Jsoup.connect(url2).get();
                          Elements navlist=URL.getElementsByClass("nav-list");
                          //System.out.println(" "+navlist.text()); //For selecting the side bar
                          //Now getting the main page
                          Elements pro=URL.getElementsByClass("product_pod");
                          Elements h3=pro.select("h3");
                          Elements price=URL.getElementsByClass("product_price");
                          Elements p=URL.getElementsByClass("price_color");
                          Elements avail=URL.getElementsByClass("instock availability");
                          System.out.println(" "+parts[i]);
                          System.out.println(" "+h3.text());
                          System.out.println(" "+p.text());
                          System.out.println(" "+avail.text());
                          System.out.println(" ");
                          System.out.println(" ");
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
            }
        }

    }
    public void extractingurl(String href) throws IOException {
        Document Doc=Jsoup.connect(href).get();
        Elements list=Doc.getElementsByClass("nav-list");
        Elements a=list.select("a");
        String[] names=new String[a.size()];
        int i;
        String ul2;
        for( i=0;i<a.size();i++)    //This loop gets all the url from navlist and stores it in names
        {
            if (names[i]=="0")
            {
                //Just to eliminate the first row as
            }
            else if (i>0){

                names[i] = a.get(i).attr("href"); //gives the list of the links
                names[i]=names[i].split("/")[2];      //gives the name travel_2
                //System.out.println(" " + names[i]);
            }
        }
        concatingurl(names,i);
}
}

