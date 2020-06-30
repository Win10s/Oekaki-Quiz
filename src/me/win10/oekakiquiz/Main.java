package me.win10.oekakiquiz;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.UIManager;

import me.win10.oekakiquiz.bot.TwitchBot;
import me.win10.oekakiquiz.frames.MainFrame;
import me.win10.oekakiquiz.frames.TopUserFrame;
import me.win10.oekakiquiz.utils.PlaySound;

public class Main {

	public static MainFrame frame;
	public static TopUserFrame frame2;

	public static String title = "Oekaki Quiz";
	public static String ver = "1.0.2";

    private static String OAUTH;
    private static final String ADDRESS = "irc.twitch.tv.";
    private static final int PORT = 6667;
    public static String Nick;

	public static TwitchBot bot;

	public static HashMap<String, String> odais = new HashMap<String, String>();
	public static HashMap<String, String> odais2 = new HashMap<String, String>();
	public static HashMap<String, Boolean> joins = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> points = new HashMap<String, Integer>();
	public static HashMap<String, File> OdaiMap = new HashMap<String, File>();

	public static float SEVolume = 0.05F;
	public static float BGMVolume = 0.05F;
	public static float MasterVolume = 1.0F;

	public static boolean autoChat;

	public static PlaySound sSeikaiSE;
	public static HashMap<Integer, PlaySound> sBGMS = new HashMap<Integer, PlaySound>();

	public static void saveSettings() {
		try {
			File file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			file = new File(file.getParent() + File.separator + "Settings.txt");
			file.createNewFile();
		    if (file.exists()){
		        if (file.isFile() && file.canWrite()){
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.write(Math.round(Main.SEVolume * 100) + "");
					bw.newLine();
					bw.write(Math.round(Main.BGMVolume * 100) + "");
					bw.newLine();
					bw.write(Math.round(Main.MasterVolume * 100) + "");
					bw.newLine();
					bw.write(autoChat + "");
					bw.close();
		        }
		    }

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String fileName = "Settings.txt";
		File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		String inputFilePath = jarFile.getParent() + File.separator + fileName;
		File file = new File(inputFilePath);

		if (file.exists()) {
			FileReader fileReader = new FileReader(file);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String data;
			int i = 0;
	        while ((data = bufferedReader.readLine()) != null) {
	        	if(i == 0) {
	        		SEVolume = Integer.parseInt(data) / 100F;
	        	}else if(i == 1) {
	        		BGMVolume = Integer.parseInt(data) / 100F;
	        	}else if(i == 2) {
	        		MasterVolume = Integer.parseInt(data) / 100F;
	        	}else if(i == 3) {
	        		autoChat = Boolean.parseBoolean(data);
	        	}
	        	i++;
	        }
	        bufferedReader.close();
		}

		/*
		fileName = "Odai.txt";
		inputFilePath = jarFile.getParent() + File.separator + fileName;
		file = new File(inputFilePath);

		if (!file.exists()) {
			return;
		}

		FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String data;
		int i = 0;
        while ((data = bufferedReader.readLine()) != null) {
        	String[] split = data.split(":", 2);//おえかき:お絵描き
        	odais.put(split[0], split[1]);
        	odais2.put(split[1], split[0]);
        }

        bufferedReader.close();
		*/

		fileName = "access.txt";
		inputFilePath = jarFile.getParent() + File.separator + fileName;
		file = new File(inputFilePath);

		if (!file.exists()) {
			return;
		}

		FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String data;
		int i = 0;
        while ((data = bufferedReader.readLine()) != null) {
			if(i == 0) {
				OAUTH = data;
			}else if (i == 1) {
				Nick = data;
			}
			i++;
        }

        bufferedReader.close();

        fileName = "timer.txt";
		inputFilePath = jarFile.getParent() + File.separator + fileName;
		file = new File(inputFilePath);

		if (!file.exists()) {
			return;
		}

		fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
		i = 0;
        while ((data = bufferedReader.readLine()) != null) {
			if(i == 0) {
				MainFrame.max = Integer.parseInt(data);
			}
			i++;
        }

        bufferedReader.close();

        fileName = "seikai.wav";
        sSeikaiSE = new PlaySound(jarFile.getParent() + File.separator + "se" + File.separator + fileName);

        int i2 = 1;
        for(File bgm : new File(jarFile.getParent() + File.separator + "bgm").listFiles()) {
        	sBGMS.put(i2, new PlaySound(bgm));
        	i2++;
        }
        for(File odais : new File(jarFile.getParent() + File.separator + "odai").listFiles()) {
        	if(odais.getName().substring(odais.getName().length() - 4).equals(".txt")) {
        		OdaiMap.put(odais.getName(), odais);
        	}
        }

		bot = new TwitchBot(Nick);

		bot.setVerbose(false);

		bot.connect(ADDRESS, PORT, OAUTH);
		bot.joinChannel("#" + Nick);
		bot.getName();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					frame = new MainFrame();
					Rectangle screen = frame.getGraphicsConfiguration().getBounds();
					frame.setVisible(true);

					frame.setLocation(
					  screen.x + screen.width / 2 - frame.getSize().width / 2,
					  screen.y + screen.height / 2 - frame.getSize().height / 2);

					frame2 = new TopUserFrame();
					frame2.setLocation(
					  screen.x + screen.width / 2 - frame2.getSize().width / 2 - frame2.getSize().width / 2 - frame.getSize().width / 2,
					  screen.y + screen.height / 2 - frame.getSize().height / 2);
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
