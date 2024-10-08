package src;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import classes.entities.dummy;

//JPanel in a nutshell is our "canvas", that's where we put all our components like sprites, etc.
//The reasone why we let the panel dictate the size insstead of using the JFrame.setSize()
//is because it includes the border (the logo, title, and min/max/close buttns you see at the top)
//we want to specifically change the content screen itself, thats why we add our panel as a component
//rather than setting that size of our main_window via .setSize()
//More info: https://stackoverflow.com/questions/6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly

public class panel extends JPanel {
    //Dymes:
    //scale to multiply our default dimenstion because 32x32 pixels is too small for modern screens
    public final int scale = 2, def_dimension = 32;
    public final int tile_size = def_dimension * scale;
    //Number of tiles to display on screen by row and column
    public final int max_screen_row = 10, max_screen_col = 20;
    //Calculating these gives our screen resolution to be 1280 x 640
    public final int screen_width = tile_size * max_screen_col;
    public int screen_height = tile_size * max_screen_row;
    //Dymes - Oct 2, 2024
    //The map's row and column length, temporary 50 for now, but will be easier to change later when we have different maps
    public final int max_map_row = 50, max_map_col = 50;

    map_constructor map = new map_constructor(
        "dummy_map", 
        max_map_row, 
        max_map_col
    );
    
    //Will listen to inputted keys to update to our player class
    key_handler key_input = new key_handler();
    
    public panel(){
        //does the sizing
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        //laerned the hard way that key inputs are only listened on focused components, rip
        //source: https://stackoverflow.com/questions/16530775/keylistener-not-working-for-jpanel
        this.setFocusable(true);
        //Listens to key input
        this.addKeyListener(key_input);
    }

    //temporary
    dummy d = new dummy(screen_width, screen_height, tile_size, max_map_col, max_map_row);

    //this will listen to the timer, and I think the Timer class creates a thread?, maybe that's why we need to listen to it?
    //source: https://www.reddit.com/r/javahelp/comments/6d5rr4/threads_or_timer_for_java_game/
    private ActionListener timer_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            //we pass our key handler so that our dummy can check which keys are pressed
            d.update_position(key_input);
            //repaint calls the paintComponent method again, so you can imagine, it redraws everything on the screen
            //basically updating what is displayed
            repaint();
        }
    };

    public void start_clock(){
        Timer timer = new Timer(10, timer_listener);
        timer.start();
    }
    
    //JPanel class has a paintComponent method, not overriding it only displays the panel
    //If we want to "draw" stuff on it, we override the method, call the super method and implement our own functionalities
    //Source: https://stackoverflow.com/questions/5446396/concerns-about-the-function-of-jpanel-paintcomponent
    @Override
    public void paintComponent(Graphics g){
        //info about Graphics class: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html#:~:text=The%20Graphics%20class%20is%20the,rendering%20operations%20that%20Java%20supports.
        super.paintComponent(g);
        map.display_tiles(g, tile_size, max_map_row, max_map_col, d, screen_height, screen_width);
        d.display_dummy(g, tile_size);
    }
}
