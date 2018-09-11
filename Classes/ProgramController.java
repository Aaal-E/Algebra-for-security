

class ProgramController {

	String command;
	
	void run() {
		Formatter formatter = new Formatter();
		while((command = formatter.nextInput()) != null) {
			parse(command);
		}
	}
	
	void parse(String cmd) {
	    
	}
	
	public static void main(String[] args){
		new ProgramController().run();
	}
}

