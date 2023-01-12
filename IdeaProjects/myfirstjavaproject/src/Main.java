import java.util.Scanner;
public class Main{
    public static void main(String args[]){
        int remainder,reverse_num=0,temp;
        Scanner myobj=new Scanner(System.in);
        int num=myobj.nextInt();
        System.out.println(num+"Enter the number to be checked");
        temp=num;
        while(num>0){
            remainder=num%10;
            reverse_num=reverse_num*10+remainder;
            num=num/10;
        }
        if(temp==reverse_num)
            System.out.println("palindrome number ");
        else
            System.out.println("not palindrome");
    }
}

