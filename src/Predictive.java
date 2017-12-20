import java.util.ArrayList;

public class Predictive {
	protected Matrix myMat;
	protected Integer q_bits;

	private Integer ceil(Integer x, Integer y) {
		return (x + y - 1) / y;
	}

	protected Integer predict(Integer a, Integer b, Integer c) {
		if (a < Math.min(b, c))
			return Math.max(b, c);
		if (a > Math.max(b, c))
			return Math.min(b, c);
		return b + c - a;
	}

	protected Integer match(ArrayList<Pair<Integer, Integer>> a, Integer val) {
		// return the index of the first interval including the val.
		int l = 0, r = (int) a.size() - 1, ret = -1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (a.get(mid).second >= val) {
				ret = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return ret;
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
		Integer range_size = ceil(all, levels);
		Integer step_size = range_size - 1;
		for (int i = 0; i < levels; ++i) {
			Integer first = min + i * range_size;
			Integer second = Math.min(first + step_size, max);
			ret.add(new Pair<Integer, Integer>(first, second));
		}
		return ret;
	}
}
