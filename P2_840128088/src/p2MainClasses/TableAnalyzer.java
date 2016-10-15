package p2MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import generalUtilities.DataUtils;
import tableCollectionClasses.Table;

/**
 * This class has the purpose of analyzing tables contained in RAFs that comply with the specifications of this project.
 *  The analysis is nothing but table tuples frequency distribution. 
 * @author J.A. Sanchez Perez
 *
 */
public class TableAnalyzer {

	private String fname;
	private Scanner input;
	
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		TableAnalyzer analyzer = new TableAnalyzer(args[0], in);
		analyzer.analyze();
		
	}
	/**
	 * Creates a new instance of this with given file name and input.
	 * @param fname the file name
	 * @param input the input source
	 */
	public TableAnalyzer(String fname, Scanner input) {
		// TODO Auto-generated constructor stub
		this.fname = fname;
		this.input = input;
		
	}

	/**
	 * Triggers the analysis. 
	 */
	public void analyze(){
		
		System.out.print("\nWelcome to Table Analyzer!\n\n");

		// create new File object
		File f = new File("InputData"+ File.separator + fname);

		boolean invalidFile = false;
		//check if file exists
		if(f.exists()){
			
			try {
				RandomAccessFile raf = new RandomAccessFile(f, "r");
				
				Table t = DataUtils.isValidProjectFile(raf, "a");
				
				if(t==null)
					invalidFile = true;
				else{
					//file is valid
					
					//show table content and attributes summary table
					t.displayTable();
					System.out.println("\n");
					t.showAttributeSummaryTable();
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//
			}
						
		}else{
			//file does not exist.
			System.out.println("Sorry, " + fname + " does not exist. Try again later!");
		}
		
		
		if(invalidFile)
			System.out.println("Sorry, " + fname + " does not comply with this project file format. Try again with another file.");
	}
}
