import java.awt.*;
import java.awt.image.BufferStrategy;

public class Play extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;



    public static boolean running = false;
    private Thread gameThread;

    public static void main(String[] args) {

        new Play();

    }

    public Play() {

        window();
        new Screen( this);
        this.setFocusable(true);
    }

    private void window() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

    }

    @Override
    public void run() {

        this.requestFocus();
        long lastTime = System.nanoTime();
        double  maxFPS = 60.0;
        double frameTime = 1000000000/maxFPS;
        double delta = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime)/frameTime;
            lastTime = now;
            if(delta>=1) {
                delta--;
                draw();
            }
        }
        stop();


    }

    private void draw() {
        BufferStrategy buffering = this.getBufferStrategy();
        if (buffering == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buffering.getDrawGraphics();
        g.setColor(Color.pink);
//        g.setFont(new Font("Calibri", Font.BOLD, 24));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.dispose();
        buffering.show();

    }


    public void start() {

        gameThread = new Thread(this);
        gameThread.start();
        running = true;

    }

    public void stop() {

        try {
            gameThread.join();
            running = false;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


