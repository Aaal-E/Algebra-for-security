import java.util.*;

class Adder {
	
	ArrayList<Boolean> add(ArrayList<Boolean> number1, ArrayList<Boolean> number2) {
		
		if ((number1.get(number1.size()) == true) && (number2.get(number2.size()) == true)) { // Both are negative
			
		} else if ((number1.get(number1.size()) == false) && (number2.get(number2.size()) == false)) { //both are positive
				
			} else 
		
		number1.remove(number1.size()-1); //Remove the sign bit from number1
		number2.remove(number2.size()-1); //Remove the sign bit from number2
		
		return null;
	}	
	
}