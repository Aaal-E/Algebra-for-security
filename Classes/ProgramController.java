
import java.util.*;
class ProgramController {

	String command;
	int base = 2;
	String currentcommand = "";
	ArrayList<Integer> x;
	ArrayList<Integer> y;
	ArrayList<Integer> m;
	Formatter formatter = new Formatter();
	ArrayList<Integer> countadd;
	ArrayList<Integer> countmul;
	
	void run() {
		while((command = formatter.nextInput()) != null) { //keep reading inputs until the file ends
			parse(command);
		}
	}
	
	void parse(String cmd) {
	    switch (cmd) {
	    case "[radix]": base = Integer.parseInt(formatter.nextInput());
	    
	    case "[add]": currentcommand = "add";
	    
	    case "[x]": x = formatter.store(formatter.nextInput(), base);
	    
	    case "[y]": y = formatter.store(formatter.nextInput(), base);
	    
	    case "[subtract]": currentcommand = "subtract";
	    
	    case "[multiply]": currentcommand = "multiply";
	        
	    case "[karatsuba]": currentcommand = "karatsuba";
	    
	    
	    
	    
	    
	    case "[answer]": {
	        switch (currentcommand) {
	        case "add": {
	            if(m.size()>0);  //formatter.print(calc.add(x,y,base), base);
	            else ; //formatter.print(calc.add(x,y,m,base), base);
	            m.clear();
	        }
	            
	        case "subtract": {
	            if(m.size()>0); //formatter.print(calc.sub(x,y,base), base);
                else ; //formatter.print(calc.sub(x,y,m,base), base);
                m.clear();
	        }
	            
	        case "multiply": {
	            if(m.size()>0) {
	                //formatter.print(calc.mul(x,y,base), base);
	                //countadd = amount of additions
	                //countmul = amount of multiplications
	            } else {
	                //formatter.print(calc.mul(x,y,m,base), base);
                    //countadd = amount of additions
                    //countmul = amount of multiplications
	            }
	            m.clear();
	        }
	        
	        }    
	    }
	    
	    case "[count-add]": formatter.print(countadd);
	        
	    case "[count-mul]": formatter.print(countmul);
	    
	    case "[m]": m = formatter.store(formatter.nextInput(), base);
	    }
	}
	
	public static void main(String[] args){
		new ProgramController().run();
	}
}

