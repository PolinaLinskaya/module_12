package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BuzzFizzMultithreaded {
    private int n;
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public BuzzFizzMultithreaded(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                queue.put("fizz");
            }
        }
    }

    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                queue.put("buzz");
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            queue.put("fizzbuzz");
        }
    }

    public void number() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                queue.put(Integer.toString(i));
            } else {
                if (i % 3 == 0 && i % 5 == 0) {
                    queue.put("fizzbuzz");
                } else if (i % 3 == 0) {
                    queue.put("fizz");
                } else {
                    queue.put("buzz");
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        BuzzFizzMultithreaded solution = new BuzzFizzMultithreaded(n);

        Thread threadA = new Thread(() -> {
            try {
                solution.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                solution.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                solution.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                solution.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!solution.queue.isEmpty()) {
            try {
                System.out.println(solution.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

