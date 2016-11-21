import java.util.Arrays;
import java.util.Collections;

public class SubstitutionOperation {
	
	private int firstIn;
	private int leastRecentlyUsed;
	private int pageFaultCounter;
	
	public SubstitutionOperation() {
		super();
		this.pageFaultCounter = 0;
		this.firstIn = 0;
		this.leastRecentlyUsed = 0;
	}
	
	public int getPageFaultCounter(){
		return pageFaultCounter;
	}
	
	public void fillPhysicalMemory(PhysicalMemory pm, VirtualMemory vm){
		for(int i=0;i<pm.getSize();i++){
			this.pageFaultCounter++;
			pm.setPage(vm.removePage(0), i);
		}
		for(int i = 0; i<pm.getSize();i++)
			System.out.print(pm.getPosition(i).getPageID()+ " ");
		System.out.println();
	}
	
	
	public void FIFO(VirtualMemory vm, PhysicalMemory pm){
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			vm.removePage(0);
		}
		else{
			pageFaultCounter++;
			pm.setPage(vm.removePage(0), firstIn);
			if(firstIn == pm.getSize()-1)
				firstIn = 0;
			else
				firstIn++;
		}
		System.out.println("firstIn: " + firstIn);
		System.out.println("////////////////////////////////////////////////");
	}
	
	public void Ideal(VirtualMemory vm, PhysicalMemory pm){
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			vm.removePage(0);
		}
			
	}
	
	public void LRU(VirtualMemory vm, PhysicalMemory pm, int counter[]){
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			vm.removePage(0);
		}
		else{
			pageFaultCounter++;
			leastRecentlyUsed = getLeastRecentlyUsedPosition(counter);
			pm.setPage(vm.removePage(0), leastRecentlyUsed);
			counter[leastRecentlyUsed]++;
		}
		System.out.println("Least Recently Used: " + leastRecentlyUsed);
		System.out.println("///////////////////////////////////////////////////");
	}
	
	private int getLeastRecentlyUsedPosition(int counter[]){
		int value = Integer.MAX_VALUE;
		int pos = 0;
		for(int i = 0; i < counter.length; i++){
			if(value > counter[i]){
				value = counter[i];
				pos = i;
			}
		}
		return pos;
	}
}
