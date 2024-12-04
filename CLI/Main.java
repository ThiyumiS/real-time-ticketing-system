import java.util.Scanner;

public class Main {
    public static <getPositiveInt> void main(String[] args) {
        configuration config = configureSystem();
        System.out.println(config);
    }


    public static configuration configureSystem() {
        Scanner scanner = new Scanner(System.in);

        int totalTickets = getPositiveInt("Enter total tickets:", scanner);
        int ticketReleaseRate = getPositiveInt("Enter ticket release rate:", scanner);
        int customerRetrievalRate = getPositiveInt("Enter customer retrieval rate:", scanner);
        int maxTicketCapacity = getPositiveInt("Enter max ticket capacity:", scanner);

        return new configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }


    // loop till user input positive number
    public static int getPositiveInt(String prompt, Scanner scanner) {
        int value = -1;
        Boolean validln = true;
        while (validln) {
            System.out.println(prompt);
            value = scanner.nextInt();
            if (value > 0) {
                validln=false;
            } else {
                System.out.println("Please enter a positive number.");
            }
        }
        return value;
    }


}
