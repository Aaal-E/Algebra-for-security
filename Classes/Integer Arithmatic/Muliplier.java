import java.util.*;

class Multiplier {
	
	ArrayList <Boolean> multiply (ArrayList <Boolean> number1, ArrayList <Boolean> number2 ){
	
		if ( number1.get(number1.size() ) == true) && number2.get(number2.size() ) == true )
		
	    //if both numbers are negative, the result is positive
		
		number1.remove (number1.size() - 1); //removing the sign bit for both numbers
	    number2.remove ( number2.size() -1);
		
		ArrayList <Boolean> result = new ArrayList<Boolean>(); //declaring the result
		
		for (int i = 0; i < number1.size(); i++)
			for ( int j = 0; j < number2.size(); j++) {
			   if(number2.get(j) == false )
				   
				   //if I multiply only by 0, then skip, nothing changes
				   continue;
			   else
				if ( number2.get(j) == true && number1.get(i) == true)
					// 1 . 1 = 1. Corresponds to the index of 
					//result.set(i+j, (int)(result.get(i+j)) + 1);
					
			     		
			   	   
			}  
		        
	}
	
	
	
	
	
	public static void main (String [] args ) {
		
		
		
		
	}
}