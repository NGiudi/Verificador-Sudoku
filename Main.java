package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //ingreso de informacion por teclado.
        Scanner entrada = new Scanner(System.in);
        //array de valores.
        int[][] mapa = new int[9][9];

        // Ingreso de todos los datos.
        for  (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                System.out.print("Ingrese el valor de la posicion [" + j +"] ["+ i + "]: ");
                mapa[j][i] = entrada.nextInt();
            }
        }

        //verifico que todos los numeros esten entre 1 y 9.
        if (!verficiarNumeros (mapa)) {
            System.out.println("Se ingreso al menos un número invalido.");
            System.out.println("Vuelva a ingresar los datos.");
            return;
        }

        imprimirMatriz(mapa);

        if (verificarFilas(mapa) && verificarColumnas(mapa) && verificarCuadrado(mapa))
            System.out.println("\n La solución del sudoko es correcta.");
        else
            System.out.println("La solución del sudoko es incorrecta.");

    }

    private static boolean verificarFila(int[] fila){
        boolean[] presencia = new boolean[9];
        int i;

        // inicializo el array en falso.
        for (i = 0; i < 9; i++)     presencia[i] = false;
        // pongo en true el numero que aparece en la fila.
        for (i = 0; i < 9; i++)     presencia [fila[i]-1] = true;
        //verifico que esten todos los numeros.
        for (i = 0; i < 9; i++)
            if (!presencia[i]) return false;

        return true;
    }

    private static boolean verificarFilas (int[][] matriz){
        for (int i=0; i<9; i++)
            if (!verificarFila(matriz[i])) return false;

            return true;
    }

    private static void transponerMatriz (int[][] matriz){
        int aux;

        for (int j=0; j<9; j++){
            for (int i=0; i<j; i++){
                aux = matriz[i][j];
                matriz[i][j] = matriz[j][i];
                matriz[j][i] = aux;
            }
        }
    }

    private static boolean verificarColumnas (int[][] matriz){
        transponerMatriz (matriz);

        return verificarFilas(matriz);
    }

    private static void imprimirMatriz (int[][] matriz){
        for (int j=0; j<9; j++){
            for (int i=0; i<9; i++){
                System.out.print(matriz[j][i] + "   ");
            }
            System.out.print("\n");
        }
    }

    private static boolean verificarCuadrado (int[][] matriz){
        boolean[] presencia = new boolean[9];

        for(int l=0; l<3; l++){
            for (int k=0; k<3; k++){
                // inicializo el array en falso.
                for (int i=0; i<9; i++)     presencia[i] = false;

                for (int j=3*l; j<3*l+3; j++){
                    for(int i=3*k; i<3*k+3; i++){
                        presencia [matriz[j][i]-1] = true;
                    }
                }

                //verifico que esten todos los numeros.
                for (int i = 0; i < 9; i++)
                    if (!presencia[i])      return false;
            }
        }

        return true;
    }

    private static boolean verficiarNumeros (int[][] matriz){
        for  (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (matriz[j][i] < 1 || matriz[j][i] > 9)   return false;
            }
        }
        return true;
    }
}
