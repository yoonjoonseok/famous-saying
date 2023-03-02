package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        long id = 0;
        Scanner sc = new Scanner(System.in);
        String input = "";
        String input2 = "";
        Map<Long,FamousSaying> map = new LinkedHashMap<>();

        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.print("명언) ");
            input = sc.nextLine();

            if(input.equals("등록")){
                System.out.print("명언: ");
                input = sc.nextLine();
                System.out.print("작가: ");
                input2 = sc.nextLine();
                FamousSaying fs = new FamousSaying(++id,input,input2);
                map.put(id,fs);
                System.out.printf("%d번 명언이 등록되었습니다\n",id);
            } else if(input.equals("목록")) {
                for(long key:map.keySet())
                    System.out.printf("%d / %s / %s\n",key,map.get(key).getFamousSaying(),map.get(key).getAuthor());
            }else if(input.matches("삭제\\?id=[0-9]+")){
                long id2 = Long.parseLong(input.replace("삭제?id=",""));
                map.remove(id2);
                System.out.printf("%d번 명언이 삭제되었습니다\n",id2);
            }
            else if(input.equals("종료"))
                break;
            else{
                System.out.println("잘못된 명령입니다");
            }
        }
    }

    static class FamousSaying{
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