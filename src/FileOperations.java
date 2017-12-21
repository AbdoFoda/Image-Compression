import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileOperations {
	static Matrix in;

	public static void writeCompressed(Integer width, Integer height,
			Integer q_bits, Integer min, Integer max, Matrix original,
			Matrix diff, String comp_file) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(comp_file);
			pw.write(width + " " + height + " " + q_bits + " " + min + " "
					+ max + " ");
			in = new Matrix(height, width);
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					if (i > 0 && j > 0) {
						pw.write(diff.get(i - 1, j - 1) + " ");
						in.set(i, j, diff.get(i - 1, j - 1));
					} else {
						pw.write(original.get(i, j) + " ");
						in.set(i, j, original.get(i, j));
					}
				}
			}
			pw.close();

			// for (int i = 0; i < width; ++i) {
			// pw.write(original.get(0, i) + " ");
			// // in.set(0, i, original.get(0, i));
			// }
			// for (int i = 1; i < height; ++i) {
			// pw.write(original.get(i, 0) + " ");
			// // in.set(i, 0, original.get(i, 0));
			// }
			// ArrayList<Integer> diff_in_row = new ArrayList<Integer>();
			// for (int i = 0; i < diff.getRow(); ++i) {
			// for (int j = 0; j < diff.getCol(); ++j) {
			// diff_in_row.add(diff.get(i, j));
			// // in.set(i+1,j+1,diff.get(i,j));
			// }
			// }
			// // System.out.println(diff_in_row.size());
			// ArrayList<Integer> binary = myMath.bin(diff_in_row, q_bits);
			// // System.out.println(binary.size());
			// for (int i = 0; i < binary.size(); ++i)
			// pw.write(binary.get(i) + " ");
			// pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Integer q_bits, min, max;
	public static Matrix q;

	public static void readCompressed(String comp_file) {
		try {
			Scanner sc = new Scanner(new File(comp_file));
			Integer width = sc.nextInt();
			Integer height = sc.nextInt();
			q_bits = sc.nextInt();
			min = sc.nextInt();
			max = sc.nextInt();
			q = new Matrix(height, width);
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j){
					q.set(i, j, sc.nextInt());
					if(q.get(i, j)!=in.get(i, j)){
						System.out.println(q.get(i,j)+" "+in.get(i,j));
					}
				}
			}
			// for (int i = 0; i < width; ++i)
			// q.set(0, i, sc.nextInt());
			// for (int i = 1; i < height; ++i)
			// q.set(i, 0, sc.nextInt());
			// Integer n = (width - 1) * (height - 1);
			// ArrayList<Integer> binary = new ArrayList<Integer>(
			// Arrays.asList(new Integer[myMath.ceil(n * q_bits,
			// myMath.int_size)]));
			// // System.out.println(binary.size());
			// for (int i = 0; i < binary.size(); ++i) {
			// binary.set(i, sc.nextInt());
			// }
			// sc.close(); // EOF
			// ArrayList<Integer> diff_in_row = myMath.decimal(binary, n,
			// q_bits);
			// // System.out.println(diff_in_row.size());
			// for (int i = 0; i < diff_in_row.size(); ++i) {
			// q.set(i / (width - 1) + 1, i % (width - 1) + 1,
			// diff_in_row.get(i));
			// }
			// Integer cnt = 0;
			// for (int i = 0; i < height; ++i) {
			// for (int j = 0; j < width; ++j) {
			// if (in.get(i, j) != q.get(i, j)) {
			// // System.out.println(i+" "+j+" "+in.get(i,j)+" "+q.get(i,j));
			// cnt++;
			// }
			// }
			// }
			// System.out.println(cnt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
