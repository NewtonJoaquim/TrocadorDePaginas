
public class PageSwitcher {
	VirtualMemory vm;
	PhysicalMemory pm;
	
	int pageMap[];
	
	public PageSwitcher(VirtualMemory vm, PhysicalMemory pm) {
		super();
		this.vm = vm;
		this.pm = pm;
		this.pageMap = new int[vm.getSize()];
	}
	
	
	
}
