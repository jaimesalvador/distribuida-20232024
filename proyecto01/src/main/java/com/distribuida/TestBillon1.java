package com.distribuida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class TestBillon1 {
    private static final String FILE = "c:/1brc/measurements.txt";


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Leyendo archivo");

        BufferedWriter bfw = new BufferedWriter( new FileWriter(FILE + ".1"));
        BufferedReader bfr = new BufferedReader(new FileReader(FILE));

        String line = bfr.readLine();

        while(line!=null) {
            if(line.startsWith("Abha")) {
                bfw.write(line);
                bfw.write(System.lineSeparator());
            }

            line = bfr.readLine();
        }
        bfw.close();;
        long end = System.currentTimeMillis();

        System.out.printf("Time: %d", (end-start) );
    }
}
