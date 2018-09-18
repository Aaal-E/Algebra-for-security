import java.util.*;

class Adder { //chooses whether to use addition or subtraction
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
		if (x_pos && y_pos) {
			result = do_add(x,y,b);
			result.add(0);
		}
		else if (!x_pos && !y_pos) {
			result = do_add(x,y,b);
			result.add(1);
		}
		else if (x_pos && !y_pos) {
			result = do_sub(x,y,b);
		}
		else {
			result = do_sub(y,x,b);	
		}
		while(result.get(0) == 0) {
			result.remove(0);
		}
		return result;
	}
	

	ArrayList<Integer> sub (ArrayList <Integer> x, ArrayList <Integer> y, int b){
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
		if (x_pos && y_pos) {
			result = do_sub(x,y,b);
		}
		else if (!x_pos && !y_pos) {
			result = do_sub(y,x,b);
		}
		else if (x_pos && !y_pos) {
			result = do_add(x,y,b);
			result.add(0);
		}
		else {
			result = do_add(x,y,b);	
			result.add(1);
		}
		while(result.get(0) == 0) {
			result.remove(0);
		}
		return result;
	}

	ArrayList<Integer> do_add (ArrayList <Integer> x, ArrayList <Integer> y, int b){
		int length = x.size()-1;
		int carry = 0;
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = length; i >= 0; i--) {
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

	ArrayList<Integer> do_sub (ArrayList <Integer> x, ArrayList <Integer> y, int b){
		int length = x.size()-1;
		int carry = 0;
		ArrayList<Integer> result = new ArrayList<>();
		boolean swap = false;
		
		for(int i = 0; i <= length;i++) {
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
		for(int i = length; i >= 0; i--) {
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
			result.add(1);
		}
		else {
			result.add(0);
		}
		
		return result;
	}
}