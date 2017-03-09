package main.java;

import java.util.Arrays;

/**
 * Created by KirillZubov on 3/9/2017.
 */
public class Philosopher implements Runnable {

    static int length = 5;
    private final String philosophersName;
    private String status = "think";
    volatile private static boolean[] forks = new boolean[length];
    private int numforkLeft;
    private int numforkRight;
    private Thread t;
    private int eatOrNoteat = 0;


    private Philosopher(String name, int number) {
        philosophersName = name;
        numforkLeft = number % forks.length;
        numforkRight = (number + 1) % forks.length;
    }

    private String getStatus() {
        return status;
    }

    private void takeLeftFolk() throws InterruptedException {
        if (!forks[numforkLeft]) {
            forks[numforkLeft] = true;
            Thread.sleep(10);
        }else{
            putFolk();
        }
    }

    private void takeRightFolk() throws InterruptedException {
        if (!forks[numforkRight]) {
            forks[numforkRight] = true;
            Thread.sleep(10);
        } else {
            putFolk();
        }
    }

    private void putFolk() throws InterruptedException {
        Thread.sleep(10);
        forks[numforkLeft] = false;
        Thread.sleep(10);
        forks[numforkRight] = false;

    }

    private void tryEat() throws InterruptedException {
        if (!forks[numforkLeft] & !forks[numforkRight]) {
            takeLeftFolk();
            takeRightFolk();
            eat();
            putFolk();
            eatOrNoteat = 0;
        } else {
            putFolk();
            eatOrNoteat += 1;
        }

    }

    private void eat() throws InterruptedException {
        status = "eat";
        Thread.sleep(20);
    }


    private void think() throws InterruptedException {
        status = "think";
        Thread.sleep(50);
    }

    private void isHungryPhilopher() {
        if (eatOrNoteat > 10) {
            System.out.println("Philopher   is   Hungry");
            return;
        }
    }

    @Override
    public void run() {
        System.out.println("Running " + philosophersName);
        try {
            while (true) {
                tryEat();
                System.out.println(philosophersName + "  " + status + " " + eatOrNoteat+" "+ Arrays.toString(forks));
                think();
                isHungryPhilopher();
            }
        } catch (InterruptedException e) {
            System.out.println("philosopher" + philosophersName + " interrupted.");
        }
        System.out.println("philosopher" + philosophersName + " exiting.");
    }

    private void start() {
        if (t == null) {
            t = new Thread(this, philosophersName);
            t.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Philosopher f1 = new Philosopher("Aristotel", 0);
        Philosopher f2 = new Philosopher("Socrat", 1);
        Philosopher f3 = new Philosopher("Platon", 2);
        Philosopher f4 = new Philosopher("Nishce", 3);
        Philosopher f5 = new Philosopher("Gomer", 4);
        f1.start();
        f2.start();
        f3.start();
        f4.start();
        f5.start();


    }


}
