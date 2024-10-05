package classes.entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.key_handler;

public class dummy {

    BufferedImage sprite;
    public int x_pos, y_pos, screen_x, screen_y, map_length, map_height, t_size;
    public int xx, yy;

    public dummy(int x, int y, int t_size, int map_length, int map_height){
        this.map_length = map_length;
        this.map_height = map_height;
        this.t_size = t_size;
        //FIXED IT! Jesus Christ, swapped scren_width and screen_height on panel.java
        x_pos = (map_height * t_size) / 2;
        y_pos = (map_length * t_size) / 2;
        //These variables will be where our sprite will be displayed, which will be at the center
        //We get this by subtracting the corods with half the tile size to offset the sprite to give the illusion that it is perfectly at the center
        //Cause you know how pixels are printed starting at the top most corner

        screen_x = x / 2;
        screen_y = y / 2;
        xx = screen_x;
        yy = screen_y;
        try { sprite = ImageIO.read(getClass().getResourceAsStream("../../assets/sprites/fidget_spinner.png"));
        } catch (IOException e) {
            System.out.println("Error reading dummy sprite");
        }
    }

    public void display_dummy(Graphics g, int t_size){
        if ((x_pos > screen_x && x_pos < ((map_length * t_size) - (screen_x + t_size))
            && (y_pos > screen_y && y_pos < ((map_height * t_size) - (screen_y + t_size))))){
                xx = screen_x;
                yy = screen_y;
                System.out.println("xx: " + xx + " yy: " + yy + " x pos: " + x_pos + " y pos: " + y_pos);
                g.drawImage(
                    sprite, 
                    (screen_x - (t_size/2)), (screen_y - (t_size/2)), 
                    t_size, t_size, 
                    null)
                ;
            }else{
                System.out.println("xx: " + xx + " yy: " + yy + " x pos: " + x_pos + " y pos: " + y_pos);
                g.drawImage(
                    sprite, 
                    (xx - (t_size/2)), (yy - (t_size/2)), 
                    t_size, t_size, 
                    null
                );
            }
    }

    public void update_position(key_handler inputs){
        //check which key is pressed and add/subtract the corresponding value
        // System.out.println("Current pos: x: " + x_pos + " y: " + y_pos + 
        // " | Screen pos: x: " + screen_x + " y: " + screen_y +
        // " | Padding: x: " + ((50 * 64) - (screen_x + 64)) + " y: " + ((64 * 10) * 4 - (screen_y + 64)));
        if(inputs.up_pressed || inputs.down_pressed || inputs.left_pressed || inputs.right_pressed){
            if ((x_pos > screen_x && x_pos < ((map_length * t_size) - (screen_x + t_size))
            && (y_pos > screen_y && y_pos < ((map_height * t_size) - (screen_y + t_size))))){
                if(inputs.up_pressed) y_pos -= 10;
                else if(inputs.down_pressed) y_pos += 10;
                else if(inputs.left_pressed) x_pos -= 10;
                else if(inputs.right_pressed) x_pos += 10;
                
            } else {
                if(inputs.up_pressed)yy -= 10;
                else if(inputs.down_pressed) yy += 10;
                else if(inputs.left_pressed) xx -= 10;
                else if(inputs.right_pressed) xx += 10;
                if(inputs.up_pressed) y_pos -= 10;
                else if(inputs.down_pressed) y_pos += 10;
                else if(inputs.left_pressed) x_pos -= 10;
                else if(inputs.right_pressed) x_pos += 10;
            }

        }
    }
    
}
