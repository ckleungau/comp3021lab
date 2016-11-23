package lab11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersTraditional {
	
	public static List<Integer> isOdd(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		for (int n : numbers) {
			if (n % 2 != 0) results.add(n);
		}
		return results;
	}
	
	public static List<Integer> isPrime(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		// TODO
		// Find out all the prime numbers
        for (int n : numbers) {
            if ( checkPrime(n) ) {
                results.add(n);
            }
        }
		return results;
	}
	
	public static List<Integer> isPalindrome(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		// TODO
		// Find out all the palindrome numbers, such as 484 and 121.
        for (int n : numbers) {
            if ( checkPalindrome(n) ) {
                results.add(n);
            }
        }
		return results;
	}

	public static boolean checkPrime(int x) {
        // check if x is a multiple of 2
        if (x % 2 == 0) { return false; }
        // if not, then just check the odds
        for (int i = 3; (i*i) <= x ; i += 2) {
            if (x % i == 0) { return false; }
        }
        return true;
    }

    public static boolean checkPalindrome(int x) {
        // check if n is a negative integer
        if (x < 0) { return false; }
        // if not, then continue the checking
        int div = 1;
        while ( (x / div) >= 10 ) {
            div *= 10;
        }
        while (x != 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) { return false; }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(480,514,484,389,709,935,328,169,649,300,685,429,243,532,308,87,25,282,91,415);
		
		System.out.println("The odd numbers are : " + isOdd(numbers));
		System.out.println("The prime numbers are : " + isPrime(numbers));
		System.out.println("The palindrome numbers are : " + isPalindrome(numbers));
		
	}
}
