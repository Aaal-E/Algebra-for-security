import java.util.*;

class Multiplier {
	
	ArrayList <Integer> multiply (ArrayList <Integer> x, ArrayList <Integer> y, int b ){
		
		int m = x.size();
		int n = y.size();
		int k; //size of the result
		int c, t; //carry and dummy variable t
		
		ArrayList <Integer> z = new ArrayList <Integer>();
		
		for (int i = n; i < m + n -1; i++)
			z.set(i,0); //reset the resulting array z = xy.
		
		for (int i = 0; i < m; i++) {
			c = 0; //reset the carry
			for ( int j = 0; j < n; j++) {
				
			   t = z.get(i+j) + x.get(i)*y.get(j) + c; //do the multiplication with carry
			   
			   c = Math.floorDiv(t, b);
			   
			   z.set(i+j, t - c*b);	
			}
			z.set(i+n, c); //store the new carry
		}
		
		if(z.get(m + n - 1) == 0)
			k = m + n - 2;
		else
			k = m + n - 1;
		
		if (x.get(m) == 1 || y.get(n) == 1)
			z.set(k, 1);   //if one of the nrs is negative, the result is negative.
		else
			z.set(k, 0);    //if both pos or negative, the result is positive.
		          
		return z; // [z]_b in the usual form sum_{0}^{m+n-1} ( z_i* b^{i)

	}
	public static void main (String [] args ) {
		
	}
}