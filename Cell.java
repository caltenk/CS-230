
import javafx.scene.image.Image;

public class Cell {

    private CellType type;
    private Image image;

    public Cell(CellType type) {
        this.type = type;
        if (this.type == CellType.GROUND) {
            image = new Image("floor.png", 70, 70, true, true);
        } else if (this.type == CellType.WALL) {
            image = new Image("wall.png", 70, 70, true, true);
        }
    }
    
    /**suggested to allow filehandling -Dan
     * note: not compiled with CellType, does not initialise cell image
     * @param cellData 
     */
    public Cell(String cellData){
        String[] splitData = cellData.split("#"); //allows additional data eg teleported co-ords
        type = new CellType(splitData[0]); //or whatever syntax, idk innumeration
        //...
        //suggest selecting cell image be a seperate method so it can be called from both constructors
    }
    
    /**suggested to allow filehandling -Dan
     * note: not compiled with CellType, assuming image selection is hard-coded to allow use of level themes
     * @return 
     */
    @Override
    public String toString(){
        String cellData = "";
        if(type != null){
            cellData += type;
        }
        //additional data eg teleported co-ords to be added here, seperated by '#'
        return cellData;
    }

    public CellType getType() {
        return this.type;
    }

    public Image getImage() {
        return this.image;
    }

}
