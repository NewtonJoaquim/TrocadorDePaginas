
public class PageSwitcher {
	VirtualMemory vm;
	PhysicalMemory pm;
	
	int pageMap[] = new int[vm.pageList.size()];
	
	public PageSwitcher(VirtualMemory vm, PhysicalMemory pm) {
		super();
		this.vm = vm;
		this.pm = pm;
	}
	
	
	
}
