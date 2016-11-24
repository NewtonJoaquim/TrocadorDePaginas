public class SubstitutionOperation {
	
	private int firstIn;
	private int leastRecentlyUsed, mostFrequentlyUsed;
	private int pageFaultCounter;
	private double accessTime, totalExecutionTime, swapTime;
	
	public SubstitutionOperation() {
		super();
		this.pageFaultCounter = 0;
		this.firstIn = 0;
		this.leastRecentlyUsed = 0;
		this.accessTime = 0.00002;
		this.totalExecutionTime = 0;
		this.swapTime = 2;
	}
	
	public int getPageFaultCounter(){
		return pageFaultCounter;
	}
	
	public void printTimeInfo(){
		System.out.println("Tempo total de execução: " + this.totalExecutionTime + " ms");
	}
	
	public void fillPhysicalMemory(PhysicalMemory pm, VirtualMemory vm){
		for(int i=0;i<pm.getSize();i++){
			this.pageFaultCounter++;
			pm.setPage(vm.removePage(0), i);
			totalExecutionTime += accessTime; 
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
			totalExecutionTime += accessTime; 
		}
		else{
			pageFaultCounter++;
			pm.setPage(vm.removePage(0), firstIn);
			if(firstIn == pm.getSize()-1)
				firstIn = 0;
			else
				firstIn++;
			totalExecutionTime += accessTime + swapTime; 
		}
		System.out.println("firstIn: " + firstIn);
		System.out.println("////////////////////////////////////////////////");
	}
	
	public void Ideal(VirtualMemory vm, PhysicalMemory pm, int frequency[]){
		System.out.println("Frequency: "+ frequency[0] +" " + frequency[1] +" "+ frequency[2] +" "+ frequency[3] +" "+ frequency[4]);
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			vm.removePage(0);
			totalExecutionTime += accessTime; 
		}
		else{
			pageFaultCounter++;
			pm.setPage(vm.removePage(0), getLessFrequentInMemory(pm, frequency));
			frequency[getLessFrequentInMemory(pm, frequency)]++;
			totalExecutionTime += accessTime + swapTime;
		}
		System.out.println("Least Frequently Used: " + minValue(frequency));
		System.out.println("///////////////////////////////////////////////////");
	}
	private int getLessFrequentInMemory(PhysicalMemory pm, int frequency[]){
		int frequencyAux[] = new int[frequency.length];
		for(int i = 0; i<frequency.length; i++){
			frequencyAux[i] = frequency[i];
		}
		while(true){
			if(pm.isPageInMemory((minValue(frequencyAux)))){
				return minValue(frequencyAux);
			}
			else{
				frequencyAux[minValue(frequencyAux)] = Integer.MAX_VALUE;
			}
		}
	}
	public void LRU(VirtualMemory vm, PhysicalMemory pm, double clock[]){
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			leastRecentlyUsed = pm.getPagePosition(vm.removePage(0));
			clock[leastRecentlyUsed] = System.currentTimeMillis();
			totalExecutionTime += accessTime; 
		}
		else{
			pageFaultCounter++;
			leastRecentlyUsed = (int) minValue(clock);
			pm.setPage(vm.removePage(0), leastRecentlyUsed);
			clock[leastRecentlyUsed]= 1;
			totalExecutionTime += accessTime + swapTime;
		}
		System.out.println("Least Recently Used: " + leastRecentlyUsed);
		System.out.println("///////////////////////////////////////////////////");
	}
	
	public void LFU(VirtualMemory vm, PhysicalMemory pm, int counter[]){
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			leastRecentlyUsed = pm.getPagePosition(vm.removePage(0));
			counter[leastRecentlyUsed]++;
			totalExecutionTime += accessTime; 
		}
		else{
			pageFaultCounter++;
			leastRecentlyUsed = minValue(counter);
			pm.setPage(vm.removePage(0), leastRecentlyUsed);
			counter[leastRecentlyUsed]++;
			totalExecutionTime += accessTime + swapTime;
		}
		System.out.println("Least Frequently Used: " + leastRecentlyUsed);
		System.out.println("///////////////////////////////////////////////////");
	}
	
	public void MFU(VirtualMemory vm, PhysicalMemory pm, int counter[]){
		if(vm.getSize() > 0)
			System.out.println("Proxima pagina: "+ vm.getPageByPosition(0).getPageID());
		if(pm.isPageInMemory(vm.getPageByPosition(0))){
			mostFrequentlyUsed = pm.getPagePosition(vm.removePage(0));
			counter[mostFrequentlyUsed]++;
			totalExecutionTime += accessTime; 
		}
		else{
			pageFaultCounter++;
			mostFrequentlyUsed = maxValue(counter);
			pm.setPage(vm.removePage(0), mostFrequentlyUsed);
			//counter[mostFrequentlyUsed]++;
			totalExecutionTime += accessTime + swapTime;
		}
		System.out.println("Most Frequently Used: " + mostFrequentlyUsed);
		System.out.println("///////////////////////////////////////////////////");
	}
	private int maxValue(int array[]){
		int value = Integer.MIN_VALUE;
		int pos = 0;
		for(int i = 0; i < array.length; i++){
			if(value < array[i]){
				value = array[i];
				pos = i;
			}
		}
		return pos;
	}
	
	private int minValue(int array[]){
		int value = Integer.MAX_VALUE;
		int pos = 0;
		for(int i = 0; i < array.length; i++){
			if(value > array[i]){
				value = array[i];
				pos = i;
			}
		}
		return pos;
	}
	private double minValue(double array[]){
		double value = Double.MAX_VALUE;
		int pos = 0;
		for(int i = 0; i < array.length; i++){
			if(value > array[i]){
				value = array[i];
				pos = i;
			}
		}
		return pos;
	}
	
	
	
	
	
}
