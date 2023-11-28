import java.util.Scanner;
public class Main {
    public static final boolean partida_fin = false;
    public static final int casilla_vacia = 0;
    public static final int ficha_circulo = 1;
    public static final int ficha_cruz = 2;

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        int filas_texto = funciones.leerInt("Introduce el valor de las filas.");
        int columnas_texto = funciones.leerInt("Introduce el valor de las columnas");//Leemos el valor introducido por el usuario//Y a continuación creamos un Array que en vez de asignar nosotros su valor lea Columnas_Texto
        if (columnas_texto < filas_texto){
            System.out.println("No has introducido unos valores válidos! Tiene que haber más columnas que filas!");
            return;
        }

        int[][] tablero = new int[filas_texto][columnas_texto];//Para int tablero vamos a asignarle los valores leidos por teclado.
        funciones.mostrarTablero(tablero);

        int modo = funciones.leerInt("Seleccione el modo de juego:"+"\n"+"1. Un jugador"+"\n"+"2. Dos Jugadores");//Leemos que ha introducido el usuario(1(un jugador) o 2(dos jugadores)).

        if (modo == 1) {//Si el modo de juego es de un jugador se activa la condición de un jugador

            System.out.println("Introduce tu nombre jugador!");
            String nombre_j1 = leer.next();

            System.out.println("Introduce que nivel de IA deseas: "+"\n"+"1. Nivel 1"+"\n"+"2. Nivel 2");
            int ia_lvl = leer.nextInt();


            while (!partida_fin){
                System.out.println("Prepárate "+nombre_j1);

            }


        } else if (modo == 2) {//Si el modo de juego es de dos jugadores se activa la condición dos jugadores
            System.out.println("Introduce tu nombre jugador 1!");
            String nombre_j1 = leer.next();
            System.out.println("Introduce tu nombre jugador 2!");
            String nombre_j2 = leer.next();
            System.out.println("Estáis listos "+nombre_j2+" y "+nombre_j1+"? Empecemos!");

        }
    }
}