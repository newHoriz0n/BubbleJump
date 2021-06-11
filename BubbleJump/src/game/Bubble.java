package game;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Vektor2D;

public class Bubble {

	private double rad;
	private Vektor2D pos; 
	private Vektor2D speed; // [pixel / s]

	private double mass;
	
	public Bubble(double rad, Vektor2D pos, Vektor2D speed) {
		super();
		this.rad = rad;
		this.pos = pos;
		this.speed = speed;

		this.mass = Math.PI * rad * rad;

	}
	
	/**
	 * 
	 * @param dt [s]
	 */
	public void update(double dt) {
				
		Vektor2D frameSpeed = new Vektor2D(speed);
		frameSpeed.scale(dt);
		
		pos.add(frameSpeed);
		
	}
	
	

	public void draw(Graphics2D g) {

		// TODO: Colors;

		
		g.setColor(Color.BLACK);
		g.drawOval((int) (pos.getX() - rad), (int) (pos.getY() - rad), (int) (2 * rad), (int) (2 * rad));
	}

	public double getMass() {
		return mass;
	}

	public Vektor2D getPos() {
		return pos;
	}

	public double getRad() {
		return rad;
	}

	public Vektor2D getSpeed() {
		return speed;
	}

}
