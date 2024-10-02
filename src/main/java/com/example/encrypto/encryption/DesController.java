package com.example.encrypto.encryption;

import org.springframework.web.bind.annotation.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


// import org.springframework.web.bind.annotation.Re


@RestController
@RequestMapping("/des")
public class DesController {
    private static final String ALGORITHM  = "DES";

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String data ) throws Exception{
        SecretKey key = KeyGenerator.getInstance(ALGORITHM).generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        String encryptedString = Base64.getEncoder().encodeToString(encryptedData);
        return "Key [" + encodedKey + "]\nEncrypted Data: [" + encryptedString +
        "]";
    }
    
    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String encryptedData, @RequestParam String base64Key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
    

}
