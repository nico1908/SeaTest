//import java.math.*;

public class ReserveIntSolution {

	public int reverse(int x) {
		int r = 0;
		double r1 = 0;
		Integer a = x;
		int b;
		if (x >= 0)
			b = a.toString().length();
		else
			b = a.toString().length() - 1;

		for (int i = b; i > 0; i--) {
			r = x % 10;
			x = x / 10;
			r1 = r * Math.pow(10, i - 1) + r1;
		}
		if (r1 > Integer.MAX_VALUE || r1 < Integer.MIN_VALUE)
			return 0;
		else {
			return r = (int) r1;
		}
	}
	public int reverse1(int x) {
        long rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            x= x/10;
            if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }
}
