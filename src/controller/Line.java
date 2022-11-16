package controller;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Line {
	private Point startpoint;
	private Point endpoint;
	private Node startNode;
	private Node endNode;
	private double weight;
	
	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	public Point getStartpoint() {
		return startpoint;
	}
	
	public void setStartpoint(Point startpoint) {
		this.startpoint = startpoint;
	}
	
	public Point getEndpoint() {
		return endpoint;
	}
	
	public void setEndpoint(Point endpoint) {
		this.endpoint = endpoint;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Line(Node startNode, Node endNode, double weight) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
		this.startpoint = new Point((int)this.startNode.getCenterX(),(int)this.startNode.getCenterY());
		this.endpoint = new Point((int)this.endNode.getCenterX(),(int)this.endNode.getCenterY());
	}
	
	public Line() {
		// TODO Auto-generated constructor stub
	}


	public Line2D GetLine2D()
	{
		updatePoint();
		return new Line2D.Float(this.startpoint, this.endpoint);
	}

	public void updatePoint() {
		this.startpoint = new Point((int)this.startNode.getCenterX(),(int)this.startNode.getCenterY());
		this.endpoint = new Point((int)this.endNode.getCenterX(),(int)this.endNode.getCenterY());
	}
}
