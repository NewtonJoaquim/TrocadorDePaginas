import java.io.IOException;

public class PageSwitcher {
	VirtualMemory vm;
	PhysicalMemory pm;
	SubstitutionOperation sub;
	
	public PageSwitcher(VirtualMemory vm, PhysicalMemory pm, SubstitutionOperation sub) {
		super();
		this.vm = vm;
		this.pm = pm;
		this.sub = sub;
	}
	
	public void doFIFO(){
		sub.fillPhysicalMemory(this.pm, this.vm);
		while(this.vm.getSize() != 0){
			this.sub.FIFO(this.vm, this.pm);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("Page Faults: "+ sub.getPageFaultCounter());
		this.sub.printTimeInfo();
	}
	
	public void doLRU(){
		sub.fillPhysicalMemory(this.pm, this.vm);
		int counter[] = new int[this.pm.getSize()];
		for(int i = 0; i<counter.length;i++){
			counter[i] = 0;
		}
		while(this.vm.getSize() != 0){
			sub.LRU(vm, pm, counter);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("Page Faults: "+ sub.getPageFaultCounter());
		this.sub.printTimeInfo();
	}
	
	public void doIdeal(){
		sub.fillPhysicalMemory(this.pm, this.vm);
		int frequency[] = new int[this.vm.getNumberOfDifferentPages()];
		while(this.vm.getSize() != 0){
			sub.Ideal(vm, pm, frequency);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("Page Faults: "+ sub.getPageFaultCounter());
		this.sub.printTimeInfo();
	}
	
	public static void main(String args[]) throws IOException{
		CSVHandler handler = new CSVHandler();
		SubstitutionOperation sub = new SubstitutionOperation();
		PhysicalMemory pm = new PhysicalMemory(4);
		VirtualMemory vm = new VirtualMemory(handler.readPageFile("pages.csv"));
		PageSwitcher ps = new PageSwitcher(vm, pm, sub);
		ps.doIdeal();
		
		//for(int i = 0; i<vm.getSize();i++){
		//	System.out.println(vm.getPageByPosition(i).getPageID());
		//}
		//ps.fillPhysicalMemory();
	}
	
}
