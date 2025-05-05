import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Designed for salting a .csv file and plotting the results
 */
public class Main
{
    public static void main(String[] args) throws FileNotFoundException {   
        Scanner input = new Scanner(System.in);
        DataHandler.generateFunctionValues();
        System.out.print("Would you like to smooth or salt the data? \n(Enter 0 to Smooth, 1 to Salt) ");
        int numericalOption = Integer.parseInt(input.nextLine());

        if (numericalOption == 0) {
            Smoother.smoothData();
        } else if (numericalOption == 1) {
            Salting.saltData();
        } else {
            System.out.println("Not a valid option!");
        }
    } 
}