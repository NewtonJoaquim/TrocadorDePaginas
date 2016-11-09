import java.util.List;


public class VirtualMemory {
	private List<Page> pageList;

	public VirtualMemory(List<Page> pageList) {
		super();
		this.pageList = pageList;
	}
	
	Page getPageByPosition(int i){
		return this.pageList.get(i);
	}
	
	Page removePage(int i){
		return this.pageList.remove(i);
	}
	
	public int getSize(){
		return this.pageList.size();
	}
}
