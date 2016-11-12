import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVHandler {
	public ArrayList<Page> readPageFile(String file) throws IOException{
		ArrayList<Page> pages= new ArrayList<Page>();
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line = br.readLine()) != null){
			String[] lineField = line.split(",");

			for(int i = 0; i< lineField.length;i++){
				pages.add(new Page(Integer.parseInt(lineField[i]), 1));
			}
		}
		return pages;
	}
}
