package org.example;

public class Main {
    public static void main(String[] args) {
        Thread timeThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime) / 1000;
                System.out.println("Пройшло " + elapsedTime + " секунд");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timeThread.start();

        Thread messageThread = new Thread(() -> {
            while (true) {
                System.out.println("Минуло 5 секунд");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        messageThread.start();
    }
}
