package com.encurtador.url.controllers;

import com.encurtador.url.dtos.UrlDtoRequest;
import com.encurtador.url.dtos.UrlDtoResponse;
import com.encurtador.url.models.Url;
import com.encurtador.url.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class UrlController {

    private final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private UrlService service;

    @PostMapping("/url")
    public ResponseEntity<UrlDtoResponse> encuradorDeUrl(@RequestBody UrlDtoRequest request){
        String originalUrl = request.url();
        String urlEncurtada = service.encurtar(originalUrl);

        UrlDtoResponse response = new UrlDtoResponse(urlEncurtada, BASE_URL + urlEncurtada);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{encurtada}")
    public ResponseEntity<Object> redirecionamento(@PathVariable String encurtada){
        Optional<Url> urlOptional = service.getOriginal(encurtada);
        if (urlOptional.isPresent()){
            Url url = urlOptional.get();
            return ResponseEntity.status(302).location(URI.create(url.getOriginal())).build();
        }
        return ResponseEntity.notFound().build();
    }

}

