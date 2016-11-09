public class SubstitutionOperation {
	
	int firstIn;
	int leastRecentlyUsed;
	
	public SubstitutionOperation() {
		super();
		this.firstIn = 0;
		this.leastRecentlyUsed = 0;
	}

	public void FIFO(VirtualMemory vm, PhysicalMemory pm){
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			vm.removePage(0);
		}
		else{
			pm.setPage(vm.removePage(0), firstIn);
		}
		
		if(firstIn == pm.getSize())
			firstIn = 0;
		else
			firstIn++;
	}
}
