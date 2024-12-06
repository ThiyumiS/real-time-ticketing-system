package com.ticketing;

import java.util.Queue;
import java.util.LinkedList;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int poolLimit; // Maximum tickets in the pool at a time
    private final int maxCapacity; // Total tickets for the event
    private int ticketsSold = 0;
    private int initialTicketsSold = 0; // Counter for initial tickets sold

    public TicketPool(int initialTickets, int maxCapacity) {
        this.poolLimit = initialTickets;
        this.maxCapacity = maxCapacity;
        // Initialize the pool with the initial number of tickets
        for (int i = 1; i <= initialTickets; i++) {
            tickets.add(i);
        }
    }

    public synchronized int getMaxCapacity() {
        return maxCapacity;
    }

    public synchronized void addTickets(int releaseRate) {
        while (tickets.size() >= poolLimit || tickets.size() + ticketsSold >= maxCapacity) {
            if (ticketsSold >= maxCapacity) {
                notifyAll(); // Notify all threads to stop
                return;
            }
            try {
                wait(); // Wait if adding would exceed limits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        int ticketsToAdd = Math.min(releaseRate, poolLimit - tickets.size());
        for (int i = 0; i < ticketsToAdd && tickets.size() + ticketsSold < maxCapacity; i++) {
            tickets.add(tickets.size() + ticketsSold + 1);
            System.out.println("Ticket added: " + (tickets.size() + ticketsSold));
        }
        notifyAll(); // Notify customers waiting for tickets
    }

    public synchronized boolean removeTicket() {
        while (tickets.isEmpty() && ticketsSold < maxCapacity) {
            try {
                wait(); // Wait if no tickets are temporarily available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        if (tickets.isEmpty() && ticketsSold >= maxCapacity) {
            notifyAll(); // Notify all threads to stop
            return false; // No more tickets to sell
        }

        Integer ticket = tickets.poll();
        if (ticket != null) {
            ticketsSold++;
            if (ticketsSold <= poolLimit) {
                initialTicketsSold++;
                System.out.println("Initial Ticket sold: " + ticket);
            } else {
                System.out.println("Ticket sold: " + ticket);
            }
            notifyAll(); // Notify vendors waiting to add tickets
            return true;
        }
        return false;
    }

    public synchronized int getTicketsSold() {
        return ticketsSold;
    }

    public synchronized boolean allTicketsSold() {
        return ticketsSold >= maxCapacity;
    }
}
