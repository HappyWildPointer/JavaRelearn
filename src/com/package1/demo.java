package com.package1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class demo {
    public static void main(String[] args) throws IOException {
        String path = "src/com/package1/demo.txt";
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        for(int i=0; i<10; i++){
            writer.write("000"+i+"\n");
        }
        for(int i=10; i<99; i++){
            writer.write("00"+i+"\n");
        }
        for(int i=100; i<999; i++){
            writer.write("0"+i+"\n");
        }
        for(int i=1000; i<10000; i++){
            writer.write(+i+"\n");
        }
    }
}
