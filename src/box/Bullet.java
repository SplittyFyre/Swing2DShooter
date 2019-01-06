package box;

public class Bullet extends Sprite {

    private final int BOARD_HEIGHT = 1;
    private final int BULLET_SPEED = 2;

    public Bullet(int x, int y) {
        super(x, y);
        
        initBullet();
    }
    
    private void initBullet() {
        
        loadImage("res/bullet.png");  
        getImageDimensions();
    }


    public void move() {
        
        y -= BULLET_SPEED;
        
        if (y < BOARD_HEIGHT) {
            vis = false;
        }
    }
}
