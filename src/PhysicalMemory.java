
public class PhysicalMemory {
	private Page frameList[];

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
	
	public int getSize(){
		return this.frameList.length;
	}
	
	public Page getPageByID(int i){
		for(int j = 0; j < this.frameList.length;j++){
			if(frameList[j].getPageID() == i)
				return frameList[j];
		}
		return null;
	}
	
	public boolean isPageInMemory(Page p){
		for(int i=0;i<this.frameList.length;i++){
			if(frameList[i] != null){
				if(frameList[i].getPageID() == p.getPageID()){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isPageInMemory(int id){
		for(int i=0;i<this.frameList.length;i++){
			if(frameList[i] != null){
				if(frameList[i].getPageID() == id){
					return true;
				}
			}
		}
		return false;
	}
	
	public int getPagePosition(Page page){
		for(int i = 0; i<this.frameList.length;i++){
			if(frameList[i].getPageID() == page.getPageID()){
				return i;
			}
		}
		return -1;
	}
	
	public int getPositionByID(int i){
		for(int j = 0; j<this.frameList.length; j++){
			if(frameList[j].getPageID() == i)
				return j;
		}
		return -1;
	}
}
