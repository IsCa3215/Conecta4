import java.lang.reflect.Array;

import java.util.Scanner;
import java.util.Arrays;
public class funciones {
    public static int leerInt(String mensaje) {
        Scanner leer = new Scanner(System.in);
        System.out.println(mensaje);
        while (!leer.hasNextInt()) {
            leer.nextLine();
            System.out.println("No se ha introducido un valor válidor, Inténtalo de nuevo");

        }
        int valor2 = leer.nextInt();
        return valor2;
    }

    public static final int casilla_vacia = 0;
    public static final int ficha_circulo = 1;
    public static final int ficha_cruz = 2;

    public static void mostrarTablero(int[][] tablero) {

        String casilla = null;
        char a = ' ';
        for (int i = 0; i < tablero.length; i++) {
            int cont[] = tablero[i];
            for (int j = 0; j < tablero.length; j++) {

                if (tablero[i][j] == casilla_vacia) {

                    casilla = " ";
                } else if (tablero[i][j] == ficha_circulo) {
                    casilla = "\033";
                }
                System.out.print("| "+"1"+" "+"\n");
                System.out.print("| " +casilla+ " ");
            }

            System.out.println("|");
        }


    }
    public static boolean colocarFicha(int [][] tablero, int ficha, int columna){
        return false;
    }

}

