package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas{

	public MyCanvas()
	{
		init();
	}

	private void init() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(getPreferredSize());
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.fillOval(100, 75, 100, 100);
	}
	
	public void paint_something()
	{
		
	}
	
	
}
