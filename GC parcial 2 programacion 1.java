 import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

    
    static Persona[] registrosPersonas = new Persona[100];
    static int contadorRegistros = 0;

    public static void main(String[] args) {
        // Crear Scanner para entrada de usuario
        Scanner scanner = new Scanner(System.in);

        // Pedir el nombre de la persona
        System.out.print("Ingresa tu nombre: ");
        String nombrePersona = scanner.nextLine();

        // Pedir la edad de la persona
        System.out.print("Ingresa tu edad: ");
        int edadPersona = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea restante

        // Crear un nuevo objeto Persona y agregarlo al array
        if (contadorRegistros < registrosPersonas.length) {
            registrosPersonas[contadorRegistros] = new Persona(nombrePersona, edadPersona);
            contadorRegistros++; // Incrementar el contador de registros
        } else {
            System.out.println("No se puede registrar más personas. El array está lleno.");
        }

        String[] palabras = {
            "gato", "perro", "pájaro",
            "manzana", "banana", "naranja",
            "rojo", "azul", "verde"
        };

        // Ordenar las palabras antes de jugar
        ordenarPalabras(palabras);

        // Pedir al usuario que ingrese la palabra a buscar
        System.out.print("Ingresa una palabra para buscar: ");
        String palabraBuscada = scanner.nextLine();

        // Buscar la palabra
        if (buscarPalabra(palabras, palabraBuscada)) {
            System.out.println("La palabra '" + palabraBuscada + "' se encuentra en la lista.");
        } else {
            System.out.println("La palabra '" + palabraBuscada + "' no se encuentra en la lista.");
        }

        // Elegir una palabra aleatoria
        Random rand = new Random();
        String palabraAdivinar = palabras[rand.nextInt(palabras.length)];

        // Inicializar variables del juego
        char[] letrasAdivinadas = new char[26];
        int contador = 0;
        int intentos = 6;

        // Comenzar el juego
        while (intentos > 0) {
            // Mostrar el progreso de la palabra
            StringBuilder progreso = new StringBuilder();
            for (char letra : palabraAdivinar.toCharArray()) {
                if (new String(letrasAdivinadas, 0, contador).indexOf(letra) >= 0) {
                    progreso.append(letra);
                } else {
                    progreso.append('_');
                }
            }
            System.out.println("Palabra: " + progreso);

            // Pedir al usuario que adivine una letra
            System.out.print("Adivina una letra: ");
            char letra = scanner.nextLine().toLowerCase().charAt(0);

            // Comprobar si la letra está en la palabra
            if (palabraAdivinar.indexOf(letra) >= 0) {
                letrasAdivinadas[contador] = letra; // Guardar letra adivinada
                contador++; // Incrementar el contador
                System.out.println("¡Correcto!");
            } else {
                intentos--;
                System.out.println("Incorrecto. Te quedan " + intentos + " intentos.");
            }

            // Comprobar si el jugador ha ganado
            if (progreso.toString().indexOf('_') < 0) {
                System.out.println("¡Ganaste! La palabra era: " + palabraAdivinar);
                break;
            }
        }

        if (intentos == 0) {
            System.out.println("Perdiste. La palabra era: " + palabraAdivinar);
        }

        // Preguntar si quiere eliminar su registro
        System.out.print("¿Quieres eliminar tu registro (sí/no)? ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("sí") || respuesta.equalsIgnoreCase("si")) {
            eliminarRegistro(nombrePersona);
        }

        // Imprimir los registros actuales
        System.out.println("\nRegistros de personas:");
        for (int i = 0; i < contadorRegistros; i++) {
            Persona persona = registrosPersonas[i];
            System.out.println("Persona " + (i + 1) + ": " + persona.getNombre() + ", Edad: " + persona.getEdad());
        }

        scanner.close();
    }

    public static void ordenarPalabras(String[] palabras) {
        for (int i = 0; i < palabras.length - 1; i++) {
            for (int j = 0; j < palabras.length - 1 - i; j++) {
                if (palabras[j].compareTo(palabras[j + 1]) > 0) {
                    // Intercambiar palabras
                    String temp = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = temp;
                }
            }
        }
    }

    public static boolean buscarPalabra(String[] palabras, String palabraBuscada) {
        for (String palabra : palabras) {
            if (palabra.equalsIgnoreCase(palabraBuscada)) {
                return true; // Palabra encontrada
            }
        }
        return false; // Palabra no encontrada
    }

    // Método para eliminar un registro de persona
    public static void eliminarRegistro(String nombrePersona) {
        for (int i = 0; i < contadorRegistros; i++) {
            Persona persona = registrosPersonas[i];
            if (persona.getNombre().equals(nombrePersona)) {
                // Eliminar el registro moviendo todos los registros hacia atrás
                for (int j = i; j < contadorRegistros - 1; j++) {
                    registrosPersonas[j] = registrosPersonas[j + 1];
                }
                registrosPersonas[contadorRegistros - 1] = null; // Limpiar el último registro
                contadorRegistros--; // Reducir el contador
                System.out.println("El registro de persona '" + nombrePersona + "' ha sido eliminado.");
                return;
            }
        }
        System.out.println("No se encontró el registro de persona '" + nombrePersona + "'.");
    }
}

// Clase Persona para almacenar nombre y edad
class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}


