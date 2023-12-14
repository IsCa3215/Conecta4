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
        do {
            modo = Funciones.leerInt("Seleccione el modo de juego:" + "\n" + "1. Un jugador" + "\n" + "2. Dos Jugadores");//Leemos que ha introducido el usuario(1(un jugador) o 2(dos jugadores)).
        } while (modo > 2 || modo < 1);


        if (modo == 1) {//Si el modo de juego es de un jugador se activa la condición de un jugador
            Random bot = new Random();
            System.out.println("Introduce tu nombre jugador!");
            String nombre_j1 = leer.next();
            int nivel_bot = 0;


            nivel_bot = Funciones.leerIntValue("Introduce un nivel", 1, 2);


            if (nivel_bot == 1) {
                int turno = 1;
                while (!PARTIDA_FIN) {
                    boolean colocar_j1 = false;
                    System.out.println("Prepárate " + nombre_j1);
                    int columna_j1 = 0;
                    int columna_bot = 0;

                    if (turno == 1) {
                        columna_j1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");

                        while (columna_j1 < 0 || columna_j1 > tablero.length) {
                            System.out.println("\u001B[31m" + "Introduce un valor válido" + "\u001B[0m");
                            columna_j1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");

                        }
                        colocar_j1 = Funciones.colocarFicha(tablero, FICHA_CIRCULO, columna_j1);
                        boolean comprobar_j1 = Funciones.comprobarLinea(tablero, columna_j1, 4);
                        Funciones.mostrarTablero(tablero, columna_j1);
                        if (comprobar_j1) {
                            System.out.println("---------------------------------------------------");
                            Funciones.mostrarTablero(tablero, columna_j1);
                            System.out.println(nombre_j1 + "\u001B[32m" + " Ha ganado la partida!" + "\u001B[0m");

                            return;
                        }
                        while (!colocar_j1) {
                            System.out.println("\u001B[31m" + "No se ha podido colocar la ficha!" + "\u001B[0m");
                            columna_j1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");
                            colocar_j1 = Funciones.colocarFicha(tablero, FICHA_CIRCULO, columna_j1);
                            Funciones.comprobarLinea(tablero, columna_j1, 4);
                            Funciones.mostrarTablero(tablero, FICHA_CIRCULO);
                        }

                        turno--;

                    }
                    if (turno == 0) {
                        columna_bot = bot.nextInt(tablero.length);
                        Funciones.colocarFicha(tablero, FICHA_CRUZ, columna_bot);
                        boolean comprobar_bot = Funciones.comprobarLinea(tablero, columna_bot, 4);
                        System.out.println("--------------bot-------------");

                        if (comprobar_bot) {
                            Funciones.mostrarTablero(tablero, FICHA_CIRCULO);
                            System.out.println("El bot" + "\u001B[32m" + " Ha ganado la partida!" + "\u001B[0m");

                            return;
                        }
                        Funciones.mostrarTablero(tablero);
                        turno++;


                    }

                }
            }
            if (nivel_bot == 2){


            }

        } else if (modo == 2) {//Si el modo de juego es de dos jugadores se activa la condición dos jugadores
            int turno = 1;
            int columna_jug1;
            int columna_jug2;
            boolean colocar = false;
            System.out.println("Introduce tu nombre jugador 1!");
            String nombre_j1 = leer.next();
            System.out.println("Introduce tu nombre jugador 2!");
            String nombre_j2 = leer.next();
            System.out.println("Estáis listos " + nombre_j2 + " y " + nombre_j1 + "? Empecemos!");
            while (!PARTIDA_FIN) {


                if (turno == 1) {
                    columna_jug1 = Funciones.leerInt("Dónde irá tu ficha " + nombre_j1 + "?");
                    while (columna_jug1 < 0 || columna_jug1 > tablero.length) {
                        System.out.println("\u001B[31m" + "No has introducido un valor válido" + "\u001B[0m");
                        columna_jug1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");
                        Funciones.mostrarTablero(tablero);
                    }
                    colocar = Funciones.colocarFicha(tablero, FICHA_CIRCULO, columna_jug1);
                    Funciones.mostrarTablero(tablero);
                    while (!colocar) {
                        System.out.println("\u001B[31m" + "No se ha podido colocar la ficha!" + "\u001B[0m");
                        columna_jug1 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j1 + "?");
                        colocar = Funciones.colocarFicha(tablero, FICHA_CIRCULO, columna_jug1);
                        Funciones.mostrarTablero(tablero);
                    }

                    turno--;
                }
                if (turno == 0) {
                    columna_jug2 = Funciones.leerInt("Es tu turno " + nombre_j2);
                    while (columna_jug2 < 0 || columna_jug2 > tablero.length) {
                        System.out.println("\u001B[31m" + "No has introducido un valor válido" + "\u001B[0m");
                        columna_jug2 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j2 + "?");
                        Funciones.mostrarTablero(tablero);
                    }
                    colocar = Funciones.colocarFicha(tablero, FICHA_CRUZ, columna_jug2);
                    Funciones.mostrarTablero(tablero, FICHA_CIRCULO);
                    while (!colocar) {
                        System.out.println("\u001B[31m" + "No se ha podido colocar la ficha!" + "\u001B[0m");
                        columna_jug2 = Funciones.leerInt("Dónde deseas colocar la ficha " + nombre_j2 + "?");
                        colocar = Funciones.colocarFicha(tablero, FICHA_CRUZ, columna_jug2);
                        Funciones.mostrarTablero(tablero, FICHA_CIRCULO);
                    }
                    turno++;
                }
            }


        }
    }
}