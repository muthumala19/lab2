package org.example;

class Bus extends Thread {
    private final BusStop busStop;

    public Bus(BusStop busStop) {
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            busStop.busArrives();
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void depart() {
        System.out.println("Bus departing.");
    }
}