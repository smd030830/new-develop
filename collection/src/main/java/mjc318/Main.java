package mjc318;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        List<Board> list = new ArrayList<>();

        list.add(new Board("제목1","내용1","글쓴이1"));
        list.add(new Board("제목2","내용2","글쓴이2"));
        list.add(new Board("제목3","내용3","글쓴이3"));
        list.add(new Board("제목4","내용4","글쓴이4"));
        list.add(new Board("제목5","내용5","글쓴이5"));

        int size = list.size();
        System.out.println("총 객체 수 : " +size);
        System.out.println();

        Board board = list.get(2);
        System.out.println(board.getSubject()+"\t"+ board.getContent()+"\t"+ board.getSubject()+"\t"+ board.getWriter());

        System.out.println("\t");
        System.out.println("\t");

        for(int i =0;i<list.size();i++){
            Board b = list.get(i);
            System.out.println(b.getSubject()+"\t"+ b.getContent()+"\t"+ b.getWriter());
        }
        System.out.println();

        list.remove(2);
        list.remove(2);

        for(Board b : list){
            System.out.println(b.getSubject()+"\t"+ b.getContent()+"\t"+ b.getWriter());
        }


        List<Board> list2 = new Vector<>();

        Thread threadA = new Thread(){
            @Override
            public void run() {
                for(int i =1;i<=1000;i++){
                    list2.add(new Board("제목"+i,"내용"+i,"글쓴이"+i));
                }
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run() {
                for(int i =1001;i<=2000;i++){
                    list2.add(new Board("제목"+i,"내용"+i,"글쓴이"+i));
                }
            }
        };
        threadA.start();
        threadB.start();

        try{
            threadA.join();
            threadB.join();
        }catch(Exception e){}

        int size1 = list2.size();
        System.out.println();
        System.out.println("총 객체수: "+size1);
        System.out.println();
    }
}
