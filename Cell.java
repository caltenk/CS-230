
import javafx.scene.image.Image;

public class Cell {

    private CellType type;
    private Image image;

    public Cell(CellType type) {
        this.type = type;
        
        try{
        	switch (this.type) {
           		case WALL:
           			image = new Image("wall.png");
           			break;
           		case GROUND:
           			image = new Image("ground.png");
           			break;
           		case RED_DOOR:
           			image = new Image("reddoor.png");
           			break;
           		case RED_KEY:
           			image = new Image("redkey.png");
           			break;
           		case TELEPORTER:
           			image = new Image("teleporter.png");
           			break;
           		case GOAL:
           			image = new Image("goal.png");
           			break;
           		case TOKEN:
           			image = new Image("token.png");
           			break;
           		default:
           			image = new Image("dirt.png");
           			break;
           }
           
        }catch(IllegalArgumentException e){
            System.out.println("ERROR - cell image not found");
        }
    }
    
    /**suggested to allow filehandling -Dan
     * note: not compiled with CellType, does not initialise cell image
     * @param cellData 
     */
    public Cell(String cellData){
        String[] splitData = cellData.split("#"); //allows additional data eg teleported co-ords
        type = CellType.valueOf(splitData[0]);
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
