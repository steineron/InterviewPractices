import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.*;

// https://practice.geeksforgeeks.org/problems/save-ironman

class IsPalindrom {

    private static Pattern alphaNumeric = Pattern.compile("\\d[a-zA-Z]");
    private static boolean verbose;
    
	public static void main (String[] args) {
	 //code
    	 Scanner scan = new Scanner(System.in).useDelimiter("\\n");
    	 verbose = args.length > 0;
    	 if(verbose){
    	 	 System.out.println("Enter the number of tests:");
    	 }
    	 int t = scan.nextInt();
    	 while(t>0){
    	 	 if(verbose){
    	 	 	 System.out.println("Enter a test:");
    	 	 }
    	     String s = scan.next();
    	     System.out.println(isPalindrom(s) ? "YES" : "NO");
    	     t--;
    	 }
    	 scan.close();
	 }
	 
	 private static boolean isPalindrom(final String s){
	     char[] chars = s.toCharArray();
	     int leftIndex = 0;
	     int rightIndex = s.length()-1;
	     while(leftIndex <= rightIndex){
	     	 if(!isAlphaNumeric(chars[leftIndex])){
	     	 		 leftIndex++;
	     	 }
	     	 else if(!isAlphaNumeric(chars[rightIndex])){
	     	 		 rightIndex--;
	     	 }
	     	 else if(chars[leftIndex]!=chars[rightIndex]){
	     	 	 return false;
	     	 }
	     	 else{
	     	 	 leftIndex++;
	     	 	 rightIndex--;

	     	 }
	     }
	     return true;
	 }
	 
	 private static boolean isAlphaNumeric(final char c){
	 	 boolean result = (c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='1' && c<='9');
	 	 if(verbose){
	 	 	 System.out.println(""+c+String.format("(%d) %s AN", (int)c, result ? "is" : "isn't"));
	 	 }
	 	 
	 	 return result;
	 }
}