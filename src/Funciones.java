

import java.util.Random;
import java.util.Scanner;

public class Funciones {
    public static final int CASILLA_VACIA = 0;
    public static final int FICHA_CIRCULO = 1;
    public static final int FICHA_CRUZ = 2;
    public static boolean PARTIDA_FIN = false;


    public static int leerInt(String mensaje) {
        Scanner leer = new Scanner(System.in);
        System.out.println(mensaje);
        while (!leer.hasNextInt()) {
            leer.nextLine();
            System.out.println("\u001B[31m" + "No se ha introducido un valor válido, Inténtalo de nuevo" + "\u001B[0m");

        }
        int valor2;
        valor2 = leer.nextInt();
        return valor2;
    }

    public static int leerIntValue(String mensaje, int min, int max) {
        int comprobar = leerInt(mensaje);
        Scanner leer = new Scanner(System.in);

        while (comprobar > max || comprobar < min) {
            System.out.println("\u001B[31m" + "No se ha introducido un valor válido" + "\u001B[0m");
            comprobar = leer.nextInt();
        }


        return comprobar;

    }

    public static void mostrarTablero(int[][] tablero) {//Esta función muestra el tablero y modifica los valores de las casillas por otros

        String casilla = null;
        for (int l = 0; l < tablero.length; l++) {
            System.out.print("   " + l + " ");
        }
        System.out.println();
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

                System.out.print(" ");

                System.out.print("| " + casilla + " ");
            }

            System.out.println("|");
        }


    }

    public static void mostrarTablero(int[][] tablero, int columna) {
        final String RESET = "\u001B[0m";
        final String RED_BOLD = "\033[1;31m";

        String casilla;
        for (int l = 0; l < tablero[0].length; l++) {
            System.out.print("   " + l + " ");
        }
        System.out.println();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                casilla = " ";

                if (tablero[i][j] == FICHA_CIRCULO) {
                    casilla = "O";
                } else if (tablero[i][j] == FICHA_CRUZ) {
                    casilla = "X";
                }

                if (j == columna) {
                    if (tablero[i][j] != CASILLA_VACIA) {
                        casilla = RED_BOLD + casilla + RESET;
                    }
                } else {
                    if (tablero[i][j] != CASILLA_VACIA) {
                        casilla = RESET + casilla;
                    }
                }

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
        if (tablero[i][columna] == CASILLA_VACIA) {//Si después de la comprobación anterior detecta una casilla vacía, coloca la ficha y devuelve true.
            tablero[i][columna] = ficha;
            ficha_colocada = true;
        }

        return ficha_colocada;//Returna el valor asignado en las condiciones si no pasa por la condición es que no se ha podido colocar la ficha.
    }

    public static boolean comprobarLinea(int[][] tablero, int columna, int n) {
        int ultima_Fila = -1;
        for (int i = tablero.length - 1; i >= 0; i--) {
            if (tablero[i][columna] != CASILLA_VACIA) {
                ultima_Fila = i;
                break;
            }
        }
        if (ultima_Fila == -1) {
            return false;
        }

        int ficha = tablero[ultima_Fila][columna];
        int contador_horizontal = 0;


        for (int i = 0; i < tablero[0].length; i++) {//añade 1 al contador cuando detecta que en una parte hay una ficha
            if (tablero[ultima_Fila][i] == ficha) {
                contador_horizontal++;
                if (contador_horizontal >= n) {// cuando el contador llegue a 4 devuelve true.
                    return PARTIDA_FIN = true;

                }
            } else {
                contador_horizontal = 0;
            }
        }

// contador vertical
        int contador_vertical = 0;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][columna] == ficha) {
                contador_vertical++;
                if (contador_vertical >= n) {
                    return PARTIDA_FIN = true;
                }
            } else {
                contador_vertical = 0;
            }
        }
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna_c = 0; columna_c < tablero[0].length; columna_c++) {
                int contador = 0;
                for (int k = 0; k < n; k++) {
                    if (fila + k < tablero.length && columna_c + k < tablero[0].length && tablero[fila + k][columna_c + k] == ficha) {
                        contador++;
                        if (contador >= n) {
                            return PARTIDA_FIN = true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna_c = tablero[0].length - 1; columna_c >= 0; columna_c--) {
                int contador = 0;
                for (int k = 0; k < n; k++) {
                    if (fila + k < tablero.length && columna_c - k >= 0 && tablero[fila + k][columna_c - k] == ficha) {
                        contador++;
                        if (contador >= n) {
                            return PARTIDA_FIN = true;
                        }
                    } else {
                        break;
                    }

                }
            }
        }
        return false;
    }

    public static boolean turnoJugador(int tablero[][], String nombre, int ficha, int modo, int turno, int nivel_bot) {


        if (turno == 1 && modo == 1 || modo == 2) {
            int columna = Funciones.leerIntValue("Dónde deseas colocar la ficha " + nombre + "?", 0, tablero.length);

            boolean colocar = Funciones.colocarFicha(tablero, ficha, columna);
            boolean comprobar_j1 = Funciones.comprobarLinea(tablero, columna, 4);
            Funciones.mostrarTablero(tablero, columna);
            if (comprobar_j1) {
                System.out.println("---------------------------------------------------");
                Funciones.mostrarTablero(tablero, columna);
                System.out.println(nombre + "\u001B[32m" + " Ha ganado la partida!" + "\u001B[0m");
                return comprobar_j1;
            }
            while (!colocar) {
                System.out.println("\u001B[31m" + "No se ha podido colocar la ficha!" + "\u001B[0m");
                columna = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre + "?");
                colocar = Funciones.colocarFicha(tablero, ficha, columna);
                Funciones.comprobarLinea(tablero, columna, 4);
                Funciones.mostrarTablero(tablero, columna);
            }

        } else if (modo == 1) {
            Random bot = new Random();
            boolean colocar = false;
            int columna_bot;
            do {
                columna_bot = bot.nextInt(tablero.length);
                colocar = Funciones.colocarFicha(tablero, FICHA_CRUZ, columna_bot);
            } while (!colocar);
            boolean comprobar_bot = Funciones.comprobarLinea(tablero, columna_bot, 4);
            if (comprobar_bot) {
                System.out.println("---------------------------------------------------");
                Funciones.mostrarTablero(tablero, columna_bot);
                System.out.println("El bot" + "\u001B[32m" + " Ha ganado la partida!" + "\u001B[0m");
                return comprobar_bot;
            }
            Funciones.mostrarTablero(tablero, columna_bot);
        }
        return false;
    }

    public static boolean casillasLibres(int[][] tablero) {
        int contador = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != CASILLA_VACIA) {
                    contador++;
                }
            }
        }
        if (contador < tablero.length) {
            return true;
        }
        return false;
    }

}

