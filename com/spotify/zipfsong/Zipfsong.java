/*
 * Zipfsong: Spotify puzzles.
 * 
 * @author     Mauro Migliarini <mauro.migliarini@mail.polimi.it>
 * @copyright  2013 Mauro Migliarini
 * @license    http://www.opensource.org/licenses/lgpl-license.php LGPL
 * @link       http://github.com/mmigliarini
 * @version    1.0.0
 */

package com.spotify.zipfsong;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Zipfsong {

	
	//	**Input
	//	The first line of input contains two integers n and m (1 ≤ n ≤ 50000, 1 ≤ m ≤ n), the number of songs on the album, and the number of songs to select. 
	//	Then follow n lines. The i’th of these lines contains an integer fi and string si, where 0 ≤ fi ≤ 1012 is the number of times the i’th song was listened to, and si is the name of the song. 
	//	Each song name is at most 30 characters long and consists only of the characters ‘a’-‘z’, ‘0’-‘9’, and underscore (‘_’).
	//
	//	**Output
	//	Output a list of the m songs with the highest quality qi, in decreasing order of quality. 
	//  If two songs have the same quality, give precedence to the one appearing first on the album 
	//  (presumably there was a reason for the producers to put that song before the other).

	
	
    /**
     * Application entry point.
     * 
     * @param args
     */
	public static void main(String[] args){

		
        int n = 0;						// number of songs on the album
        int m = 0;						// number of songs to select
		
		
		// Initialize BufferReader, read lines from STDIN
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.flush();
               
        
        
        // ** 1. Retrive n and m from the first line
        try {
            String sLine = in.readLine();
            String[] firstLine = sLine.split(" ");
            
            n = Integer.parseInt(firstLine[0]);
            //System.out.println("Numero n: " + n);
            m = Integer.parseInt(firstLine[1]);
            //System.out.println("Numero m: " + m);
            
            if(n < 1 || n > 50000 || m < 1 || m > n) {
            	System.exit(-1);
            }
        } catch (Exception e){
        	System.exit(-1);
        }

        
        // ** 2. Retrive the song list       
        int[] fi = new int[n];			// array how many times the ith song was listened
        float[] qi = new float[n];		// array song quality index
        String[] si = new String[n];	// array song title
        

        for(int i = 0; i < n; i++){
        	
        	try {
        		
        		// read line
	        	String sLine = in.readLine();
	        	String[] songLine = sLine.split(" ");
	        	
	        	// get integer: how many times a song was listened (0 - 1012 times)
	        	fi[i] = Integer.parseInt(songLine[0]);
	        	//System.out.println("Numero fi: " + fi[i]);
	        	
	        	if(fi[i] < 0 || fi[i] > 1012){
	        		System.exit(-1);
	        	}

	        	// get string: name of the song (30 chars, a-z, 0-9, _)
	        	si[i] = songLine[1];
	        	//System.out.println("Stringa si: " + si[i]);   
	        	
	        	if(!si[i].matches("[a-z0-9_]{1,30}")){
	        		System.exit(-1);
	        	}
	        	
	        	qi[i] = (float) fi[i]/(fi[0]/(i+1));
	        	//System.out.println("Qualità qi: " + qi[i]);
	        	
	        	
	        	
            } catch (Exception e){
            	System.exit(-1);
            }
        }
        
		// ** 3. unset fi array
        fi = null;
        
        // ** 4. sort arrays qi and si, based on quality
        bubbleSort(qi, si);
        
        // ** 5. print song chart
        printChart(si, m);
        
            
        
  	}

	
	
	/**
	 * Bubble Sort, array sorting for song list based on quality index
	 * 
     * @param qi, si 
	 */	
	private static void bubbleSort(float[] qi, String si[]) {
		
	    boolean swapped = true;
	    int j = 0;
	    float tmp_qi;
	    String tmp_si;
	    
	    while (swapped) {
	        swapped = false;
	        j++;
	        for (int i = 0; i < qi.length - j; i++) {
	            if (qi[i] >= qi[i + 1]) {			// >= allow to print songs with the same qi in the right album order
	                tmp_qi = qi[i];
	                qi[i] = qi[i + 1];
	                qi[i + 1] = tmp_qi;
	                
	                tmp_si = si[i];
	                si[i] = si[i + 1];
	                si[i + 1] = tmp_si;
	                
	                swapped = true;
	            }
	        }
	    }
	
	}
	
	
	
	/**
	 * Print m songs
	 * 
     * @param si, m 
	 */		
	private static void printChart(String[] si, int m){
		
        for (int i = si.length - 1; i > si.length - 1 - m; i--) {
            System.out.println(si[i]);
        }
		
	}
	
}
