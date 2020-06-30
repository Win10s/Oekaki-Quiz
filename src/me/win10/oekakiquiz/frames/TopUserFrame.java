package me.win10.oekakiquiz.frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import me.win10.oekakiquiz.Main;

public class TopUserFrame extends JFrame {

	private JPanel contentPane;

	public static JLabel lblNewLabel = new JLabel("");

	public TopUserFrame() {
		setTitle(Main.title + " - Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		lblNewLabel.setBounds(12, 10, 1920, 1080);
		contentPane.add(lblNewLabel);
	}

}
