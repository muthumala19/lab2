package org.example;

import java.util.concurrent.Semaphore;

public class BusStopProperties {
    public final Integer maxCapacity = 50;
    public final Semaphore mutex = new Semaphore(1);
    public final Semaphore busArrived = new Semaphore(0);
    public final Semaphore ridersBoarded = new Semaphore(0);
}
