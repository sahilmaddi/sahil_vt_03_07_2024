package com.urlshortner.app.service;


import com.urlshortner.app.entity.UrlMapping;
import com.urlshortner.app.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlMappingService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public UrlMapping shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setLongUrl(longUrl);
        urlMapping.setCreatedAt(LocalDateTime.now());
        urlMapping.setExpiresAt(LocalDateTime.now().plusMonths(10));
        return urlMappingRepository.save(urlMapping);
    }

    public UrlMapping updateUrl(String shortUrl, String newLongUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            urlMapping.setLongUrl(newLongUrl);
            return urlMappingRepository.save(urlMapping);
        }
        return null;
    }

    public UrlMapping getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl);
    }

    private String generateShortUrl() {
        // Generate a random short URL (e.g., using UUID)
        return "http://localhost:8080/" + UUID.randomUUID().toString().substring(0, 8);
    }
}
