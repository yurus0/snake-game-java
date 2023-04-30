package snake_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;

public class LaunchScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public LaunchScreen() {
	    setPreferredSize(new Dimension(600, 600));
	    setTitle("Snake Game");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    JLabel titleLabel = new JLabel("Snake Game", JLabel.CENTER);
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
	    titleLabel.setForeground(new Color(5, 110, 25));
	    add(titleLabel, BorderLayout.CENTER);
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    JButton startButton = new JButton("Start");
	    startButton.setFont(new Font("Arial", Font.BOLD, 30));
	    buttonPanel.setBackground(Color.BLACK);

        
	    startButton.setUI(new BasicButtonUI() {
	        public void installDefaults(AbstractButton button) {
	            super.installDefaults(button);
	            button.setBackground(Color.BLACK);
	            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 100));
	            button.setForeground(new Color(220, 240, 195));
	        }
	    });
	    
	    startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	getContentPane().removeAll();
                add(new Game());
                validate();
            }
        });
	    buttonPanel.add(startButton);

	    add(buttonPanel, BorderLayout.SOUTH);

	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	    getContentPane().setBackground(Color.BLACK);
	}
	
	
}

