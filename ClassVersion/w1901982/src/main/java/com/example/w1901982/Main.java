package com.example.w1901982;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;




public class Main  {
    static int fullFuelStock = 500;
    static Scanner input = new Scanner(System.in);
    static FuelQueue[][] queue = new FuelQueue[5][6];

    static FuelQueue customer = new FuelQueue();

    public static void main(String[] args) {

        System.out.println("""
                Filling Station
                -------------------------------------------------------------------
                100  => View all Fuel Queues.
                101  => View all Empty Queues.
                102  => Add customer to a Queue.
                103  => Remove a customer from a Queue.
                104  => Remove a served customer.
                105  => View Customers Sorted in alphabetical order.
                106  => Store Program Data into file.
                107  => Load Program Data from file.
                108  => View Remaining Fuel Stock.
                999 or EXT => Exit the Program.
                -------------------------------------------------------------------
                """);

        while (true) {
            System.out.println("Enter the Option:");
            String Option = input.next();

            if (Option.equals("100") || Option.equalsIgnoreCase("VFQ")) { // view all fuel queues
                view();
            }
            if (Option.equals("101") || Option.equalsIgnoreCase("VEQ")) {  // view all empty queues
                q_empty();
            }
            if (Option.equals("102") || Option.equalsIgnoreCase("ACQ")) {  // add customers to queues
                addCustomer();
            }
            if (Option.equals("103") || Option.equalsIgnoreCase("RCQ")) {  // remove customer from queue
                removeCustomer();
            }
            if (Option.equals("104") || Option.equalsIgnoreCase("PCQ")) {  // remove reserved customer from queues
                removeSerCustomer();
            }
            if (Option.equals("105") || Option.equalsIgnoreCase("VCS")) {  // View Customers Sorted in alphabetical order
                viewCustomerInAl_Order();
            }
            if (Option.equals("106") || Option.equalsIgnoreCase("SPD")) {  // Store Program Data into file.
                storeData();
            }
            if (Option.equals("107") || Option.equalsIgnoreCase("LPD")) {  // Load Program Data from file.
                loadData();
            }
            if (Option.equals("108") || Option.equalsIgnoreCase("STK")) {  // View Remaining Fuel Stock.
                remainingStock();
            }
            if (Option.equals("999") || Option.equalsIgnoreCase("EXT")) {  //Exit the Program
                exit();
            }
        }

    }

    static void q_empty() {   // view all empty queues
        for (int i = 0; i < 3; i++) { /*5 to 2*/
            if (queue[i][0] == null) {
                System.out.println("Line number " + (i + 1) + " is still empty");
            } else {
                System.out.println("Line number " + (i + 1) + " is not empty");
            }
        }
        /*check that specific position is empty or not and
        In here it is run until all positions get filled  in each row.*/
    }

    static void view() {    // view all fuel queues
        System.out.println("These are the Queues");
        for (int i = 0; i < 2; i++) { /*5 to 2*/
            System.out.println("Queue No:" + (i + 1));
            for (int j = 0; j < 3; j++) { /*6 to 3*/
                if (queue[i][j] == null) {
                    System.out.println("empty/empty......");
                }
                else {
                    System.out.println("Already occupied");
                }
                /*Two fuel queues with three customers for each. print Queue no by adding one to the index.*/
            }
        }
    }

    static void exit() {
        System.out.println("Thank you!!!"); //to exit from the programme
        System.exit(0);
    }

    static void remainingStock() {      //prints remaining stock after serve for customers
        System.out.println("Remaining Stock"+" "+fullFuelStock);
    }

    static void loadData() {        //load data to the text file called fuel_Queue_Details
        try{
            File txt = new File("fuel_Queue_Details.txt");
            Scanner read = new Scanner(txt);
            for(int i = 0; i < 2; i++){ /*5 to 2*/        //looping for 30 positions
                for(int j = 0; j < 3; j++){ /*6 to 3*/
                    if(read.hasNextLine()){
                        String readingLine = read.nextLine();
                        if( readingLine.equals("null")){
                            queue[i][j] = null;             //If there is empty record, null is assign to it
                        }
                        else {
                            queue[i][j] = new FuelQueue();
                            queue[i][j].passenger.setFirstName(readingLine);      //take the passenger details from line by line.
                            queue[i][j].passenger.setSecondName(read.nextLine());
                            queue[i][j].passenger.setVehicleNo(read.nextLine());
                            queue[i][j].passenger.setLitersRequired(Integer.parseInt(read.nextLine()));

                        }
                    }
                }
            }
            read.close();
            System.out.println("Data loaded successful.");
        }
        catch(IOException e){
            System.out.println("File cannot be found.");
        }
        /*make new object as File and naming as txt.after taking details, it will call  close() method. If data loaded successfully, It prints Data loaded successfully
        * and if not it prints file can not be found */
    }


    static void storeData() {
        try{
            FileWriter writer = new FileWriter("fuel_Details.txt");     //store data into text file
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 6; j++) {
                    if (queue[i][j]==null) {
                        writer.write("null");
                    }
                    /*looping for 30 occurrences. If one record is null, it takes only one line and if it is not null it prints customer details.
                    * from one customer , it takes four lines */
                    else {
                        writer.write(queue[i][j].passenger.getFirstName() + "\n");
                        writer.write(queue[i][j].passenger.getSecondName() + "\n");
                        writer.write(queue[i][j].passenger.getVehicleNo() + "\n");
                        writer.write(queue[i][j].passenger.getLitersRequired() + "\n");

                    }
                }

            }
            writer.close();
            System.out.println("Data was successfully stored");
        }
        catch (IOException e){
            System.out.println("Data couldn't be stored.");
        }
        /*make new object as File and naming as txt.after taking details, it will call  close() method. If data loaded successfully, It prints Data loaded successfully
         * and if not it prints file can not be found */
    }

    static void viewCustomerInAl_Order() {
        FuelQueue[][] customerOrder = new FuelQueue[2][3];     //make new array called customerOrder
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                customerOrder[i][j] = queue[i][j];      //current array assign to new array called customerOrder
                if (customerOrder[i][j] == null) {
                    customerOrder[i][j] = new FuelQueue();
                    customerOrder[i][j].passenger.setFirstName("empty");
                }
                /*If */
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int x = 1; x < 3 - j; x++) {
                    if ((customerOrder[i][x - 1].passenger.getFirstName().compareToIgnoreCase(customerOrder[i][x].passenger.getFirstName())) > 0) {
                        FuelQueue tempElement = customerOrder[i][x - 1];
                        customerOrder[i][x - 1] = customerOrder[i][x];
                        customerOrder[i][x] = tempElement;
                    }
                }
                /*Sorted using bubble sort.*/
            }
        }
        System.out.println("The sorted queue is viewed below");
        for (int i = 0; i < 2; i++) {
            System.out.println("Queue " + (i + 1));
            for (int j = 0; j < 3; j++) {
                if (customerOrder[i][j].passenger.getFirstName().equals("empty")) {
                    System.out.println("still empty");
                    /*If the first name equals empty, it prints that position is empty*/
                } else {
                    System.out.println(customerOrder[i][j].passenger.getFirstName());
                }
            }
            System.out.println();
        }
    }

    static void removeSerCustomer() {
        System.out.print("Enter Queue number: ");
        int que_Num = input.nextInt();
        System.out.println("Served Customer removed from the queue");
        queue[que_Num - 1][0] = null;
        fullFuelStock -=10;
        System.out.println(fullFuelStock+" ");
        for (int i = 1; i < 3; i++) {
            if (queue[que_Num - 1][i - 1] == null) {
                queue[que_Num - 1][i - 1] = queue[que_Num - 1][i];
                queue[que_Num - 1][i] = null;
            }
            /*If queue number -1 and i-1 equals to null.when front customer removed, next customer goes to front place.
        Then next iteration null assigned to person in next position.In here fuel stock also get reduce */
        }
        for (int i = 0; i < 3; i++){
            if(queue[que_Num - 1][i] == null){
                queue[que_Num - 1][i] = customer.deQueue();
            }
        }
         /*If one position get blanked in the queue, it filled by customer in waiting list.So deQueue from waiting list*/

    }

    static void removeCustomer() {
        System.out.print("Enter Queue number: ");
        int que_Num = input.nextInt();
        int posNum;
        System.out.print("Enter the position number: ");
        posNum = input.nextInt();
        /*customer can enter queue number and position in that queue.*/
        queue[que_Num - 1][posNum - 1] = null;
        System.out.println("Customer is removed from the queue");
        fullFuelStock +=10;
        System.out.println(fullFuelStock+"\n ");
        /*when customer removed fuel stock get increase by 10  */
        for(int i = posNum; i< 3;i++)

        {
            if (queue[que_Num - 1][i - 1] == null) {
                queue[que_Num - 1][i - 1] = queue[que_Num - 1][i];
                queue[que_Num - 1][i] = null;
            }
        /*If queue number -1 and i-1 equals to null.when front customer removed, next customer goes to front place.
        Then next iteration null assigned to person in next position.*/

        }
        for (int i = 0; i < 3; i++){
            if(queue[que_Num - 1][i] == null){
                queue[que_Num - 1][i] = customer.deQueue();
            }
        }
        /*If one position get blanked in the queue, it filled by customer in waiting list.So deQueue from waiting list*/
    }

    static void addCustomer() {
        if (fullFuelStock <= 50) { /*500 to 50*/
            System.out.println("Warning! Fuel Stock is low " + fullFuelStock);
            /*gave the warning when full stock get decrease more than the 50*/
        }
        int maxSpaces = 0;
        int queueNumber = minQueue();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (queue[i][j] != null) {
                    maxSpaces += 1;
                }
            }
        }
        /*take the variable as maxSpaces. then take the queue number by minQueue() method.
        * If position is not null maxSpaces increment by 1*/
        if (maxSpaces==6) {
            System.out.println("The Queues are full\nSo you have to wait in waiting queue");
            System.out.print("Enter the Customer's First Name: ");
            customer.passenger.setFirstName(input.next());
            System.out.print("Enter the Customer's Last Name: ");
            customer.passenger.setSecondName(input.next());
            System.out.print("Enter the Vehicle Number: ");
            customer.passenger.setVehicleNo(input.next());
            System.out.print("Enter the No.of Litres: ");
            customer.passenger.setLitersRequired(input.nextInt());
            customer.enQueue(customer);
            /*when maxSpaces gets equal to 6, customers will be sent to the waiting list.*/
        } else {
            System.out.print("Enter the Customer's First Name: ");
            String firstName = input.next();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            System.out.print("Enter the Customer's Last Name: ");
            String secondName = input.next();
            secondName = secondName.substring(0, 1).toUpperCase() + secondName.substring(1).toLowerCase();
            System.out.print("Enter the Vehicle Number: ");
            String vehicleNumber = input.next();
            System.out.print("Enter the No.of Litres: (only from number)");
            int litersRequired = input.nextInt();
            /*Until maxSpaces get into 6, customers enter to the queues.*/
            for (int j = 0; j < 3; j++) {
                if (queue[queueNumber][j] == null) {
                    queue[queueNumber][j] = new FuelQueue();
                    queue[queueNumber][j].passenger.setFirstName(firstName);
                    queue[queueNumber][j].passenger.setSecondName(secondName);
                    queue[queueNumber][j].passenger.setVehicleNo(vehicleNumber);
                    queue[queueNumber][j].passenger.setLitersRequired(litersRequired);
                    /*take the customer details from passenger class, if the position is empty.*/
                    fullFuelStock -= litersRequired;
                    System.out.println("\n" + firstName + " " + secondName + " added to the queue " + (queueNumber + 1) + " successfully");
                    System.out.println(fullFuelStock);
                    break;
                }
            }
        }
    }

    static int minQueue() {
        int maxCount = 3; //
        int minimumQueueNo = 0;
        for (int i=0;i<5;i++)
        {
            int count = 0;
            for (int j=0;j<3;j++) //
            {
                if (queue[i][j] != null)
                {
                    count ++;
                }
            }
            if (count<maxCount)
            {
                maxCount = count;
                minimumQueueNo = i ;
            }
        }
        return minimumQueueNo;
    }
    /*getting the queue with maximum spaces.*/
    /*starts from first queue to enter the customers in order, and  it goes along through 2 and 3*/
    /*first enter customers to first index */
}

