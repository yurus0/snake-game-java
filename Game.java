package snake_game;

import javax.swing.*;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;

    public Game() {
        this.add(new Graphics());
        this.setTitle("Snake Game");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
