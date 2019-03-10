/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Ting Gu (CS 121B)
 * 12-9-2016
 */

import java.util.Scanner; //import all necessary libraries
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Caesar {

	static Scanner console = new Scanner(System.in);
	
	//Create encryption and decryption methods
	
	public static String encrypt(int shift, String line, File encrypted) throws FileNotFoundException //throw an error if file is not found
	{	
		String cipher = "";
		
		for(int i = 0;i < line.length(); i++) //run through each line, one character at a time
		{
			if(!Character.isLetter(line.charAt(i))) //output directly if character is NOT a letter.
			{
				cipher += (char) (line.charAt(i));
			}
			
			else if(Character.isLowerCase(line.charAt(i)) && (line.charAt(i)+shift) > 'z') // lowercase characters that shift past 'z'
			{
				cipher += (char) (line.charAt(i)-26+shift); // perform the wrap-around to return to 'a'.
			}
			
			else if(Character.isUpperCase(line.charAt(i)+shift) && (line.charAt(i)+shift) > 'Z') //uppercase characters that shift past 'Z'
			{
				cipher += (char) (line.charAt(i)-26+shift); // perform wrap-around
			}
			
			else //character never surpasses Z, regardless of case (upper or lower)
			{
				cipher += (char) (line.charAt(i)+shift); // no wrap is performed
			}
		}
		PrintWriter out = new PrintWriter(new FileOutputStream(encrypted)); //establish connection to encrypted file to write complete lines of cipher text
		
		out.print(cipher); //write cipher text to file
		
		out.close(); //close connection
		return cipher;
	}
		
	public static String decrypt(int shift, String line, File decrypted) throws FileNotFoundException //same as encryption method above
	{
		
		/* ****The format and computations are the same as the encryption method above***
		 * The shift is reversed to take the characters back to the original plaintext.
		 */
		
		String plainstr = "";
		
		for(int i = 0;i < line.length(); i++)
		{
				if(!Character.isLetter(line.charAt(i)))
				{
					plainstr += (char) (line.charAt(i));
				}
				
				else if(Character.isLowerCase(line.charAt(i)) && (line.charAt(i)-shift) < 'a')
				{
					plainstr += (char) (line.charAt(i)+26-shift);
				}
				
				else if(Character.isUpperCase(line.charAt(i)) && (line.charAt(i)-shift) < 'A')
				{
					plainstr += (char) (line.charAt(i)+26-shift);
				}
				
				else
				{
					plainstr += (char) (line.charAt(i)-shift);
				}
		}
		
		PrintWriter out = new PrintWriter(new FileOutputStream(decrypted));
		
		out.print(plainstr);

		out.close();
		return plainstr;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		String line; //line is the characters in a line of a file while it's reading the files.
		int shift; //the amount that the text should be shifted
		char inp = '\0'; //the input command to control the caesar cpher program.
		
		System.out.print("what is the shift? ");
		shift = console.nextInt();
		
		while(inp != '*') // while the command is not an *, the program will run.
		{
			inp = '\0'; //initialize variables
			
			System.out.print("Encrypt (e) or decrypt (d) or quit (*)? "); //ask for user command
			inp = console.next().charAt(0);
			
			if(inp == 'd') //if command is 'd'
			{
				
				System.out.print("File name of plaintext: ");
				String p_file = console.next(); // set the file name for the file to be created in the decrypt method
				
				File decrypted = new File("c:\\Users\\hoguet7260\\Desktop\\"+p_file+".txt"); //create file object
				
				File encrypted = new File("c:\\Users\\hoguet7260\\Desktop\\cipher.txt"); //create file object for cipher file to read from and decypt
				
				Scanner in = new Scanner(encrypted); // create scanner object to read lines of text in encrypted file
				
				System.out.println();
				while(in.hasNextLine()) //keep reading while the file has another line of text to read
				{
					line = in.nextLine();
					System.out.print(line);
					System.out.println();
					
					decrypt(shift, line, decrypted); //run decryption method on read text
				}
				in.close(); //close scanner object
				
			System.out.print("\n File has been created and written to! \n");
			}
			
			else if(inp == 'e') //encrypt if 'e' is entered
			{
				
				System.out.print("File name of cipher: ");
				String c_file = console.next(); //user enters the name of the cipher file to create in encryption method
				
				File encrypted = new File("c:\\Users\\hoguet7260\\Desktop\\"+c_file+".txt"); //create file with specified name
				
				File decrypted = new File("c:\\Users\\hoguet7260\\Desktop\\plain.txt"); //read from plain.txt
				
				Scanner in = new Scanner(decrypted); //create scanner object to read data from plain.txt
				
				System.out.println();
				while(in.hasNextLine())
				{
					line = in.nextLine();
					System.out.print(line);
					System.out.println();
					
					encrypt(shift, line, encrypted); //run encryption method
					line = "";
				}
				in.close(); //close scanner object
				
				System.out.print("\n File has been created and written to! \n");
			}
		}
		
		System.out.println("The end!");
		
		/* Note: I'm not sure why, but the program can't read more than one line of text AND print the encrypted lines in the same format
		* as in the plaintext file
		*/
	}
}
