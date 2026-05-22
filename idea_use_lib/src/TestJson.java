import com.google.gson.Gson;

public class TestJson {
    public void runTestJson() {
        Gson gson = new Gson();
        int[] intArray = {1, 2, 3, 4, 5, 6};
        String s1 = gson.toJson(intArray);
        System.out.println(s1);

        Myclass myclass = new Myclass();
        myclass.setMyarray(intArray);
        String s2 = gson.toJson(myclass);
        System.out.println(s2);
    }
}
