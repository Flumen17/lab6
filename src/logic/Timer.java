package logic;

import gui.TimerGUI;
import javafx.application.Platform;
import main.Main;

public class Timer{
	
	
	public static final int SLEEP_TIME = 1000;
	public static final int COUNT_UP = 1;
	public static final int COUNT_DOWN = -1; 
			
	private TimerGUI timerGUI;
	private int hour, minute, second, countMode;
	private boolean isCounting;
	private Thread thread;
	
	
	public Timer(String name, int countMode) {
		timerGUI = new TimerGUI(name);
		this.countMode = countMode;
		resetHandle();
		setEventHandling();
		threadInitialize();
	}
	
	
	private void threadInitialize() {
		thread = new Thread(new Runnable() {
			public void run() {
				try {
					while(isCounting) {
						if(Timer.this.countMode == COUNT_UP) {
							Main.timer.getThread().join();
						}
						updateTime();
						updateGUI();
						Thread.sleep(SLEEP_TIME);
					}
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	private void updateGUI() {
		Platform.runLater(()->{
			timerGUI.getDisplayPart().update(getTimeString());
		});
	}
	
	private void updateTime() {
		if(countMode == COUNT_UP) {
			second++;
			if(second == 60) {
				second = 0;
				minute++;
				if(minute == 60) {
					minute = 0;
					hour++;
					if(hour == 24) {
						hour = 0;
					}
				}
			}
		}
		else {
			second--;
			if(second == 0) {
				resetHandle();
			}
		}
		
	}
	
	
	private String getTimeString() {
		String hour, minute, second;
		hour = Integer.toString(this.hour);
		minute = Integer.toString(this.minute);
		second = Integer.toString(this.second);
		if(this.hour < 10) {
			hour = "0" + hour;
		}
		if(this.minute < 10) {
			minute = "0" + minute;
		}
		if(this.second < 10) {
			second = "0" + second;
		}
		return String.format("%s:%s:%s", hour, minute, second);
	}
	
	private void resetHandle() {
		isCounting = false;
		hour = 0;
		minute = 0;
		second = 0;
		if(countMode == COUNT_DOWN){
			second = 30;
		}
		updateGUI();
	}
	
	private void startStopHandle() {
		if(isCounting) {
			isCounting = false;
		}
		else {
			isCounting = true;
			threadInitialize();
			thread.start();
		}
	}
	
	
	private void setEventHandling() {
		timerGUI.getControlPart().getStartStopButton().setOnAction(e->{
			startStopHandle();
		});
		timerGUI.getControlPart().getResetButton().setOnAction(e->{
			resetHandle();
		});
		
	}


	public TimerGUI getTimerGUI() {
		return timerGUI;
	}


	public Thread getThread() {
		return thread;
	}
	
	
		
}
