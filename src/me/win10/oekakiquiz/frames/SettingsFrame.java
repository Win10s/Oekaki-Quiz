package me.win10.oekakiquiz.frames;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;

import me.win10.oekakiquiz.Main;
import me.win10.oekakiquiz.filter.IntegerDocumentFilter;

public class SettingsFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private int max = 100;
	private JTextField textField_2;

	public SettingsFrame() {
		setTitle(Main.title + " - Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
			}
		});

		JLabel lblNewLabel = new JLabel("効果音ボリューム");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 10, 158, 37);
		contentPane.add(lblNewLabel);

		JLabel lblBgm = new JLabel("BGMボリューム");
		lblBgm.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblBgm.setBounds(12, 57, 158, 37);
		contentPane.add(lblBgm);

		JSlider slider = new JSlider();
		slider.setValue((int)(Main.SEVolume * 100));
		slider.setMaximum(max);
		slider.setBounds(182, 57, 222, 37);
		contentPane.add(slider);
	    slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Main.BGMVolume = slider.getValue() * 0.01F;
				textField_1.setText(slider.getValue() + "");
				if(MainFrame.nowBGM != null) {
					MainFrame.nowBGM.setVolume(Main.BGMVolume * Main.MasterVolume);
				}
			}
	    });

		JSlider slider_1 = new JSlider();
		slider_1.setValue((int)(Main.SEVolume * 100));
		slider_1.setMaximum(max);
		slider_1.setBounds(182, 10, 222, 37);
		slider_1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Main.SEVolume = slider_1.getValue() * 0.01F;
				textField.setText(slider_1.getValue() + "");
				if(Main.sSeikaiSE != null) {
					Main.sSeikaiSE.setVolume(Main.SEVolume * Main.MasterVolume);
				}
			}
	    });
		contentPane.add(slider_1);

		JButton btnNewButton = new JButton("保存して閉じる");
		btnNewButton.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		btnNewButton.setBounds(314, 172, 158, 43);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.saveSettings();
			}
		});

		textField = new JTextField((int)(Main.SEVolume * 100) + "");
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBounds(416, 10, 56, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new IntegerDocumentFilter());

		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				int i = Integer.parseInt(textField.getText());
				if(i > 100) {
					i = 100;
				}else if (i < 0) {
					i = 0;
				}
				slider_1.setValue(i);
			}

			@Override public void keyTyped(KeyEvent e){}
			@Override public void keyPressed(KeyEvent e){}
		});

		textField_1 = new JTextField((int)(Main.BGMVolume * 100) + "");
		textField_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		textField_1.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_1.setColumns(10);
		((AbstractDocument) textField_1.getDocument()).setDocumentFilter(new IntegerDocumentFilter());
		textField_1.setBounds(416, 57, 56, 37);
		textField_1.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				int i = Integer.parseInt(textField_1.getText());
				if(i > 100) {
					i = 100;
				}else if (i < 0) {
					i = 0;
				}
				slider.setValue(i);
			}

			@Override public void keyTyped(KeyEvent e){}
			@Override public void keyPressed(KeyEvent e){}
		});
		contentPane.add(textField_1);

		JLabel label = new JLabel("マスターボリューム");
		label.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		label.setBounds(12, 104, 158, 37);
		contentPane.add(label);

		JSlider slider_2 = new JSlider();
		slider_2.setValue((int)(Main.MasterVolume * 100));
		slider_2.setMaximum(max);
		slider_2.setBounds(182, 104, 222, 37);
		slider_2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Main.MasterVolume = slider_2.getValue() * 0.01F;
				textField_2.setText(slider_2.getValue() + "");
				if(MainFrame.nowBGM != null) {
					MainFrame.nowBGM.setVolume(Main.BGMVolume * Main.MasterVolume);
				}
				if(Main.sSeikaiSE != null) {
					Main.sSeikaiSE.setVolume(Main.SEVolume * Main.MasterVolume);
				}
			}
	    });
		contentPane.add(slider_2);

		textField_2 = new JTextField((int)(Main.MasterVolume * 100) + "");
		textField_2.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_2.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		textField_2.setColumns(10);
		((AbstractDocument) textField_2.getDocument()).setDocumentFilter(new IntegerDocumentFilter());
		textField_2.setBounds(416, 104, 56, 37);
		textField_2.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				int i = Integer.parseInt(textField_2.getText());
				if(i > 100) {
					i = 100;
				}else if (i < 0) {
					i = 0;
				}
				slider_2.setValue(i);
			}

			@Override public void keyTyped(KeyEvent e){}
			@Override public void keyPressed(KeyEvent e){}
		});
		contentPane.add(textField_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox("自動チャット");
		chckbxNewCheckBox.setBounds(225, 180, 81, 31);
		chckbxNewCheckBox.setSelected(Main.autoChat);
		contentPane.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.autoChat = chckbxNewCheckBox.isSelected();
			}
		});
	}
}
