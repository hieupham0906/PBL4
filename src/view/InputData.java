package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Currency;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.InputDataClickHandler;
import controller.InputDataController;
import controller.Line;
import controller.Node;

public class InputData extends JPanel implements Runnable
{
	//CREAT BUTTON
	public JPanel clickPanel;
	public JButton btnAdd, btnMove, btnDelete, btnAddLine;
	public boolean addClicked, moveClicked, delClicked, lineClicked;
	
	//CREAT APP THREAD
	Thread inputDataThread;
	
	//CREAT INFO OF INPUTDATA PANEL
	int screenWidth = 500;
	int screenHeight = 500;
	
	int FPS = 30;
	
	InputDataClickHandler clickHandler;
	InputDataController inDataController;
	public InputData()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		//this.setBackground(Color.LIGHT_GRAY);
		
		
		clickHandler = new InputDataClickHandler(this, inDataController);
		inDataController = new InputDataController(this, clickHandler);
		clickHandler.setInputDataController(inDataController);
		
		this.addMouseListener(clickHandler);
		this.addMouseMotionListener(clickHandler);
		
		setClickPanel();
		setLayout(new BorderLayout());
		add(clickPanel, BorderLayout.EAST);
	}
	
	public void setClickPanel()
	{
		clickPanel = new JPanel(new GridLayout(10,1));
		
		btnAdd = new JButton();		btnAdd.addMouseListener(clickHandler);
		btnAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(InputDataView.class.getResource("/image/Add.png"))));
		
		btnMove = new JButton();	btnMove.addMouseListener(clickHandler);
		btnMove.setSize(10, 10);
		btnMove.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(InputDataView.class.getResource("/image/move.png"))));
		
		btnDelete = new JButton();	btnDelete.addMouseListener(clickHandler);
		btnDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(InputDataView.class.getResource("/image/delete.png"))));
		
		btnAddLine = new JButton("Line");	btnAddLine.addMouseListener(clickHandler);
		
		clickPanel.add(btnAdd);
		clickPanel.add(btnMove);
		clickPanel.add(btnDelete);
		clickPanel.add(btnAddLine);
	}
	
	public void startAppThread()
	{
		inputDataThread = new Thread(this);
		inputDataThread.start();
	}
	
	@Override
	public void run() 
	{
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (inputDataThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1)
			{
				//UPDATE: Update Information such as Node possition, lines possition,...
				update();
				//DRAW: draw again with the updated information
				repaint();
				delta--;
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		drawLines(g2d, inDataController.getLines());
		drawNodes(g2d, inDataController.getNodes());
		
		g2d.dispose();
	}

//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponents(g);
//		
//	}

	public void drawNodes(Graphics2D g2d, ArrayList<Node> nodes)
	{
		g2d.setColor(Color.blue);
		for (Node node : nodes) {
			paintNode(g2d, node.getNode(), Color.blue, node.getName());
		}
		
		if (clickHandler.nodeDragged != null) {
			g2d.setColor(Color.red);
			g2d.draw(clickHandler.nodeDragged.getNode());
		}
	}
	
	public void drawLines(Graphics2D g2d, ArrayList<Line> lines) 
	{
		for (Line line : lines) {
			paintLine(g2d,line);
		}
	};
	
	public void paintNode(Graphics2D g2d, Ellipse2D node, Color color, String name) 
	{
		g2d.setColor(color);
		g2d.fill(node);
		g2d.setColor(Color.BLACK);
		Rectangle r = node.getBounds();
		int fontOfNumber = 20;
		g2d.setFont(new Font("Arial", Font.BOLD, fontOfNumber));
		g2d.drawString(name, (int) r.getCenterX() - 5, (int) r.getCenterY() + 5);
	};
	
	public void paintLine(Graphics2D g2d, Line line)
	{
		g2d.setColor(Color.black);
		g2d.draw(line.GetLine2D());
		int fontOfNumber = 20;
		g2d.setFont(new Font("Arial", Font.BOLD, fontOfNumber));
		g2d.drawString(Double.toString(line.getWeight()), (line.getStartpoint().x + line.getEndpoint().x) / 2,
				(line.getStartpoint().y + line.getEndpoint().y) / 2);
	}
	public void update()
	{
		inDataController.update();
	}

	public void drawLineWhenMouseMove(Point p1, Point p2) {
		Graphics g = this.getGraphics();
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		g2d.dispose();
		g.dispose();
	}
}
