import java.util.Scanner;

public class Pyramid {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        int num = in.nextInt();

        String space = " ";
        String x = "X";

        int h = num;
        int countFirstLineSpace = h - 1;

        for (int i = 1; i <= h; i++) {
            for (int j = countFirstLineSpace; j > 0; j--) {
                System.out.print(space);
            }
            countFirstLineSpace--;

            for (int k = 1; k <= (i * 2 - 1); k++) {
                System.out.print(x);
            }

            System.out.println();
        }

    }
}
