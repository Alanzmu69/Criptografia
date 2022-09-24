package criptografia;

import java.util.*;

public class Criptografia {
    
    Scanner  sc = new Scanner(System.in);
    
    public static void main(String[] args) {
       Criptografia c1 = new Criptografia();
       c1.menu();
    }
    
    public  void menu(){
        
        int menu;
        
        String texto;
        String keyword;
        String palabraChida;
        
        System.out.print("""
                         ---------------
                         1. OTP
                         2. PlayFair
                         3. Hill
                         -> """);
        menu=sc.nextInt();
        System.out.println("---------------");
        switch (menu) {
            case 1 -> {
                System.out.print("""
                                                 Ingrese el texto a cifrar
                                                 -> """);
                texto = sc.next();
                keyword=crearLlave(texto);
                palabraChida=encriptarOtp(texto.toUpperCase(),keyword.toUpperCase());
                System.out.println("Mensaje encriptado: "+palabraChida);
                palabraChida=decriptarOtp(palabraChida.toUpperCase(),keyword.toUpperCase()).toUpperCase();
                System.out.println("Mensaje decriptado: "+palabraChida);
            }
            case 2 -> encriptarPlayFair();
            case 3 -> encriptarHill();
            default -> {
                System.out.println("Opción Incorrecta.");
            }
        }
    }
    
    
    public  String encriptarOtp(String textoPlano, String keyword){
        
        String cipherText="";
        
        int cipher[]=new int[keyword.length()];
        
        for(int i=0;i<keyword.length();i++){
            cipher[i] = textoPlano.charAt(i)-'A'+keyword.charAt(i)-'A';
        }
        
        for(int i=0;i<keyword.length();i++){
            if(cipher[i]>25){
                cipher[i] = cipher[i] - 26;
            }
        }
        
        for(int i=0;i<keyword.length();i++){
            int x = cipher[i] + 'A';
            cipherText+=(char)x;
        }
        
        return cipherText;
    }
    
    public  String decriptarOtp(String texto, String key){
        
        String textoPlano="";
        
        int plano[]=new int[key.length()];
        
        for(int i=0;i<key.length();i++){
            plano[i]=texto.charAt(i)-'A'-(key.charAt(i)-'A');
        }
        
        for (int i = 0; i < key.length(); i++) {
            if (plano[i] < 0) {
                plano[i] = plano[i] + 26;
            }
        }
         for (int i = 0; i < key.length(); i++) {
            int x = plano[i] + 'A';
            textoPlano += (char)x;
        }
        return textoPlano;
    }
    
    public String crearLlave(String texto){
        char[] llave = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int longitud = llave.length;
        Random rand = new Random();
        StringBuilder buffer = new StringBuilder();
        
        for(int i = 0;i<texto.length();i++){
            buffer.append(llave[rand.nextInt(longitud)]);
        }
       
        return buffer.toString();
    }
    
    public  void encriptarPlayFair(){
        
    }
    
    public  void encriptarHill(){
        
    }
}
