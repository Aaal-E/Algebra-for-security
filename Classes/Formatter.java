/*
	handles the in and output to and from files, as well as the conversion from our storage format to a printable format
*/


import java.util.*;
import java.io.*;
class Formatter {
    
    BufferedReader reader;
    PrintWriter writer;
    
    public Formatter(){
        try{
            reader = new BufferedReader(new FileReader("input.txt")); //we read via a buffered reader to easily read per line and character
            writer = new PrintWriter("output.txt", "UTF-8"); //we open a central printer to avoid overwriting part of the file with other classes
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String nextInput() {
        try{
            String result = "";
            int intValueOfChar;
            while ((intValueOfChar = reader.read()) != -1) { //stop at end of file
                writer.print((char) intValueOfChar);
                String temp;
                if (intValueOfChar == 35) {
                    temp = reader.readLine(); //ignore lines after #
                    writer.println(temp);
                } else if (intValueOfChar == 32) {
                    if (result != "") return result; //return the end of a word when encountering a space, but only if there is a word read
                } else {
                    result += (char) intValueOfChar; //add the read character to the resulting word
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    //print a line using the printer
    void print(String str) {
        writer.print(str);
    }
	
	//print a number
    void print(ArrayList<Integer> number) {
		print(toString(number));
	}
	
	//print a number in a given base
	void print(ArrayList<Integer> number, int base){
		print(toString(number, base));
	}
	
	//print a number with added prefix
	void print(String str, ArrayList<Integer> number){
		str = str + toString(number);
		print(str);
	}
	
	//print a number in a given base with added prefix
	void println(String str, ArrayList<Integer> number, int base){
		str = str + toString(number, base);
		print(str);
	}
    
    //close the writer, to use at the end of the program, when the entire output file has been produced
    void closeWriter() {
        writer.close();
    }
    
    //converts the array to a binary String
    String toString(ArrayList<Integer> number) {
        String result = "";
        if(number.get(number.size()) == 1) result = result + "-";
        number.remove(number.size());
        for(int i=number.size(); i>0; i--){
            result = result + number.get(i);
        }
        return result;
    }
    
    //converts the array to a String of base b
    String toString(ArrayList<Integer> number, int base) {
        String result = "";
        if(number.get(number.size()) == 1) result = result + "-";
        number.remove(number.size());
            for(int i = number.size(); i>0; i--) {
                result = result + Integer.toString(number.get(i), base);
            }
        return result; 
    }
    
    //stores a string of numbers as an arraylist of its digits
    ArrayList<Integer> store(String str, int base){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int neg = 0;
        if(str.substring(0, 1) == "-") {
            str = str.substring(1, str.length());
            neg = 1;
        }
        
        for(int i=str.length();i>0;i--){
            result.add(Integer.parseInt(str.substring(i, i+1), base));
        }
        result.add(neg);
        return result;
    }
}
