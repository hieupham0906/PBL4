package view;

import java.awt.Dimension;

import javax.swing.JPanel;

public class ResultView extends JPanel{

	
	private int screenWidth = 1000;
	private int screenHeight = 500;
	
	public ResultView()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
}
