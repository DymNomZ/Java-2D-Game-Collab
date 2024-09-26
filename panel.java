
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class panel extends JPanel {
    //Dymes:
    //You get the idea
    int screen_width = 1920;
    int screen_height = 1080;

    map_constructor map = new map_constructor("/assets//map/temp_tile.png", "map");
    
    //JPanel in a nutshell is our "canvas", that's where we put all our components like sprites, etc.
    //The reasone why we let the panel dictate the size insstead of using the JFrame.setSize()
    //is because it includes the border (the logo, title, and min/max/close buttns you see at the top)
    //we want to specifically change the content screen itself, thats why we add our panel as a component
    //rather than setting that size of our main_window via .setSize()
    //More info: https://stackoverflow.com/questions/6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly
    public panel(){
        //does the sizing
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
    }

    //JPanel class has a paintComponent method, not overriding it only displays the panel
    //If we want to "draw" stuff on it, we override the method, call the super method and implement our own functionalities
    //Source: https://stackoverflow.com/questions/5446396/concerns-about-the-function-of-jpanel-paintcomponent
    @Override
    public void paintComponent(Graphics g){
        //info about Graphics class: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html#:~:text=The%20Graphics%20class%20is%20the,rendering%20operations%20that%20Java%20supports.
        super.paintComponent(g);
        map.display_tiles(g);
    }
}
