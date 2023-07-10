public class this_test {
    int a,b ;

    this_test(){
        a=15 ;
        b=30 ;
    }

    this_test get(){
        return this ;
    }

    void display(){
        System.out.println("a: "+a+"b :"+b);
    }

    public static void main(String[] args) {
        this_test obj = new this_test() ;
        obj.get().display(); ;
    }


}
