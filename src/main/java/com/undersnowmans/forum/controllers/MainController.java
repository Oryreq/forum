package com.undersnowmans.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.net.*;


@Controller
public class MainController {

    @GetMapping
    public String getMainPage(Model model) {
        String URLCatImage = "/static/cats/0.jpg";

        URI url = null;
        try {
            url = new URI("https://api.thecatapi.com/v1/images/search");
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            if (result != null) {
                URLCatImage = result.split("url\":\"")[1].split("\",\"width")[0];
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("catPic", URLCatImage);

        return "index";
    }
}
