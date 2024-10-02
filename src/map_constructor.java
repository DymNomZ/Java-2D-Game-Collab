package src;
import Class.Entities.dummy;
import Class.map.tile;
import java.awt.Graphics;

public class map_constructor {
    //Dymes
    //the following variables are temporary as of the moment
    //this is only for drawing a temporary map for testing
    String path, kind;
    tile temp;
    int recent_x = 0, recent_y = 0;
    public map_constructor(String path, String kind){
        this.path = path;
        this.kind = kind;

        initialize_map_tiles();
    }

    void initialize_map_tiles(){
        System.out.println("Initializing map tiles of: " + kind);
        temp = new tile(path, kind);
        System.out.println("Intizializing complete");
    }

    void display_tiles(Graphics g, int t_size, int map_height, int map_length, dummy d){
        //for loop to draw map of temp_tile.png

        //Dymes - Oct 2, 2024
        //these variables are intended for easier use once we have multiple maps
        //because we will store the map data inside an array, the names explain what they do
        //they get the tile associated with that specific index of the row and col
        int map_tile_row = 0, map_tile_col = 0;

        //Just like the dummy class, these variables will dictate where a specific tile will be drawn to the screen
        //Essentially, tile_pos variables will hold the position of the tile relative to the map data
        //While screen_pos variables will hold the position of the tile relative to the screen
        //So its all illusions? Always has been
        int tile_x, tile_y, screen_x, screen_y;

        while(map_tile_row < map_height){
            //indicates the position of the tile on the map
            tile_y = map_tile_row * t_size; //we multiply to "jump" the length of tile size to not overwrite the current pixel displayed

            while(map_tile_col < map_length){
                tile_x = map_tile_col * t_size;
                
                //The purpose of this if statement is so that we only display the tiles that we can see in the screen
                //Because if we don't add this, when our game get bigger, this will hurt the perfomance as it will draw all the tiles of the map
                //So it checks whether a specific tile is within range of the position of the player +- its x and y on the screen
                //Basically it checks whether a tile is inside the screen, if not, we do not display it to save resources
                //We add tile_pos with the tile_size, basically we check a tile extra, because if you notice when you remove the addition
                //It has a "border" so we check tiles that are just shy of being part of the screen so that it removes that border
                
                if ((d.x_pos > d.screen_x && d.x_pos < ((map_length * t_size) - (d.screen_x + t_size))
                && (d.y_pos > d.screen_y && d.y_pos < ((map_height * t_size) - (d.screen_y + t_size))))) {
                    recent_x = d.x_pos;
                    recent_y = d.y_pos;
                    screen_x = tile_x - d.x_pos + d.screen_x;    
                    screen_y = tile_y - d.y_pos + d.screen_y; 
                    if ((tile_x + t_size > d.x_pos - d.screen_x && tile_x - t_size < d.x_pos + d.screen_x) &&
                    (tile_y + t_size > d.y_pos - d.screen_y && tile_y - t_size < d.y_pos + d.screen_y)) {
                       g.drawImage(temp.texture, screen_x, screen_y, t_size, t_size, null);
                
                    }
                } else {
                    //System.out.println(recent_x + " " + recent_y);
                    screen_x = tile_x - recent_x + d.screen_x;    
                    screen_y = tile_y - recent_y + d.screen_y; 
                    g.drawImage(temp.texture, screen_x, screen_y, t_size, t_size, null);
                }
                
                
                
                map_tile_col++;
            }
            map_tile_col = 0;
            map_tile_row++;
        }
    }
}
