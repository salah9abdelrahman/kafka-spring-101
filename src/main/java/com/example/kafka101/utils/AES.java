package com.example.kafka101.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

@Slf4j
public class AES {

    private final SecretKeySpec secretKey;
    private final IvParameterSpec ivspec;

    public AES(String secret, String salt) {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        ivspec = new IvParameterSpec(iv);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (Exception e) {
            log.error("Exception during initialization of AES: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public byte[] encrypt(byte[] values) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return cipher.doFinal(values);
        } catch (Exception e) {
            log.error("Error while encrypting: {}", e.toString());
        }
        return null;
    }

    public byte[] decrypt(byte[] val) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return cipher.doFinal(val);
        } catch (Exception e) {
            log.error("Error while decrypting: {}", e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        AES aes = new AES("secret2", "salt2");
    }


}
