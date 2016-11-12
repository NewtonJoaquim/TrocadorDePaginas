import java.io.IOException;

public class PageSwitcher {
	VirtualMemory vm;
	PhysicalMemory pm;
	SubstitutionOperation sub;
	int pageMap[];
	
	public PageSwitcher(VirtualMemory vm, PhysicalMemory pm, SubstitutionOperation sub) {
		super();
		this.vm = vm;
		this.pm = pm;
		this.sub = sub;
		this.pageMap = new int[vm.getSize()];
	}
	
	public void doFIFO(){
		fillPhysicalMemory();
		while(this.vm.getSize() != 0){
			this.sub.FIFO(this.vm, this.pm);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
	}
	
	private void fillPhysicalMemory(){
		for(int i=0;i<this.pm.getSize();i++){
			//System.out.println(this.vm.getPageByPosition(0).getPageID());
			this.pm.setPage(this.vm.removePage(0), i);
			//System.out.println(this.pm.getPageByID(i));
		}
		for(int i = 0; i<pm.getSize();i++)
			System.out.println(pm.getPosition(i).getPageID());
	}
	
	public static void main(String args[]) throws IOException{
		CSVHandler handler = new CSVHandler();
		SubstitutionOperation sub = new SubstitutionOperation();
		PhysicalMemory pm = new PhysicalMemory(3);
		VirtualMemory vm = new VirtualMemory(handler.readPageFile("pages.csv"));
		PageSwitcher ps = new PageSwitcher(vm, pm, sub);
		//ps.doFIFO();
		
		//for(int i = 0; i<vm.getSize();i++){
		//	System.out.println(vm.getPageByPosition(i).getPageID());
		//}
		ps.fillPhysicalMemory();
	}
	
}
