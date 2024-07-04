package com.urlshortner.app.controller;


import com.urlshortner.app.entity.UrlMapping;
import com.urlshortner.app.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RedirectController {

    @Autowired
    private UrlMappingService urlMappingService;

    @GetMapping("/{shortenString}")
    @ResponseStatus(HttpStatus.FOUND)
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString) throws IOException {
        UrlMapping urlMapping = urlMappingService.getOriginalUrl("http://localhost:8080/" + shortenString);
        if (urlMapping != null) {
            response.sendRedirect(urlMapping.getLongUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

