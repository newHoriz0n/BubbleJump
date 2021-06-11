package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import math.Vektor2D;

public class BubbleJump {

	private List<Bubble> bubbles;

	private Player p;
	private boolean loadJump;
	private long jumpLoadTime;
	private long maxJumpLoadTime = 500; // [ms]

	private long lastUpdate;

	public BubbleJump() {

		loadBubbles();
		loadPlayer();

		Timer t = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				update();
			}
		};
		t.scheduleAtFixedRate(tt, 0, 10);

	}

	public void update() {
		long now = System.nanoTime();
		double dt = (double) (now - lastUpdate) / 1000000000.0;
		if (dt < 1) {
			for (Bubble b : bubbles) {
				b.update(dt);
			}
			p.update(dt);
		}
		lastUpdate = now;

	}

	private void loadPlayer() {
		this.p = new Player();
	}

	private void loadBubbles() {

		this.bubbles = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			// TODO: Generate Random Bubbles

			Bubble b = new Bubble(100, new Vektor2D(), new Vektor2D());
			bubbles.add(b);

		}
	}

	public Vektor2D getPlayerPosition() {
		return p.getPos();
	}

	public void draw(Graphics2D g) {

		for (Bubble b : bubbles) {
			b.draw(g);
		}

		p.draw(g);

	}

	public void setPlayerPos(Vektor2D v) {
		p.setPosition(v);
	}

	public void startLoadJump() {
		this.jumpLoadTime = System.currentTimeMillis();
		this.loadJump = true;
	}

	public double getJumpPower() {
		if (loadJump) {
			return Math.min(Math.pow((double) (System.currentTimeMillis() - jumpLoadTime) / (double) maxJumpLoadTime, 2), 1.0);
		} 
		return 0;
	}

	public void jump(Vektor2D ziel) {
		Vektor2D dif = ziel.subtract(p.getPos());
		dif.norm();
		p.jump(dif, getJumpPower());
		this.loadJump = false;
	}

}
