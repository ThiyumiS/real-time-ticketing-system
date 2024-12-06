package com.ticketing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxTicketCapacity = getValidInput(scanner, "Enter max ticket capacity (total tickets for the event):");
        int totalTickets = getValidInput(scanner, "Enter total tickets (maximum tickets available in the pool at any time):");
        int ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate:");
        int customerRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate:");

        while (true) {
            TicketPool pool = new TicketPool(totalTickets, maxTicketCapacity);

            System.out.println("Choose an option:");
            System.out.println("1. Start System");
            System.out.println("2. Stop System");
            System.out.println("3. Exit");

            int choice = getValidInput(scanner, "Enter your choice:");

            switch (choice) {
                case 1:
                    System.out.println("System started.");
                    Vendor vendor = new Vendor(pool, ticketReleaseRate);
                    Customer customer = new Customer(pool, customerRetrievalRate);

                    Thread vendorThread = new Thread(vendor);
                    Thread customerThread = new Thread(customer);

                    vendorThread.start();
                    customerThread.start();

                    try {
                        vendorThread.join();
                        customerThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

//                    System.out.println("No tickets available. All tickets are sold.");
//                    System.out.println("All tickets for the event are sold out.");

                    // Retry logic
                    String retry = getValidYesNo(scanner, "Would you like to try again? (yes/no): ");
                    if (retry.equalsIgnoreCase("yes")) {
                        maxTicketCapacity = getValidInput(scanner, "Enter new max ticket capacity (total tickets for the event):");
                    } else {
                        System.out.println("Exiting program...");
                        return;
                    }
                    break;

                case 2:
                    System.out.println("System stopped.");
                    return;

                case 3:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Helper method to get a valid integer input from the user.
     *
     * @param scanner Scanner object for input
     * @param prompt  The message to prompt the user
     * @return Valid integer input
     */
    private static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Helper method to get a valid "yes" or "no" response from the user.
     *
     * @param scanner Scanner object for input
     * @param prompt  The message to prompt the user
     * @return Valid "yes" or "no" response
     */
    private static String getValidYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("yes") || input.equals("no")) {
                return input;
            }
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }
}