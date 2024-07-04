package com.urlshortner.app.controller;


import com.urlshortner.app.entity.UrlMapping;
import com.urlshortner.app.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlMapping> shortenUrl(@RequestBody String longUrl) {
        UrlMapping urlMapping = urlMappingService.shortenUrl(longUrl);
        return new ResponseEntity<>(urlMapping, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UrlMapping> updateUrl(@RequestParam String shortUrl,
                                                @RequestBody String newLongUrl) {
        UrlMapping urlMapping = urlMappingService.updateUrl(shortUrl, newLongUrl);
        if (urlMapping != null) {
            return new ResponseEntity<>(urlMapping, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
