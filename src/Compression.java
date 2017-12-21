import java.util.ArrayList;

public class Compression extends Predictive {
	Compression(String img_path, Integer q_bits) {
		this.q_bits = q_bits;
		this.myMat = ImageClass.readImage(img_path);
	}

	public void compress(String comp_file) {
		Matrix diff = new Matrix(myMat.getRow() - 1, myMat.getCol() - 1);
		// different matrix:
		for (int i = 1; i < myMat.getRow(); ++i) {
			for (int j = 1; j < myMat.getCol(); ++j) {
				Integer original = myMat.get(i, j);
				Integer predicted = predict(myMat.get(i - 1, j - 1),
						myMat.get(i - 1, j), myMat.get(i, j - 1));
				diff.set(i - 1, j - 1, original - predicted);
			}
		}
		// we make quantization on the difference here (quantized difference):
		min = diff.min();
		max = diff.max();
		ArrayList<Pair<Integer, Integer>> ranges = quantize(min, max, q_bits);
		for (int i = 0; i < diff.getRow(); ++i) {
			for (int j = 0; j < diff.getCol(); ++j) {
				diff.set(i, j, myMath.match(ranges, diff.get(i, j)));
			}
		}
		// write on the file
		FileOperations.writeCompressed(myMat.getCol(), myMat.getRow(), q_bits,
				min, max, myMat, diff, comp_file);
	}
}
