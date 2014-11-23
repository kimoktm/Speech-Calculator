package gui;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;

import speech.calculator.SpeechCalculator;

public class Window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 312, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JTextArea txtrResult = new JTextArea();
		txtrResult.setForeground(Color.WHITE);
		txtrResult.setBackground(Color.BLACK);
		txtrResult.setEnabled(false);
		txtrResult.setEditable(false);
		txtrResult.setBounds(12, 17, 288, 15);
		frame.getContentPane().add(txtrResult);

		final JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(12, 245, 288, 15);
		frame.getContentPane().add(textArea);

		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "1");
			}
		});
		button.setBackground(Color.WHITE);
		button.setBounds(12, 44, 61, 25);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "2");
			}
		});
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(85, 44, 61, 25);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("3");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "3");
			}
		});
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(158, 44, 61, 25);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("4");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "4");
			}
		});
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(12, 81, 61, 25);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("5");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "5");
			}
		});
		button_4.setBackground(Color.WHITE);
		button_4.setBounds(85, 81, 61, 25);
		frame.getContentPane().add(button_4);

		JButton button_5 = new JButton("6");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "6");
			}
		});
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(158, 81, 61, 25);
		frame.getContentPane().add(button_5);

		JButton button_6 = new JButton("7");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "7");
			}
		});
		button_6.setBackground(Color.WHITE);
		button_6.setBounds(12, 118, 61, 25);
		frame.getContentPane().add(button_6);

		JButton button_7 = new JButton("8");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "8");
			}
		});
		button_7.setBackground(Color.WHITE);
		button_7.setBounds(85, 118, 61, 25);
		frame.getContentPane().add(button_7);

		JButton button_8 = new JButton("9");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "9");
			}
		});
		button_8.setBackground(Color.WHITE);
		button_8.setBounds(158, 118, 61, 25);
		frame.getContentPane().add(button_8);

		JButton btnE = new JButton("e");
		btnE.setBackground(Color.WHITE);
		btnE.setBounds(12, 155, 61, 25);
		frame.getContentPane().add(btnE);

		JButton button_10 = new JButton("0");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "0");
			}
		});
		button_10.setBackground(Color.WHITE);
		button_10.setBounds(85, 155, 61, 25);
		frame.getContentPane().add(button_10);

		JButton btnLog = new JButton("log");
		btnLog.setBackground(Color.WHITE);
		btnLog.setBounds(158, 155, 61, 25);
		frame.getContentPane().add(btnLog);

		JButton button_9 = new JButton("+");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "+");
			}
		});
		button_9.setBackground(Color.WHITE);
		button_9.setBounds(231, 44, 61, 25);
		frame.getContentPane().add(button_9);

		JButton button_11 = new JButton("-");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "-");
			}
		});
		button_11.setBackground(Color.WHITE);
		button_11.setBounds(231, 81, 61, 25);
		frame.getContentPane().add(button_11);

		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "*");
			}
		});
		btnX.setBackground(Color.WHITE);
		btnX.setBounds(231, 118, 61, 25);
		frame.getContentPane().add(btnX);

		JButton button_13 = new JButton("/");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + "/");
			}
		});
		button_13.setBackground(Color.WHITE);
		button_13.setBounds(231, 155, 61, 25);
		frame.getContentPane().add(button_13);

		JButton btnRecord = new JButton("record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpeechCalculator.main(new String[0]);
			}
		});
		btnRecord.setBackground(Color.WHITE);
		btnRecord.setBounds(12, 193, 80, 25);
		frame.getContentPane().add(btnRecord);

		JButton btnEnter = new JButton("=");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = SpeechCalculator.evaluateText(txtrResult
						.getText());
				if (result.equals("Syntax Error"))
					textArea.setText(result);
				else
					txtrResult.setText(result);
			}
		});
		btnEnter.setBackground(Color.WHITE);
		btnEnter.setBounds(231, 192, 61, 25);
		frame.getContentPane().add(btnEnter);

		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int endIndex = txtrResult.getText().length() - 1;
				if (endIndex < 0)
					endIndex = 0;
				txtrResult.setText(txtrResult.getText().substring(0, endIndex));
			}
		});
		btnC.setBackground(Color.WHITE);
		btnC.setBounds(158, 192, 61, 25);
		frame.getContentPane().add(btnC);

		JButton button_12 = new JButton(".");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrResult.setText(txtrResult.getText() + ".");
			}
		});
		button_12.setBackground(Color.WHITE);
		button_12.setBounds(104, 192, 42, 25);
		frame.getContentPane().add(button_12);
	}
}
