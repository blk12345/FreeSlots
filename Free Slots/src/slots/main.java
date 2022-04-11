package slots;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;

public class main extends JFrame {

	
	private JPanel contentPane;
	private int y =100;
	private int x = 100;
	private int index = 0;
	private int index2 = 0;
	private int index3 = 0;
	private int time =0;
	private static File file = new File("balance");
	private ImageIcon icon = new ImageIcon("image/"+list[index]);

	private Image image = icon.getImage();
	private Image image2 = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	private boolean uppressed =false;
	private int bal = 0;
	private String name = random(list, index);
	private String name2 = random(list, index2);
	private String name3 = random(list, index3);
	
	private ArrayList <String> imgs = new ArrayList <String>();
	public static File folder = new File("image");
	
	 public static String[] list = folder.list();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
					ImageIcon icon = new ImageIcon("cherry.jpg");
					frame.setIconImage(icon.getImage());
					frame.setTitle("Virtual Slot Machine!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
		        bal = Integer.parseInt(myReader.nextLine());
		        System.out.println(bal);
		      }
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		UpAction space = new UpAction();
		UpReleased nonspace = new UpReleased();
	
		contentPane.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "space");
		contentPane.getActionMap().put("space", space);
		contentPane.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "nonspace");
		contentPane.getActionMap().put("nonspace", nonspace);
		
		image = icon.getImage();
		image2 = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image2);
		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		contentPane.add(label);
		JLabel label2 = new JLabel(icon);
		label2.setBounds(50, 0, icon.getIconWidth(), icon.getIconHeight());
		contentPane.add(label2);
		
		JLabel label3 = new JLabel(icon);
		label3.setBounds(50, 0, icon.getIconWidth(), icon.getIconHeight());
		contentPane.add(label3);
		
		JLabel info = new JLabel("Press Spacebar");
		info.setFont(new Font("Tahoma", Font.PLAIN, 20));
		info.setBounds(107, 171, 303, 55);
		contentPane.add(info);
		
		JLabel balance = new JLabel(Integer.toString(bal));
		balance.setForeground(new Color(0, 100, 0));
		balance.setBounds(353, 10, 73, 13);
		contentPane.add(balance);
		contentPane.repaint();
		
		
		
		System.out.println(list[1]);
		
		
		Timer timer = new Timer(1, new ActionListener() {
	        public void actionPerformed(ActionEvent a) {
	        	if(uppressed==true) {
	        		time=0;
	        		
	        	}
	        	if(label.getY()<-50) {
	        		
	        		y=50;
	        		 name = random(list, index);
	        		 name2 = random(list, index2);
	        		 name3 = random(list, index3);
	        		ImageIcon icon = new ImageIcon("image/"+name);
	        		ImageIcon icon2 = new ImageIcon("image/"+name2);
	        		ImageIcon icon3 = new ImageIcon("image/"+name3);
	        		label.setIcon(scaleIcon(icon,50,100));
	        		label2.setIcon(scaleIcon(icon2,50,100));
	        		label3.setIcon(scaleIcon(icon3,50,100));
	        		//System.out.println(index);
	        	}
	        	if(time<200) {
	        		y-=5;
	        	}else if(time<400){
	        		y-=3;
	        	}else if(time<1000){
	        		y-=1;
	        	}else {
	        		y=100;
	        		
	        		if((name.equals(name2))&&(name2.equals(name3))) {
	        		
	        			info.setForeground(Color.GREEN);
	        			info.setText("Winner!");
	        			if(time<1001) {
	        			bal+=(1000*findIndex(list,name3));
	        			try {
	  				      FileWriter myWriter = new FileWriter("balance");
	  				      myWriter.write(Integer.toString(bal));
	  				      myWriter.close();
	  				}catch(Exception e1) {
	  					System.out.println(e1);
	  				}
	        			}
	        			balance.setText(Integer.toString(bal));
	        			balance.repaint();
	        		}else {
	        			info.setForeground(Color.RED);
	        			info.setText("Better Luck Next Time!");
	        		
	        		}
	        	}
	        	
	        	label.setLocation(x,y);
	        	label2.setLocation(x+50,y);
	        	label3.setLocation(x+100,y);
	        	time++;
	        	//clock.setText(Integer.toString(time));
	        	
	        }
		});timer.start();
		
	}
	private static ImageIcon scaleIcon(ImageIcon icon, int x, int y) {
		
		Image image = icon.getImage();
		Image image2=image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image2);
		return icon;
	}
	public static String random(String[] array, int index) {
		String a = "";
		double rand = Math.random();
		for(int i=0;i<array.length;i++) {
			if(rand<(.99/i)) {
				 a =array[i];
				 
			}
		}
		index = findIndex(array, a);
		return a;
		
	}
	public static int findIndex(String[] array, String s) {
		int index =0;
		for (int i=0;i<array.length;i++) {
			if(s.equals(array[i])) {
				index = i;
			}
		}
		return index;
	}

	
	class UpAction extends AbstractAction{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			uppressed=true;
			//leftpressed=false;
		}
		
	}
	class UpReleased extends AbstractAction{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			uppressed=false;
			//leftpressed=false;
		}

		}
}
