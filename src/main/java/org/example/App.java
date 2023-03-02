package org.example;

import org.example.famousSaying.controller.FamousSayingController;
import org.example.famousSaying.entity.FamousSaying;
import org.example.system.controller.SystemController;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class App {
    private final Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }
    public void run() {
        FamousSayingController fc = new FamousSayingController(sc);
        SystemController syc = new SystemController();
        String input = "";
        fc.repositoryLoad();

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명언) ");
            input = sc.nextLine();

            if (input.equals("등록")) {
                fc.register();
            } else if (input.equals("목록")) {
                fc.list();
            } else if (input.matches("삭제\\?id=[0-9]+")) {
                fc.delete(input);
            } else if (input.matches("수정\\?id=[0-9]+")) {
                fc.modify(input);
            } else if (input.equals("빌드")) {
                fc.build();
            } else if (input.equals("종료")){
                syc.exit();
                break;
            }
            else
                System.out.println("잘못된 명령입니다");
        }
        sc.close();

        fc.repositorySave();
    }
}
