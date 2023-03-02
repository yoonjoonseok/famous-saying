package org.example;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class App {
    private final Scanner sc;
    private long id = 0;
    private Map<Long, FamousSaying> map = new LinkedHashMap<>();
    public App(Scanner sc) {
        this.sc = sc;
    }
    public void run() {
        String input = "";
        repositoryLoad();

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명언) ");
            input = sc.nextLine();

            if (input.equals("등록")) {
                register();
            } else if (input.equals("목록")) {
                list();
            } else if (input.matches("삭제\\?id=[0-9]+")) {
                delete(input);
            } else if (input.matches("수정\\?id=[0-9]+")) {
                modify(input);
            } else if (input.equals("빌드")) {
                build();
            } else if (input.equals("종료"))
                break;
            else
                System.out.println("잘못된 명령입니다");
        }
        sc.close();

        repositorySave();
    }

    void register(){
        System.out.print("명언: ");
        String input = sc.nextLine();
        System.out.print("작가: ");
        String input2 = sc.nextLine();
        FamousSaying fs = new FamousSaying(++id, input, input2);
        map.put(id, fs);
        System.out.printf("%d번 명언이 등록되었습니다\n", id);
    }
    void list(){
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for (long key : map.keySet())
            System.out.printf("%d / %s / %s\n", key, map.get(key).getFamousSaying(), map.get(key).getAuthor());
    }
    void delete(String input){
        long id2 = Long.parseLong(input.replace("삭제?id=", ""));
        if (map.containsKey(id2)) {
            map.remove(id2);
            System.out.printf("%d번 명언이 삭제되었습니다\n", id2);
        } else
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id2);
    }
    void modify(String input){
        long id2 = Long.parseLong(input.replace("수정?id=", ""));
        System.out.printf("명언(기존) : %s\n", map.get(id2).getFamousSaying());
        System.out.print("명언: ");
        input = sc.nextLine();
        System.out.printf("작가(기존) : %s\n", map.get(id2).getAuthor());
        System.out.print("작가: ");
        String input2 = sc.nextLine();
        map.get(id2).setFamousSaying(input);
        map.get(id2).setAuthor(input2);
    }
    void build(){
        List<JSONObject> list = new ArrayList<>();
        for (long key : map.keySet()) {
            JSONObject obj = new JSONObject();
            obj.put("id", map.get(key).getId());
            obj.put("content", map.get(key).getFamousSaying());
            obj.put("author", map.get(key).getAuthor());
            list.add(obj);
        }
        try {
            FileWriter fwriter = new FileWriter("data.json");
            fwriter.write(list.toString());
            fwriter.flush();
            fwriter.close();
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void repositoryLoad(){
        File file = new File("repository.txt");

        try {
            file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = "";
            while ((str = br.readLine()) != null) {
                String[] strings = str.split("/");
                id = Long.parseLong(strings[0]);
                map.put(id, new FamousSaying(id, strings[1], strings[2]));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void repositorySave(){
        File file = new File("repository.txt");
        try {
            FileWriter fwriter = new FileWriter(file);
            for (long key : map.keySet())
                fwriter.write(key + "/" + map.get(key).getFamousSaying() + "/" + map.get(key).getAuthor() + "\n");
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
