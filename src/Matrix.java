import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
	private ArrayList<ArrayList<Integer>> mat;
	private Integer r, c;
	private static final Integer OO = 10000000;

	Matrix(Integer r, Integer c) {
		this.r = r;
		this.c = c;
		mat = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < r; ++i) {
			mat.add(new ArrayList<Integer>(Arrays.asList(new Integer[c])));
		}
	}

	Matrix(Matrix rhs) {
		this.r = rhs.r;
		this.c = rhs.c;
		mat = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < r; ++i)
			mat.add(new ArrayList<Integer>(Arrays.asList(new Integer[c])));

		for (int i = 0; i < r; ++i)
			for (int j = 0; j < c; ++j)
				mat.get(i).set(j, rhs.mat.get(i).get(j));
	}

	public void set(int i, int j, int val) {
		mat.get(i).set(j, val);
	}

	public Integer get(int i, int j) {
		return mat.get(i).get(j);
	}

	public Integer getRow() {
		return r;
	}

	public Integer getCol() {
		return c;
	}

	public Integer min() {
		Integer ret = OO;
		for (int i = 0; i < r; ++i)
			for (int j = 0; j < c; ++j)
				ret = Math.min(ret, mat.get(i).get(j));
		return ret;
	}
	public Integer max() {
		Integer ret = -OO;
		for (int i = 0; i < r; ++i)
			for (int j = 0; j < c; ++j)
				ret = Math.max(ret, mat.get(i).get(j));
		return ret;
	}
}
