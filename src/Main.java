import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        int FICHA_CIRCULO = Funciones.FICHA_CIRCULO;
        int FICHA_CRUZ = Funciones.FICHA_CRUZ;
        boolean PARTIDA_FIN = Funciones.PARTIDA_FIN;

        int filas_texto = Funciones.leerInt("Introduce el valor de las filas.");
        int columnas_texto = Funciones.leerInt("Introduce el valor de las columnas");//Leemos el valor introducido por el usuario//Y a continuación creamos un Array que en vez de asignar nosotros su valor lea Columnas_Texto
        if (columnas_texto < filas_texto) {
            System.out.println("No has introducido unos valores válidos! Tiene que haber más columnas que filas!");
            return;
        }


        int[][] tablero = new int[filas_texto][columnas_texto];//Para int tablero vamos a asignarle los valores leidos por teclado.
        Funciones.mostrarTablero(tablero);
        int modo;

        modo = Funciones.leerIntValue("Seleccione el modo de juego:" + "\n" + "1. Un jugador" + "\n" + "2. Dos Jugadores", 1, 2);//Leemos que ha introducido el usuario(1(un jugador) o 2(dos jugadores)).


        //Si el modo de juego es de un jugador se activa la condición de un jugador
        Random bot = new Random();
        String nombre_j1 = " ";
        String nombre_j2 = " ";
        if (modo == 1) {
            System.out.println("Introduce tu nombre jugador!");
            nombre_j1 = leer.next();
        }
        if (modo == 2) {
            System.out.println("Introduce tu nombre jugador 1");
            nombre_j1 = leer.next();
            System.out.println("Introduce tu nombre jugador 2");
            nombre_j2 = leer.next();
        }

        int nivel_bot = 0;

        if (modo == 1) {
            nivel_bot = Funciones.leerIntValue("Introduce un nivel", 1, 2);
        }


        while (!PARTIDA_FIN) {
            if (modo == 1) {
                System.out.println("Prepárate " + nombre_j1);
                int columna_j1 = 0;
                int columna_bot = 0;
                int contador = 1;
                if (contador == 1) {
                    boolean casillas_libres = Funciones.casillasLibres(tablero);
                    if (!casillas_libres){
                        System.out.println("Empate!");
                        return;

                    }
                    boolean ver = Funciones.turnoJugador(tablero, nombre_j1, FICHA_CIRCULO, modo, 1, nivel_bot);
                    contador--;
                    if (ver) {
                        return;
                    }
                }
                if (contador == 0) {
                    boolean casillas_libres = Funciones.casillasLibres(tablero);
                    if (!casillas_libres){
                        System.out.println("Empate!");
                        return;

                    }
                    boolean ver = Funciones.turnoJugador(tablero, "bot", FICHA_CRUZ, modo, 0, nivel_bot);
                    if (ver) {
                        return;
                    }
                    contador++;
                }


            } else if (modo == 2) {
                int contador = 1;
                if (contador == 1) {
                    boolean ver = Funciones.turnoJugador(tablero, nombre_j1, FICHA_CIRCULO, modo, 1, 1);
                    if (ver) {
                        return;
                    }
                    contador++;
                }
                if (contador == 2) {
                    boolean ver = Funciones.turnoJugador(tablero, nombre_j2, FICHA_CRUZ, modo, 0, 1);
                    if (ver) {
                        return;
                    }
                    contador--;
                }
            }
        }
    }
}
