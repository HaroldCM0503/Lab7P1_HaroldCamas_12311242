/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_haroldcamas_12311242;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author harol
 */
public class Lab7P1_HaroldCamas_12311242 {

    /**
     * @param args the command line arguments
     */
    static Scanner leer = new Scanner (System.in);
    static Random rand = new Random();
    
    public static void main(String[] args) {
        boolean seguir = true;
        int opcion = 0;
        while(seguir){
            opcion = Menu();
            
            switch(opcion){
                case 1:
                    int puntosJ1 = 0;
                    int puntosJ2 = 0;
                    int eleccion = 0;
                    int filas = 0;
                    int columnas = 0;
                    int balas = 0;
                    
                    System.out.println("Elija una cantidad de filas: ");
                    filas = leer.nextInt();
                    filas = Validacion(filas);
                    
                    System.out.println("Elija una cantidad de columnas: ");
                    columnas = leer.nextInt();
                    columnas = Validacion(columnas);
                    
                    if (filas*columnas > 87){
                        System.out.println("La matriz se sale del limite establecido.");
                        break;
                    }
                    int [][] tablero = new int[filas][columnas];
                    tablero = Generar(filas,columnas,tablero);
                    
                    System.out.println("Elija una cantidad de balas: ");
                    balas = leer.nextInt();
                    balas = Validacion(balas);
                    if(balas > filas*columnas/2){
                        System.out.println("No puede elejir tantas balas");
                        break;
                    }
                    int balasJ1 = balas;
                    int balasJ2 = balas;
                    
                    while(balasJ1 > 0){
                        Imprimir(tablero);
                        int turno = 1;
                        boolean estar = true;
                        
                        switch(turno){
                            case 1:
                                System.out.println("Turno Jugador 1:");
                                System.out.println("Tiene: " + balasJ1 + " balas.");
                                System.out.println("Puntos: " + puntosJ1);
                                System.out.println("Elija una opcion a disparar: ");
                                eleccion = leer.nextInt();
                                eleccion = Validacion_Eleccion(eleccion);
                                estar = buscar_matriz(tablero,eleccion);
                                tablero = Disparo(tablero,eleccion,turno);
                                //Si no encuentra el numero elegido no le suma los puntos, si lo halla le suma.
                                if(estar == true){
                                    puntosJ1 += eleccion;
                                }
                                balasJ1 -= 1;
                                turno = 2;
                                //La falta del break es aproposito para que siempre se ejecute el caso 2 que es el turno del jugador 2. El caso 2 es identico al 1 solo que usa las variables que le pertenecen al jugador 2.
                            
                            case 2:
                                Imprimir(tablero);
                                System.out.println("Turno Jugador 2:");
                                System.out.println("Tiene: " + balasJ2 + " balas.");
                                System.out.println("Puntos: " + puntosJ2);
                                System.out.println("Elija una opcion a disparar: ");
                                eleccion = leer.nextInt();
                                eleccion = Validacion_Eleccion(eleccion);
                                estar = buscar_matriz(tablero,eleccion);
                                tablero = Disparo(tablero,eleccion,turno);
                                if(estar == true){
                                    puntosJ2 += eleccion;
                                }
                                balasJ2 -= 1;
                                break;
                        }
                    }
                    System.out.println("El juego se ha terminado!");
                    
                    if(puntosJ1 > puntosJ2){
                        System.out.println("Gano el jugador 1 con: " + puntosJ1 + " puntos!!!");
                    }
                    else if(puntosJ1 < puntosJ2){
                        System.out.println("Gano el jugador 2 con: " + puntosJ2 + " puntos!!!");
                    }
                    else{
                        System.out.println("Quedaron en empate con: " + puntosJ1 + " puntos!!!");
                    }
                    
                    
                    
                    break;
                    
                case 2:
                    int jugador = 0;
                    int maquina = 0;
                    int resultado = 0;
                    int[][] opciones = { {0,2,1,0,1,0}, {1,0,2,1,0,1}, {2,1,0,2,1,0}, {3,0,1,0,2,1},{4,1,0,1,0,2} };
                    System.out.println("Elija una de las opciones: ");
                    Menu_Opciones();
                    jugador = leer.nextInt();
                    jugador = ValidarOpciones(jugador);
                    maquina = rand.nextInt(5) + 1;
                    MaquinaImprimir(maquina);
                    resultado = opciones[jugador - 1][maquina];
                    
                    if(resultado == 0){
                        System.out.println("La maquina gana!!!");
                    }
                    if(resultado == 1){
                        System.out.println("El jugador gana!!!");
                    }
                    if(resultado == 2){
                        System.out.println("Es un empate!!!");
                    }
                    break;
                    
                case 3:
                    seguir = false;
                    break;
                    
                default:
                    System.out.println("Elija una opcion valida");
                    break;
            }
        }
    }
    
    public static int Menu(){
        int op = 0;
        System.out.println("");
        System.out.println("1.)Ejercicio 1");
        System.out.println("2.)Ejercicio 2");
        System.out.println("3.)Salir");
        op = leer.nextInt();
        return op;
    }
    
    public static int[][] Disparo(int[][] matriz, int elemento, int jugador){
        boolean atino = true;
        atino = buscar_matriz(matriz,elemento);
        int [][] nueva = matriz;
        
        //Busca a donde esta el numero seleccionado.
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                
                //Cuando lo halla lo cambia por 99 o 88 dependiendo del turno del jugador.
                if (matriz[i][j] == elemento){
                    System.out.println("Le acertaste!!!");
                    if (jugador == 1){
                        nueva[i][j] = 99;
                    }
                    else{
                        nueva[i][j] = 88;
                    }   
                }
            }
        }
        //Si no lo halla le avisa al jugador que fallo.
        if(atino == false){   
            System.out.println("Fallaste :(");
        }
        return nueva;
    }
    
    public static int[][] Generar(int filas, int columnas,int[][] matriz){
        int numero = 0;
        int conteo = 0;
        int[][] temporal = new int[filas][columnas];
        
        //Esta lista guarda los numeros que ya genero la maquina.
        int [] numeros_ya_generados = new int[filas*columnas];
         for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                boolean esta = true;
                while (esta == true){
                    
                    //Genera un numero y luego lo busca en la lista de arriba, si esta lo fuerza a generar un numero hasta que cree uno completamente nuevo.
                    numero = rand.nextInt(filas*columnas) + 1;
                    esta = buscar(numeros_ya_generados,numero);
                }
                
                //Luego de encontrar uno nuevo los guarda en donde van.
                temporal[i][j] = numero;
                numeros_ya_generados[conteo] += numero;
                conteo++;
            }
         }
         return temporal;
    }
    
    public static boolean buscar(int[] arreglo, int numero){
        //Busca algo dentro de un arreglo.
        boolean esta = false;
        for(int i = 0; i < arreglo.length; i++){
            if(arreglo[i] == numero){
                esta = true;
            }
        }
        return esta;
    }
    
    public static boolean buscar_matriz(int[][] matriz, int elemento){
        //Busca algo dentro de una matriz.
        boolean esta = false;
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                if(matriz[i][j] == elemento){
                    esta = true;
                }
            }
        }
        return esta;
    }
    
    public static void Imprimir(int[][] matriz){
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                System.out.print("[" + matriz[i][j] + "] ");
                if(matriz[i][j] < 10){
                    System.out.print(" ");
                }
            }
             System.out.println("");
        }
        System.out.println("");
    }
    
    public static int Validacion(int numero){
        //Esto se ve feo en el main tan repetido tons mejor lo hago un metodo xd.
        while(numero <= 0){
            System.out.println("Elija un numero mayor que 0.");
            numero = leer.nextInt();
        }
        return numero;
    }
    
    public static int Validacion_Eleccion(int numero){
        //Este es para validar las entradas del disparo.
        while(numero > 87 || numero <= 0){
            System.out.println("Elija un numero dentro del parametro!");
            numero = leer.nextInt();
        }
        return numero;
    }
    
    public static void Menu_Opciones(){
        System.out.println("1.)Scissors");
        System.out.println("2.)Paper");
        System.out.println("3.)Rock");
        System.out.println("4.)Lizard");
        System.out.println("5.)Spock");
    }
    
    public static void MaquinaImprimir(int numero){
        if (numero == 1){
            System.out.println("La maquina elige Scissors!");
        }
        if (numero == 2){
            System.out.println("La maquina elige Paper!");
        }
        if (numero == 3){
            System.out.println("La maquina elige Rock!");
        }
        if (numero == 4){
            System.out.println("La maquina elige Lizard!");
        }
        if (numero == 5){
            System.out.println("La maquina elige Spock!");
        }
    }
    
    public static int ValidarOpciones(int numero){
        while(numero > 5 || numero < 1){
            System.out.println("Elija una opcion valida!");
            numero = leer.nextInt();
        }
        return numero;
    }
    
    
}
