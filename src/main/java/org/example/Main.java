package org.example;

import java.util.Random;

public class Main {
    private static final double RIDER_MEAN = 30.0;
    private static final double BUS_MEAN = 1200.0;

    public static void main(String[] args) {
        BusStop busStop = new BusStop(50);
        Random random = new Random();

        new Thread(() -> {
            while (true) {
                try {
                    double busArrivalTime = generateExponential(BUS_MEAN, random);
                    System.out.println("Next bus will arrive in " + busArrivalTime + " seconds.");
                    Thread.sleep((long) (busArrivalTime * 1000));
                    new Bus(busStop).start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    double riderArrivalTime = generateExponential(RIDER_MEAN, random);
                    System.out.println("Next rider will arrive in " + riderArrivalTime + " seconds.");
                    Thread.sleep((long) (riderArrivalTime * 1000));
                    new Rider(busStop).start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static double generateExponential(double mean, Random random) {
        return -mean * Math.log(1 - random.nextDouble());
    }
}