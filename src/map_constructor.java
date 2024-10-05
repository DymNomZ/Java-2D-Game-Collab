package src;
import classes.entities.dummy;
import classes.map.tile;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class map_constructor {
    //Dymes
    //the following variables are temporary as of the moment
    //this is only for drawing a temporary map for testing
    BufferedReader reader;
    tile temp;
    tile tiles[] = new tile[4];
    int map_tiles[][] = new int[50][50];
    String path, kind;
    int recent_x = 0, recent_y = 0;
    public map_constructor(String path, String kind, int rows, int cols){
        this.path = path;
        this.kind = kind;

        initialize_map_tiles(rows, cols);
    }

    void initialize_map_tiles(int rows, int cols){
        System.out.println("Initializing map tiles of: " + kind);
        try {
            tiles[0] = new tile("../../assets/map_tiles/temp_green.png", "temp_green");
            tiles[1] = new tile("../../assets/map_tiles/temp_blue.png", "temp_blue");
            tiles[2] = new tile("../../assets/map_tiles/temp_red.png", "temp_red");
            tiles[3] = new tile("../../assets/map_tiles/temp_orange.png", "temp_orange");
        } catch (Exception e) {
            System.out.println("Error retrieving tile textures for map: " + kind);
        }

        //We use FileReader as we are only rading characters, other readers are too much for our usage
        //More info: https://stackoverflow.com/questions/7991770/inputstreamreader-vs-filereader
        //Encountered an error when reading earlier, this fixed it: https://stackoverflow.com/questions/22585621/the-filereader-cannot-find-my-text-file
        try {
            reader = new BufferedReader(new FileReader("assets/maps/dummy_map.txt"));
        } catch (IOException e) {
            System.out.println("Error reading map data for map: " + kind);
        }

        //This array will store the integers per line from the txt file
        String tile_types[] = new String[50];
        int row = 0, col = 0;

        for(; row < rows; row++){
            try {
                //readline returns a string of the first line of text then moves to the next line after it is called.
                tile_types = reader.readLine().split(" ");
            } catch (IOException e) {
                System.out.println("Error reading line " + row + " of " + kind + " map_data");
            }
            for(; col < cols; col++) map_tiles[row][col] = Integer.parseInt(tile_types[col]);
            col = 0;
        }
        System.out.println("Intizializing complete");
    }

    void display_tiles(
        Graphics g, int t_size, 
        int map_height, int map_length, 
        dummy d, 
        int screen_height, int screen_width){
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
                        //Get the tile type of a specific tile of the map
                        g.drawImage(
                            tiles[map_tiles[map_tile_row][map_tile_col]].texture, 
                            screen_x, 
                            screen_y, 
                            t_size, t_size, 
                            null
                        );
                    }
                } else {
                    screen_x = tile_x - recent_x + d.screen_x;    
                    screen_y = tile_y - recent_y + d.screen_y; 
                    if(d.y_pos < recent_y - (screen_height/2) || 
                    d.y_pos > recent_y + (screen_height/2)){
                        recent_y = d.y_pos;
                        screen_y = tile_y - d.y_pos + d.screen_y; 
                    }else if (d.x_pos < recent_x - (screen_width/2) || 
                    d.x_pos > recent_x + (screen_width/2)) {
                        recent_x = d.x_pos;
                        screen_x = tile_x - d.x_pos + d.screen_x;  
                    }
                    g.drawImage(
                        tiles[map_tiles[map_tile_row][map_tile_col]].texture, 
                        screen_x, 
                        screen_y, 
                        t_size, t_size, 
                        null
                    );
                }
                map_tile_col++;
            }
            map_tile_col = 0;
            map_tile_row++;
        }
    }
}
