
import java.util.*;
class ProgramController {

	String command;
	int base = 2;
	String currentcommand = "";
	ArrayList<Boolean> x;
	ArrayList<Boolean> y;
	Formatter formatter = new Formatter();
	
	void run() {
		while((command = formatter.nextInput()) != null) { //keep reading inputs until the file ends
			parse(command);
		}
	}
	
	void parse(String cmd) {
	    switch (cmd) {
	    case "[radix]": base = Integer.parseInt(formatter.nextInput());
	    
	    case "[add]": currentcommand = "add";
	    
	    case "[x]": x = formatter.convert(formatter.nextInput(), base);
	    
	    case "[y]": y = formatter.convert(formatter.nextInput(), base);
	    }
	}
	
	public static void main(String[] args){
		new ProgramController().run();
	}
}

