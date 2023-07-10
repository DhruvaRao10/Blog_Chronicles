/*Program to implement digital lock security where system explicitly remember
        last three digit entered*/

import java.util.Scanner;

class CombinationLock {
    private int firstCombinationDigit;
    private int secondCombinationDigit;
    private int thirdCombinationDigit;
    private int firstEnteredDigit;
    private int secondEnteredDigit;
    private int thirdEnteredDigit;
    private boolean open;

    // Constructor
    public CombinationLock(int combinationKey) {
        thirdCombinationDigit = combinationKey % 10;
        combinationKey = combinationKey / 10;
        secondCombinationDigit = combinationKey % 10;
        firstCombinationDigit = combinationKey / 10;
        open = true;
        firstEnteredDigit = -1;
        secondEnteredDigit = -1;
        thirdEnteredDigit = -1;
    }

    public boolean open()
    {
        return open;
    }//open
    public void close ()
    {
        open=false;
        firstEnteredDigit=-1;
        thirdEnteredDigit=-1;
        secondEnteredDigit=-1;
    }//close
public void digit_input( int digit)
{

    firstEnteredDigit=secondEnteredDigit;
        secondEnteredDigit=thirdEnteredDigit;
        int thirdEnteredDigit=digit;
        if(firstEnteredDigit==firstCombinationDigit &&
        secondEnteredDigit==secondCombinationDigit &&thirdEnteredDigit==thirdCombinationDigit)
        open= true;
        }//digit_input

}//class CombinantionLoc
 class DigitalLock
{
    static int securityKey, digit;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
//CombinationLock lock=new CombinationLock();
        System.out.println("please enter security key : ");
        securityKey=sc.nextInt();
        CombinationLock lock=new CombinationLock(securityKey);
//lock.Combinantion_key_input( securityKey);
        System.out.println("lock status : "+ lock.open());
        lock.close();
        System.out.println("lock status : "+ lock.open());
        while (!lock.open())
        {
            System.out.println("please enter digit : ");
            digit=sc.nextInt();
            lock.digit_input(digit);
        }
        System.out.println("lock status : "+ lock.open());
    }//main
}//class DigitalLoc
