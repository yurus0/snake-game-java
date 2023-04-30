package snake_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Graphics extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

    static final int width=600;
    static final int height=600;
    static final int dot_sz=20;
    static final int board_sz=(width*height)/(dot_sz*dot_sz);

    final Font font = new Font("Arial", Font.BOLD, 50);

    int[] snakePosX = new int[board_sz];
    int[] snakePosY = new int[board_sz];
    int snakelen;

    Food food;
    int foodEaten;

    char direction='R';
    boolean ismoving=false, paused=false;
    final Timer timer=new Timer(150, this);

    public Graphics() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (ismoving) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction!='R') {
                                direction='L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction!='L') {
                                direction='R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction!='D') {
                                direction='U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction!='U') {
                                direction='D';
                            }
                            break;
                        case KeyEvent.VK_SPACE:{
                        	if(paused) {
                        		paused=false; timer.start(); repaint();
                        	} else {
                        		paused=true; timer.stop();
                        		repaint();
                        	}
                        }
                    }
                } else {
                    start();
                }
            }
        });
        start();
    }

    protected void start() {
        snakePosX=new int[board_sz];
        snakePosY=new int[board_sz];
        snakelen=5;
        foodEaten=0;
        direction='R';
        ismoving=true;
        generateFood();
        timer.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (ismoving) {
	        if(!paused) {
	            g.setColor(new Color(225, 150, 0));
	            g.fillOval(food.getPosX(), food.getPosY(), dot_sz, dot_sz);
	            
	            g.setColor(new Color(150, 220, 150));
	            g.fillRect(snakePosX[0], snakePosY[0], dot_sz, dot_sz);
	            g.setColor(new Color(5, 110, 25));
	            for (int i=1; i<snakelen; i++) {
	                g.fillRect(snakePosX[i], snakePosY[i], dot_sz, dot_sz);
	            }
	            Font font1=new Font("Arial", Font.BOLD, 20);
	            String scoreText = String.format("Score: %d", foodEaten);
	            g.setColor(Color.WHITE);
	            g.setFont(font1);
	            g.drawString(scoreText, (width-getFontMetrics(g.getFont()).stringWidth(scoreText))/2, 20);
        	} else {
        		String pauseText="Paused";
        		g.setColor(Color.YELLOW);
        		g.setFont(font);
        		g.drawString(pauseText, (width-getFontMetrics(g.getFont()).stringWidth(pauseText))/2, height/2-35);
        		g.setColor(Color.WHITE);
                Font font1= new Font("Arial", Font.BOLD, 20);
                g.setFont(font1);
                pauseText=String.format("Score: %d.", foodEaten);
                g.drawString(pauseText, (width-getFontMetrics(g.getFont()).stringWidth(pauseText))/2, height/2);
                font1= new Font("Arial", Font.PLAIN, 18);
                g.setFont(font1);
                pauseText="Press any key to resume game >";
                g.drawString(pauseText, (width-getFontMetrics(g.getFont()).stringWidth(pauseText))/2, height/2+250);
        	}
        } else {
            String scoreText = String.format("Game Over...");
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString(scoreText, (width-getFontMetrics(g.getFont()).stringWidth(scoreText))/2, height/2-35);
            g.setColor(Color.WHITE);
            Font font1= new Font("Arial", Font.BOLD, 20);
            g.setFont(font1);
            scoreText=String.format("Score: %d.", foodEaten);
            g.drawString(scoreText, (width-getFontMetrics(g.getFont()).stringWidth(scoreText))/2, height/2);
            font1= new Font("Arial", Font.PLAIN, 18);
            g.setFont(font1);
            scoreText="Press any key to play again >";
            g.drawString(scoreText, (width-getFontMetrics(g.getFont()).stringWidth(scoreText))/2, height/2+250);
        }
    }
    
    
    protected void move() {
	        for (int i=snakelen; i>0; i--) {
	            snakePosX[i]=snakePosX[i-1];
	            snakePosY[i]=snakePosY[i-1];
	        }
	
	        switch (direction) {
	            case 'U'->snakePosY[0]-=dot_sz;
	            case 'D'->snakePosY[0]+=dot_sz;
	            case 'L'->snakePosX[0]-=dot_sz;
	            case 'R'->snakePosX[0]+=dot_sz;
	        }
    	}

    protected void generateFood() {
        food=new Food();
    }

    protected void eatFood() {
        if ((snakePosX[0]==food.getPosX()) && (snakePosY[0]==food.getPosY())) {
            snakelen++;
            foodEaten++;
            generateFood();
        }
    }

    protected void collisionTest() {
    	//body hit check
        for (int i=snakelen; i>0; i--) {
            if ((snakePosX[0]==snakePosX[i])&&(snakePosY[0] == snakePosY[i])) {
                ismoving=false;
                break;
            }
        }
        //wall hit check
        if (snakePosX[0]<0 || snakePosX[0]>width-dot_sz || snakePosY[0]<0 || snakePosY[0]>height-dot_sz) {
            ismoving=false;
        }

        if (!ismoving) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ismoving) {
            move();
            collisionTest();
            eatFood();
        }

        repaint();
    }
    
}
