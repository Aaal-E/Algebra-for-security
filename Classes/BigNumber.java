/*
	stores big numbers as a boolean arraylist
	maybe add a method to convert to string (in base b)
*/
import java.util.*;

class BigNumber {
	
	ArrayList<Boolean> number = new ArrayList<Boolean>();
	
	void setNumber(ArrayList<Boolean> newnumber) {
		number = newnumber;
	}
	
	ArrayList<Boolean> getNumber() {
		return number;
	}
	
	
}