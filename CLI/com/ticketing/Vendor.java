package com.ticketing;

public class Vendor implements Runnable {
    private final TicketPool pool;
    private final int ticketReleaseRate;

    public Vendor(TicketPool pool, int ticketReleaseRate) {
        this.pool = pool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (!pool.allTicketsSold()) {
            pool.addTickets(ticketReleaseRate);
            try {
                Thread.sleep(1000 / ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
