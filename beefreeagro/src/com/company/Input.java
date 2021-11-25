package com.company;

import java.io.File;
import java.util.Scanner;

public class Input {
    public String getFileName(String path){
        File file = new File(path);
        Scanner scanner = new Scanner(path);

        StringBuilder sb = new StringBuilder();
        while(scanner.hasNext()){
            sb.append(scanner.nextLine());
            sb.append("\n");
        }

        return sb.toString();
    }
}
