package snake_game;

import java.util.Random;

public class Food {
	private final int posX;
	private final int posY;

	public Food() {
	   	posX=generatePos(Graphics.width);
	   	posY=generatePos(Graphics.height);
	}

	private int generatePos(int size) {
	    Random random=new Random();
	    return random.nextInt(size/Graphics.dot_sz)*Graphics.dot_sz;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
}
