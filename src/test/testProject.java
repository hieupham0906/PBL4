package test;

import java.awt.GridLayout;
import java.awt.SecondaryLoop;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.ChooseStartEndNodeView;
import view.InputData;
import view.ResultView;

public class testProject {

	
	
	public static void main(String[] args) {
		JFrame window;
		JPanel topHaftWindow;
		JPanel bottomHaftWindow;
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout(2,1));
		
		topHaftWindow = new JPanel();
		topHaftWindow.setLayout(new GridLayout(1,2));
		ChooseStartEndNodeView firstView = new ChooseStartEndNodeView();
		InputData secondView = new InputData();
		secondView.startAppThread();
		topHaftWindow.add(firstView);
		topHaftWindow.add(secondView);
		
		bottomHaftWindow = new JPanel();
		ResultView lastView = new ResultView();
		bottomHaftWindow.add(lastView);
		
		window.add(topHaftWindow);
		window.add(bottomHaftWindow);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
