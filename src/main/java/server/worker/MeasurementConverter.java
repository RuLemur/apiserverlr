package server.worker;

import server.worker.pojo.InputJson;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by RuLemur on 22.07.2018 in 15:05.
 * testSegmento
 */
public class MeasurementConverter extends Thread {
    private int waitDelay = 1000;

    public MeasurementConverter() {
        System.out.println("create sub thread");
    }

    public final static Queue<InputJson> syncJsonQueue = new ConcurrentLinkedQueue<InputJson>() {
    };

    public void convert(InputJson take) {
        System.out.println(take.toString() + "CONVERTED");
    }

    public void run() {
        System.out.println("thread was runned 2");
        while (true) {

            if (syncJsonQueue.isEmpty()) {
                try {
                    this.sleep(waitDelay);
                    System.out.println("thread waiting");
                } catch (InterruptedException e) {
                    //TODO: log
                    e.printStackTrace();
                }
            } else {
                convert(syncJsonQueue.poll());

            }
        }
    }
}
