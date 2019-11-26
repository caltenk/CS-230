
public class TeleporterCell extends Cell {
	public int xLinkedCoord;
	public int yLinkedCoord;
	
	public TeleporterCell (int xLinkedCoord, int yLinkedCoord) {
		this.xLinkedCoord = xLinkedCoord;
		this.yLinkedCoord = yLinkedCoord;
	}
	
	public int getLinkedX () {
		return this.xLinkedCoord;
	}
	
	public int getLinkedY () {
		return this.yLinkedCoord;
	}
}
