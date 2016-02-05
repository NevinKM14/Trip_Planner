import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CityHashTable {
	
	private double[][] ht;
	private List<String> cityList = new ArrayList<String>();
	private int size;
	
	public void setHt(String zipFile, String cityFile) throws IOException{
	     CSVReader reader = new CSVReader(new FileReader(zipFile));
	     CSVReader reader2 = new CSVReader(new FileReader(cityFile));
	     List<String[]> zips = reader.readAll();
	     List<String[]> cityInfoList = reader2.readAll();
	     reader.close();
	     reader2.close();
	     
	     ht = new double[cityInfoList.size()][2];
	     
	     for(int i = 0; i < cityInfoList.size(); i++){
	    	 cityList.add(cityInfoList.get(i)[0]);
	    	 for(int j = 1; j < zips.size(); j++){
	    		if(cityInfoList.get(i)[1].equals(zips.get(j)[2])&& cityInfoList.get(i)[0].toUpperCase().equals(zips.get(j)[3])){
	    			ht[i][0] = Double.parseDouble(zips.get(j)[4]);  
	    			ht[i][1] = Double.parseDouble(zips.get(j)[5]);
	    			break;
	    		}
	    	}
	     }
	     setSize();
	}
	
	private void setSize() {
		size = cityList.size();	
	}
	
	public int size(){
		return cityList.size();
	}

	public double[][] getHt(){
		return ht;
	}
	
	public double[] lookUp(String city){
		return ht[hashFunction(city)];	
	}
	
	public String inverseHashFunction(int index){
		return cityList.get(index);
	}
	public int hashFunction(String city){
		return cityList.indexOf(city);
	}
	
}
