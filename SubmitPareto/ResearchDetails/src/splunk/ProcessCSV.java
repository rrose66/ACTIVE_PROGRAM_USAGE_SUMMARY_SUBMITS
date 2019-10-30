package splunk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProcessCSV 
{

	public static void main(String[] args) 
	{
		File fileCDSID = new File("C:\\PROJECTS\\DATA\\FEDEBOM\\ORACLE\\ORACLE_CDSID.TXT");
		Scanner scCDSID=null;
		String strCDSID=null;
		
//		File fileGeneric3008 = new File("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\LOGS\\GENERIC_3008_2019920164758.TXT");
//		File fileGeneric3008 = new File("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\AllLogsInOneFile.txt");
		File fileAllLogsInOne = new File("C:\\PROJECTS\\DATA\\SUBMIT_REPORT_RESEARCH\\ALL_FDB_DASHBOARD_LOGS_IN_ONE_FILE\\logs.txt");

		File fileCDSIDtarget=null;
		FileWriter fwFile=null;
		BufferedWriter bwTextBuffer =null;
		PrintWriter pwTextPrinter = null;

		
		Scanner scGeneric3008=null;
		try 
		{
			scCDSID = new Scanner(fileCDSID);
//			scGeneric3008 = new Scanner(fileGeneric3008);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		String strGeneric3008="";
		while(scCDSID.hasNextLine())
		{
			strCDSID=scCDSID.nextLine();
			strCDSID="FSOUZA51";
			try 
			{
				scGeneric3008 = new Scanner(fileAllLogsInOne);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			while (scGeneric3008.hasNextLine())
			{
				strGeneric3008=scGeneric3008.nextLine();
//				System.out.println(strGeneric3008);
				if(strGeneric3008.contains(strCDSID))
				{
					fileCDSIDtarget = new File("C:\\PROJECTS\\DATA\\LOGS_BY_CDSID\\" + strCDSID + "\\" + strCDSID + "_DATA.txt");
					try 
					{
						fwFile = new FileWriter(fileCDSIDtarget,true);
						bwTextBuffer = new BufferedWriter(fwFile);
						pwTextPrinter = new PrintWriter(bwTextBuffer);
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					pwTextPrinter.println(strGeneric3008);
					pwTextPrinter.close();
				}
			}	
			scGeneric3008.close();
		}
	}

}
