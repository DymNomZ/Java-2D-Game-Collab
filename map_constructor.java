
import java.awt.Graphics;



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

    void display_tiles(Graphics g){
        System.out.println("Displaying map tiles of: " + kind);
        //for loop to draw map of temp_tile.png
        int x = 0, y = 0; //+32 every iteration because of 32x32 dimension
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                g.drawImage(temp.texture, x, y, null);
                x += 32;
            }
            y += 32;
            x = 0;
        }
        System.out.println("Displaying complete");
    }
}
