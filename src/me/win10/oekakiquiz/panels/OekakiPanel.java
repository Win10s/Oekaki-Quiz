package me.win10.oekakiquiz.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import me.win10.oekakiquiz.Main;
import me.win10.oekakiquiz.frames.MainFrame;

public class OekakiPanel extends JPanel  implements MouseListener, MouseMotionListener {

	private int lastx=0, lasty=0, newx, newy;

	public BufferedImage cImage = null;
	public Graphics2D g2d;

	public OekakiPanel() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setBackground(Color.WHITE);
		cImage = new BufferedImage(525, 490, BufferedImage.TYPE_INT_RGB);
		g2d = cImage.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 525, 490);
		repaint();
	}

	public void resetGrap() {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 525, 490);
		g2d.fillRect(0, 0, MainFrame.getCont().getWidth() - 188 - 12 * 2, MainFrame.getCont().getHeight() - 71);
		getGraphics().drawImage(cImage, 0, 0, null);
	}

	public void join() {
	    g2d.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 525, 490);
		g2d.setColor(Color.BLACK);
	    int i = 0;
        ArrayList<String> keys = new ArrayList<String>(Main.joins.keySet());
	    for(int i2 = keys.size() - 1; i2 >= 0; i2--){
		    g2d.drawString("+ " + keys.get(i2), 2, 20 + i * 20);
		    i++;
        }
		getGraphics().drawImage(cImage, 0, 0, null);
	}

	public void showSeikai(String sender, int points) {
		g2d.setColor(Color.RED);
		BasicStroke bs1 = new BasicStroke(12, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(bs1);
	    g2d.drawOval(getWidth() / 2 - ((getHeight() - 100) / 2), getHeight() / 2 - (getHeight() - 100) / 2 - 40, getHeight() - 100, getHeight() - 100);
	    g2d.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
	    g2d.drawString("正解！ " + MainFrame.nowOdai, getWidth() / 2 - g2d.getFontMetrics().stringWidth("正解！ " + MainFrame.nowOdai) / 2, getHeight() / 2 + (getHeight() - 100) / 2 + 2);
	    g2d.drawString(sender + "さんが" + points + "ポイント獲得しました！", getWidth() / 2 - g2d.getFontMetrics().stringWidth(sender + "さんが" + points + "ポイント獲得しました！") / 2, getHeight() / 2 + (getHeight() - 100) / 2 + 2 + 26);
		bs1 = new BasicStroke(MainFrame.nowSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(bs1);
		getGraphics().drawImage(cImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(cImage, 0, 0, null);
	}
	public void drawLine(int x1, int y1, int x2, int y2, int size){
		//Graphics g = this.getGraphics();
		//g.setColor(MainFrame.nowColor);
		//Graphics2D g2 = (Graphics2D)g;
		g2d.setColor(MainFrame.nowColor);
		BasicStroke bs1 = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(bs1);
		g2d.drawLine(x1, y1, x2, y2);
		getGraphics().drawImage(cImage, 0, 0, null);
	}

	public void showRanking() {
		HashMap<String, Integer> p2 = (HashMap<String, Integer>) Main.points.clone();
		String str = "";
		for(int i = 1; i <= p2.size() + 1; i++) {
			String mplayer = "";
			int mpoints = -1;
			for (String key : p2.keySet()) {
				if(mpoints < p2.get(key)) {
					mplayer = key;
					mpoints = p2.get(key);
				}
			}
			if(str.length() == 0) {
				str = "<html><body>" + i + "位. " + mplayer + ": " + mpoints + "ポイント";
			}else {
				str = str + "<br/>" + i + "位. " + mplayer + ": " + mpoints + "ポイント";
			}
			p2.remove(mplayer);
		}
		str = str + "</body></html>";
		Main.frame2.lblNewLabel.setText(str);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		newx = (int) e.getX();
		newy = (int) e.getY();
		drawLine(newx, newy, newx, newy, MainFrame.nowSize);
		lastx = newx;
		lasty = newy;
	}

	public void mouseDragged(MouseEvent e) {
		newx = (int) e.getX();
		newy = (int) e.getY();
		//drawLine(lastx,lasty,newx,newy);
		drawLine(lastx, lasty, newx, newy, MainFrame.nowSize);
		lastx = newx;
		lasty = newy;
	}

	@Override public void mouseMoved(MouseEvent arg0) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}

}
