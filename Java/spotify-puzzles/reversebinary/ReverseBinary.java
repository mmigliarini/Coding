package com.spotify.reversebinary;
import java.util.Scanner;

public class ReverseBinary {

	//	Your task will be to write a program for reversing numbers in binary. 
	//	For instance, the binary representation of 13 is 1101, and reversing it gives 1011, which corresponds to number 11.
	
    /**
     * Application entry point.
     * 
     * @param args
     */
	public static void main(String[] args){

		// Get new integer from STDIN
		Scanner in = new Scanner(System.in);
		int num = getNumber(in);

		// Printer reversed binary number
		System.out.println(getReversedNumber(num));
  	}
	
	
	/**
	 * Request and parse an integer from the STDIN. The input number has to be 1 ≤ N ≤ 1000000000.
	 * 
	 * @param in Scanner for STDIN
	 * @return input integer or exit program.
	 */	
	public static int getNumber(Scanner in) 
    {

        int i = 0;
        try {
        	
        	//System.out.println("Give me an integer N, 1 ≤ N ≤ 1000000000");
        	i = in.nextInt();
        	if(i < 1 || i > 1000000000) throw new Exception();
        	
        } catch(Exception e) {
        	
        	System.exit(-1);
        }
        return i;
    }
	
	
	
	/**
	 * Decimal to binary, reverse binary, reversed binary to decimal.
	 * 
	 * @return reversed binary input, to decimal.
	 */	
	public static int getReversedNumber(int iNum) {
		
		
		// generate the bit array, with the binary converted number
		int i = 0;
		byte[] binaryNum = new byte[30];	// Using a byte data type, since is the smallest one (the boolean has a 'not specified' size). 
											// The array is initialized to handle 30 byte, since an integer <= 1000000 can be rappresented with 30 bits.
		while ( iNum > 0 ) {
			binaryNum[i++] = (byte)(iNum & 1);		// & compare LSB
			iNum = iNum >> 1;						// >> right shift
		}


		// calculate the decimal number
		int t = 1;
		int iRev = 0;
		for(int a = (i-1); a>=0; a--){
			iRev = iRev + binaryNum[a]*t;
			t = t << 1;	// exp
		}
		
		return iRev;	
	}	

	
}
