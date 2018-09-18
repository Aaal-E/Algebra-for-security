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
	Adder adder = new Adder();
	ArrayList<Integer> a;
	ArrayList<Integer> b;
	Multiplier multiplier = new Multiplier();
	Modulator modulator = new Modulator();
	ArrayList<Integer> result;
	Inverser inverser = new Inverser();
	
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
	    
	    
	    
	    
	    
	    case "[answer]": {  //process the commands previously read, and output the output
	        switch (currentcommand) {
	        case "add": {
	            if(m.size()>0) formatter.print(adder.add(x,y,base), base);
	            else ;//formatter.print(adder.add(x,y,m,base), base);
	            m.clear();
	        }
	            
	        case "subtract": {
	            if(m.size()>0) formatter.print(adder.sub(x,y,base), base);
                else ; //formatter.print(calc.sub(x,y,m,base), base);
                m.clear();
	        }
	            
	        case "multiply": {
	            if(m.size()>0) {
	                //formatter.print(multiplier.mul(x,y,base), base);
	                //countadd = amount of additions
	                //countmul = amount of multiplications
	            } else {
	                //formatter.print(multiplier.mul(x,y,m,base), base);
                    //countadd = multiplier.getCountAdd();
                    //countmul = multiplier.getCountMul();
	            }
	            m.clear();
	        }
	        
	        case "reduce": {
	            formatter.print(modulator.mod(x, m, base));
	            m.clear();
	        }
	        
	        case "inverse": {
	            result = inverser.invert(x, m, base);
	            if(result != null) formatter.print(result);
	            else formatter.print("ERROR");
	        }
	           
	        }    
	    }
	    
	    case "[count-add]": formatter.print(countadd);
	        
	    case "[count-mul]": formatter.print(countmul);
	    
	    case "[m]": m = formatter.store(formatter.nextInput(), base);
	    
	    case "[reduce]": currentcommand = "reduce";
	    
	    case "[inverse]": currentcommand = "inverse";
	    
	    case "[euclid]": currentcommand = "euclid";
	    
	    case "[answ-a]": formatter.print(a);
	    
	    case "[answ-b]": formatter.print(b);
	    
	    case "[answ-d]": if(currentcommand == "euclid"){
	        //formatter.print(euclid.run(x, y, base), base);
	        //a = a
	        //b = b
	            
	    }
	    }
	}
	
	public static void main(String[] args){
		new ProgramController().run();
	}
}

