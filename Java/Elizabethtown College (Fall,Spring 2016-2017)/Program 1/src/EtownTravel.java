/* Tyler Hogue
 * hoguet@etown.edu
 * CSC 121 B
 * 10-2-2016
 * Dr. Ting Gu
 * 
 * Description: This program will calculate the cost of driving to Elizabethtown
 * college based on gas mileage and distance and also
 * recommends flight based on a comparison of the prices
 */

import java.util.Scanner;

public class EtownTravel {
static Scanner console=new Scanner(System.in);

public static void main(String[] args)
{
	String collegename="Elizabethtown College";
	
	int collegepop=1822; //# of undergrads in college
	double hrs_toBaltimore=1.5; // Hours to get from Etown to Baltimore
	double hrs_toDC=2.5; // Hours from Etown to Washington D.C.
	int students_toStates=30; // number of states the population is made of.
	int students_toCountries=20; //number of countries the population is made of.
	
	System.out.println(collegename+" is a liberal arts college "
			+ " in Elizabethtown, Pennsylvania.\n It has an "
			+ "undergraduate student body population of "
			+ "approximately "+collegepop+". \n"+collegename
			+ " offers more than 53 academic programs in the"
			+ " liberal arts, sciences,\n and professional studies. "
			+ collegename+" is located approximately "+hrs_toBaltimore
			+ " hours by car\n from Baltimore and Philadelphia and "
			+ hrs_toDC+" hours from D.C. and New York. Its"
			+ " approximately \n"+ collegepop+" undergraduates"
			+ " hail from nearly "+students_toStates+" states and "
			+students_toCountries+" foreign countries.");
	
	System.out.print("\n Hometown: "); //user input hometown
	String hometown=console.next();
	
	System.out.print("Distance from "+hometown+" to Etown (miles): ");
	double mi_HometoEtown=console.nextDouble(); /* user input distance
	from hometown to Etown.
	
    */
	
	System.out.print("Gas Mileage (miles per gallon): ");
	double gas_perMile=console.nextDouble();
	// user input gas mileage
	
	System.out.print("Price per Gallon ($): ");
	double pricePerGallon=console.nextDouble();
	//user input price of gas
	
	double finalCost=(mi_HometoEtown/gas_perMile)*pricePerGallon;
	/*Calculates the total cost of driving from home to etown
	
	distance/gas_perMile: the number of gallons used,
	
	(gals used)*pricepergal: how much does it cost  for all
	gallons used.
	
	*/
	
	System.out.printf("\n User Summary\n -----------------------\n"
			+ "Hometown: "+hometown+"\n Distance to "+collegename
			+": %.2f miles \n Gas mileage: %.2f miles per gallon \n "
			+ "Price of Gas: $%.2f per gallon \n Final Driving Cost: $%.2f",
		mi_HometoEtown, gas_perMile, pricePerGallon, finalCost);
	// outputs a summary of what the user had entered.
	
	System.out.print("\n Is there a flight from "+hometown
			+" to "+collegename+"? ");
	//ask user if there is a flight nearby
	boolean flights=console.next().equals("yes"); /*
	if the user types 'yes' to the question above, set to true
	*/
	
	if(flights) // if there is a flight near the user to Etown
	{
		System.out.print("How much does it cost per "
				+ "flight? ");
		double flightprice=console.nextDouble();
		//ask for price of each flight
		
		double cmpprice = finalCost-flightprice; /* Compare prices of
		driving and the flight.
		
		*/
	
	if(cmpprice<0) //if plane costs more than by car
	{
		System.out.printf("\n Flight cost: $%.2f", flightprice);
		System.out.print("\n Going to college by plane is not "
				+ "recommended. Just drive there.");
	}
	else if(cmpprice==0) // if both prices are the same
	{
		System.out.printf("\n Flight cost: $%.2f", flightprice);
		System.out.print("\n Going either way won't matter much.");
	}
	else // if driving costs more than plane
	{
	 	System.out.printf("\n Flight cost: $%.2f", flightprice);
		System.out.print("\n Going by plane may be better than"
				+ " by car!");
	}
	}
	else
	{
		System.out.print("\n In that case, a car is the only option.");
	}
}
}