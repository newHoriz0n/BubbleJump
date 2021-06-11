package game;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Vektor2D;

public class Player {

	private static double size = 20; // durchmesser

	private Vektor2D pos;
	private Vektor2D speed;
	private Vektor2D acc;

	private double sprungkraft;
	
	public Player() {
		this.pos = new Vektor2D();
		this.speed = new Vektor2D();
		this.acc = new Vektor2D();
		
		loadEigenschaften();
		
	}

	private void loadEigenschaften() {
		this.sprungkraft = 500;
	}

	public void update(double dt) {
		Vektor2D frameSpeed = new Vektor2D(speed);
		frameSpeed.scale(dt);
		pos.add(frameSpeed);
		speed.scale(0.99);
	}
	
	public void setPosition(Vektor2D newPos) {
		this.pos.set(newPos.getX(), newPos.getY());
	}
	
	public Vektor2D getAcc() {
		return acc;
	}

	public Vektor2D getPos() {
		return pos;
	}

	public static double getRadius() {
		return size / 2.0;
	}

	public Vektor2D getSpeed() {
		return speed;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawOval((int) (pos.getX() - size / 2.0), (int) (pos.getY() - size / 2.0), (int) size, (int) size);
	}


	public void jump(Vektor2D dif, double jumpPower) {
		this.speed.add(dif.scale(sprungkraft * jumpPower));
	}




}
