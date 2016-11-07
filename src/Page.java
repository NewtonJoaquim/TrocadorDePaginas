
public class Page {
	private int pageID;
	private int size;
	
	public int getPageID() {
		return pageID;
	}
	
	public void setPageID(int pageID) {
		this.pageID = pageID;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public Page(int pageID, int size) {
		super();
		this.pageID = pageID;
		this.size = size;
	}
}
