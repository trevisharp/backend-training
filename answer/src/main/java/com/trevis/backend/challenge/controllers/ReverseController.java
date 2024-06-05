package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.backend.challenge.dto.PalindromeJson;

@RestController
public class ReverseController {
    
    @GetMapping("/reverse/{word}")
    public PalindromeJson reverse(@PathVariable String word) {
        char[] characters = word.toCharArray();
        int n  = characters.length / 2;

        for (int i = 0, j = characters.length - 1; i < n; i++, j--) {
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        String reverse = new String(characters);

        return new PalindromeJson( 
            reverse,
            reverse.equals(word)
        );
    }
}