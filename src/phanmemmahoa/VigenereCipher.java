/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phanmemmahoa;

public class VigenereCipher{
    

    public static String encrypt(String plaintext, String keyword)    {
 char msg[] = plaintext.toCharArray();
 int msgLen = msg.length;
 int i,j;
  
 char key[] = new char[msgLen];
 char encryptedMsg[] = new char[msgLen];
  
 for(i = 0, j = 0; i < msgLen; ++i, ++j)
 {
  if(j == keyword.length())
  {
   j = 0;
  }
  key[i] = keyword.charAt(j);
 }
  
 for(i = 0; i < msgLen; ++i)
 {
     encryptedMsg[i] = (char) (((msg[i] + key[i]) % 26) + 'A');
 }
 //for(i = 0; i < msgLen; ++i)
 //decryptedMsg[i] = (char)((((encryptedMsg[i] - key[i]) + 26) % 26) + 'A');
 return (String.valueOf(encryptedMsg));
  }
    
    public static String decrypt(String plaintext, String keyword)
    {
 char msg[] = plaintext.toCharArray();
 int msgLen = msg.length;
 int i,j;
  
 char key[] = new char[msgLen];
 char decryptedMsg[] = new char[msgLen];
  
 for(i = 0, j = 0; i < msgLen; ++i, ++j)
 {
  if(j == keyword.length())
  {
   j = 0;
  }
  key[i] = keyword.charAt(j);
 }
 for(i = 0; i < msgLen; ++i)
 decryptedMsg[i] = (char)((((msg[i] - key[i]) + 26) % 26) + 'A');
 return (String.valueOf(decryptedMsg));
    }
}
