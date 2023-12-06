import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final boolean partida_fin = false;
    public static final int casilla_vacia = 0;
    public static final int ficha_circulo = 1;
    public static final int ficha_cruz = 2;

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        int filas_texto = Funciones.leerInt("Introduce el valor de las filas.");
        int columnas_texto = Funciones.leerInt("Introduce el valor de las columnas");//Leemos el valor introducido por el usuario//Y a continuación creamos un Array que en vez de asignar nosotros su valor lea Columnas_Texto
        if (columnas_texto < filas_texto) {
            System.out.println("No has introducido unos valores válidos! Tiene que haber más columnas que filas!");
            return;
        }




        int[][] tablero = new int[filas_texto][columnas_texto];//Para int tablero vamos a asignarle los valores leidos por teclado.
        Funciones.mostrarTablero(tablero);
        int num_linea = -1;
        do {
            num_linea = Funciones.leerInt("Introduce cuántas fichas son necesarias para completar la fila:");
        }while(num_linea<0||num_linea > tablero.length);
        
        int modo = Funciones.leerInt("Seleccione el modo de juego:" + "\n" + "1. Un jugador" + "\n" + "2. Dos Jugadores");//Leemos que ha introducido el usuario(1(un jugador) o 2(dos jugadores)).


        if (modo == 1) {//Si el modo de juego es de un jugador se activa la condición de un jugador
            Random bot = new Random();
            System.out.println("Introduce tu nombre jugador!");
            String nombre_j1 = leer.next();

            System.out.println("Introduce que nivel de IA deseas: " + "\n" + "1. Nivel 1" + "\n" + "2. Nivel 2");


            int turno = 1;
            while (!partida_fin) {
                boolean colocar = false;
                System.out.println("Prepárate " + nombre_j1);
                int columna_j1 = 0;
                int columna_bot = 0;

                if (turno == 1) {
                    columna_j1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");

                    while (columna_j1 < 0 || columna_j1 > tablero.length) {
                        System.out.println("\u001B[31m" + "Introduce un valor válido" + "\u001B[0m");
                        columna_j1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");
                        colocar = Funciones.colocarFicha(tablero, ficha_circulo, columna_j1);
                        Funciones.mostrarTablero(tablero);

                    }
                    colocar = Funciones.colocarFicha(tablero, ficha_circulo, columna_j1);
                    Funciones.mostrarTablero(tablero);
                    while (!colocar) {
                        System.out.println("\u001B[31m" + "No se ha podido colocar la ficha!" + "\u001B[0m");
                        columna_j1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");
                        colocar = Funciones.colocarFicha(tablero, ficha_circulo, columna_j1);
                        Funciones.mostrarTablero(tablero);
                    }

                    turno--;

                }
                if (turno == 0) {
                    columna_bot = bot.nextInt(tablero.length);
                    Funciones.colocarFicha(tablero, ficha_cruz, columna_bot);
                    turno++;

                }
            }


        } else if (modo == 2) {//Si el modo de juego es de dos jugadores se activa la condición dos jugadores
            System.out.println("Introduce tu nombre jugador 1!");
            String nombre_j1 = leer.next();
            System.out.println("Introduce tu nombre jugador 2!");
            String nombre_j2 = leer.next();
            System.out.println("Estáis listos " + nombre_j2 + " y " + nombre_j1 + "? Empecemos!");

        }
    }
}