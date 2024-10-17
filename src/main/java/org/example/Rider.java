package org.example;

class Rider extends Thread {
    private final BusStop busStop;

    public Rider(BusStop busStop) {
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            busStop.riderWaitAndBoard();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}