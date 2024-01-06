package com.distribuida;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

record BufferSegment(int start, int end) {
}

class MyTask implements Runnable {

    BufferSegment segment;

    //ByteBuffer buffer;
    byte[] data;
    Map<String, double[]> cache;

    public MyTask(BufferSegment segment, byte[] data ) {
        this.segment = segment;
        this.data = data;
        cache = new HashMap<>();
    }

    @Override
    public void run() {
        String line = "";
        String value = "";

        char txt[] = new char[100];
        int index = 0;

        int i = segment.start();
        int end = segment.end();

        while(i<end) {
            if(data[i]=='\n') {
                value = String.valueOf(txt);
                for(int k=0;k<index;k++) txt[k] = '\0';
                index = 0;

                var dd = Float.valueOf(value);

                double[] vals = null;

                if(cache.containsKey(line)) {
                    vals = cache.get(line);
                }
                else {
                    vals = new double[4];
                    vals[0] = 0; //count
                    vals[1] = 0; //sum
                    vals[2] = Double.MAX_VALUE; //min
                    vals[3] = Double.MIN_VALUE; //max
                    cache.put(line, vals);
                }

                vals[0]++; // count
                vals[1] += dd; // sum
                if(dd<vals[2]) vals[2] = dd; //min
                if(dd>vals[3]) vals[3] = dd; //max
            }
            else if(data[i]==';') {
                line = String.valueOf(txt, 0, index);
                for(int k=0;k<index;k++) txt[k] = '\0';
                index = 0;
            }
            else {
                txt[index] = (char )data[i];
                index++;
            }

            i++;
        }

        //System.out.printf("%s\n", segment);
    }
}

public class TestBillon {
    private static final String FILE = "c:/1brc/measurements.txt";

    static List<BufferSegment> getFileSegments( byte[] data)  {

        int numberOfSegments = Runtime.getRuntime().availableProcessors();

        int segmentSize = data.length / numberOfSegments;

        List<BufferSegment> segments = new ArrayList<>(numberOfSegments);

        // Pointless to split small files
        if (segmentSize < 1_000_000) {
            segments.add(new BufferSegment(0, data.length));
            return segments;
        }

        int segStart = 0 * segmentSize;
        for (int i = 0; i < numberOfSegments; i++) {
            int segEnd = segStart + segmentSize;

            while(segEnd<data.length && data[segEnd]!='\n') {
                segEnd++;
            }

            if(i == numberOfSegments - 1) {
                segEnd = data.length;
            }

            segments.add(new BufferSegment(segStart, segEnd));
            segStart = segEnd+1;
        }

        return segments;
    }


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Leyendo archivo");

        var fileChannel = (FileChannel )Files.newByteChannel(Path.of(FILE), StandardOpenOption.READ);

        var size = fileChannel.size();

        ByteBuffer buffer = ByteBuffer.allocate(2*1024*1014*1014);

        int block = 0;
        while(true) {
            int bytesRead = fileChannel.read(buffer);

            System.out.printf("Block %d, bytes %d\n",block++, bytesRead);

            if(bytesRead<=0)
                break;

            byte[] data = buffer.array();

            var segments = getFileSegments(data);

            //segments.forEach(System.out::println);

            List<Thread> threads = new ArrayList<>();
            List<MyTask> tasks = new ArrayList<>();

            for (var seg : segments) {
                var task = new MyTask(seg, data);
                var th = new Thread(task);
                threads.add(th);
                tasks.add(task);
                th.start();
            }

            for (Thread th : threads) {
                th.join();
            }

            // juntar todos los resultados parciales
            TreeMap<String, double[]> sorted = new TreeMap<>();
            for (MyTask task : tasks) {
                task.cache.forEach((key, val) -> {
                    if (!sorted.containsKey(key)) {
                        sorted.put(key, val);
                    } else {
                        var vals = sorted.get(key);

                        vals[0] = vals[0] + val[0];
                        vals[1] = vals[1] + val[1];
                        if (val[2] < vals[2]) vals[2] = val[2];
                        if (val[3] > vals[3]) vals[3] = val[3];
                    }
                });
            }

            sorted.forEach((key,val)->{
                //System.out.printf("%s=%.1f/%.2f/%.1f==>%.0f\n", key, val[2], val[1]/val[0], val[3], val[0]);
            });

            buffer.clear();
        }

        long end = System.currentTimeMillis();

        System.out.printf("Time: %d", (end-start) );
    }

    public static void main2(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Leyendo archivo");

        var fileChannel = (FileChannel )Files.newByteChannel(Path.of(FILE), StandardOpenOption.READ);

        var size = fileChannel.size();

        //MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);

        ByteBuffer buffer = ByteBuffer.allocate((int )size);
        //int bytesRead= fileChannel.read(buffer);

        byte[] data = buffer.array();
        //byte[] data = new byte[sizee];

        int yy = data.length;

        String line = "";
        String value = "";

        char txt[] = new char[50];
        int index = 0;

        int i = 0;
        Map<String, float[]> cache = new HashMap<>();

        int pp = 0;

        buffer.position(0);
        while(i<size) {
            byte c = buffer.get( );

            //if(data[i]=='\n') {
            if(c=='\n') {
                // nueva línea
                value = String.valueOf(txt,0,index);
                index = 0;
                Arrays.fill(txt, '\0');

                var dd = Float.valueOf(value);

//                if(line.startsWith("Abha")) {
//                    //System.out.println(dd);
//                    pp++;
//                }

                float[] vals = null;

                if(cache.containsKey(line)) {
                    vals = cache.get(line);
                }
                else {
                    vals = new float[4];
                    vals[0] = 0; //count
                    vals[1] = 0; //sum
                    vals[2] = Float.MAX_VALUE; //min
                    vals[3] = Float.MIN_VALUE; //max
                    cache.put(line, vals);
                }

                vals[0]++;
                vals[1] += dd;
                if(dd<vals[2]) vals[2] = dd;
                if(dd>vals[3]) vals[3] = dd;
            }
            //else if(data[i]==';') {
                else if(c==';') {
                line = String.valueOf(txt, 0, index);
                Arrays.fill(txt, '\0');
                index = 0;
            }
            else {
                //txt[index] = (char )data[i];
                txt[index] = (char )c;
                index++;
            }

            i++;
        }

        TreeMap<String, float[]> sorted1 = new TreeMap<>();
        sorted1.putAll(cache);

        sorted1.forEach((key,val)->{
            System.out.printf("%s=%.2f/%.2f/%.2f==>%.0f\n", key, val[2], val[1]/val[0], val[3], val[0]);
        });

        System.out.println(pp);

        long end = System.currentTimeMillis();

        System.out.printf("Time: %d", (end-start) );
    }
}
