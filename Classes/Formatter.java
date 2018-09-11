/*
	handles the in and output to and from files, as well as the conversion from our storage format to a printable format
*/


import java.util.*;
import java.io.*;
class Formatter {
    
    BufferedReader reader;
    PrintWriter writer;
    
    Formatter(){
        try{
            reader = new BufferedReader(new FileReader("input.txt")); //we read via a buffered reader to easily read per line and character
            writer = new PrintWriter("Output.txt", "UTF-8"); //we open a central printer to avoid overwriting part of the file with other classes
        } catch(IOException e) {
            
        }
    }
    
    String nextInput() {
        try{
            String result = "";
            int intValueOfChar;
            while ((intValueOfChar = reader.read()) != -1) { //stop at end of file
                writer.print((char) intValueOfChar);
                if (intValueOfChar == 35) reader.readLine(); //ignore lines after #
                else if (intValueOfChar == 32) {
                    if (result != "") return result; //return the end of a word when encountering a space, but only if there is a word read
                } else {
                    result += (char) intValueOfChar; //add the read character to the resulting word
                }
            }
        } catch(IOException e) {
        }
        return null;
    }
    
    //print a line using the printer
    void println(String str) {
        writer.println(str);
    }
	
	//print a number
    void println(ArrayList<Boolean> number) {
		println(toString(number));
	}
	
	//print a number in a given base
	void println(ArrayList<Boolean> number, int base){
		println(toString(number, base));
	}
	
	//print a number with added prefix
	void println(String str, ArrayList<Boolean> number){
		str = str + toString(number);
		println(str);
	}
	
	//print a number in a given base with added prefix
	void println(String str, ArrayList<Boolean> number, int base){
		str = str + toString(number, base);
		println(str);
	}
    
    //close the writer, to use at the end of the program, when the entire output file has been produced
    void closeWriter() {
        writer.close();
    }
    
    //converts the boolean array to a binary String
    String toString(ArrayList<Boolean> number) {
        String result = "";
        if(number.get(number.size())) result = result + "-";
        number.remove(number.size());
        for(int i=number.size(); i>0; i--){
            if(number.get(i)) { result = result + "1"; 
            } else {
                result = result + "0";
            }
        }
        return result;
    }
    
    //converts the boolean array to a String of base b
    String toString(ArrayList<Boolean> number, int base) {
        ArrayList<Integer> convertednumber = toBase(number, base);
        String result = "";
        if(convertednumber.get(convertednumber.size()) == 1) result = result + "-";
        convertednumber.remove(convertednumber.size());                
        for(int i=convertednumber.size(); i>0; i--){
            result = result + convertednumber.get(i);
        }
        return result; 
    }
    
    //change the boolean array to a digit array
    ArrayList<Integer> toBase(ArrayList<Boolean> number, int base) {
        ArrayList<Integer> temp = toInteger(number);
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(number.size() != 0){
            //divide temp by base, store quotient in temp and remainder in remainder
            //result.add(remainder);
        }
        return result;
    }
    
    //transforms an array of digits into a binary array of digits
    ArrayList<Integer> toBinary(ArrayList<Integer> num, int base){
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(num.size() != 0) {
            //divide number by 2, store quotient in num, and remainder in r
            //result.add(r)
        }  
        return result;
    }
    
    //converts a string from a base to a boolean array
    ArrayList<Boolean> convert(String str, int base){
        ArrayList<Integer> temp = store(str, base);
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        if (base != 2) {
            toBinary(temp, base);
        }
        for(int i=0;i<temp.size(); i++) {
            result.add(temp.get(i).equals(1));
        }
        return result;
    }
    
    ArrayList<Integer> toInteger(ArrayList<Boolean> number){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 0; i<number.size();i++) {
            if(number.get(i)) temp.add(1); else temp.add(0); 
        }
        return temp;
    }
    
    //stores a string of numbers as an arraylist of its digits
    ArrayList<Integer> store(String str, int base){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int neg = 0;
        if(str.substring(0, 1) == "-") {
            str = str.substring(1, str.length());
            neg = 1;
        }
        result.add(neg);
        for(int i=str.length();i>0;i--){
            result.add(Integer.parseInt(str.substring(i, i+1), base));
        }
        
        return result;
    }
}