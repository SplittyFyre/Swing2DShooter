package box;

public class Missile extends Sprite {

    private final int BOARD_HEIGHT = 1;
    private final int MISSILE_SPEED = 6;

    public Missile(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("res/missile.png");  
        getImageDimensions();
    }


    public void move() {
        
        y -= MISSILE_SPEED;
        
        if (y < BOARD_HEIGHT) {
            vis = false;
        }
    }
}
