package org.example.famousSaying.controller;

import org.example.Container;
import org.example.famousSaying.entity.FamousSaying;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class FamousSayingController {
    private long id = 0;
    private Map<Long, FamousSaying> map = new LinkedHashMap<>();

    public void register(){
        System.out.print("명언: ");
        String input = Container.getScanner().nextLine();
        System.out.print("작가: ");
        String input2 = Container.getScanner().nextLine();
        FamousSaying fs = new FamousSaying(++id, input, input2);
        map.put(id, fs);
        System.out.printf("%d번 명언이 등록되었습니다\n", id);
    }
    public void list(){
        List<Long> alKeys = new ArrayList<Long>(map.keySet());
        Collections.reverse(alKeys);

        System.out.println("번호 / 작가 / 명언\n----------------------");
        for (long key : alKeys)
            System.out.printf("%d / %s / %s\n", key, map.get(key).getFamousSaying(), map.get(key).getAuthor());
    }
    public void delete(String input){
        long id2 = Long.parseLong(input.replace("삭제?id=", ""));
        if (map.containsKey(id2)) {
            map.remove(id2);
            System.out.printf("%d번 명언이 삭제되었습니다\n", id2);
        } else
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id2);
    }
    public void modify(String input){
        long id2 = Long.parseLong(input.replace("수정?id=", ""));
        System.out.printf("명언(기존) : %s\n명언: ", map.get(id2).getFamousSaying());
        input = Container.getScanner().nextLine();
        System.out.printf("작가(기존) : %s\n작가: ", map.get(id2).getAuthor());
        String input2 = Container.getScanner().nextLine();
        map.get(id2).setFamousSaying(input);
        map.get(id2).setAuthor(input2);
    }
    public void build(){
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
    public void repositoryLoad(){
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

    public void repositorySave(){
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
