package box;

import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameHelper extends JFrame {

    public GameHelper() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Game());
        
        setSize(400, 300);
        setResizable(false);
        
        setTitle("Jet Fighters");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                GameHelper ex = new GameHelper();
                ex.setVisible(true);
            }
        });
    }
}