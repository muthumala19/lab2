package org.example;

import java.util.concurrent.Semaphore;

class BusStop {
    private final int maxCapacity;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore busArrived = new Semaphore(0);
    private final Semaphore allAboard = new Semaphore(0);
    private int waitingRiders = 0;

    public BusStop(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void riderWaitAndBoard() throws InterruptedException {
        mutex.acquire();
        waitingRiders++;
        System.out.println("Rider waiting. Total riders: " + waitingRiders);
        mutex.release();

        busArrived.acquire();
        boardBus();
    }

    private void boardBus() throws InterruptedException {
        System.out.println("Rider boarding.");
        mutex.acquire();
        waitingRiders--;
        if (waitingRiders == 0) {
            allAboard.release();
        }
        mutex.release();
    }

    public void busArrives() throws InterruptedException {
        mutex.acquire();
        if (waitingRiders > 0) {
            System.out.println("Bus arrived. Waiting for riders to board...");
            for (int i = 0; i < Math.min(waitingRiders, maxCapacity); i++) {
                busArrived.release();
            }
            mutex.release();

            allAboard.acquire();
        } else {
            System.out.println("Bus arrived but no riders. Departing immediately.");
            mutex.release();
        }
    }
}