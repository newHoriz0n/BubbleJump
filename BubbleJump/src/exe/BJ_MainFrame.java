package exe;

import javax.swing.JFrame;

import game.BubbleJump;

public class BJ_MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BJ_MainFrame() {
		
		BubbleJump bj = new BubbleJump();
		BJ_View v = new BJ_View(bj);
		
		add(v);
		
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	public static void main(String [] args ) {
		BJ_MainFrame mf = new BJ_MainFrame();
		mf.requestFocus();
	}

}
