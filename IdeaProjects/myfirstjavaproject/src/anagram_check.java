import java.util.Scanner;
public class anagram_check {
    public static void main(String[] args) {

        String word1="dhruva";
        String word2="rudvah";
                System.out.println("String:" + word1);
        char[] arr = word1.toCharArray();
        System.out.println("Character array");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "  ");
        }


        System.out.println("String:" + word2);
        char[] arr1 = word2.toCharArray();
        System.out.println("Character array");
        for (int j = 0; j < arr1.length; j++) {
            System.out.println(arr1[j] + "  ");
        }
        char temp;
        int l=arr.length;
        int l1=arr1.length;
        if(l!=l1)
        {
            System.out.print("The words do not form an anagram");
        }

        for(int i=0;i<l;i++)
        {
            temp=arr[i];
            for(int j=0;j<l1;j++)
            {

               }
            if(arr1[j]!=temp){
            System.out.print("The words do not form an anagram");

            return  ;


               System.out.print("The words form an anagram");


            }

        }
        System.out.print("The words form an anagram");




    }
}


