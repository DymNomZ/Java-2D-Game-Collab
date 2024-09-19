
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;


public class panel extends JPanel {
    //Dymes:
    //You get the idea
    int screen_width = 1920;
    int screen_height = 1080;
    
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
}
