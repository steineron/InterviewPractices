import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.*;

// https://practice.geeksforgeeks.org/problems/save-ironman

class IsPalindrom {

    private static Pattern alphaNumeric = Pattern.compile("\\d[a-zA-Z]");
    
	public static void main (String[] args) {
	 //code
    	 Scanner scan = new Scanner(System.in);
    	 int t = scan.nextInt();
    	 while(t>0){
    	     String s = scan.next();
    	     System.out.println(isPalindrom(s) ? "YES" : "NO");
    	 }
	 }
	 
	 private static boolean isPalindrom(final String s){
	     char[] chars = s.toCharArray();
	     int leftIndex = 0;
	     int rightIndex = s.length()-1;
	     while(leftIndex <= rightIndex){
	     	 if(!alphaNumeric.matcher(chars[leftIndex]).matches()){
	     	 		 leftIndex++;
	     	 }
	     	 else if(!alphaNumeric.matcher(chars[rightIndex]).matches()){
	     	 		 rightIndex--;
	     	 }
	     	 else if(chars[leftIndex]!=chars[rightIndex]){
	     	 	 return false;
	     	 }
	     }
	     return true;
	 }
}