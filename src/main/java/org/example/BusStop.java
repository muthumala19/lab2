package org.example;

import java.util.LinkedList;
import java.util.Queue;

class BusStop {
    private final BusStopProperties busStopProperties;
    private final Queue<Rider> riderQueue;

    public BusStop() {
        this.busStopProperties = new BusStopProperties();
        this.riderQueue = new LinkedList<>();
    }

    public void addRider() throws InterruptedException {
        Rider rider = new Rider(busStopProperties);
        rider.start();
        this.busStopProperties.mutex.acquire();
        this.riderQueue.add(rider);
        this.busStopProperties.mutex.release();
    }

    public void arriveBus() throws InterruptedException {
        Bus bus = new Bus(busStopProperties, riderQueue);
        bus.start();
    }

}