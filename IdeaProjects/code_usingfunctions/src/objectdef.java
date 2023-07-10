 class objectdef {
    int id ;
    String name ;

     public objectdef(int id , String name) {
         this . id = id ;
         this . name = name ;
     }
 }

class student{
    public static void main(String[] args) {
        objectdef stu = new objectdef(11,"dhruva") ;
        System.out.println(stu.id);
        System.out.println(stu.name);

    }
}
