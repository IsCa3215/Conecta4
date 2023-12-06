import java.util.Scanner;

public class Funciones {
    public static int leerInt(String mensaje) {
        Scanner leer = new Scanner(System.in);
        System.out.println(mensaje);
        while (!leer.hasNextInt()) {
            leer.nextLine();
            System.out.println("\u001B[31m" + "No se ha introducido un valor válido, Inténtalo de nuevo" + "\u001B[0m");

        }
        int valor2 = leer.nextInt();
        return valor2;
    }

    public static final int CASILLA_VACIA = 0;
    public static final int FICHA_CIRCULO = 1;
    public static final int FICHA_CRUZ = 2;

    public static int num_linea = 0;

    public static void mostrarTablero(int[][] tablero) {//Esta función muestra el tablero y modifica los valores de las casillas por otros

        String casilla = null;

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero.length; j++) {

                if (tablero[i][j] == CASILLA_VACIA) {

                    casilla = " ";
                }
                if (tablero[i][j] == FICHA_CIRCULO) {
                    casilla = "\033";
                }
                if (tablero[i][j] == FICHA_CRUZ) {
                    casilla = "\044";
                }
                System.out.print("---------------------------");
                System.out.print(" ");

                System.out.print("| " + casilla + " ");
            }

            System.out.println("|");
        }


    }

    public static boolean colocarFicha(int[][] tablero, int ficha, int columna) {//Esta función comprueba si hay fichas colocadas y las coloca según esté vacía.

        boolean ficha_colocada = false;
        int i = tablero.length - 1;


        while (tablero[i][columna] != CASILLA_VACIA && i > 0) {//Si la casilla no está vacía le resta al contador i -1, Si no detecta ninguna casilla vacía devuelve false.
            i--;
        }
        if (tablero[i][columna] == CASILLA_VACIA) {//Si después de la comprobación anterior detecta una casilla vacía coloca la ficha y devuelve true.
            tablero[i][columna] = ficha;
            ficha_colocada = true;
        }

        return ficha_colocada;//Returna el valor asignado en las condiciones si no pasa por ninguna condición es que no se ha podido colocar la ficha.
    }

    public static boolean comprobarLinea(int[][] tablero, int columna, int n){



        return false;
    }
}


