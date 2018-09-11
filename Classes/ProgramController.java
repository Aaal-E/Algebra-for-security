

class ProgramController {

	String command;
	
	void run() {
		new Formatter formatter;
		while(command = formatter.nextInput() != null) {
			parse(command);
		}
	}
	
	
	
	public static void main(String[] args){
		new ProgramController().run();
	}
}

