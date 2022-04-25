/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phanmemmahoa;

import java.util.ArrayList;  
import java.util.Scanner;  

public class HillCipher {
    
    
    public static String encrypt(String msg, String k){
        msg=msg.replaceAll("\\s", "");
        msg=msg.toUpperCase();
        
        int lenCheck = 0;
        if(msg.length()%2!=0){
            msg += "0";
            lenCheck = 1;
        }
               
        //chuyen ban ro thanh ma tran
        int [][] msg2D = new int[2][msg.length()];
        int itr1 =0;
        int itr2 =0;
        for (int i=0;i<msg.length();i++){
            if(i%2==0){
                msg2D[0][itr1] = (int)msg.charAt(i)-65;
                itr1++;
            } else{
                msg2D[1][itr2] = (int)msg.charAt(i)-65;
                itr2++;
            }
        }
        
        k=k.replaceAll("\\s", "");
        k=k.toUpperCase();
        
        //chuyen key thanh ma tran 2x2 (yeu cau da nhap 4 chu cai)
        int [][] key2D = new int [2][2];
        int itr3=0;
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                key2D[i][j]= (int)k.charAt(itr3)-65;
                itr3++; 
            }
        }
        //00  01
        //10  11
        //key hop le, tim dinh thuc cua ma tran key
        int deter = key2D[0][0]*key2D[1][1] - key2D[0][1]*key2D[1][0];
        deter = modulo(deter,26);
        
        //ma tran k phai ton tai ma tran nghich dao
        //tuc la dinh thuc cua ma tran k phai co phan tu nghich dao tren Z26
        //MOD(X*...,26)
        int mulInv=-1;
        for(int i=0;i<26;i++){
            if(modulo(deter*i,26)==1){
                mulInv=i;
                break;
            } else{
                continue;
            }
        }
        
        if(mulInv==-1){
            return "";
        }
        
        String cipher="";
        int itrCount = msg.length()/2;
        if(lenCheck==0){
            for(int i=0;i<itrCount;i++){
                int tmp1 = msg2D[0][i]*key2D[0][0] + msg2D[1][i]*key2D[0][1];
                cipher+=(char)((tmp1%26)+65);
                int tmp2 = msg2D[0][i]*key2D[1][0] + msg2D[1][i]*key2D[1][1];
                cipher+=(char)((tmp2%26)+65);
            }
        } else{
            for(int i=0;i<itrCount-1;i++){
                int tmp1 = msg2D[0][i]*key2D[0][0] + msg2D[1][i]*key2D[0][1];
                cipher+=(char)((tmp1%26)+65);
                int tmp2 = msg2D[0][i]*key2D[1][0] + msg2D[1][i]*key2D[1][1];
                cipher+=(char)((tmp2%26)+65);
            }
        }
        
        return cipher;
    }
    
    public static String decrypt(String msg, String k){
        msg=msg.replaceAll("\\s", "");
        msg=msg.toUpperCase();
        
        int lenCheck = 0;
        if(msg.length()%2!=0){
            msg += "0";
            lenCheck = 1;
        }
               
        //chuyen ban ro thanh ma tran
        int [][] msg2D = new int[2][msg.length()];
        int itr1 =0;
        int itr2 =0;
        for (int i=0;i<msg.length();i++){
            if(i%2==0){
                msg2D[0][itr1] = (int)msg.charAt(i)-65;
                itr1++;
            } else{
                msg2D[1][itr2] = (int)msg.charAt(i)-65;
                itr2++;
            }
        }
        
        k=k.replaceAll("\\s", "");
        k=k.toUpperCase();
        
        //chuyen key thanh ma tran 2x2 (yeu cau da nhap 4 chu cai)
        int [][] key2D = new int [2][2];
        int itr3=0;
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                key2D[i][j]= (int)k.charAt(itr3)-65;
                itr3++; 
            }
        }
        
        int deter = key2D[0][0]*key2D[1][1] - key2D[0][1]*key2D[1][0];
        deter = modulo(deter,26);
        
        //ma tran k phai ton tai ma tran nghich dao
        //tuc la dinh thuc cua ma tran k phai co phan tu nghich dao tren Z26
        //MOD(X*...,26)
        int mulInv=-1;
        for(int i=0;i<26;i++){
            if(modulo(deter*i,26)==1){
                mulInv=i;
                break;
            } else{
                continue;
            }
        }
        
        //tim K-1 = detK-1 * inverse matrix
        //tim ma tran nghich dao
        //k11 -k01
        //-k10 k00
        int swapTemp=key2D[0][0];
        key2D[0][0]=key2D[1][1];
        key2D[1][1]=swapTemp;
        
        //doi dau
        key2D[0][1] *= -1;
        key2D[1][0] *= -1;
        
        key2D[0][1] = modulo(key2D[0][1], 26);
        key2D[1][0] = modulo(key2D[1][0], 26);
                
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                key2D[i][j] *= mulInv;
            }
        }
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                key2D[i][j] = modulo(key2D[i][j], 26);
            }
        }
        //after dc ma tran nghich dao
                
        String plaintext="";
        int itrCount=msg.length()/2;
        if(lenCheck==0){
            for(int i=0;i<itrCount;i++){
                int tmp1 = msg2D[0][i]*key2D[0][0] + msg2D[1][i]*key2D[0][1];
                plaintext+=(char)((tmp1%26)+65);
                int tmp2 = msg2D[0][i]*key2D[1][0] + msg2D[1][i]*key2D[1][1];
                plaintext+=(char)((tmp2%26)+65);
            }
        } else{
            for(int i=0;i<itrCount-1;i++){
                int tmp1 = msg2D[0][i]*key2D[0][0] + msg2D[1][i]*key2D[0][1];
                plaintext+=(char)((tmp1%26)+65);
                int tmp2 = msg2D[0][i]*key2D[1][0] + msg2D[1][i]*key2D[1][1];
                plaintext+=(char)((tmp2%26)+65);
            }
        }
        
        return plaintext;
    }
    
    public static int modulo(int a, int b){
        int result = a%b;
        if(result<0){
            result+=b;
        }
        return result;
    }  
} 
