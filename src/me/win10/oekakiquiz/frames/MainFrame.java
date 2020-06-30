package me.win10.oekakiquiz.frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import me.win10.oekakiquiz.Main;
import me.win10.oekakiquiz.panels.OekakiPanel;
import me.win10.oekakiquiz.utils.PlaySound;

public class MainFrame extends JFrame implements MouseListener{
	public static OekakiPanel panel;

	public static Color nowColor = Color.BLACK;

	public static int nowSize;
	public static int nowGames;
	public static int nowGamesCount;
	public static int count;
	public static int max = 60;
	public static int maxpoint = 100;

	public static boolean start;
	public static boolean startmae;

	public static String nowOdai;

	public static JTextPane textPane = new JTextPane();
	public static JButton button = new JButton("開始");
	public static JLabel lblNewLabel_1 = new JLabel("お題");

	public static JTextField textField;

	public static Timer nowTimer;
	public static Timer bgmTimer;

	public static PlaySound nowBGM;

	public static Container contentPane;

	public static File nowOdaiFile;

	public static Container getCont() {
		return contentPane;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int panelWidth = MainFrame.getCont().getWidth() - 188 - 12 * 2;
		panel.setBounds(0, 71, panelWidth, MainFrame.getCont().getHeight() - 71);
		panel.cImage = new BufferedImage(panelWidth, MainFrame.getCont().getHeight() - 71, BufferedImage.TYPE_INT_RGB);
		panel.g2d = panel.cImage.createGraphics();
		panel.g2d.setColor(Color.WHITE);
		panel.g2d.fillRect(0, 0, panelWidth, MainFrame.getCont().getHeight() - 71);
		textField.setBounds(12 + panelWidth, MainFrame.getCont().getHeight() - 86 - 12, 188, 86);
		textPane.setBounds(12 + panelWidth, 132, 188, MainFrame.getCont().getHeight() - 86 - 12 * 2 - 132);
		lblNewLabel_1.setBounds(12 + panelWidth, 71, 188, 51);
	}

	public MainFrame() {
		bgmTimer = new Timer();
	    bgmTimer.schedule(new TimerTask() {
	    	private int i = 1;
	    	private boolean played = false;
	    	private long now = System.currentTimeMillis();

			@Override
			public void run() {
				if(!played) {
					if(Main.sBGMS.containsKey(i)) {
						nowBGM = Main.sBGMS.get(i);
						nowBGM.setVolume(Main.BGMVolume * Main.MasterVolume);
						nowBGM.play();
						now = System.currentTimeMillis();
						played = true;
					}else {
						i = 1;
						return;
					}
				}
				if(nowBGM != null) {
					if(System.currentTimeMillis() - now >= (long)(nowBGM.getSoundSeconds() * 1000) + 1000L) {
						played = false;
						i++;
					}
				}
			}

	    }, 5, 1000);

		this.setTitle(Main.title + " - Ver." + Main.ver);
		this.setSize(940, 600);
		getContentPane().setLayout(null);
		MainFrame.panel = new OekakiPanel();
		MainFrame.panel.setBounds(0, 71, 525, 490);
		this.getContentPane().add(MainFrame.panel);

		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		comboBox.setBounds(619, 10, 97, 51);
		getContentPane().add(comboBox);
		comboBox.addItem(2);
		comboBox.addItem(4);
		comboBox.addItem(8);
		comboBox.addItem(12);
		comboBox.addItem(16);
		comboBox.addItem(20);
		comboBox.addItem(24);
		comboBox.addItem(32);
		comboBox.addItem(42);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getItemAt(comboBox.getSelectedIndex());
				nowSize = i;
			}
		});

		JLabel lblNewLabel = new JLabel("サイズ:");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(537, 10, 70, 51);
		getContentPane().add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(12, 10, 20, 20);
		getContentPane().add(panel_1);
		panel_1.addMouseListener(this);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(12, 40, 20, 20);
		getContentPane().add(panel_2);
		panel_2.addMouseListener(this);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(44, 40, 20, 20);
		getContentPane().add(panel_3);
		panel_3.addMouseListener(this);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(76, 40, 20, 20);
		getContentPane().add(panel_4);
		panel_4.addMouseListener(this);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(108, 40, 20, 20);
		getContentPane().add(panel_5);
		panel_5.addMouseListener(this);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.BLUE);
		panel_6.setBounds(44, 10, 20, 20);
		getContentPane().add(panel_6);
		panel_6.addMouseListener(this);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.GREEN);
		panel_7.setBounds(76, 10, 20, 20);
		getContentPane().add(panel_7);
		panel_7.addMouseListener(this);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.YELLOW);
		panel_8.setBounds(108, 10, 20, 20);
		getContentPane().add(panel_8);
		panel_8.addMouseListener(this);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.MAGENTA);
		panel_9.setBounds(140, 10, 20, 20);
		getContentPane().add(panel_9);
		panel_9.addMouseListener(this);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.CYAN);
		panel_10.setBounds(172, 10, 20, 20);
		getContentPane().add(panel_10);
		panel_10.addMouseListener(this);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.PINK);
		panel_11.setBounds(204, 10, 20, 20);
		getContentPane().add(panel_11);
		panel_11.addMouseListener(this);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(140, 40, 20, 20);
		getContentPane().add(panel_14);
		panel_14.addMouseListener(this);

		JButton btnNewButton = new JButton("クリア");
		btnNewButton.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		btnNewButton.setBounds(236, 9, 146, 52);
		getContentPane().add(btnNewButton);

		button.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		button.setBounds(394, 9, 131, 52);
		getContentPane().add(button);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(726, 71, 188, 51);
		getContentPane().add(lblNewLabel_1);

		textPane.setBounds(726, 132, 188, 302);
		getContentPane().add(textPane);

		JLabel lblGemusuu = new JLabel("ゲーム数:");
		lblGemusuu.setHorizontalAlignment(SwingConstants.CENTER);
		lblGemusuu.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblGemusuu.setBounds(728, 10, 77, 51);
		getContentPane().add(lblGemusuu);

		JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		comboBox_1.setBounds(817, 10, 97, 51);
		getContentPane().add(comboBox_1);

		textField = new JTextField(max + "");
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 98));
		textField.setBounds(726, 444, 188, 86);
		getContentPane().add(textField);
		textField.setColumns(10);
		for(int i = 1; i <= 100; i++) {
			comboBox_1.addItem(i);
		}
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
				nowGames = i;
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.panel.resetGrap();
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nowOdaiFile == null) {
					JOptionPane.showMessageDialog(null, "お題が選択されていません！\nタブのお題から選択してください！", Main.title, JOptionPane.DEFAULT_OPTION);
					return;
				}
				if(start && startmae) {
					start = false;
					startmae = false;
					button.setText("開始");

    				nowOdai = null;
    				nowTimer.cancel();
    				MainFrame.panel.resetGrap();
    				textField.setText(max + "");
    				textPane.setText("");
    				setTitle(Main.title);
    				if(Main.autoChat)
    					Main.bot.sendMessage("#" + Main.Nick, "お絵描きクイズが強制終了されました！");
				}else if(startmae) {
					start = true;
					MainFrame.nowOdai = Main.odais.get(MainFrame.getRandom(Main.odais));
					nowGamesCount = 0;
					textPane.setText(nowOdai);
					timer();
					MainFrame.panel.resetGrap();
					button.setText("強制終了");
					Main.frame2.lblNewLabel.setText("");
    				setTitle(Main.title + " - Ver." + Main.ver + "(" + (MainFrame.nowGamesCount + 1) + "ゲーム目)");
    				if(Main.autoChat)
    					Main.bot.sendMessage("#" + Main.Nick, "お絵描きクイズが開始されました！");
				}else {
					startmae = true;
					MainFrame.panel.resetGrap();
					button.setText("締め切る");
					Main.frame2.lblNewLabel.setText("");
    				if(Main.autoChat)
    					Main.bot.sendMessage("#" + Main.Nick, "お絵描きクイズに参加したい人は!drawと打ちましょう！");
				}

			}
		});

		JMenuBar menuBar = new JMenuBar();

		JMenu quiz = new JMenu(Main.title);
		JMenu odais = new JMenu("お題");

		menuBar.add(quiz);
		menuBar.add(odais);

		JMenuItem settings = new JMenuItem("設定");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsFrame frame = new SettingsFrame();
				frame.setVisible(true);
			}
		});
		for (String key : Main.OdaiMap.keySet()) {
			JMenuItem odai = new JMenuItem(key);
			odai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						FileReader fileReader = new FileReader(Main.OdaiMap.get(key));
				        BufferedReader bufferedReader = new BufferedReader(fileReader);
				        String data;
						int i = 0;
						boolean pattern = false;
				        while ((data = bufferedReader.readLine()) != null) {
				        	if(i == 0) {
				        		if(data.equals("true")) {
				        			pattern = true;
				        		}else if(data.equals("false")) {
				        			pattern = false;
				        		}else {
									JOptionPane.showMessageDialog(null, "お題ファイルは最初にtrueもしくはfalseが必要です！", Main.title, JOptionPane.DEFAULT_OPTION);
							        bufferedReader.close();
				        			return;
				        		}
				        	}
				        	else {
				        		if(i == 1) {
						        	Main.odais.clear();
						        	Main.odais2.clear();
				        		}
				        		if(pattern) {
						        	String[] split = data.split(":", 2);//おえかき:お絵描き
						        	Main.odais.put(split[0], split[1]);
						        	Main.odais2.put(split[1], split[0]);
					        	}else {
						        	Main.odais.put(data, data);
						        	Main.odais2.put(data, data);
					        	}
				        	}
				        	i++;
				        }

				        bufferedReader.close();

						JOptionPane.showMessageDialog(null, "お題: " + key + "が選択されました！", Main.title, JOptionPane.DEFAULT_OPTION);
					} catch (FileNotFoundException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
					nowOdaiFile = Main.OdaiMap.get(key);

				}
			});
			odais.add(odai);
		}

		quiz.add(settings);

		setJMenuBar(menuBar);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = getContentPane();
	}

	public static String getRandom(HashMap<String, String> map) {
		List<String> keysAsArray = new ArrayList<>(map.keySet());
		Random rnd = new Random();
		String key = keysAsArray.get(rnd.nextInt(keysAsArray.size()));
		return key;
	}

	public static void timer() {
		int co = max;
		textField.setText(co + "");
		nowTimer = new Timer();
	    count = 0;
	    nowTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				count++;
				if(co - count < 0) {
					this.cancel();
					end(co);
					if(MainFrame.nowGamesCount >= MainFrame.nowGames) {
						return;
					}
    				timer();
					return;
				}
				MainFrame.textField.setText(co - count + "");
			}

	    }, 1000, 1000);
	}
	public static void end(int co) {
		MainFrame.nowGamesCount++;
		nowTimer.cancel();
		if(MainFrame.nowGamesCount >= MainFrame.nowGames) {
			ended(co);
			return;
		}

		timer();
		MainFrame.panel.resetGrap();

		MainFrame.nowOdai = Main.odais.get(MainFrame.getRandom(Main.odais));
		MainFrame.textPane.setText(MainFrame.nowOdai);
	}

	public static void end(int co, String sender) {
		MainFrame.nowGamesCount++;
		Main.frame.setTitle(Main.title + " - Ver." + Main.ver + "(" + (MainFrame.nowGamesCount + 1) + "ゲーム目)");
		int i = 0;
		nowTimer.cancel();
		if(Main.points.containsKey(sender)) {
			i = Main.points.get(sender);
		}
		Main.points.put(sender, i + (int)((co - count) / (co / maxpoint)));
		panel.showRanking();
		if(MainFrame.nowGamesCount >= MainFrame.nowGames) {
			ended(co);
			return;
		}
		timer();
		MainFrame.panel.resetGrap();

		MainFrame.nowOdai = Main.odais.get(MainFrame.getRandom(Main.odais));
		MainFrame.textPane.setText(MainFrame.nowOdai);
	}

	public static void ended(int co) {
		Main.joins.clear();
		MainFrame.start = false;
		MainFrame.startmae = false;
		MainFrame.button.setText("開始");

		MainFrame.nowOdai = null;
		MainFrame.textPane.setText("");
		MainFrame.textField.setText(co + "");

		MainFrame.panel.resetGrap();

		String mplayer = "";
		int mpoints = -1;
		for (String key : Main.points.keySet()) {
			if(mpoints < Main.points.get(key)) {
				mplayer = key;
				mpoints = Main.points.get(key);
			}
		}

		Main.frame.setTitle(Main.title + " - Ver." + Main.ver);

		Main.points.clear();
		if(mplayer.length() == 0) {
			Main.bot.sendMessage("#" + Main.Nick, "ゲームが終了しました！勝者はいません！");
			JOptionPane.showMessageDialog(null, "ゲームが終了しました！\n勝者はいません！", Main.title, JOptionPane.DEFAULT_OPTION);
		}else {
			Main.bot.sendMessage("#" + Main.Nick, "ゲームが終了しました！");
			Main.bot.sendMessage("#" + Main.Nick, "今回の優勝者は" + mplayer + "(" + mpoints + "ポイント)です！");
			JOptionPane.showMessageDialog(null, "ゲームが終了しました！\n" + mplayer + "が" + mpoints + "ポイントで優勝しました！", Main.title, JOptionPane.DEFAULT_OPTION);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();

		nowColor = panel.getBackground();
	}


	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
