import java.util.ArrayList;

public class myMath {
	public static final Integer int_size = 8;

	public static Integer ceil(Integer x, Integer y) {
		return (x + y - 1) / y;
	}

	public static String bin(Integer x, Integer len) {
		String ret = "";
		while (x > 0) {
			ret += String.valueOf(x % 2);
			x /= 2;
		}
		while (ret.length() < len)
			ret += "0";
		return new StringBuffer(ret).reverse().toString();
	}

	public static ArrayList<Integer> bin(ArrayList<Integer> decimal,
			Integer bits) {
		/*
		 * given array of decimal numbers convert them to binary, concatenate
		 * them and cut every 8 consecutive bits to form new integer!
		 */
		ArrayList<Integer> ret = new ArrayList<Integer>();
		String binary_string = "";
		for (int i = 0; i < decimal.size(); ++i) {
			binary_string += bin(decimal.get(i), bits);
		}
		while (binary_string.length() % int_size != 0)
			binary_string += "0";
		for (int i = 0; i != binary_string.length(); i += int_size) {
			ret.add(decimal(binary_string.substring(i, i + int_size)));
		}
		return ret;
	}

	public static Integer decimal(String binary) {
		Integer ret = 0;
		binary = new StringBuffer(binary).reverse().toString();
		for (int i = 0; i < binary.length(); ++i)
			ret += (int) Math.pow(2, i) * (binary.charAt(i) - '0');
		return ret;
	}

	public static ArrayList<Integer> decimal(ArrayList<Integer> binary,
			Integer n, Integer bits) {
		/*
		 * given array of numbers came from bin_fn,construct the binary string
		 * again hence, we can read n-numbers each of binary length=bits
		 */
		ArrayList<Integer> ret = new ArrayList<Integer>();
		String binary_string = "";
		for (int i = 0; i < binary.size(); ++i) {
			binary_string += bin(binary.get(i), int_size);
		}
		for (int i = 0; i < n; ++i) {
			ret.add(decimal(binary_string.substring(i, i + bits)));
		}
		return ret;
	}

	public static Integer match(ArrayList<Pair<Integer, Integer>> a, Integer val) {
		// return the index of the first interval including the val.
		int l = 0, r = (int) a.size() - 1, ret = -1;
		while (l <= r) {
			int mid = mid(new Pair<Integer, Integer>(l, r));
			if (a.get(mid).second >= val) {
				ret = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return ret;
	}

	public static Integer mid(Pair<Integer, Integer> p) {
		return (p.second - p.first) / 2 + p.first;
	}

}
