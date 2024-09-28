package Class.Entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.key_handler;

public class dummy {

    BufferedImage sprite;
    int x_pos, y_pos;

    public dummy(int x, int y){
        //I was trying to display it at the center of the screen but it isntead went to the left, meh AHAHAHAH, we'll figure this later
        x_pos = x / 2;
        y_pos = y / 2;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("../../assets/sprites/fidget_spinner.png"));
        } catch (IOException e) {
            System.out.println("Error reading dummy sprite");
        }
    }

    public void display_dummy(Graphics g, int t_size){
        g.drawImage(sprite, x_pos, y_pos, t_size, t_size, null);
    }

    public void update_position(key_handler inputs){
        //check which key is pressed and add/subtract the corresponding value
        if(inputs.up_pressed || inputs.down_pressed || inputs.left_pressed || inputs.right_pressed){
            if(inputs.up_pressed) y_pos -= 10;
            else if(inputs.down_pressed) y_pos += 10;
            else if(inputs.left_pressed) x_pos -= 10;
            else if(inputs.right_pressed) x_pos += 10;
        }
    }
    
}
