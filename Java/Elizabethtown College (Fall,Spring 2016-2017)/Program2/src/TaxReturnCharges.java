/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Ting Gu (CSC 121B)
 * 
 * 11-11-2016
 * 
 * Program: This program is a tax charge calculator for the J&J Accounting Firm. It calculates
 * the charge one must pay depending on the individual's income, and the number of hours they were
 * helped filling out tax forms.
 * 
 * <= $25000/yr: chargerate*0.7*((help-30)/60) = charge
 * > $25000/yr: chargerate*0.7*((help-20)/60) = charge
 */

import java.util.Scanner;

public class TaxReturnCharges {
public static Scanner console = new Scanner(System.in);

public static void hello() //mske method to output Intro.
{
	System.out.print("Welcome to the J&J Accounting Firm! We charge you for filing taxes!");
}

public static void bye() //Farewell method
{
	System.out.print("Thank you for giving back to the government!");
}

public static double inputincome() //Asks user for yearly income
{
	System.out.print("Yearly income ($): ");
	double income = console.nextDouble();
	return income;
}

public static double chargeRate() //asks user for the amount charged per hour
{
	System.out.print("Charge rate ($ per hour): ");
	double rate = console.nextDouble();
	return rate;
}

public static double consultTime()
{
	System.out.print("Time help was given (minutes): ");
	double time = console.nextDouble();
	return time;
}

public static double  totalCharge(double income, double consultTime, double rate)
{ //calculates total charge
	double charge = 0.0;
	if(income <= 25000)
	{
		if(consultTime > 30)
			charge = 0.4*rate*(consultTime-30)/60;
	}
	
	else
	{
		if(consultTime > 20)
			charge = 0.7*rate*(consultTime-20)/60;
	}
		return charge;
}



	public static void main(String[] args)
	
	{
	hello();
	
	System.out.println();
	boolean go = true;
	boolean strgo = true;
	
	while(go && strgo)
	{
		System.out.print("Continue or Stop? "); //ask user whether to keep going or stop
		String conf = console.next();
		
		if(conf.equals("Continue")) //if user wants to continue
		{
				double income = inputincome();
				double time = consultTime();
				double rate = chargeRate();
				
				double serviceCost = totalCharge(income, time, rate);
			
				System.out.printf("\n Total cost is $%.2f \n", serviceCost);
				
		}
			
		else //if user wants to stop
		{
				go = false;
				strgo = false;
				
				bye();
		}
	}
	}
}