package org.example;

class Rider extends Thread {
    private final BusStopProperties busStopProperties;

    public Rider(BusStopProperties busStopProperties) {
        this.busStopProperties = busStopProperties;
    }

    @Override
    public void run() {
        try {
            System.out.println("Rider arrived bus stop, waiting for bus to come.");
            busStopProperties.busArrived.acquire();
            boardBus();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void boardBus() throws InterruptedException {
        System.out.println("Rider boarding.");
        busStopProperties.ridersBoarded.release();
    }
}
