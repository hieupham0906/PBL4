package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.InputDataController;
import controller.InputDataListener;
import controller.Line;
import controller.Node;
import dijkstra.algo.Edge;
import dijkstra.algo.PathFinder;
import dijkstra.algo.Vert;

public class InputDataView extends JFrame implements ActionListener, Runnable {
	
	private InputDataListener idc;
	
	Graphics g;
	Graphics2D g2D;
	
	JPanel jPanel1;
	JTextField jTextField;
	JButton jbtnAdd, jbtnMove, jbtnDelete, jbtnAddLine, jbtnResult;
	

	public boolean isAdd = true, isMove = false, isDelete = false, isAddLine = false;

	Thread appThread;
	
	public InputDataView() 
	{
		idc = new InputDataListener(this);
		
		jPanel1 = new JPanel(new GridLayout(10, 1));
		jbtnAdd = new JButton();
		jbtnAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(InputDataView.class.getResource("/image/Add.png"))));
		jbtnMove = new JButton();
		jbtnMove.setSize(10, 10);
		jbtnMove.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(InputDataView.class.getResource("/image/move.png"))));
		jbtnDelete = new JButton();
		jbtnDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(InputDataView.class.getResource("/image/delete.png"))));
		jbtnAddLine = new JButton("Line");
		jbtnResult = new JButton("Result");
		jPanel1.add(jbtnResult);
		jbtnResult.addActionListener(this);
		jPanel1.add(jbtnAdd);
		jbtnAdd.addActionListener(this);
		jPanel1.add(jbtnMove);
		jbtnMove.addActionListener(this);
		jPanel1.add(jbtnDelete);
		jbtnDelete.addActionListener(this);
		jPanel1.add(jbtnAddLine);
		jbtnAddLine.addActionListener(this);
		this.init();
	}

	public void startAppThread()
	{
		appThread = new Thread(this);
		appThread.start();
	}
	
	@Override
	public void run() {
		//UPDATE
//		idc.updateLines();
//		idc.updateNodes();
		//DRAW
		
	}
	public void init() {
		this.addMouseListener(idc);
		this.addMouseMotionListener(idc);

		this.add(jPanel1, BorderLayout.EAST);
		// this.add(jPanel2, BorderLayout.CENTER);

		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		g2D = (Graphics2D) g.create();
		g2D.setColor(Color.black);
		g2D.setColor(Color.blue);
		paint_lines(idc.getLines());
		paint_nodes(idc.getNodes(), idc.getNodeDragged(), idc.getNodeDraggedOffset());
		g2D.dispose();
	}

	public void paint_lines(ArrayList<Line> lines) {
		for (Line line : lines) {
			paint_line(line);
		}
	}

	public void paint_line(Line line) {
		g2D.setColor(Color.black);
		g2D.draw(line.GetLine2D());
		int fontOfNumber = 20;
		g2D.setFont(new Font("Arial", Font.BOLD, fontOfNumber));
		g2D.drawString(Double.toString(line.getWeight()), (line.getStartpoint().x + line.getEndpoint().x) / 2,
				(line.getStartpoint().y + line.getEndpoint().y) / 2);

	}

	public void paint_nodes(List<Node> nodes, Node node_dragged, Point node_dragged_offset) {
		g2D.setColor(Color.blue);
		for (Node node : nodes) {
			paint_node(node.getNode(), Color.blue, node.getName());
		}

		if (node_dragged != null) {
			g2D.setColor(Color.red);
			g2D.draw(node_dragged.getNode());
		}
	}

	public void paint_node(Ellipse2D node, Color color, String name) {
		g2D.setColor(color);
		g2D.fill(node);
		g2D.setColor(Color.BLACK);
		Rectangle r = node.getBounds();
		int fontOfNumber = 20;
		g2D.setFont(new Font("Arial", Font.BOLD, fontOfNumber));
		g2D.drawString(name, (int) r.getCenterX() - 5, (int) r.getCenterY() + 5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnAdd) {
			isAdd = true;
			isMove = false;
			isDelete = false;
			isAddLine = false;
			System.out.println("click add button");
		} else if (e.getSource() == jbtnMove) {
			isAdd = false;
			isMove = true;
			isDelete = false;
			isAddLine = false;
			System.out.println("click move button");
		} else if (e.getSource() == jbtnDelete) {
			isAdd = false;
			isMove = false;
			isDelete = true;
			isAddLine = false;
			System.out.println("click delete button");
		} else if (e.getSource() == jbtnAddLine) {
			isAdd = false;
			isMove = false;
			isDelete = false;
			isAddLine = true;
			System.out.println("click add line button");
		} else if (e.getSource() == jbtnResult) {
//			List<Node> nodes = new ArrayList<>();
//			List<Line> lines = new ArrayList<>();
//			nodes = InputDataListener.getNodes();
//			lines = InputDataListener.getLines();
//			for (Line line : lines) {
//				System.out.println(
//						line.getStartNode().getName1() + " " + line.getEndNode().getName1() + " " + line.getWeight());
//			}
//			List<Vert> verts = new ArrayList<>();
//			List<Edge> edges = new ArrayList<>();
//
//			for (Node node : nodes) {
//				verts.add(node.getName1());
//
//			}
//
//			for (Line line : lines) {
//			//	System.out.println("vert = " + vert.getName());
//				line.getStartNode().getName1().addNeighbour(
//						new Edge(line.getWeight(), line.getStartNode().getName1(), line.getEndNode().getName1()));
//				line.getEndNode().getName1().addNeighbour(
//						new Edge(line.getWeight(), line.getEndNode().getName1(), line.getStartNode().getName1()));
//
//			}
			List<Node> nodes = new ArrayList<>();
			List<Line> lines = new ArrayList<>();
			nodes = idc.getNodes();
			lines = idc.getLines();
			for (Line line : lines) {
				System.out.println(
						line.getStartNode().getName() + " " + line.getEndNode().getName() + " " + line.getWeight());
			}
			List<Vert> verts = new ArrayList<>();

			for (Node node : nodes) {
				//Vert a = verts.get(0);
				verts.add(node.getName1());
			}
			for (Vert vert : verts) {
				System.out.println("vert = " + vert.getName());
			}
			for (Line line : lines) {
				// System.out.println("vert = " + vert.getName());
				line.getStartNode().getName1().addNeighbour(
						new Edge(line.getWeight(), line.getStartNode().getName1(), line.getEndNode().getName1()));
				line.getEndNode().getName1().addNeighbour(
						new Edge(line.getWeight(), line.getEndNode().getName1(), line.getStartNode().getName1()));

			}

			PathFinder shortestPath = new PathFinder();
			shortestPath.ShortestP(verts.get(0));
			String VertStart = verts.get(0).toString();
			System.out.println("Start vert = " + VertStart);
			System.out.println("Khoảng cách ngắn nhất:");
			for (Vert vert : verts) {
				if(!vert.getName().equals(VertStart)) {
					System.out.println( VertStart  + "->" + vert.getName()+ ": " + vert.getDist());
				}
				

			}
			for (Vert vert : verts) {
				if(!vert.getName().equals(VertStart)) {
					System.out.println( VertStart  + "->" + vert.getName()+ ": " + shortestPath.getShortestP(vert));
				}

			}

		}

	}
}
