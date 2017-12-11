package com.kahn.crawler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kahn.crawler.Notice;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Grabber extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	static Grabber frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Grabber();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Grabber() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8ACB\u8F38\u5165\u7DB2\u5740:");
		lblNewLabel.setFont(new Font("標楷體", Font.PLAIN, 25));
		lblNewLabel.setBounds(140, 30, 150, 35);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		textPane.setBounds(15, 107, 400, 35);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("\u4E0B\u8F09");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = textPane.getText();
				if(url.length() != 0){
					Crawler_Ver1 crawler = new Crawler_Ver1(frame);
					crawler.getPic(url);
				} else {
					Notice notice = new Notice();
					notice.newwindow(frame);
				}
			}
		});
		btnNewButton.setFont(new Font("標楷體", Font.PLAIN, 22));
		btnNewButton.setBounds(50, 190, 130, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6E05\u9664\u7DB2\u5740");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("標楷體", Font.PLAIN, 22));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(250, 190, 130, 35);
		contentPane.add(btnNewButton_1);
		
	}
}
