import java.util.Scanner;
import java.lang.Math;
public class Linear_search {
    public static void main(String[]args)
    {
        int[] arr={40,20,30,5,3,700};
        int  n=arr.length;
        Scanner num_elements=new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int n=num_elements.nextInt();
        System.out.println("Enter the number of elements"+n);
        for(int i=0;i<n;i++){
        Scanner arr_elements=new Scanner(System.in)
        //System.out.println("Enter the elements into the array")
        //int i=arr_elements.next.Int();

        //System.out.println("Enter the elements in the array"+arr[i])
        //system
        //}
       Scanner myobj=new Scanner(System.in);
        System.out.println("Enter number:");

        int num=myobj.nextInt();
        System.out.println("Enter the number:"+num);

        for(int i=0;i<n;i++)
        {
           if (num==arr[i])
           {
               System.out.println("The element to be found is at index : "+i);
           }

        }


    }
}
