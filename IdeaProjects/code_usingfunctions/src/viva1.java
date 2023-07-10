import java.util.*;
public class viva1 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the file");
        String file=sc.nextLine();
        int l=file.length();
        System.out.println(l);
        int count=1;
        String y;

        for(int i=0;i<l;i++){

            String t=file.substring(i,i+1);
            if(t.equals(" ")){
                count++;
            }
        }
        System.out.println(count);
    }
}