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
			for (int i = 0; i < width; ++i)
				pw.write(original.get(0, i) + " ");
			for (int i = 1; i < height; ++i)
				pw.write(original.get(i, 0));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
