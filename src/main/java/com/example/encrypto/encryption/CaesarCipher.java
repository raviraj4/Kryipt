package com.example.encrypto.encryption;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CaesarCipher {

    @GetMapping("/encrypt/caesar")
    public String encrypt(@RequestParam String text, @RequestParam int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0 ; i < text.length(); i++){
            char ch = text.charAt(i);
            if(Character.isUpperCase(ch)){
                char newCh = (char) (((ch + shift - 'A') % 26 ) + 'A');
                encrypted.append(newCh);
            }else if (Character.isLowerCase(ch)){
                char newCh = (char) (((ch + shift - 'a')+ 'a'));
                encrypted.append(newCh);
            }else{
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }
    
    
}
