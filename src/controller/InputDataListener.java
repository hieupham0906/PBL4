package controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dijkstra.algo.Vert;
import view.InputDataView;

public class InputDataListener implements MouseMotionListener, MouseListener {

	private InputDataView idv;
	public Point mouse_location;
	
	/*nodes la mang luu tru cac dinh*/
	private ArrayList<Node> nodes; 
	
	

	/*radius la ban kinh cua node */
	private int radius = 40; 
	/*node_dragged la bien tham chieu toi node dang bi chon de di chuyen*/
	private Node node_dragged;
	/* node_dragged_offset luu tru khoang cach keo di*/
	private Point node_dragged_offset;
	
	//lines chua cac duong thang noi cac dinh
	private ArrayList<Line> lines;
	
	private Node node_start_line;
	
	private Node node_end_line;
	
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	
	public InputDataListener(InputDataView idv)
	{
		nodes = new ArrayList<Node>();
		lines = new ArrayList<Line>();
		this.idv = idv;
	}

	public List<Node> getNodes()
	{
		return nodes;
	}
	
	public Node getNodeDragged()
	{
		return this.node_dragged;
	}
	
	public Point getNodeDraggedOffset()
	{
		return this.node_dragged_offset;
	}
	public boolean nodes_contains_mouse_location()
	{
		if (nodes.size()>0)
		for (Node node : nodes)
			if (node.getNode().contains(mouse_location))
				return true;
		return false;
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if (idv.isAdd)
		{
			mouse_location = e.getPoint();
			System.out.println("mouse location: " + e.getX() + " " + e.getY());
			Ellipse2D new_node = new Ellipse2D.Float(e.getX() - (radius / 2), 
													e.getY() - (radius / 2) , 
													radius,
													radius);
			//tao hop thoai de nhap ten vao
			
			try {
				String name = JOptionPane.showInputDialog(idv,"Nhap ten Dinh");
				Vert name1 = new Vert(name);
				if (name_already_exist(name))
				{
					JOptionPane.showMessageDialog(idv,"TEN DINH DA TON TAI");
					return;
				}
				Node node = new Node(new_node,name, name1);
				if (!nodes_contains_mouse_location())
				{
					nodes.add(node);
					idv.paint_node(node.getNode(), Color.blue, node.getName());
					idv.repaint();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(idv,"Vui long dat ten cho Dinh");
			}
			
		}
		else if (idv.isDelete)
		{
			mouse_location = e.getPoint();
			if (nodes.size()>0)
			{
				for (Node node : nodes)
					if (node.getNode().contains(mouse_location))
					{
						remove_line_contains(node);
						nodes.remove(nodes.indexOf(node));
						idv.repaint();
						break;
					}	
			}		
		}

		else if (idv.isAddLine)
		{
			mouse_location = e.getPoint();
			if (nodes.size()>0)
			{
				for (Node node : nodes)
					if (node.getNode().contains(mouse_location))
					{
						Point p = new Point((int)node.getCenterX(), (int)node.getCenterY());
						if (node_start_line==null)
						{
							node_start_line = node;
						}
						else 
						{
							node_end_line = node;
							String value = JOptionPane.showInputDialog(idv,"Nhap khoang canh");
							try {
								int weight = Integer.parseInt(value);
								if (weight <= 0) throw new Exception();
								Line line = new Line(node_start_line, node_end_line, weight);
								if (!isLineExistInLines(line))
								{
									lines.add(line);
								}
								else
								{
									for (Line baseline : lines)
									{
										if (baseline.getEndNode() == line.getEndNode())
											if (baseline.getStartNode() == line.getStartNode())
												baseline.setWeight(line.getWeight());
									}
								}
								
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(idv,"Khoang cach phai la so nguyen duong");
							} 
							node_start_line = null;
							node_end_line = null;
							idv.repaint();
						}
						break;
					}	
			}		
		}
		
		
		
	}

	private boolean name_already_exist(String name) {
		for (Node node:nodes)
		{
			if (node.getName().equals(name.trim()))
				return true;
		}
		return false;
	}

	private boolean isLineExistInLines(Line line) {
		for (Line baseline : lines)
		{
			if (baseline.getEndNode() == line.getEndNode())
				if (baseline.getStartNode() == line.getStartNode())
					return true;
		}
		
		if (line.getStartNode() == line.getEndNode())
			return true;
		return false;
	}

	private void remove_line_contains(Node node) {
		if (lines.size()>0)
		{
			int index = 0;
			while (index < lines.size())
			{
				Line line = lines.get(index);
				if (line.getStartNode() == node || line.getEndNode() == node)
				{
					System.out.println(lines.size());
					lines.remove(lines.indexOf(line));
					continue;
				}
				index++;
			}
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		if (idv.isMove)
		{
			for (Node node : nodes) {
	            if (node.getNode().contains(e.getPoint())) {
	            	node_dragged = node;
	            	node_dragged_offset = new Point(node.getNode().getBounds().x - e.getX(), node.getNode().getBounds().y - e.getY());
	            	break;
	            }
			}
		}
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		if (idv.isMove)
		{
			this.node_dragged = null;
			this.node_dragged_offset = null;
			
		}
		idv.repaint();
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (idv.isMove)
		if (node_dragged != null && node_dragged_offset != null)
		{
			Point to = e.getPoint();
            to.x += node_dragged_offset.x;
            to.y += node_dragged_offset.y;

            Rectangle bounds = node_dragged.getNode().getBounds();
            bounds.setLocation(to);
            node_dragged.getNode().setFrame(bounds);
            modify_line();
            idv.repaint();
            try {
				Thread.sleep((long) 30);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void modify_line() {
		for (Line line:lines)
		{
			line.updatePoint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	public  ArrayList<Line> getLines() {
		return lines;
	}
}
