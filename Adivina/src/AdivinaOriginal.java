import java.util.Scanner;

public class AdivinaOriginal {
    public static void main(String[] args) throws Exception {
        int numAzar = generarNumero(MAXIMO);
        int numUsuario;
        System.out.println(
                "\n*************************************\n*   Juego del Adivinar 2ºDAM 2023   *\n*************************************\n");

        while (!acierto) {
            numUsuario = LeerNumero();
            infoUsuario(infoNumero(numUsuario, numAzar));
        }
    }

    public static boolean acierto = false;
    public static final int MAXIMO = 100;
    public static Scanner sc;

    public static int generarNumero(int max) {
        return (int) (Math.random() * max);
    }

    public static int LeerNumero() {
        int num = 0;
        sc = new Scanner(System.in);
        System.out.print("\n\tElige un numero: ");

        try {
            num = sc.nextInt();
        } catch (Exception e) {
            LeerNumero();
        }
        return num;
    }

    public static int infoNumero(int num, int azar) {
        if (azar == num) {
            return 0;
        } else if (azar > num) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void infoUsuario(int num) {
        acierto = false;
        if (num == 0) {
            System.out.println("\n*** Enhorabuena has acertado el número ***\n");
            acierto = true;
        } else if (num == 1) {
            System.out.println("\n\tEl número es: --> MAYOR <--");
        } else {
            System.out.println("\n\tEl número es: --> MENOR <--");
        }
    }

}