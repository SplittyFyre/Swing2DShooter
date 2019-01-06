package box;

public class Alien extends Sprite {
	
	public int HEALTH = 3;

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
        
    }
    
    
    public int getHealth() {
    	return HEALTH;
    }
    
    private void initAlien() {

        loadImage("res/alien.png");
        getImageDimensions();
    }

    public void move() {

        y += 1;
        if (y > 299) {
        	this.setVisible(false);
            Game.pass();
        }
    }
    
    public void hitb(){
    	HEALTH -= 1;
    }
    
    
    {
    
    
    }
}