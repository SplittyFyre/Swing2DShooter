package box;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EntityShip extends Sprite {

    private int dx;
    private int dy;

    @SuppressWarnings("rawtypes")
	private ArrayList bullets;
    @SuppressWarnings("rawtypes")
	private ArrayList missiles;

    public EntityShip(int x, int y) {
        super(x, y);
        
        initCraft();
    }

    @SuppressWarnings("rawtypes")
	private void initCraft() {

        bullets = new ArrayList();
        missiles = new ArrayList();
        loadImage("res/ship.png"); 
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
  
        if (x < 0) {
        	x = 0;
        }
        if (x > 365) {
        	x = 365;
        }
        if (y > 245) {
        	y = 245;
        }
        
    }

    @SuppressWarnings("rawtypes")
	public ArrayList getBullets() {
        return bullets;
    }
    
    @SuppressWarnings("rawtypes")
	public ArrayList getMissiles() {
    	return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            firebullet();
        }
        
        if (key == KeyEvent.VK_M) {
        	firemissile();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        
        if (key == KeyEvent.VK_N) {
        	addscore();
        }
        
        if (key == KeyEvent.VK_COMMA) {
        	Game.SCORE -= 300;
        	Game.AMMO += 300;
        }
        
        if (key == KeyEvent.VK_J) {
        	Game.LIFE += 2;
        }
        
    }
 
    
    public void addscore() {
    	if (Game.mshots >= 50) {
    		Game.SCORE += 5000;
    		Game.mshots -= 50;
    	}
    }
    
    @SuppressWarnings("unchecked")
	public void firebullet() {
    	if (Game.AMMO > 0) {
        bullets.add(new Bullet(x + width / 2 - 8, y + height - 10));
        Game.mshots += 1;
        Game.AMMO -= 1;
    	}
    }
    
    @SuppressWarnings("unchecked")
	public void firemissile() {
    	if (Game.mshots >= 10) {
    		missiles.add(new Missile(x + width / 2 - 9, y + height - 20));
    		Game.mshots -= 10;
    		
    	}
    	else {
    		
    	}
        
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}