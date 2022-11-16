package test;

import javax.swing.JFrame;

import view.ChooseStartEndNodeView;

public class TestFirstInputPanel {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ChooseStartEndNodeView firstView = new ChooseStartEndNodeView();
		window.add(firstView);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
