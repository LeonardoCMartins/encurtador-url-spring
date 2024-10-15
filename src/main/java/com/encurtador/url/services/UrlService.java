package com.encurtador.url.services;

import com.encurtador.url.models.Url;
import com.encurtador.url.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String encurtar(String original){
        String urlEncurtada = geradorUrl();
        Url url = new Url();

        url.setOriginal(original);
        url.setEncurtada(urlEncurtada);
        url.setTempoExpiracao(LocalDateTime.now().plusSeconds(3600));

        urlRepository.save(url);
        return urlEncurtada;
    }

    public String geradorUrl(){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder urlEncurtada = new StringBuilder();

        Random random = new Random();
        int length = 5 + random.nextInt(6);
        for (int i = 0; i < length; i++){
            urlEncurtada.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return urlEncurtada.toString();
    }

    public Optional<Url> getOriginal(String encurtada){
        Optional<Url> urlOptional = urlRepository.findByEncurtada(encurtada);
        if (urlOptional.isPresent()){
            Url url = urlOptional.get();
            if (url.getTempoExpiracao().isAfter(LocalDateTime.now())){
                return Optional.of(url);
            } else {
                urlRepository.delete(url);
            }
        }
        return Optional.empty();
    }
}