
public class PhysicalMemory {
	Page frameList[];

	public PhysicalMemory(int size) {
		super();
		this.frameList = new Page[size];
	}
	
	public PhysicalMemory(Page[] frameList) {
		super();
		this.frameList = frameList;
	}

	public void setPage(Page p, int i){
		this.frameList[i] = p;
	}
	
	public Page getPosition(int i){
		return frameList[i];
	}
	
	public Page getPageByID(int i){
		for(int j = 0; j < this.frameList.length;j++){
			if(frameList[j].getPageID() == i)
				return frameList[j];
		}
		return null;
	}
}
