package scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Version05 
{

	public static void main(String[] args) 
	{
	
		File fileSubmitRecords = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\VERSION05\\NO_COUNTERS.csv");
		File fileParsedOutput = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\VERSION05\\PARSED_OUTPUT.TXT");
		
		BufferedWriter bwParsedOutput=null;
		FileWriter fwParsedOutput = null;
		PrintWriter pwParsedOutput=null;
		Scanner scSubmitRecords=null;

		Boolean blnFoundCDSID=false;
		Boolean blnSave=true;
		
		Integer intArrayPosition=0;
		Integer intDateStart = 0;
		Integer intBOMSaveSubmitChangesProductStart=0;
		Integer intUserRoleStart=0;
		
		String[] aryBOMSaveSubmitChangesProduct=null;
		String[] aryCDSID=null;
		String[] aryCtqCode=null;
		String[] aryKeyWord=null;
		String[] arySubmitRecords=null;
		String[] aryUserRole=null;
		
		String strBOMSaveSubmitChangesProduct=null;
		String strCDSID=null;
		String strCtqCode=null;
		String strDate=null;
		String strFoundSubmits=null;
		String strKeyWord=null;
		String strOutput=null;
		String strUserRole=null;
		
		try 
		{
			scSubmitRecords = new Scanner(fileSubmitRecords);
			fwParsedOutput = new FileWriter(fileParsedOutput,false);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		bwParsedOutput = new BufferedWriter(fwParsedOutput);
		pwParsedOutput = new PrintWriter(bwParsedOutput);
		pwParsedOutput.println("DATE,CDSID,ROLE,PRODUCT,KEYWORD,CTQ_CODE,RAWDATA");
		pwParsedOutput.close();
		
		while (scSubmitRecords.hasNextLine())
		{
			blnFoundCDSID=false;
			blnSave=true;
			strBOMSaveSubmitChangesProduct=null;
			strCDSID=null;
			strDate=null;
			strFoundSubmits=null;
			strOutput=null;
			strUserRole=null;
			
			strFoundSubmits=scSubmitRecords.nextLine();
			if(strFoundSubmits.startsWith("\""))
			{
				strFoundSubmits.replaceAll("\"", "");
				blnSave=false;
			}
			if(blnSave)
			{
				arySubmitRecords=strFoundSubmits.split("");
			}
			if(blnSave)
			{
				if (strFoundSubmits.contains("BOMSaveSubmitChanges"))
				{
					blnSave=true;
				}
				else
				{
					blnSave=false;
				}
			}
			if (blnSave)
			{
				aryKeyWord=strFoundSubmits.split(":");
				strKeyWord=aryKeyWord[2];
				aryKeyWord=strKeyWord.split(" ");
				strKeyWord=aryKeyWord[3];
			}
			if (blnSave)
			{			
				strDate=strFoundSubmits.substring(0,10);
				if(strDate.substring(0,1).matches("\""))
				{
					strDate=strFoundSubmits.substring(1,11);
					
				}
			}
			if (blnSave)
			{
				if(strFoundSubmits.contains("UserRole:"))
				{
					intUserRoleStart=strFoundSubmits.indexOf("UserRole:");
					strUserRole=strFoundSubmits.substring(intUserRoleStart+9,strFoundSubmits.length());
					if(strUserRole.contains(".")) 
					{
						strUserRole=strUserRole.substring(0,strUserRole.indexOf("."));
					}
					else
					{
						strUserRole=strUserRole.substring(0,strUserRole.indexOf(",")-1);
					}
				}
				else
				{
					strUserRole="";
				}
			}
			if (blnSave)
			{
				if(strFoundSubmits.contains("ctqlog:"))
				{
					strCDSID=strFoundSubmits.substring(strFoundSubmits.indexOf("ctqlog:"),strFoundSubmits.length());
					aryCDSID=strCDSID.split(":");
					strCtqCode=aryCDSID[1];
					aryCtqCode=strCtqCode.split(" ");
					strCtqCode=aryCtqCode[0];
					strCDSID=aryCDSID[1];
					aryCDSID=strCDSID.split("-");
					strCDSID=aryCDSID[1];
					aryCDSID=strCDSID.split(" ");
					strCDSID=aryCDSID[1];
					aryCDSID=strCDSID.split("_");
					strCDSID=aryCDSID[0];
					blnFoundCDSID=true;
					blnSave=true;
				}
				else
				{
					strCDSID="";
					blnSave=true;
				}
			}
			if (blnSave)
			{
				if(strFoundSubmits.contains("BOMSaveSubmitChangesProduct"))
				{
					blnSave=true;
					intBOMSaveSubmitChangesProductStart=strFoundSubmits.indexOf("BOMSaveSubmitChangesProduct");
					strBOMSaveSubmitChangesProduct = strFoundSubmits.substring(intBOMSaveSubmitChangesProductStart + 31,strFoundSubmits.length());
					if(strBOMSaveSubmitChangesProduct.contains(";"))
					{
						strBOMSaveSubmitChangesProduct=strBOMSaveSubmitChangesProduct.substring(0,strBOMSaveSubmitChangesProduct.indexOf(";"));
					}
					else
					{
						aryBOMSaveSubmitChangesProduct=strBOMSaveSubmitChangesProduct.split("\\.");
						strBOMSaveSubmitChangesProduct=aryBOMSaveSubmitChangesProduct[0];
					}
					if(strBOMSaveSubmitChangesProduct.indexOf(",") > 0)
					{
						aryBOMSaveSubmitChangesProduct=strBOMSaveSubmitChangesProduct.split(",");
						for(intArrayPosition=intBOMSaveSubmitChangesProductStart;intArrayPosition < aryBOMSaveSubmitChangesProduct.length;intArrayPosition++)
						{
							if(aryBOMSaveSubmitChangesProduct[intArrayPosition].matches(","))
							{
								break;
							}
							else
							{
								try 
								{
									fwParsedOutput = new FileWriter(fileParsedOutput,true);
								} 
								catch (IOException e) 
								{
									e.printStackTrace();
								}
								if(blnSave)
								{
									bwParsedOutput = new BufferedWriter(fwParsedOutput);
									pwParsedOutput = new PrintWriter(bwParsedOutput);
									strOutput="";
									strOutput = strOutput + strDate;
									strOutput = strOutput + "," + strCDSID.toUpperCase();
									strOutput = strOutput + "," + strUserRole.toUpperCase();
									strOutput = strOutput + "," + aryBOMSaveSubmitChangesProduct[intArrayPosition].toUpperCase();
									strOutput = strOutput + "," + strKeyWord;
									strOutput = strOutput + "," + strCtqCode;
									strOutput = strOutput + ",\"" + strFoundSubmits + "\"";
									pwParsedOutput.println(strOutput);
									pwParsedOutput.close();
								}
							}
						}
					}
					else
					{
						try 
						{
							fwParsedOutput = new FileWriter(fileParsedOutput,true);
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						if(blnSave)
						{
							bwParsedOutput = new BufferedWriter(fwParsedOutput);
							pwParsedOutput = new PrintWriter(bwParsedOutput);
							strOutput = "";
							strOutput = strOutput +strDate;
							strOutput = strOutput + "," + strCDSID.toUpperCase();
							strOutput = strOutput + "," + strUserRole.toUpperCase();
							strOutput = strOutput+ "," + strBOMSaveSubmitChangesProduct.toUpperCase();
							strOutput = strOutput + "," + strKeyWord;
							strOutput = strOutput + "," + strCtqCode;
							strOutput = strOutput + ",\"" + strFoundSubmits + "\"";
							pwParsedOutput.println(strOutput);
							pwParsedOutput.close();
						}
						else
						{
							try 
							{
								fwParsedOutput = new FileWriter(fileParsedOutput,true);
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
							if(blnSave)
							{
								bwParsedOutput = new BufferedWriter(fwParsedOutput);
								pwParsedOutput = new PrintWriter(bwParsedOutput);
								strOutput = "";
								strOutput = strOutput + strDate;
								strOutput = strOutput + "," + strCDSID.toUpperCase(); 
								strOutput = strOutput +  "," + strUserRole.toUpperCase() ;
								strOutput = strOutput + "," + strBOMSaveSubmitChangesProduct.toUpperCase() ;
								strOutput = strOutput + "," + strKeyWord ;
								strOutput = strOutput + "," + strCtqCode;
								strOutput = strOutput + ",\"" + strFoundSubmits + "\"";
								pwParsedOutput.println(strOutput);
								pwParsedOutput.close();
							}
						}
					}
				}
			}
		}
		
		scSubmitRecords.close();
		try 
		{
			fwParsedOutput.close();
			bwParsedOutput.close();
			pwParsedOutput.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}


