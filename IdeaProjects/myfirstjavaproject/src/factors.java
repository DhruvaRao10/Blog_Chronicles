import java.util.Scanner;
import java.lang.Math;
public class factors {
    public static void main(String[] args)
    {
        int factor=0;
        Scanner myobj=new Scanner(System.in);

        System.out.println("Enter number:");

        int num=myobj.nextInt();

        System.out.println("Number: " + num);

        for(int i=1;i<=10;++i)
        {
            if(num%i==0)

            {
                System.out.println("The factors of the number are:" +i);

            }
        }

        }
    }

