package box;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {
	
	public static int AMMO = 300;
	public static long SCORE = 0;
	public static int LIFE = 3;
	private boolean NEEDNEW = false;
	public static int mshots = 0;
    private final int ICRAFT_X = 150;
    private final int ICRAFT_Y = 240;
    private final int DELAY = 10;
    private Timer timer;
    private EntityShip craft;
    public static ArrayList<Alien> aliens;

    Random rand = new Random();
    
    public Game() {

        initBoard();
        
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        aliens = new ArrayList<>();
        rnew();
        craft = new EntityShip(ICRAFT_X, ICRAFT_Y);
        
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
  
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        doMissile(g);

        Toolkit.getDefaultToolkit().sync();
    }
    
    
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(craft.getImage(), craft.getX(),
                craft.getY(), this);
        
        g.setColor(Color.WHITE);
        g.drawString("Power: " + mshots, 5, 15);
        g.drawString("Health: " + LIFE, 340, 15);
        g.drawString("Score: " + SCORE, 5, 30);
        g.drawString("Bullets: " + AMMO, 325, 25);

        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        ArrayList<?> ms = craft.getBullets();
 
        for (Object m1 : ms) {
            Bullet m = (Bullet) m1;
            g2d.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
        }
        
    }
    
    public static void pass() {
    	LIFE -= 1;
    	aliens.add(new Alien(200, 2));
    	if (LIFE == 0) {
    		System.out.println("Your Score Is: " + SCORE);
    		System.exit(0);
    	}
    }
    
    public void rnew() {
    	int x = rand.nextInt((300))+50;
    	anew(x, 2);
    }
    public void anew(int coordx, int coordy) {
    	aliens.add(new Alien(coordx, coordy));
    }
    
       public void checkCollisions() {
    	   @SuppressWarnings("unchecked")
		ArrayList<Bullet> md = craft.getBullets();
    	@SuppressWarnings("unchecked")
		ArrayList<Missile>bb = craft.getMissiles();

           for (Bullet m : md) {

               Rectangle r1 = m.getBounds();
               	
               for (Alien alien : aliens) {
               
                   Rectangle r2 = alien.getBounds();

                   if (r1.intersects(r2)) {
                	   m.setVisible(false);
                       alien.hitb();
                       if (0 == alien.getHealth()){
                    	  alien.setVisible(false);
                    	  NEEDNEW = true;
                    	  SCORE += 100;
                    	  
                    	  
                    	  
                       }
                   }
               }
           }
 
           for (Missile b : bb) {
        	   
        	   Rectangle r3 = b.getBounds();
        	   
        	for (Alien alien : aliens) {
        	   Rectangle r4 = alien.getBounds();
        	   		
        	   		if (r3.intersects(r4)) {
        	   			b.setVisible(false);
        	   			alien.setVisible(false);
        	   			NEEDNEW = true;
        	   			SCORE += 150;
        	   		}
        	}
           }
       }
        
     
       		private void doMissile(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(craft.getImage(), craft.getX(),
                    craft.getY(), this);

            ArrayList<?> bs = craft.getMissiles();

            for (Object b1 : bs) {
                Missile b = (Missile) b1;
                g2d.drawImage(b.getImage(), b.getX(),
                        b.getY(), this);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateBullets();
        updateMissiles();
        updateAliens();
        updateCraft();
        checkCollisions();
        

        repaint();
    }
    
    private void updateAliens() {
    	for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
    	}
    	
    	if (NEEDNEW == true) {
    		rnew();
    		NEEDNEW = false;
    	}
    }
    
    private void updateBullets() {

        ArrayList<?> ms = craft.getBullets();

        for (int i = 0; i < ms.size(); i++) {

            Bullet m = (Bullet) ms.get(i);

            if (m.isVisible()) {

                m.move();
            } else {

                ms.remove(i);
            }
        }
    }
    
 
	private void updateMissiles() {

        ArrayList<?> bs = craft.getMissiles();

        for (int i = 0; i < bs.size(); i++) {

            Missile b = (Missile) bs.get(i);

            if (b.isVisible()) {

                b.move();
            } else {

                bs.remove(i);
            }
        }
    }


    private void updateCraft() {

        craft.move();
    }
    
    
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
    
}