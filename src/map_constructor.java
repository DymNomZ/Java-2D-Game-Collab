package src;
import java.awt.Graphics;
import Class.map.tile;

public class map_constructor {
    //Dymes
    //the following variables are temporary as of the moment
    //this is only for drawing a temporary map for testing
    String path, kind;
    tile temp;
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

    void display_tiles(Graphics g, int t_size, int row, int col){
        //for loop to draw map of temp_tile.png
        int x = 0, y = 0; //+tile_size to adjust to number of pixels
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                g.drawImage(temp.texture, x, y, t_size, t_size, null);
                x += t_size;
            }
            y += t_size;
            x = 0;
        }
    }
}
