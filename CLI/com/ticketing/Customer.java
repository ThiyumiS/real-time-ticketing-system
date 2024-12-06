package com.ticketing;

public class Customer implements Runnable {
    private final TicketPool pool;
    private final int customerRetrievalRate;

    public Customer(TicketPool pool, int customerRetrievalRate) {
        this.pool = pool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            boolean ticketSold = pool.removeTicket();
            if (!ticketSold) {
                System.out.println("All tickets for the event are sold out.");
                break;
            }
            try {
                Thread.sleep(1000 / customerRetrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
