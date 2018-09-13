/*
	stores big numbers as a boolean arraylist
	maybe add a method to convert to string (in base b)
*/
import java.util.*;

class BigNumber {
	
	ArrayList<Integer> number = new ArrayList<Integer>();
	
	void setNumber(ArrayList<Integer> newnumber) {
		number = newnumber;
	}
	
	ArrayList<Integer> getNumber() {
		return number;
	}
	
	
}