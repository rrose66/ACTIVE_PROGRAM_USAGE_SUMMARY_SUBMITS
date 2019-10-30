package scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindSubmitRecords 
{

	public static void main(String[] args) 
	{
		File fileSubmitRecords = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\LOGS_GOOD_RECORDS\\NO_COUNTERS.csv");
//		File fileParsedOutput = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\VERSION03\\PARSED_OUTPUT.TXT");
		File fileParsedOutput = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\VERSION04\\PARSED_OUTPUT.TXT");
		
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
		String[] arySubmitRecords=null;
		String[] aryUserRole=null;
		
		String strBOMSaveSubmitChangesProduct=null;
		String strCDSID=null;
		String strDate=null;
		String strFoundSubmits=null;
		String strOutput=null;
		String strUserRole=null;
		
		try 
		{
			scSubmitRecords = new Scanner(fileSubmitRecords);
			fwParsedOutput = new FileWriter(fileParsedOutput,false);
//			fwParsedOutput = new FileWriter(fileParsedOutput,false);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		bwParsedOutput = new BufferedWriter(fwParsedOutput);
		pwParsedOutput = new PrintWriter(bwParsedOutput);
		pwParsedOutput.println("DATE,CDSID,ROLE,PRODUCT,RAWDATA");
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
			arySubmitRecords=strFoundSubmits.split("");
			System.out.println(strFoundSubmits);
			
			if(strFoundSubmits.contains("Include Deleted Parts is true"))
			{
				System.out.print("complete");
			}
			if (strFoundSubmits.contains("BOMSaveSubmitChanges"))
			{
//				if(strFoundSubmits.contains("BOMFBLAppGenericImpl"))
//				{
//					blnSave=true;
//				}
//				else
//				{
//					blnSave=false;
//				}
				strDate=strFoundSubmits.substring(0,10);
				if(strDate.substring(0,1).matches("\""))
				{
					System.out.print("complete");
					strDate=strFoundSubmits.substring(1,11);
					
				}
				System.out.println(strDate);
				if (blnSave)
				{
				if(strFoundSubmits.contains("UserRole:"))
				{
					intUserRoleStart=strFoundSubmits.indexOf("UserRole:");
					System.out.println(intUserRoleStart);
					strUserRole=strFoundSubmits.substring(intUserRoleStart+9,strFoundSubmits.length());
					if(strUserRole.contains(".")) 
					{
						strUserRole=strUserRole.substring(0,strUserRole.indexOf("."));
						
					}
					else
					{
						strUserRole=strUserRole.substring(0,strUserRole.indexOf(",")-1);
						
					}
					if(strUserRole.matches("RSCHWALM"))
					{
					System.out.println(strUserRole);
					}
					if(strUserRole.matches("Include Deleted Parts is true"))
					{
						System.out.println(strUserRole);
						
					}
					if(strUserRole.matches("ABIELAK1"))
					{
						System.out.println(strUserRole);
						
					}
					if(strUserRole.matches("AKRAJINS"))
					{
						System.out.println(strUserRole);
						
					}
					if(strUserRole.isEmpty())
					{
						System.out.println(strUserRole);
						
					}
					blnSave=true;
				}
				else
				{
					strUserRole="";
					blnSave=true;
				}
				}
				if (blnSave)
				{
					if(strFoundSubmits.contains("ctqlog:"))
					{
						strCDSID=strFoundSubmits.substring(strFoundSubmits.indexOf("ctqlog:"),strFoundSubmits.length());
						aryCDSID=strCDSID.split(":");
						strCDSID=aryCDSID[1];
						aryCDSID=strCDSID.split("-");
						strCDSID=aryCDSID[1];
						aryCDSID=strCDSID.split(" ");
						strCDSID=aryCDSID[1];
						aryCDSID=strCDSID.split("_");
						strCDSID=aryCDSID[0];
						
//						strCDSID=strCDSID.substring(9,strCDSID.indexOf(":"));
//						if(strCDSID.contains(";"))
//						{
//							strCDSID=strCDSID.substring(0,strCDSID.indexOf(";"));
//						}
//						if(strCDSID.isEmpty())
//						{
//							strCDSID="";
//						}
//						System.out.println(strCDSID);
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
						intBOMSaveSubmitChangesProductStart=strFoundSubmits.indexOf("BOMSaveSubmitChangesProduct");
						strBOMSaveSubmitChangesProduct = strFoundSubmits.substring(intBOMSaveSubmitChangesProductStart + 31,strFoundSubmits.length());
						if(strBOMSaveSubmitChangesProduct.contains(";"))
						{
							strBOMSaveSubmitChangesProduct=strBOMSaveSubmitChangesProduct.substring(0,strBOMSaveSubmitChangesProduct.indexOf(";"));
						}
						else
						{
							System.out.println(strBOMSaveSubmitChangesProduct);
							aryBOMSaveSubmitChangesProduct=strBOMSaveSubmitChangesProduct.split("\\.");
							strBOMSaveSubmitChangesProduct=aryBOMSaveSubmitChangesProduct[0];
						}
//						if(strBOMSaveSubmitChangesProduct.contains(","))
//						{
//							System.out.println("fix");
//						}
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
										strOutput=strDate + "," + strCDSID + "," + strUserRole + "," + aryBOMSaveSubmitChangesProduct[intArrayPosition] + ",\"" + strFoundSubmits + "\"";
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
								strOutput=strDate + "," + strCDSID + "," + strUserRole + "," + strBOMSaveSubmitChangesProduct   + ",\"" + strFoundSubmits + "\"";
								pwParsedOutput.println(strOutput);
								pwParsedOutput.close();
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
					strOutput=strDate + "," + strCDSID + "," + strUserRole + "," + strBOMSaveSubmitChangesProduct  + ",\"" + strFoundSubmits + "\"";
					pwParsedOutput.println(strOutput);
					pwParsedOutput.close();
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
