import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashSet;

public class combineOutput {
	static String TEES_output = "/home/kbs/Combine/TEES_output/";
	
	public static void main(String[] args) throws IOException {

		LinkedHashSet<String> teesOutput = new LinkedHashSet<String>();
		teesOutput.clear();

		// String TEES_Output_Path = "/home/kbs/Combine/Output/tees_output.txt";
		String Naive_Output_Path = "/home/kbs/Combine/Output/naive_output.tsv";

		File dir = new File("/home/kbs/Combine/TEES_output");
		File[] fileList = dir.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			StringBuffer checkFilename = new StringBuffer();
			checkFilename.append(fileList[i]);
			int pathLength = checkFilename.length();

			if (pathLength > 12) {

				String output_check = checkFilename.substring(pathLength - 11);
				File file = fileList[i];
				String filename = checkFilename.toString();

				if (output_check.equals("coconut.txt")) {
					System.out.println(filename);
					BufferedReader tees = new BufferedReader(new FileReader(filename));
					String textLine = "";

					System.out.println("-----------------Read TEES Output---------------");
					while ((textLine = tees.readLine()) != null) {
						teesOutput.add(textLine);
					}
					tees.close();
					System.out.println("------------------Close TEES Output---------------");
				}
			}

		}
		// File dir = new File("/home/kbs/Combine/TEES_output");

		
		BufferedWriter combine = new BufferedWriter(new FileWriter(Naive_Output_Path, true));
		System.out.println("-----------------Combine TEES and Naive Output----------------");

		for (String o : teesOutput) {
			combine.write("\n");
			combine.write(o);
		}

		combine.close();

		System.out.println("---------------Combination is finished---------------");

	}
	public static void createFolder() {
		File annotationFolder = new File(TEES_output);
		if (!annotationFolder.exists()){
			annotationFolder.mkdir();
		}
		else{
			removeDIR(TEES_output);
			annotationFolder.mkdir();
		}
	}
	public static void removeDIR(String source){
		File[] listFile = new File(source).listFiles(); 
		try{
			if(listFile.length > 0){
				for(int i = 0 ; i < listFile.length ; i++){
					if(listFile[i].isFile()){
						listFile[i].delete(); 
					}else{
						removeDIR(listFile[i].getPath());
					}
					listFile[i].delete();
				}
			}
		}catch(Exception e){
			System.err.println(System.err);
			System.exit(-1); 
		}
			
	}
}
