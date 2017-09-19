
public class PalindromeNumSolution {

	public boolean isPalindrome(int x) {
		boolean b = false;
		Integer a = x;
		int l;

		if (x < 0)
			b = false;
		else {
			l = a.toString().length();

			double h2 = x / Math.pow(10, (l - l / 2));
			int h3 = (int) h2;
			int res = 0;
			while (h3 != 0) {
				res = res * 10 + h3 % 10;
				h3 = h3 / 10;
			}
			double h = x % Math.pow(10, l / 2);
			int h1 = (int) h;
			if (res == h1)
				b = true;
			else {
				b = false;
			}
		}
		return b;
	}

	public static boolean isPalindrome1(int x) {
		if (x < 0 || (x != 0 && x % 10 == 0))
			return false;
		int rev = 0;
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
		return (x == rev || x == rev / 10);
	}
}