/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phanmemmahoa;

import java.util.Scanner;
 
public class AffineCipher
{
    public static String encrypt(String text ,int a, int b)
    {
        String CTxt = "";
        for (int i = 0; i < text.length(); i++)
        {
            CTxt = CTxt + (char) ((((a * text.charAt(i)) + b) % 26) + 65);
        }
        return CTxt;
    }
 
    public static String decrypt(String text, int a, int b)
    {
        String Msg = "";
        int a_inv = 0;
        int flag = 0;
        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;
            if (flag == 1)
            {
                a_inv = i;
                System.out.println(i);
            }
        }
        for (int i = 0; i < text.length(); i++)
        {
            Msg = Msg + (char) (((a_inv * ((text.charAt(i) - b)) % 26)) + 65);
        }
        return Msg;
    }
 
}