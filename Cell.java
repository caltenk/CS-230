import javafx.scene.image.Image;

public class Cell {

    private CellType type;
    private Image image;

    public Cell (CellType type) {
        this.type = type;
        if (this.type == CellType.FLOOR) {
            image = new Image("floor.png", 70, 70, true, true);
        }
        else if (this.type == CellType.WALL) {
            image = new Image("wall.png",70,70,true,true);
        }
    }
    
    public CellType getType() {
        return this.type;
    }
    
    public Image getImage () {
        return this.image;
    }

    public CellType getType() {
        return this.type;
    }


}
