import java.util.*;

class Adder { //chooses whether to use addition or subtraction for addition
	ArrayList<Integer> add (ArrayList <Integer> x, ArrayList <Integer> y, int b){
		int x_end = x.size()-1; //last index of x
		int y_end = y.size()-1; //last index of y
		
		boolean x_pos = x.get(x_end) == 0; //check whether x is negative
		x.remove(x_end);
		boolean y_pos = y.get(y_end) == 0; //check whether y is negative
		y.remove(y_end);
		
		while (x.size() < y.size()) { //makes x as large as y if x is smaller
			x.add(0, 0);
		}
		while(y.size() < x.size()) { // makes y as large as x if y is smaller
			y.add(0,0);
		}
		
		ArrayList<Integer> result; 
		if (x_pos && y_pos) { // case 1: both x and y are positive
			result = do_add(x,y,b);
			result.add(0);
		}
		else if (!x_pos && !y_pos) { //case 2: both x and y are negative
			result = do_add(x,y,b);
			result.add(1);
		}
		else if (x_pos && !y_pos) { // case 3: x is positive and y is negative
			result = do_sub(x,y,b);
		}
		else {
			result = do_sub(y,x,b);	//case 4: x is negative and y is positive
		}
		while(result.get(0) == 0) { //remove the insignificant zeroes from the result
			result.remove(0);
		}
		return result;
	}
	

	ArrayList<Integer> sub (ArrayList <Integer> x, ArrayList <Integer> y, int b){//chooses whether to use addition or subtraction for subtraction
		int x_end = x.size()-1; //last index of x
		int y_end = y.size()-1; //last index of y
		
		boolean x_pos = x.get(x_end) == 0; //check whether x is negative
		x.remove(x_end);
		boolean y_pos = y.get(y_end) == 0; //check whether y is negative
		y.remove(y_end);
		
		while (x.size() < y.size()) {
			x.add(0, 0);
		}
		while(y.size() < x.size()) {
			y.add(0,0);
		}
		
		ArrayList<Integer> result;
		if (x_pos && y_pos) { // case 1: both x and y are positive
			result = do_sub(x,y,b);
		}
		else if (!x_pos && !y_pos) { //case 2: both x and y are negative
			result = do_sub(y,x,b);
		}
		else if (x_pos && !y_pos) { // case 3: x is positive and y is negative
			result = do_add(x,y,b);
			result.add(0);
		}
		else { //case 4: x is negative and y is positive
			result = do_add(x,y,b);	
			result.add(1);
		}
		while(result.get(0) == 0) { //remove the insignificant zeroes from the result
			result.remove(0);
		}
		return result;
	}

	ArrayList<Integer> do_add (ArrayList <Integer> x, ArrayList <Integer> y, int b){ // does addition
		int length = x.size()-1;
		int carry = 0;
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = length; i >= 0; i--) { // adds the digits in x and y to each other, with a carry if necessary 
			int digit =  x.get(i) + y.get(i)+ carry;
			if (digit >= b) {
				carry = 1;
				digit -= b;
			}
			else {
				carry = 0;
			}
			result.add(0,digit);
			
		}
		
		if (carry==1) {
			result.add(0,1);
		}
		return result;
	}

	ArrayList<Integer> do_sub (ArrayList <Integer> x, ArrayList <Integer> y, int b){ //does subtraction
		int length = x.size()-1;
		int carry = 0;
		ArrayList<Integer> result = new ArrayList<>();
		boolean swap = false;
		
		for(int i = 0; i <= length;i++) { // checks whether swapping is necessary, depending on which number is the biggest
			if (x.get(i) > y.get(i)) { 
				break;
			}
			else if (y.get(i) > x.get(i)) {
				swap = true;
				break;
			}
		}
		if (swap) {
			ArrayList<Integer> temp = y;
			y=x;
			x=temp;
		}
		for(int i = length; i >= 0; i--) { // subtracts the digits in x and y from each other, with a carry if necessary
			int digit =  x.get(i) - y.get(i)- carry;
			if (digit < 0) {
				carry = 1;
				digit += b;
			}
			else {
				carry = 0;
			}
			result.add(0,digit);
			
		}
		
		if (swap) {
			result.add(1); // if you swapped, the result is negative
		}
		else {
			result.add(0); // no swap means that you get a positive number
		}
		
		return result;
	}
}