package exe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import game.BubbleJump;
import math.Vektor2D;

public class BJ_View extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BubbleJump bj;

	private double[] offset = new double[2];
	private double offsetTraegheit = 0.99;

	public BJ_View(BubbleJump bj) {
		this.bj = bj;

		setVisible(true);

		setDoubleBuffered(true);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				bj.jump(calcRealPosVonScreenPos(e.getX(), e.getY()));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				bj.startLoadJump();
//				bj.setPlayerPos(calcRealPosVonScreenPos(e.getX(), e.getY()));
				updateUI();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Timer t = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				updateUI();
			}
		};
		t.scheduleAtFixedRate(tt, 0, 10);

	}

	protected Vektor2D calcRealPosVonScreenPos(int x, int y) {
		return new Vektor2D(x - offset[0], y - offset[1]);
	}

	@Override
	protected void paintComponent(Graphics g) {

		// Hintergrund
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		calcOffset();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(offset[0], offset[1]);

		bj.draw((Graphics2D) g2d);

		g2d.translate(-offset[0], -offset[1]);

		g2d.setColor(Color.BLUE);
		g2d.fillRect(getWidth() - 50, (int) (getHeight() - (getHeight() * bj.getJumpPower())), 10,
				(int) (getHeight() * bj.getJumpPower()));
	}

	private void calcOffset() {

		if (offset != null) {
			Vektor2D pp = bj.getPlayerPosition();
			offset[0] = (offsetTraegheit * offset[0] + (1 - offsetTraegheit) * ((getWidth() / 2) - pp.getX()));
			offset[1] = (offsetTraegheit * offset[1] + (1 - offsetTraegheit) * ((getHeight() / 2) - pp.getY()));
		}

	}

}
