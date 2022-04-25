/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phanmemmahoa;

import java.io.*;
import java.util.*;

public class HoanViCipher {
 
    public static String encrypt(String message, String cipherSmall) {
        String encoded = "";
 
        // This map is used to encode
        Map<Character,Character> cipherMap = new HashMap<Character,Character>();
 
        char beginSmallLetter = 'a';
        char beginCapitalLetter = 'A';
 
        cipherSmall = cipherSmall.toLowerCase();
        String cipherCapital = cipherSmall.toUpperCase();
 
        // To handle Small and Capital letters
        for(int i = 0; i < cipherSmall.length(); i++){
            cipherMap.put(beginSmallLetter++,cipherSmall.charAt(i));
            cipherMap.put(beginCapitalLetter++,cipherCapital.charAt(i));
        }
 
        for(int i = 0; i < message.length(); i++){
            if(Character.isAlphabetic(message.charAt(i)))
                encoded += cipherMap.get(message.charAt(i));
            else
                encoded += message.charAt(i);
        }
 
        return encoded;
    }
 
    public static String decrypt(String encryptedMessage, String cipherSmall) {
        String decoded = "";
 
        Map<Character,Character> cipherMap = new HashMap<Character,Character>();
 
        char beginSmallLetter = 'a';
        char beginCapitalLetter = 'A';
 
        cipherSmall = cipherSmall.toLowerCase();
        String cipherCapital = cipherSmall.toUpperCase();
 
        for(int i = 0; i < cipherSmall.length(); i++){
            cipherMap.put(cipherSmall.charAt(i),beginSmallLetter++);
            cipherMap.put(cipherCapital.charAt(i),beginCapitalLetter++);
        }
 
        for(int i = 0; i < encryptedMessage.length(); i++){
            if(Character.isAlphabetic(encryptedMessage.charAt(i)))
                decoded += cipherMap.get(encryptedMessage.charAt(i));
            else
                decoded += encryptedMessage.charAt(i);
        }
 
        return decoded;
    }
}