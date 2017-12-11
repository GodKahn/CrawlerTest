package com.kahn.crawler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Notice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void newwindow(Grabber frame1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notice frame = new Notice();
					frame.setLocationRelativeTo(frame1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Notice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8ACB\u8F38\u5165\u7DB2\u5740\uFF01");
		lblNewLabel.setFont(new Font("º–∑¢≈È", Font.BOLD, 30));
		lblNewLabel.setBounds(45, 29, 200, 50);
		contentPane.add(lblNewLabel);
	}

}
