package excoding;

public class MyResource implements AutoCloseable{
    private  String name;
    public MyResource(String name){
        this.name=name;
        System.out.println(name +" 열기");
    }
    public String read1(){
        System.out.println(name + "1 읽기");
        return "100";
    }
    public String read2(){
        System.out.println(name+"2 읽기");
        return "abc";
    }
    @Override
    public void close() throws Exception {
        System.out.println(name +"닫기");
    }
}
