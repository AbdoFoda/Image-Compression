public class GUI {
	public static void main(String[] args) {
		for (int i = 10; i < 12; ++i) {
			Compression p = new Compression("lena.jpg", i);
			p.compress("lena.pc");

			Decompression d = new Decompression("lena.pc");
			d.deCompress("lena1"+String.valueOf(i)+".jpg");
		}
		// for(int i=0;i<20;++i){
		// System.out.println(myMath.bin(i,4));
		// }
	}
}
