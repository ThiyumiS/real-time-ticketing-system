package com.ticketing;

public class Configuration {
    public int totalTickets;
    public int ticketReleaseRate;
    public int customerRetrievalRate;
    public int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString() {
        return "Configuration: \n" +
                "Total Tickets: " + totalTickets + "\n" +
                "Ticket Release Rate: " + ticketReleaseRate + "\n" +
                "Customer Retrieval Rate: " + customerRetrievalRate + "\n" +
                "Max Ticket Capacity: " + maxTicketCapacity;
    }
}
