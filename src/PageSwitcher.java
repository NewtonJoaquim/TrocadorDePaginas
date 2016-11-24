import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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
		double clock[] = new double[this.pm.getSize()];
		for(int i = 0; i<clock.length;i++){
			clock[i] = 0;
		}
		while(this.vm.getSize() != 0){
			sub.LRU(vm, pm, clock);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("Page Faults: "+ sub.getPageFaultCounter());
		this.sub.printTimeInfo();
	}
	
	public void doLFU(){
		sub.fillPhysicalMemory(this.pm, this.vm);
		int counter[] = new int[this.pm.getSize()];
		for(int i = 0; i<counter.length;i++){
			counter[i] = 0;
		}
		while(this.vm.getSize() != 0){
			sub.LFU(vm, pm, counter);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("Page Faults: "+ sub.getPageFaultCounter());
		this.sub.printTimeInfo();
	}
	
	public void doMFU(){
		sub.fillPhysicalMemory(this.pm, this.vm);
		int counter[] = new int[this.pm.getSize()];
		for(int i = 0; i<counter.length;i++){
			counter[i] = 0;
		}
		while(this.vm.getSize() != 0){
			sub.MFU(vm, pm, counter);
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
		int counter[] = new int[this.pm.getSize()];
		boolean verificator[] = new boolean[this.pm.getSize()];
		for(int i = 0;i<verificator.length;i++){
			verificator[i] = false;
		}
		for(int i = 0;i<counter.length;i++){
			counter[i] = 0;
		}
		System.out.println();
		
		while(this.vm.getSize() != 0){
			sub.Ideal(vm, pm, counter, verificator);
			for(int i = 0; i<this.pm.getSize();i++){
				System.out.print(this.pm.getPosition(i).getPageID() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("Page Faults: "+ sub.getPageFaultCounter());
		this.sub.printTimeInfo();
	}
	
	public static void main(String args[]) throws IOException{
		Scanner scan = new Scanner(System.in);
		CSVHandler handler = new CSVHandler();
		SubstitutionOperation sub = new SubstitutionOperation();
		PhysicalMemory pm = new PhysicalMemory(4);
		VirtualMemory vm = new VirtualMemory(handler.readPageFile("pages.csv"));
		PageSwitcher ps = new PageSwitcher(vm, pm, sub);
		System.out.println("Escolha o algoritmo que deseja utilizar: ");
		System.out.println("1: FIFO");
		System.out.println("2: LRU");
		System.out.println("3: Otimo");
		System.out.println("4: LFU");
		System.out.println("5: MFU");
		String n = scan.next();
		switch(Integer.parseInt(n)){
			case 1: ps.doFIFO();break;
			case 2: ps.doLRU();break;
			case 3: ps.doIdeal();break;
			case 4: ps.doLFU();break;
			case 5: ps.doMFU();break;
		}
		scan.close();
		
	}
	
}
