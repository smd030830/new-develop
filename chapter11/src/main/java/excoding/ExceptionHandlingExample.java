package excoding;

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        String[] array = {"100", "1oo"};

        for (int i = 0; i < array.length; i++) {
            try {
                int value = Integer.parseInt(array[i]);
                System.out.println("array[" + i + "]" + value);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Array index out of bounds"+e.getMessage());
//            }catch (Exception e){
//                System.out.println("problem");
//            }
            }catch (NullPointerException|NumberFormatException e) {
                System.out.println("문제있음"+e.getMessage());
            }
        }
    }
}
