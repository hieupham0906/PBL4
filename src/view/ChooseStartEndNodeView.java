package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


//this is First Panel in ours Project
public class ChooseStartEndNodeView extends JLayeredPane {

	public JPanel panel;
	private int screenWidth = 500;
	private int screenHeight = 300;
	
	private JLabel lbStart, lbEnd;
	private JTextField txtStart, txtEnd;
	private JButton btnSubmit;
	
	public ChooseStartEndNodeView()
	{
		//setup border in firstPanel on ours Project
		setupMainBorder();
	}


	private void setupMainBorder() {
		Border tempBorder = BorderFactory.createLineBorder(Color.black);
		Border mainBorder = BorderFactory.createTitledBorder(tempBorder, "Chọn trạm trên mạng");
		this.setBorder(mainBorder);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		lbStart = new JLabel("Nguồn");
		lbStart.setFont(new Font("Serif", Font.PLAIN, 40));
		lbStart.setBounds(80, 50, 170, 50);
		
		lbEnd = new JLabel("Đích");
		lbEnd.setFont(new Font("Serif", Font.PLAIN, 40));
		lbEnd.setBounds(325, 50, 170, 50);
		
		txtStart = new JTextField();
		txtStart.setBounds(110, 120, 50, 50);
		txtStart.setFont(new Font("Serif", Font.PLAIN, 40));
		txtStart.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtEnd = new JTextField();
		txtEnd.setBounds(340, 120, 50, 50);
		txtEnd.setFont(new Font("Serif", Font.PLAIN, 40));
		txtEnd.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSubmit = new JButton("chọn");
		btnSubmit.setBounds(200,230,100,50);
		btnSubmit.setFont(new Font("Serif", Font.PLAIN, 30));
		
		this.add(lbStart);
		this.add(lbEnd);
		this.add(txtStart);
		this.add(txtEnd);
		this.add(btnSubmit);

	}
	
}
