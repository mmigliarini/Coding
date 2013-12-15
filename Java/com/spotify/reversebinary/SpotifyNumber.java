package com.spotify.reversebinary;

public class SpotifyNumber {

	int input;
	
	public SpotifyNumber(int input){
		this.input = input;
	}

	
	/**
	 * Getter method: input.
	 * 
	 * @return input integer.
	 */	
	public int getInput() {
		return this.input;
	}
	
	
	
	/**
	 * Decimal to binary, reverse binary, reversed binary to decimal.
	 * 
	 * @return reversed binary input, to decimal.
	 */	
	public int getReversedNumber() {
		
		
		// generate the bit array, with the binary converted number
		int iNum = this.input;
		int i = 0;
		byte[] binaryNum = new byte[30];	// Using a byte data type, since is the smallest one (the boolean has a 'not specified' size). 
											// The array is initialized to handle 30 byte, since an integer <= 1000000 can be rappresented with 30 bits.
		while ( iNum > 0 ) {
			binaryNum[i++] = (byte)(iNum & 1);
			iNum = iNum >> 1;
		}


		// calculate the decimal number
		int t = 1;
		int iRev = 0;
		for(int a = (i-1); a>=0; a--){
			iRev = iRev + binaryNum[a]*t;
			t*=2;	// exp
		}
		
		return iRev;	
	}
	
}
