import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
			e.printStackTrace();
		}

	}

	public static boolean validFile(String filePath) {
		File f = new File(filePath);
		return f.exists();
	}

	public static boolean validDirectory(String dirPath) {
		File f = new File(dirPath);
		return f.isDirectory();
	}

	public static void newFile(String filePath) {
		File f = new File(filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String fileExtension(String filename) {
		int dotIndex = filename.lastIndexOf('.');
		return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
	}

	public static String childFile(String dir, String filePath, String newExt) {
		String output = dir + "/";
		int start = filePath.lastIndexOf('/') + 1, end = filePath
				.lastIndexOf('.');
		output += filePath.substring(start, end) +"1."+ newExt;
		return output;
	}
}