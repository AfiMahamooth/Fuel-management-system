import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayVersion {
    public static void main(String[] args) throws IOException {
        //Creating a Scanner
        Scanner read = new Scanner(System.in);
        //Creating a variable Called Liters to Store the Capacity of the
        int fuelStock = 500;

        //Creating 3 array for 3 pumps
        String[] pump1 = new String[3];
        String[] pump2 = new String[3];
        String[] arrayFuelStock = new String[3];

        // While Loop to Show the Menu Recurring
        int i = 1;
        while (i == 1) {
            //Displaying the menu
            System.out.println("""
                
                100   :     View all Fuel Queues.
                101   :     View all Empty Queues.
                102   :     Add customer to a Queue.
                103   :     Remove a customer from a Queue.
                104   :     Remove a served customer.
                105   :     View Customers Sorted in alphabetical order
                106   :     Store Program Data into file.
                107   :     Load Program Data from file.
                108   :     View Remaining Fuel Stock.
                EXT   :     Exit the Program.
                
                """);

            //asking for the menu Option
            System.out.println("Select 1 Option :");
            String menuSelect = read.nextLine();
            switch (menuSelect) {
                // Displaying All Fuel Queues
                case "100" -> {
                    System.out.println("all fuel queues");
                    //Displaying the Pumps
                    System.out.println("Pump 1 - " + Arrays.toString(pump1));
                    System.out.println("Pump 2 - " + Arrays.toString(pump2));
                }
                //Displaying Null ele
                case "101" ->
                    // Calling Method
                        ViewEmptyQueues(pump1, pump2);
                case "102" -> {
                    //Calling the Method addCustomerToQueue  Where it Adds Customers to The Queue
                    AddCustomerToQueue(pump1, pump2, read);
                    fuelStock -= 10;
                }
                case "103" -> {
                    //Calling the Method RemoveCustomerFromQueue  Where it Removes Customers From The Queue
                    RemoveCustomerFromQueue(pump1, pump2, read);
                    fuelStock += 10;
                }
                case "104" ->
                    //Calling the Method RemoveCustomerFromQueue  Where it Removes Customers From The Queue
                        RemoveServedCustomer(pump1, pump2, read);
                case "105" ->
                    //Calling Alphabetical order Method
                        AlphabeticalOrder(pump1, pump2);
                case "106" ->
                    // calling File Write Method
                        FileWrite(pump1, pump2, fuelStock);
                case "107" -> {
                    System.out.println("load program data from file");
                    FileRead(pump1, pump2, arrayFuelStock);
                    fuelStock = Integer.parseInt(arrayFuelStock[0]);
                }
                case "108" -> {
                    System.out.println("REMAINING FUEL STOCK");
                    //Displaying Liters
                    System.out.println("Fuel Stock -> " + fuelStock);
                }
                case "EXT" -> {
                    System.out.println("SUCCESS FULLY EXITED ");
                    i = 0;
                }
                default -> System.out.println("Please enter the menu number again");
            }
        }
    }

    public static void FileRead(String[] pump1, String[] pump2, String[] arrayFuelStock) {
        try {
            File myObj = new File("database.txt");
            Scanner Reader = new Scanner(myObj);

            String data1 = Reader.nextLine();
            String[] tempData = data1.split(" ");
            for (int i = 0; i < tempData.length; i++) {
                if (tempData[i].equals("null")) {
                    tempData[i] = null;
                }
                pump1[i] = tempData[i];
            }

            String data2 = Reader.nextLine();
            tempData = data2.split(" ");
            for (int i = 0; i < tempData.length; i++) {
                if (tempData[i].equals("null")) {
                    tempData[i] = null;
                }
                pump2[i] = tempData[i];
            }


            String data4 = Reader.nextLine();
            tempData = data4.split(" ");
            System.arraycopy(tempData, 0, arrayFuelStock, 0, tempData.length);

            Reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("File Cannot be Found");

        }

    }



    public static void ViewEmptyQueues(String[] pump1, String[] pump2) {
        System.out.println("all empty queues");
        for (int j = 0; j <= 2; j++) {
            // Checking whether is there a Null item And If there is Displaying the Array
            if (pump1[j] == null || pump1[j].equals("null")) {
                //Displaying the Array
                System.out.println("Pump 1 - " + Arrays.toString(pump1));
                break;
            }
        }
        for (int j = 0; j <= 2; j++) {
            // Checking whether is there a Null item And If there is Displaying the Array
            if (pump2[j] == null || pump2[j].equals("null")) {
                //Displaying the Array
                System.out.println("Pump 2 - " + Arrays.toString(pump2));
                break;
            }
        }


    }


    public static void FileWrite(String[] pump1, String[] pump2, int fuelStock) throws IOException {
        //Creating File Writer
        FileWriter writeFile = new FileWriter("Database.txt");
        for (String word: pump1) {
            writeFile.append(word).append(" ");
        }
        System.out.println("Pump 1 successfully wrote to the file.");
        writeFile.append("\n");

        for (String word: pump2) {
            writeFile.append(word).append(" ");
        }
        System.out.println("Pump 2 Successfully wrote to the file.");
        writeFile.append("\n");


        writeFile.append(String.valueOf(fuelStock));
        writeFile.close();
    }

    public static void AddCustomerToQueue(String[] pump1, String[] pump2, Scanner read){
        System.out.println("add customer");
        System.out.println("Please Enter the Customer Name :");
        String userName = read.next();
        System.out.print("Enter The Pump Number (1 or 2) : ");
        while (true) {
            int pumpNumber = read.nextInt();
            if (pumpNumber == 1) {
                Adder(pump1,userName);
                break;
            } else if (pumpNumber == 2) {
                Adder(pump2,userName);
                break;
            } else {
                System.out.println("Please Enter The Correct Queue Number Again :");
            }
        }
    }

    public static void Adder(String[] pump, String userName) {
        for (int j = 0; j <= 2; j++) {
            if (pump[j] == null) {
                pump[j] = userName;
                break;
            }
        }
    }


    public static void RemoveCustomerFromQueue(String[] pump1,String[] pump2,Scanner read){

        System.out.println("remove a customer");
        //
        System.out.println("From Which Queue Do u want to Remove The Customer: ");
        int pumpNumber = read.nextInt();
        System.out.println("Which Customer Do u Want to Remove (If the 2nd Customer Enter 2) : ");
        int cn = read.nextInt();
        cn = cn - 1;
        if (pumpNumber == 1) {
            pump1[cn] = null;
            ArrayShifter(cn, pump1);
            System.out.println("Customer Removed");
            System.out.println("Pump 1 - " + Arrays.toString(pump1));
        }

        if (pumpNumber == 2) {
            pump2[cn] = null;
            ArrayShifter(cn,pump2);
            System.out.println("Customer Removed");
            System.out.println("Pump 2 - " + Arrays.toString(pump2));
        }
    }

    public static void AlphabeticalOrder(String[] pump1, String[] pump2) {

        System.out.print("Pump 1 :");
        OrderSort(pump1);
        System.out.print("Pump 2 :");
        OrderSort(pump2);
    }

    private static void OrderSort(String[] temp) {
        String[] pump = new String[3];
        System.arraycopy(temp, 0, pump, 0, temp.length);
        for(int i = 0; i < pump.length; i++) {
            if (pump[i] != null){

                int smallest = i;
                for(int j = i + 1; j < pump.length; j++){
                    if (pump[j] != null){
                        if(pump[j].compareTo(pump[i]) < 0)
                            smallest = j;
                    }
                }
                String aux = pump[i];
                pump[i] = pump[smallest];
                pump[smallest] = aux;
            }
        }
        System.out.println(Arrays.toString(pump));
    }

    public static void RemoveServedCustomer(String[] pump1, String[] pump2, Scanner read){
        System.out.println("remove a served customer");
        //Asking Which Queue you would  want to remove
        System.out.println("From Which Queue Do you want to Remove The Customer: ");
        //in a Queue The 1st Member will only be Served therefor 1st location will be removed from the queue
        int pumpNumber = read.nextInt();

        if (pumpNumber == 1) {
            int i = 0;
            ArrayShifter(i,pump1);
            System.out.println("Served Customer Removed");
            System.out.println("Pump 1 - " + Arrays.toString(pump1));

        }

        if (pumpNumber == 2) {
            int i = 0;
            ArrayShifter(i,pump2);
            System.out.println("Served Customer Removed");
            System.out.println("Pump 2 - " + Arrays.toString(pump2));
        }
    }

    private static void ArrayShifter(int i, String[] pump) {
        while ( i<3){
            if (i==2){
                pump[i]=null;
            } else{
                pump[i]=pump[i+1];
            }
            i++;
        }
    }
}