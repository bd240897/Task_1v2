public class Name {
    public static void main(String[] args) {

    }

    public static String Hello(){
        return "Hello";
    }

    public static String HelloAge(int age){
        if(age <= 0) {
            throw new IllegalArgumentException();
        }
        return "Hello " + age;
    }
}
