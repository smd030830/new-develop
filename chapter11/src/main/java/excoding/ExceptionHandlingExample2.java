package excoding;

public class ExceptionHandlingExample2 {
    public static  void printLength(String data){
        try{
            int result = data.length();
            System.out.println("result : "+result);
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }finally{
            System.out.println("finally block");
        }
    }
}
