import java.util.Scanner;
public class HCF {
    public static void main(String[] args) {

        int remainder;
        Scanner number1 = new Scanner(System.in);
        System.out.println("Enter number:");

        int num1 = number1.nextInt();

        System.out.println("Enter the number:" + num1);


        Scanner number2 = new Scanner(System.in);
        System.out.println("Enter number:");

        int num2 = number2.nextInt();

        System.out.println("Enter the number:" + num2);

        int result1 = (num1 < num2) ? num1 : num2;
        int result2 = (num1 > num2) ? num1 : num2;

        remainder = result1 % result2;

        if (num1 % result1 == 0 & num2 % result1 == 0) {
            System.out.println(" The GCD of the two integers is %d" + result1);
        } else if (num1 % result1 != 0 || num2 % result1 != 0) {
            System.out.println("The GCD of the two integers is %d  " + remainder);
        } else {
            System.out.println("The GCD is 1");
        }

    }

}
