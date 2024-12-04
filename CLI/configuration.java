public class configuration {
    public int totalTickets;
    public int ticketReleaseRate;
    public int customerRetrievalRate;
    public int maxTicketCapacity;

    public configuration(int totalTickets,int ticketReleaseRate,int customerRetrievalRate, int maxTicketCapacity){
        this.totalTickets=totalTickets;
        this.ticketReleaseRate= ticketReleaseRate;
        this.customerRetrievalRate= customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }


    public String tostring(){
        return "Configuration: \n"+
                "Total Tickets: " + totalTickets + "\n" +
                "Ticket Release Rate: " + ticketReleaseRate + "\n" +
                "Customer Retrieval Rate: " + customerRetrievalRate + "\n" +
                "Max Ticket Capacity: " + maxTicketCapacity;
    }



}
