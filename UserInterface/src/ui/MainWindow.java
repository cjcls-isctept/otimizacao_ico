package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JFrame {
	
	JButton start_btn;
	JButton close_btn;
	
	public MainWindow() {
		this.setTitle("AHP");

        createView();
        
        pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		
		
	}
	
	private void createView() {
		
		start_btn = new JButton("START");
		this.add(start_btn);
		
		start_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				start_btn.setEnabled(false);
				//for (int i = 0; i < 3; i++) {
					//DialogComponent d = new DialogComponent();
					//waitAction();
				//new InteractionsHandler(2);
				//}
				start_btn.setEnabled(true);
				
				
			}
		});
	}
	
	public synchronized void waitAction() {
		try {
			while(true){
				wait();
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainWindow();
	}

}
