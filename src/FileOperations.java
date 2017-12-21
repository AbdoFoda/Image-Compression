import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperations {

	public static void writeCompressed(Integer width, Integer height,
			Integer q_bits, Integer min, Integer max, Matrix original,
			Matrix diff, String comp_file) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(comp_file);
			pw.write(width + " " + height + " " + q_bits + " " + min + " "
					+ max + " ");
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					if (i > 0 && j > 0) {
						pw.write(diff.get(i - 1, j - 1) + " ");
					} else {
						pw.write(original.get(i, j) + " ");
					}
				}
			}
			pw.close();
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
				for (int j = 0; j < width; ++j) {
					q.set(i, j, sc.nextInt());
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
