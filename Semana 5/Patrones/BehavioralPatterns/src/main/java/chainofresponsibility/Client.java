package chainofresponsibility;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        System.out.println("Welcome to ATM Money dispenser");
        Scanner scan = new Scanner(System.in);

        MoneyChainHandler hundredDollarHandler_100 = new HundrenDollarHandler_100();
        MoneyChainHandler fiftyDollarHandler_50 = new FiftyDollarHandler_50();
        MoneyChainHandler tenDollarHandler_10 =new TenDollarHandler_10();
        MoneyChainHandler fiveDollarHandler_5 = new FiveDollarHandler_5();
        MoneyChainHandler twoDollarHandler_2 = new TwoDollarHandler_2();
        MoneyChainHandler oneDollarHandler_1 = new OneDollarHandler_1();

        // Setting up the change
        hundredDollarHandler_100.setNextHandler(fiftyDollarHandler_50);
        fiftyDollarHandler_50.setNextHandler(tenDollarHandler_10);
        tenDollarHandler_10.setNextHandler(fiveDollarHandler_5);
        fiveDollarHandler_5.setNextHandler(twoDollarHandler_2);
        twoDollarHandler_2.setNextHandler(oneDollarHandler_1);

        System.out.println("Please enter amount you want to withdraw ex: 3450 ");
        int choice = scan.nextInt();
        hundredDollarHandler_100.handler(choice);

        System.out.println("=============================");

    }
}
