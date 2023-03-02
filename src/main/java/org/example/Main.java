package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long id = 0;
        Scanner sc = new Scanner(System.in);
        String input = "";
        String input2 = "";
        Map<Long, FamousSaying> map = new LinkedHashMap<>();

        File file=new File("repository.txt");

        try{
            file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = "";
            while((str = br.readLine()) != null) { // 한 줄 읽고 집어넣고...의 반복
                String[] strings = str.split("/");
                id = Long.parseLong(strings[0]);
                map.put(id,new FamousSaying(id,strings[1],strings[2]));
            }
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명언) ");
            input = sc.nextLine();

            if (input.equals("등록")) {
                System.out.print("명언: ");
                input = sc.nextLine();
                System.out.print("작가: ");
                input2 = sc.nextLine();
                FamousSaying fs = new FamousSaying(++id, input, input2);
                map.put(id, fs);
                System.out.printf("%d번 명언이 등록되었습니다\n", id);
            } else if (input.equals("목록")) {
                System.out.println("번호 / 작가 / 명언\n----------------------");
                for (long key : map.keySet())
                    System.out.printf("%d / %s / %s\n", key, map.get(key).getFamousSaying(), map.get(key).getAuthor());
            } else if (input.matches("삭제\\?id=[0-9]+")) {
                long id2 = Long.parseLong(input.replace("삭제?id=", ""));
                if (map.containsKey(id2)) {
                    map.remove(id2);
                    System.out.printf("%d번 명언이 삭제되었습니다\n", id2);
                } else
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", id2);
            } else if (input.matches("수정\\?id=[0-9]+")) {
                long id2 = Long.parseLong(input.replace("수정?id=", ""));
                System.out.printf("명언(기존) : %s\n", map.get(id2).getFamousSaying());
                System.out.print("명언: ");
                input = sc.nextLine();
                System.out.printf("작가(기존) : %s\n", map.get(id2).getAuthor());
                System.out.print("작가: ");
                input2 = sc.nextLine();
                map.get(id2).setFamousSaying(input);
                map.get(id2).setAuthor(input2);
            } else if (input.equals("종료"))
                break;
            else {
                System.out.println("잘못된 명령입니다");
            }
        }
        sc.close();
        try{
            FileWriter fwriter = new FileWriter(file);
            for(long key: map.keySet())
                fwriter.write(key+"/"+map.get(key).getFamousSaying()+"/"+map.get(key).getAuthor()+"\n");
            fwriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FamousSaying {
        private long id;
        private String famousSaying;
        private String author;

        public FamousSaying(long id, String famousSaying, String author) {
            this.id = id;
            this.famousSaying = famousSaying;
            this.author = author;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFamousSaying() {
            return famousSaying;
        }

        public void setFamousSaying(String famousSaying) {
            this.famousSaying = famousSaying;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}