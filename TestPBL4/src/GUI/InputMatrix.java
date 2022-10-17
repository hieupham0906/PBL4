package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InputMatrix extends JPanel{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
        frame.add(new InputMatrix());
        frame.setSize(500, 300);
        frame.setVisible(true);
        

	}
	
	private Point clickedPoint;
	private ArrayList<Ellipse2D> nodes = new ArrayList<Ellipse2D>();
	private Ellipse2D dragged; //điểm bị kéo đi
	private int radius = 30;
	Graphics g = getGraphics();
	
	
	public InputMatrix()
	{
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseClicked (MouseEvent e)
			{
				clickedPoint = e.getLocationOnScreen();
				for (Ellipse2D node:nodes)
				{
					if (node.contains(clickedPoint))
						return;
				}
				Ellipse2D node = new Ellipse2D.Float(clickedPoint.x - (radius / 2), clickedPoint.y - (radius / 2), radius, radius);
				dragged = new Ellipse2D.Float(clickedPoint.x - (radius / 2), clickedPoint.y - (radius / 2), radius, radius);
				nodes.add(node);
				g.drawOval(clickedPoint.x - (radius / 2), clickedPoint.y - (radius / 2), radius, radius);
				
			}
			
			
		});
	}

	
}
