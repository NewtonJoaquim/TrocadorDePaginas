import java.util.List;


public class VirtualMemory {
	List<Page> pageList;

	public VirtualMemory(List<Page> pageList) {
		super();
		this.pageList = pageList;
	}
	
	Page getPageByID(int i){
		return this.pageList.get(i);
	}
}
