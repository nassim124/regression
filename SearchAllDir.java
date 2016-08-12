
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchAllDir {


	public static ArrayList<String>  searchInFile(String path) throws IOException {

		ArrayList<String> list = new ArrayList<String>(); // list of files.

		File dir = new File(path); // directory = target directory.
		if(dir.exists()) // Directory exists then proceed.
		{ 
			
			
			// keyword = keyword to search in files.
			Pattern f = Pattern.compile("Failures: 0"); 
			Pattern f1 = Pattern.compile("Failures");
			
			//Pattern e = Pattern.compile("Errors: 0"); 
			//Pattern e1 = Pattern.compile("Errors");
			
			for(File file : dir.listFiles())
			{
				if(!file.isFile()) continue;
				try
				{

					FileInputStream fis = new FileInputStream(file);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text = new String(data);
					Matcher fm = f.matcher(text);
					Matcher fm1 = f1.matcher(text);
					//Matcher em = e.matcher(text);
					//Matcher em1 = e1.matcher(text);
					if(!fm.find())
					{
						if (fm1.find()){
							list.add(file.getName()); // add file to found-keyword list.

						}
					}
					fis.close();
				} 
				catch(Exception ex)
				{
					//System.out.print("\n\t Error processing file : "+file.getName());
				}

			}
			//System.out.println("\t List : "+list); // list of files containing keyword.

		} // IF directory exists then only process.
		else
		{
			//System.out.println("Directory " +dir.getName()+" :"+" doesn't exist.");
		}
		return list;
	}

	public static void searchInAllSubDir (String args) throws IOException{
		
		
		ArrayList<String> list = new ArrayList<String>();
		File dir = new File(args);

		for (File subDir:dir.listFiles()){
			if(subDir.isDirectory()){
				//System.out.println("---------------------- "+"/"+subDir.getName()+" ------------------------------------");
				for (File subSubDir:subDir.listFiles()){
					if (subSubDir.isDirectory()){
						//System.out.println("\n/"+subDir.getName()+"/"+subSubDir.getName());
						if (subSubDir.getName().equals("target")){
							list.addAll(searchInFile(subSubDir.getAbsolutePath()+"/surefire-reports/"));
						}else{
							list.addAll(searchInFile(subSubDir.getAbsolutePath()+"/target/surefire-reports/"));
						}
						
					}
				}
			}
		}
		
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}

	}

	public static void main(String[] args) throws IOException {
		// args[0] should be the the absolute path of the project 
		searchInAllSubDir(args[0]);

	}

}
