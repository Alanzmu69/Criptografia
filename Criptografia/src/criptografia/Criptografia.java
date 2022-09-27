package criptografia;

import java.util.*;

public class Criptografia {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Criptografia c1 = new Criptografia();
        c1.menu();
    }

    public void menu() {

        int menu;

        String texto;
        String keyword;
        String palabraChida;

        System.out.print("""
                         ---------------
                         1. OTP
                         2. PlayFair
                         3. Hill
                         ->  """);
        menu = sc.nextInt();
        System.out.println("---------------");
        switch (menu) {
            case 1 -> {
                System.out.print("""
                                                 Ingrese el texto a cifrar
                                                 -> """);
                texto = sc.next();
                keyword = crearLlave(texto);
                palabraChida = encriptarOtp(texto.toUpperCase(), keyword.toUpperCase());
                System.out.println("Mensaje Encriptado: " + palabraChida);
                palabraChida = decriptarOtp(palabraChida.toUpperCase(), keyword.toUpperCase()).toUpperCase();
                System.out.println("Mensaje Decriptado: " + palabraChida);
            }
            case 2 -> {
                System.out.println("\nCifrado Playfair\n");
                
                int decision;
                System.out.print("""
                         ---------------
                         1. Encriptar
                         2. Desencriptar
                         ->  """);
                decision = sc.nextInt();

                switch(decision) {

                    case 1 -> {

                        System.out.print("Ingrese texto a encriptar: ");
                        Scanner sc = new Scanner(System.in);
                        texto = sc.nextLine();
                        texto = texto.toUpperCase();
                
                        System.out.print("Ingrese keyword: ");
                        keyword = sc.nextLine();
                        keyword = keyword.toUpperCase();
                
                        sc.close();
                
                        EncriptarPlayFair(texto, keyword);

                    }

                    case 2 -> {
                        System.out.print("Ingrese texto a desencriptar: ");
                        Scanner sc = new Scanner(System.in);
                        texto = sc.nextLine();
                        texto = texto.toUpperCase();
                
                        System.out.print("Ingrese keyword: ");
                        keyword = sc.nextLine();
                        keyword = keyword.toUpperCase();
                
                        sc.close();
                
                        DesencriptarPlayFair(texto, keyword);
                    }

                    default -> {
                        System.out.println("Opción Incorrecta.");
                    }
                }
            }
            case 3 ->
                SubMenu_Hill();

            default -> {
                System.out.println("Opción Incorrecta.");
            }
        }
    }

    public String encriptarOtp(String textoPlano, String keyword) {

        String cipherText = "";

        int cipher[] = new int[keyword.length()];

        for (int i = 0; i < keyword.length(); i++) {
            cipher[i] = textoPlano.charAt(i) - 'A' + keyword.charAt(i) - 'A';
        }

        for (int i = 0; i < keyword.length(); i++) {
            if (cipher[i] > 25) {
                cipher[i] = cipher[i] - 26;
            }
        }

        for (int i = 0; i < keyword.length(); i++) {
            int x = cipher[i] + 'A';
            cipherText += (char) x;
        }

        return cipherText;
    }

    public String decriptarOtp(String texto, String key) {

        String textoPlano = "";

        int plano[] = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            plano[i] = texto.charAt(i) - 'A' - (key.charAt(i) - 'A');
        }

        for (int i = 0; i < key.length(); i++) {
            if (plano[i] < 0) {
                plano[i] = plano[i] + 26;
            }
        }
        for (int i = 0; i < key.length(); i++) {
            int x = plano[i] + 'A';
            textoPlano += (char) x;
        }
        return textoPlano;
    }

    public String crearLlave(String texto) {
        char[] llave = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int longitud = llave.length;
        Random rand = new Random();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            buffer.append(llave[rand.nextInt(longitud)]);
        }

        return buffer.toString();
    }

    static void EncriptarPlayFair(String texto, String llave) {

        System.out.println("\nEncriptador:");
        char[] arr_texto = texto.toCharArray();
        char[] arr_llave = llave.toCharArray();
        char[] abecedario = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 
                            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[][] tabla = new char[5][5];
        char[] arr_encpt = new char[100];
        int contador_arr_encpt = 0;
        int contador = -1;

        for (int i = 0; i < arr_llave.length; i++) {
            for (int j = 0; j < 25; j++) {
                if (arr_llave[i] == abecedario[j]) {
                    contador++;
                    int ronda = contador / 5;
                    tabla[ronda][contador % 5] = abecedario[j];
                    abecedario[j] = '0';
                    break;
                }
            }
        }

        for (int i = 0; i < abecedario.length; i++) {
            if (abecedario[i] != '0') {
                contador++;
                int ronda = contador / 5;
                tabla[ronda][contador % 5] = abecedario[i];
            }
        }

        char[] cualq = new char[100];
        int c = 0;
        
        for (int i = 0; i < arr_texto.length; i++) {
            if (i == 0 && arr_texto[i] != arr_texto[i + 1] && i + 1 < arr_texto.length) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == 1 && arr_texto[i] != arr_texto[i + 1] && i + 1 < arr_texto.length) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 == 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 == 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 == 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 == 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 != 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 != 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 != 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 2 && i % 2 != 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i == arr_texto.length - 1 && i % 2 == 0 && arr_texto[i] != arr_texto[i - 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 1 && i % 2 == 0 && arr_texto[i] == arr_texto[i - 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i == arr_texto.length - 1 && i % 2 != 0 && arr_texto[i] != arr_texto[i - 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i == arr_texto.length - 1 && i % 2 != 0 && arr_texto[i] == arr_texto[i - 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i % 2 == 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i % 2 == 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i % 2 == 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i % 2 == 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i % 2 != 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

            if (i % 2 != 0 && arr_texto[i] != arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i % 2 != 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] != arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                continue;
            }

            if (i % 2 != 0 && arr_texto[i] == arr_texto[i - 1] && arr_texto[i] == arr_texto[i + 1]) {
                cualq[c++] = arr_texto[i];
                cualq[c++] = 'X';
                continue;
            }

        }

        if (c % 2 != 0) {
            cualq[c++] = 'X';
        }

        System.out.println("\nTexto a procesar: ");
        for (int i = 0; i < c; i++) 
            System.out.print(cualq[i]);
        System.out.println();

        for (int i = 0; i < c; i = i + 2) {
            if (cualq[i] == 'J')
                cualq[i] = 'I';
            
            if(cualq[i + 1] == 'J')
                cualq[i + 1] = 'I';

            int fila1 = 0, fila2 = 0, col1 = 0, col2 = 0;

            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if (cualq[i] == tabla[j][k]) {
                        fila1 = j;
                        col1 = k;
                        break;
                    }
                }
            }

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (cualq[i + 1] == tabla[j][k]) {
                        fila2 = j;
                        col2 = k;
                        break;
                    }
                }
            }

            if (fila1 == fila2) {
                col1 = (col1 + 1) % 5;
                col2 = (col2 + 1) % 5;

                arr_encpt[contador_arr_encpt++] = tabla[fila1][col1];
                arr_encpt[contador_arr_encpt++] = tabla[fila2][col2];
            }

            else if (col1 == col2) {
                fila1 = (fila1 + 1) % 5;
                fila2 = (fila2 + 1) % 5;

                arr_encpt[contador_arr_encpt++] = tabla[fila1][col1];
                arr_encpt[contador_arr_encpt++] = tabla[fila2][col2];
            }

            else if (fila1 != fila2 && col1 != col2) {
                int fila = 0, col = 0;
                fila = fila1;
                col = col2;

                arr_encpt[contador_arr_encpt++] = tabla[fila][col];
                fila = fila2;
                col = col1;

                arr_encpt[contador_arr_encpt++] = tabla[fila][col];
            }

            else {

            }
        }

        System.out.println("\nTabla: ");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(tabla[i][j] + " ");
            System.out.print("\n");
        }

        System.out.print("\nTexto encriptado: ");
        for (int i = 0; i < contador_arr_encpt; i++)
            System.out.print(arr_encpt[i]);
        System.out.println();
    }

    static void DesencriptarPlayFair(String texto, String llave) {
        System.out.println("\nDesencriptador:");
        char[] arr_encpt = texto.toCharArray();
        char[] arr_llave = llave.toCharArray();
        char[] abecedario = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 
                            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[][] tabla = new char[5][5];
        int contador = -1;
        
        for (int i = 0; i < arr_llave.length; i++) {
            for (int j = 0; j < 25; j++) {
                if (arr_llave[i] == abecedario[j]) {
                    contador++;
                    int ronda = contador / 5;
                    tabla[ronda][contador % 5] = abecedario[j];
                    abecedario[j] = '0';
                    break;
                }
            }
        }

        for (int i = 0; i < abecedario.length; i++) {
            if (abecedario[i] != '0') {
                contador++;
                int ronda = contador / 5;
                tabla[ronda][contador % 5] = abecedario[i];
            }
        }

        System.out.println("\nTabla: ");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(tabla[i][j] + " ");
            System.out.print("\n");
        }

        char[] cualq = new char[arr_encpt.length];
        int cont_cualq = 0;

        for (int i = 0; i < arr_encpt.length; i = i + 2) {
            int fila1 = 0, fila2 = 0, col1 = 0, col2 = 0;

            for (int j = 0; j < 5; j++) {

                for (int k = 0; k < 5; k++) {

                    if (arr_encpt[i] == tabla[j][k]) {
                        fila1 = j;
                        col1 = k;
                        break;
                    }
                }
            }

            for (int j = 0; j < 5; j++) {
                
                for (int k = 0; k < 5; k++) {

                    if (arr_encpt[i + 1] == tabla[j][k]) {
                        fila2 = j;
                        col2 = k;
                        break;
                    }
                }
            }

            if (fila1 == fila2) {
                col1 = (col1 - 1 + 5) % 5;
                col2 = (col2 - 1 + 5) % 5;

                cualq[cont_cualq++] = tabla[fila1][col1];
                cualq[cont_cualq++] = tabla[fila2][col2];
            }

            else if (col1 == col2) {
                fila1 = (fila1 - 1 + 5) % 5;
                fila2 = (fila2 - 1 + 5) % 5;

                cualq[cont_cualq++] = tabla[fila1][col1];
                cualq[cont_cualq++] = tabla[fila2][col2];
            }

            else if (fila1 != fila2 && col1 != col2) {
                int fila = 0, col = 0;
                fila = fila1;
                col = col2;

                cualq[cont_cualq++] = tabla[fila][col];
                fila = fila2;
                col = col1;
                cualq[cont_cualq++] = tabla[fila][col];
            }

            else {

            }
        }

        System.out.println("\nTexto a procesar: ");
        for (int i = 0; i < cont_cualq; i++) 
            System.out.print(cualq[i]);
        System.out.println();

        char[] arr_desencp = new char[100];
        int contador_arr_desencp = 0;

        for (int i = 0; i < cont_cualq; i++) {

            if (i == 0) {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }

            if (i == 1 && cualq[i - 1] == cualq[i + 1] && cualq[i] == 'X') {
                continue;
            }

            if (i == 1 && cualq[i - 1] != cualq[i + 1] && cualq[i] != 'X') {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }

            if (i == 2 && cualq[i - 1] == cualq[i + 1] && cualq[i] == 'X') {
                continue;
            }

            if (i == 2 && cualq[i - 1] != cualq[i + 1] && cualq[i] != 'X') {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }

            if (i != cont_cualq - 2 && i != cont_cualq - 1 && cualq[i - 1] == cualq[i + 1] && cualq[i] == 'X') {
                continue;
            }

            if (i != cont_cualq - 2 && i != cont_cualq - 1 && cualq[i - 1] == cualq[i + 1] && cualq[i] != 'X') {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }

            if (i == cont_cualq - 2 && cualq[i - 1] == cualq[i + 1] && cualq[i] == 'X') {
                continue;
            }

            if (i == cont_cualq - 2 && cualq[i - 1] == cualq[i + 1] && cualq[i] != 'X') {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }

            if (i == cont_cualq - 1 && i % 2 != 0 && cualq[i] == 'X') {
                continue;
            }

            if (i == cont_cualq - 1 && cualq[i] != 'X') {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }

            if (i == cont_cualq - 1 && i % 2 == 0 && cualq[i] == 'X') {
                continue;
            }

            if(i >= 0) {
                arr_desencp[contador_arr_desencp++] = cualq[i];
                continue;
            }
        }

        System.out.print("\n\nTexto desencriptado: ");
        for (int i = 0; i < contador_arr_desencp; i++) {
            System.out.print(arr_desencp[i]);
        }
    }

    public void SubMenu_Hill() {
        int opcion;
        System.out.print("Cifrado Hill (Llave ya predifinida).\n1. Encriptar\n2. Desencriptar\n-> ");
        opcion = sc.nextInt();

        switch (opcion) {
            case 1 -> {
                encriptarHill();
            }
            case 2 -> {
                descriptarHill();
            }
            default ->
                throw new AssertionError();
        }

    }

    public void encriptarHill() {
        Scanner sc2 = new Scanner(System.in);
        String abecedario2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";  // 27

        System.out.println("---------------");
        System.out.println("Palabra a encriptar: ");
        String palabra_encriptar = sc2.nextLine();
        String palabra_encriptar_final = "";

        if (palabra_encriptar.length() % 3 != 0) {
            int aux = palabra_encriptar.length() % 3;

            if (aux == 1) {
                palabra_encriptar_final = palabra_encriptar + "  ";
            } else if (aux == 2) {
                palabra_encriptar_final = palabra_encriptar + " ";
            }
        } else {
            palabra_encriptar_final = palabra_encriptar;
        }

        char[] palabra_encriptar_arreglo = palabra_encriptar_final.toUpperCase().toCharArray();

        int columnas = palabra_encriptar_arreglo.length / 3;
        int contador = 0;
        int matriz[][] = new int[3][columnas];
        int matriz_llave[][] = { // Llave ya predifinida
            {35, 53, 12},
            {12, 21, 5},
            {2, 4, 1}
        };

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[j][i] = abecedario2.indexOf(palabra_encriptar_arreglo[contador]);
                contador++;
            }
        }
        System.out.print("\n");
        int matriz_resultado[][] = multiplicacionMatrices(matriz_llave, matriz);
        int matriz_resultado_mod[][] = moduloMatriz(matriz_resultado);
        int arreglo[] = transformacionArreglo(matriz_resultado_mod);

        String palabraEncriptada = palabraEncriptada(arreglo);
        System.out.println("La palabra encriptada es: " + palabraEncriptada);
    }

    public void descriptarHill() {
        Scanner sc2 = new Scanner(System.in);
        String abecedario2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";  // 27

        System.out.println("---------------");
        System.out.println("Palabra a desencriptar: ");
        String palabra_desencriptar = sc2.nextLine();

        if (palabra_desencriptar.length() % 3 == 0) {
            int columnas = palabra_desencriptar.length() / 3;
            int matriz[][] = new int[3][columnas];
            int contador = 0;
            char[] palabra_descriptar_array = palabra_desencriptar.toUpperCase().toCharArray();
            int matriz_llave_inversa[][] = { // Llave ya predifinida le aplicamos inversa
                {1, -5, 13},
                {-2, 11, -31},
                {6, -34, 99}
            };
            int[][] c = moduloMatriz(matriz_llave_inversa);

            for (int i = 0; i < columnas; i++) {
                for (int j = 0; j < 3; j++) {
                    matriz[j][i] = abecedario2.indexOf(palabra_descriptar_array[contador]);
                    contador++;
                }
            }

            int[][] d = multiplicacionMatrices(c, matriz);
            int[][] f = moduloMatriz(d);
            int[] array = transformacionArreglo(f);

            String palabraDescriptada = palabraDescriptada(array);
            System.out.println("La palabra desencriptada es: " + palabraDescriptada);

        } else {
            System.out.println("Meter una palabra encriptada con HIll.");
        }
    }

    public static int[][] multiplicacionMatrices(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static int[][] moduloMatriz(int[][] a) {
        int[][] c = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] > 0) {
                    c[i][j] = a[i][j] % 27;
                } else {
                    int aux2 = (((a[i][j] % 27) + 27) % 27);

                    c[i][j] = aux2;
                }
            }
        }

        return c;
    }

    public static int[] transformacionArreglo(int[][] a) {
        int b[] = new int[a.length * a[0].length];
        int contador = 0;

        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[contador] = a[j][i];
                contador++;
            }
        }
        return b;
    }

    public static String palabraEncriptada(int[] a) {
        String abc[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};
        String p = "";

        for (int i = 0; i < a.length; i++) {
            p += abc[a[i]];
        }

        return p;
    }

    public static String palabraDescriptada(int[] a) {
        String abc[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};
        String p = "";

        for (int i = 0; i < a.length; i++) {
            p += abc[a[i]];
        }

        return p;
    }
}
