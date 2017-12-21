import java.util.ArrayList;

public class Decompression extends Predictive {
	Decompression(String comp_file) {
		FileOperations.readCompressed(comp_file);
		this.q_bits = FileOperations.q_bits;
		this.myMat = new Matrix(FileOperations.q);
		this.min = FileOperations.min;
		this.max = FileOperations.max;
	}

	public void deCompress(String out_img_path) {
		ArrayList<Pair<Integer, Integer>> ranges = quantize(min, max, q_bits);
		for (int i = 1; i < myMat.getRow(); ++i) {
			for (int j = 1; j < myMat.getCol(); ++j) {
				Integer diff = myMath.mid(ranges.get(myMat.get(i, j)));
				Integer predicted = predict(myMat.get(i - 1, j-1),
						myMat.get(i-1, j), myMat.get(i, j - 1));
				// diff = original -predicted
				Integer original = diff + predicted;
				myMat.set(i, j, original);
			}
		}
		ImageClass.writeImage(myMat, out_img_path);
	}
}
