package classes.entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.key_handler;

public class dummy {
    final public int MAX_X = 3200;
    final public int MAX_Y = 3200;
    final public int MIN_X = 0;
    final public int MIN_Y = 0;
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
                //System.out.println("xx: " + xx + " yy: " + yy + " x pos: " + x_pos + " y pos: " + y_pos);
                g.drawImage(
                    sprite, 
                    (screen_x - (t_size/2)), (screen_y - (t_size/2)), 
                    t_size, t_size, 
                    null)
                ;
            }else{
                //System.out.println("xx: " + xx + " yy: " + yy + " x pos: " + x_pos + " y pos: " + y_pos);
                if(yy < 0) yy = 0;
                else if(yy > 640) yy = 640;
                if(xx < 0) xx = 0;
                else if(xx > 1280) xx = 1280;
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
        System.out.println("Current pos: x: " + x_pos + " y: " + y_pos + " | Screen pos: x: " + screen_x + " y: " + screen_y);
        if(inputs.up_pressed || inputs.down_pressed || inputs.left_pressed || inputs.right_pressed){

            //--Out of bounds map edge check--
            //TODO INSTEAD OF USING 2, USE SCALE and POLISHING
            if(x_pos <= (MIN_X + (16 * 2)) && inputs.left_pressed) return;
            /*
            if character is touching left edge and pressing left button
            stops all the code below from running to prevent "passing through" the map
            */

            if(x_pos >= (MAX_X - (16 * 0)) && inputs.right_pressed) return;
            /*
            if character is touching right edge and pressing right button
            stops all the code below from running to prevent "passing through" the map
            */

            if(y_pos <= (MIN_Y + (16 * 2)) && inputs.up_pressed) return;
            /*
            if character is touching north edge and up button
            stops all the code below from running to prevent "passing through" the map
            */

            if(y_pos >= (MAX_Y - (16 * 2)) && inputs.down_pressed) return;
            /*
            if character is touching south edge and pressing down button
            stops all the code below from running to prevent "passing through" the map
            */

            /*
            The minor problem with the code above is that if for example:
            You were on the left edge and pressing the A key at the same time
            You cannot press up or down key to move upwards whilst holding the A key as the condition
            would just stop the code below from being executed
            */


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
