import java.util.*;

class Multiplier {
	
    ArrayList <Integer> countadd = new ArrayList <Integer>();
    ArrayList <Integer> countmul = new ArrayList <Integer>();
    Modulator mod = new Modulator();
	ArrayList <Integer> mul (ArrayList <Integer> x, ArrayList <Integer> y, int b ){
		
		int m = x.size();
		int n = y.size();
		int k; //size of the result
		int carry, t; //carry and dummy variable t
		
		ArrayList <Integer> z = new ArrayList <Integer>();
		
		countadd.clear();
		countmul.clear();
		
		for (int i = n; i < m + n -1; i++)
			z.set(i,0); //reset the resulting array z = xy.
		
		for (int i = 0; i < m; i++) {
			carry = 0; //reset the carry
			for ( int j = 0; j < n; j++) {
				
			   t = z.get(i+j) + x.get(i)*y.get(j) + carry; //do the multiplication with carry
			   
			   carry = Math.floorDiv(t, b); //update the carry
			   
			   z.set(i+j, t - carry*b);	//t - carry * b = t mod b = t % b. 
			}
			z.set(i+n, carry); //store the new carry
		}
		
		if(z.get(m + n - 1) == 0)
			k = m + n - 2;           //update the size of the result of the multiplication
		else
			k = m + n - 1;
		
		if (x.get(m) == 1 || y.get(n) == 1)
			z.set(k, 1);   //if one of the nrs is negative, the result is negative.
		else
			z.set(k, 0);    //if both pos or negative, the result is positive.
		          
		countadd.add(0,m + m*(n * 7) + 6);  //first for + second for (==) + if -Is this correct?
		countmul.add (0,m * n * 3);        // is this correct?
        
				
		return z; // [z]_b in the usual form sum_{0}^{k} ( z_i* b^{i)

	}
	ArrayList<Integer> getCountAdd() {
			
	    return countadd;  
    }
       
    ArrayList<Integer> getCountMul() {
           
        return countmul;
    }
<<<<<<< HEAD:Classes/Integer Arithmatic/Muliplier.java
    
    ArrayList<Integer> mul (ArrayList <Integer> x, ArrayList <Integer> y, ArrayList<Integer> m, int b ){
        x = mod.mod(x, m, b);
        y = mod.mod(y, m, b);
        return mod.mod(mul(x, y, b), m, b);
    }
}
=======
		
}
>>>>>>> aa343c107378343b7169c1ab0b4c97785928a9bc:Classes/Integer Arithmatic/Multiplier.java
