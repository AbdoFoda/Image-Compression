import java.util.ArrayList;

public class Predictive {
	protected Matrix myMat;
	protected Integer q_bits;
	protected Integer min;
	protected Integer max;

	protected Integer predict(Integer a, Integer b, Integer c) {
		if (a < Math.min(b, c))
			return Math.max(b, c);
		if (a > Math.max(b, c))
			return Math.min(b, c);
		return b + c - a;
	}

	
	protected ArrayList<Pair<Integer, Integer>> quantize(Integer min,
			Integer max, Integer bits) {
		/*
		 * we have all=(max-min+1) possible Integers in our range so, we will
		 * have range_size=ceil(all,levels) Integer in each range then ,
		 * step_size =range_size-1 , and the last range will be minimized with
		 * max
		 */
		ArrayList<Pair<Integer, Integer>> ret = new ArrayList<Pair<Integer, Integer>>();
		Integer levels = (int) Math.pow(2, bits);
		Integer all = (max - min + 1);
		Integer range_size = myMath.ceil(all, levels);
		Integer step_size = range_size - 1;
		for (int i = 0; i < levels; ++i) {
			Integer first = min + i * range_size;
			Integer second = Math.min(first + step_size, max);
			ret.add(new Pair<Integer, Integer>(first, second));
		}
		return ret;
	}
}
