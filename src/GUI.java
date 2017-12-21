import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JPanel {
	public JFrame frame;
	JTextField fromFile;
	JTextField toDirectory;
	private JFileChooser fc;
	private JFileChooser dc;
	private JButton btnAddFile;
	private JButton btnSelectDirectory;
	private JButton compress;
	private JButton deCompress;
	private JTextField bits;

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.init_frame();
		gui.action();
	}

	public void init_frame() {
		frame = new JFrame("Predictive Coding Compression");
		frame.setVisible(true);
		frame.setBounds(200, 200, 777, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel header = new JLabel("Compression and deCompression Program :)");
		header.setBounds(10, 10, 500, 100);
		frame.getContentPane().add(header);

		JLabel lblChooseAFile = new JLabel("choose A file       :");
		lblChooseAFile.setBounds(10, 50, 200, 100);
		frame.getContentPane().add(lblChooseAFile);

		JLabel lblChooseADirectory = new JLabel("choose directory:");
		lblChooseADirectory.setBounds(10, 100, 200, 100);
		frame.getContentPane().add(lblChooseADirectory);

		fromFile = new JTextField();
		fromFile.setBounds(150, 90, 400, 19);
		frame.getContentPane().add(fromFile);

		toDirectory = new JTextField();
		toDirectory.setBounds(150, 140, 400, 19);
		frame.getContentPane().add(toDirectory);

		btnAddFile = new JButton("Add File");
		btnAddFile.setBounds(600, 90, 150, 25);
		frame.getContentPane().add(btnAddFile);

		btnSelectDirectory = new JButton("Select Directory");
		btnSelectDirectory.setBounds(600, 140, 150, 25);
		frame.getContentPane().add(btnSelectDirectory);

		compress = new JButton("Compress");
		compress.setBounds(600, 200, 150, 25);
		frame.getContentPane().add(compress);
		// compress.setEnabled(false);

		deCompress = new JButton("deCompress");
		deCompress.setBounds(430, 200, 150, 25);
		frame.getContentPane().add(deCompress);
		// deCompress.setEnabled(false);

		bits = new JTextField("number of bits");
		bits.setBounds(450, 50, 150, 25);
		frame.getContentPane().add(bits);

		fc = new JFileChooser();
		dc = new JFileChooser();

		frame.repaint();
	}

	private void err_msg() {
		JOptionPane.showMessageDialog(null,
				"An error has been occured with the file");
	}

	private void action() {
		compress.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (FileOperations.validFile(fromFile.getText())
						&& FileOperations.validDirectory(toDirectory.getText())) {
					String out_img = FileOperations.childFile(
							toDirectory.getText(), fromFile.getText(),
							Predictive.ext);
					FileOperations.newFile(out_img);
					Compression c = new Compression(fromFile.getText(), Integer
							.decode(bits.getText()));
					c.compress(out_img);
				} else {
					err_msg();
				}
			}
		});

		deCompress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (FileOperations.validFile(fromFile.getText())
						&& FileOperations.validDirectory(toDirectory.getText())) {
					String out_img = FileOperations.childFile(
							toDirectory.getText(), fromFile.getText(),
							ImageClass.ext);
					FileOperations.newFile(out_img);
					Decompression d = new Decompression(fromFile.getText());
					d.deCompress(out_img);
				} else {
					err_msg();
				}
			}
		});
		btnAddFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int ret = fc.showOpenDialog(GUI.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					java.io.File f = fc.getSelectedFile();
					fromFile.setText(f.getAbsolutePath());
					toDirectory.setText(f.getParentFile().getAbsolutePath());
				}
			}
		});

		btnSelectDirectory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int ret = dc.showOpenDialog(GUI.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					toDirectory.setText(dc.getCurrentDirectory()
							.getAbsolutePath());
				}

			}
		});
	}
}
