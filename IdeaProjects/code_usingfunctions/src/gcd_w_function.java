import java.lang.Math;


public class gcd_w_function {
     static void myMethod() {
         int a=6;
         int b=2;
          int greater_num=Math.max(a,b);
          int lesser_num=Math.min(a,b);
          int remainder=greater_num%lesser_num;
         if(greater_num%lesser_num==0)
         {
             System.out.println(lesser_num);

         }
         else {
             System.out.println(remainder)      ;
         }



       }

     public static void main(String[]args){
         myMethod();

    }

}
