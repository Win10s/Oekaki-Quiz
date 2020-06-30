package me.win10.oekakiquiz.bot;

import java.util.Timer;
import java.util.TimerTask;

import org.jibble.pircbot.PircBot;

import me.win10.oekakiquiz.Main;
import me.win10.oekakiquiz.frames.MainFrame;


public class TwitchBot extends PircBot {

    private final String requestedNick;

    public TwitchBot(String nick) {
        this.requestedNick = nick;

        setName(this.requestedNick);
        setLogin(this.requestedNick);
    }

    @Override
    protected void onDisconnect() {
    	super.onDisconnect();
    	System.exit(0);
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
    	if(MainFrame.start) {
    		if(Main.joins.containsKey(sender)){
    			if(message.equals(Main.odais2.get(MainFrame.nowOdai))) {
					MainFrame.panel.showSeikai(sender, (int)((MainFrame.max - MainFrame.count) / (MainFrame.max / MainFrame.maxpoint)));
					Main.sSeikaiSE.setVolume(Main.SEVolume * Main.MasterVolume);
					Main.sSeikaiSE.play();
					MainFrame.nowTimer.cancel();
					Timer nowTimer = new Timer();
				    nowTimer.schedule(new TimerTask() {
						@Override
						public void run() {
		    				MainFrame.end(MainFrame.max, sender);
							cancel();
						}

				    }, 2000);
    			}
    		}
    	}else if(MainFrame.startmae) {
    		if(message.equalsIgnoreCase("!draw") && !Main.joins.containsKey(sender)) {
    			Main.joins.put(sender, true);
    			MainFrame.panel.join();
    		}
    	}
    }
}