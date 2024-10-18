package org.example;

import java.util.Queue;

class Bus extends Thread {
    private final BusStopProperties busStopProperties;
    private final Queue<Rider> riders;

    public Bus(BusStopProperties busStopProperties, Queue<Rider> riders) {
        this.busStopProperties = busStopProperties;
        this.riders = riders;
    }

    @Override
    public void run() {
        try {
            busStopProperties.mutex.acquire();
            int ridersToBoard = Math.min(riders.size(), busStopProperties.maxCapacity);

            if (ridersToBoard > 0) {
                System.out.println("Bus arrived. Waiting for riders to board...");

                // Signal riders to board
                for (int i = 0; i < ridersToBoard; i++) {
                    riders.poll();
                    busStopProperties.busArrived.release();
                }

                // Wait for all riders to finish boarding
                for (int i = 0; i < ridersToBoard; i++) {
                    busStopProperties.ridersBoarded.acquire();
                }
            } else {
                System.out.println("Bus arrived but no riders. Departing immediately.");
            }
            busStopProperties.mutex.release();
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void depart() {
        System.out.println("Bus departing.");
    }
}
