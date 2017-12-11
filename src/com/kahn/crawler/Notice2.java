package com.kahn.crawler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Notice2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void newwindow(Grabber frame1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notice2 frame = new Notice2();
					frame.setLocationRelativeTo(frame1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Notice2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7DB2\u5740\u932F\u8AA4!!!");
		lblNewLabel.setFont(new Font("º–∑¢≈È", Font.PLAIN, 23));
		lblNewLabel.setBounds(55, 30, 130, 40);
		contentPane.add(lblNewLabel);
	}
}
