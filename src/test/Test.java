package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.InputData;
import view.InputDataView;

public class Test {
	public static void main(String[] args) {
		//THIS IS TEST VIEW IN FISRT PROJECT
//		InputDataView inputDataView = new InputDataView();
//		inputDataView.startAppThread();
//		inputDataView.show();
		
		
		//THIS IS SECOND TEST
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		InputData inputData = new InputData();
		window.add(inputData);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		inputData.startAppThread();
	}
}
