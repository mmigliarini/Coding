<?php
/**
 * Palindrome is a collection of utilities for Palindromic String.
 * It includes methods for getting longet palindrome, inverse a number, etc.
 *
 * @author     Mauro Migliarini <mauro.migliarini@mail.polimi.it>
 * @copyright  2013 Mauro Migliarini
 * @license    http://www.opensource.org/licenses/lgpl-license.php LGPL
 * @link       http://github.com/mmigliarini/Palindrome
 * @version    1.1.0
 */
class Palindrome {

	// Vars
	private $string;
	private $longest;
	
	
	/**
	 * Constructor
	 *
	 * @param string $string An input string
	 */	
	public function __construct($string = ''){
		$this->string = $string;
	}
	
	
	/**
	 * Return current input string
	 *
	 * @return string Current Input String
	 */		
	public function getString(){
		return $this->string;
	}
	
	
	
	/**
	 * Return the longest palindrome substring found in the input string, with a O(N2) complexity
	 *
	 * @return string Longest Palindrome
	 */	
	public function getLongestPalindrome(){
		
		// get string length
		$len = strlen($this->string);
		
		// 
		if($len==0) return "";
		
		$this->longest = $this->string[0]; // initialize longest palindrome with the first char
		
		for($i=1; $i<$len; $i++){
			
			// the center is a letter
			$current1 = $this->expandPalindrome($i-1, $i+1);
			if(strlen($current1)>strlen($this->longest)) $this->longest = $current1;
			
			// the center is between two letters
			$current2 = $this->expandPalindrome($i-1, $i);
			if(strlen($current2)>strlen($this->longest)) $this->longest = $current2;			
					
		}
		
		
		return $this->longest;
		
	}


	
	/**
	 * Expand a found Palindrome, decrementing left index and incrementing right index
	 *
	 * @return string Exstended Palindrome
	 */
	private function expandPalindrome($left, $right){
		
		$li = $left;	// left index
		$ri = $right;	// right index
		
		$len = strlen($this->string);	// computed once
		
		while($li>=0 && $ri<$len && $this->string[$li]==$this->string[$ri]){
			$li--;
			$ri++;
		}
		
		return substr($this->string, $li+1, $ri-($li+1));
	}
		

	
	/**
	 * Verify if a given input is Palindromic 
	 *
	 * @return boolean 
	 */
	public function isPalindrome($input = '') {
		
		if($input='') $input = $this->string;
		
		if(is_integer($input)){	
			return $this->isPalindromeNumber($input);
		} elseif (is_string($input)){
			return $this->isPalindromeString($input);
		} else {
			return false;
		}
		
	}
	

	
	/**
	 * Verify if an input STRING is Palindrome 
	 *
	 * @return boolean 
	 */
	private function isPalindromeString($string){
		
		if(strlen($string)==0 || empty($string)) return false;
		
		$len = strlen($string);
		for($i=0; $i<floor($len/2); $i++){
			if($string[$i] != $string[$len-$i-1]){
				return false;
			}
		}
		
		return true;
	}


	/**
	 * Verify if an input NUMBER is Palindrome 
	 *
	 * @return boolean 
	 */
	private function isPalindromeNumber($num){
		if($num = $this->reverseNumber($num))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Reverse an integer
	 *
	 * @param int $num An input number
	 * @return int Reversed Number
	 */
	private function reverseNumber($num){
		
		$rev = 0;
		while ( $num != 0 ) {
			$rev = $rev*10 + $num%10;
			$num = (int)($num/10);
		}
		
		return $rev;
	}
	
	
}



#######################################################################################################

$palindrome = new Palindrome("aa");
echo "String: ".$palindrome->getString()." - Longest Palindrome: ".$palindrome->getLongestPalindrome();



