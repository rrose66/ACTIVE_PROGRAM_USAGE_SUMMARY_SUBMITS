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

public class SubmitRecords 
{

	public static void main(String[] args) 
	{

		File fileSubmitRecords = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\VERSION02\\TARGET_RECORDS\\SUBMIT_RECORDS.TXT");
		File fileParsedOutput = new File ("C:\\PROJECTS\\DATA\\FEDEBOM\\LEADERSHIP_DECK\\GENERIC_LOG_PROCESSING\\VERSION02\\RESULTS\\PARSED_OUTPUT.TXT");
		File fileOraclecCDSID = new File("C:\\PROJECTS\\DATA\\FEDEBOM\\ORACLE\\ORACLE_CDSID.TXT");
		
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
		
		List<String> linesOracleCDSID = new ArrayList<String>();
		Scanner scOracleCDSID = null;
//		Readable fileOraclecCDSID = null;
		try 
		{
			scOracleCDSID = new Scanner(fileOraclecCDSID);
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		scOracleCDSID.nextLine();
		while (scOracleCDSID.hasNextLine())
		{
			String strOracleCDSIDData = scOracleCDSID.nextLine();
			strOracleCDSIDData=strOracleCDSIDData.replace("\"", "");
			linesOracleCDSID.add(strOracleCDSIDData);
		}
		scOracleCDSID.close();
		
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
		pwParsedOutput.println("DATE,CDSID,ROLE,PRODUCT");
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
			
			if (strFoundSubmits.contains("BOMSaveSubmitChanges"))
			{
				strDate=strFoundSubmits.substring(0,10);
				System.out.println(strDate);
				if(strFoundSubmits.contains("UserRole:"))
				{
					intUserRoleStart=strFoundSubmits.indexOf("UserRole:");
					System.out.println(intUserRoleStart);
					strUserRole=strFoundSubmits.substring(intUserRoleStart+9,strFoundSubmits.length());
					strUserRole=strUserRole.substring(0,strUserRole.indexOf("."));
					System.out.println(strUserRole);
				}
				else
				{
					strUserRole=null;
				}
				if(strFoundSubmits.contains("2019-06-17 03:30:52"))
				{
					System.out.print("complete");
				}
				if(strFoundSubmits.contains("CDSID is"))
				{
					strCDSID=strFoundSubmits.substring(strFoundSubmits.indexOf("CDSID is"),strFoundSubmits.length());
					strCDSID=strCDSID.substring(9,strCDSID.indexOf("."));
					System.out.println(strCDSID);
					blnFoundCDSID=true;
				}
				if (blnFoundCDSID==false)
				{
					if(strFoundSubmits.contains("BOMFBLAppGenericImpl:237"))
					{
						if(strFoundSubmits.contains("BOMFBLAppGenericImpl:237 - "))
						{
							if(strFoundSubmits.contains("ctqlog:87"))
							{
								System.out.print("complete");
								strCDSID=null;
//								strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
								strCDSID=strFoundSubmits.substring(102,strFoundSubmits.length());
								aryCDSID=strCDSID.split("-");
								strCDSID=aryCDSID[1];
								aryCDSID=strCDSID.split("_");
								strCDSID=aryCDSID[0];
//								aryCDSID=strCDSID.split(":");
//								strCDSID=aryCDSID[1];
//								aryCDSID=strCDSID.split(" ");
//								strCDSID=aryCDSID[1].trim();
								blnFoundCDSID=true;
							}
							else
							{
								blnFoundCDSID=true;
								blnSave = false;
							}
						}
						else
						{
							System.out.println("fix");
							if(strFoundSubmits.contains("ctqlog:54"))
							{
								blnSave=false;
								blnFoundCDSID=true;
							}
							else if(strFoundSubmits.contains("ctqlog:87"))
							{
								System.out.print("complete");
								strCDSID=null;
								strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
//								strCDSID=strFoundSubmits.substring(102,strFoundSubmits.length());
								aryCDSID=strCDSID.split("-");
								strCDSID=aryCDSID[0];
								aryCDSID=strCDSID.split("_");
								strCDSID=aryCDSID[0];
								aryCDSID=strCDSID.split(":");
								strCDSID=aryCDSID[1];
								aryCDSID=strCDSID.split(" ");
								strCDSID=aryCDSID[1].trim();
								blnFoundCDSID=true;
							}
							else
							{
								System.out.println("fix");
								blnFoundCDSID=true;
								blnSave=false;
							}
							
						}
					}
				}
				if (blnFoundCDSID==false)
				{
					if(strFoundSubmits.contains("BOMFBLAppImpl:"))
					{
						if(strFoundSubmits.contains("BOMFBLAppImpl: -"))
						{
							System.out.print("complete");
							blnFoundCDSID=true;
							if(strFoundSubmits.contains("ctqlog:54"))
							{
								System.out.println("skip because this is timing only");
								blnFoundCDSID=true;
								blnSave=false;
							}
							else
							{
								System.out.print("complete");
								blnFoundCDSID=true;
							}
						}
						else
						{
							System.out.println("fix");
							if(strFoundSubmits.contains("ctqlog:87"))
							{
								System.out.print("complete");
								strCDSID=null;
//								strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
								strCDSID=strFoundSubmits.substring(102,strFoundSubmits.length());
								aryCDSID=strCDSID.split("-");
								strCDSID=aryCDSID[1];
								aryCDSID=strCDSID.split("_");
								strCDSID=aryCDSID[0];
//								aryCDSID=strCDSID.split(":");
//								strCDSID=aryCDSID[1];
//								aryCDSID=strCDSID.split(" ");
//								strCDSID=aryCDSID[1].trim();
								blnFoundCDSID=true;
							}
						}
					}
				}
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
									strOutput=strDate + "," + strCDSID + "," + strUserRole + "," + aryBOMSaveSubmitChangesProduct[intArrayPosition];
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
							strOutput=strDate + "," + strCDSID + "," + strUserRole + "," + strBOMSaveSubmitChangesProduct;
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
					strOutput=strDate + "," + strCDSID + "," + strUserRole + "," + strBOMSaveSubmitChangesProduct;
					pwParsedOutput.println(strOutput);
					pwParsedOutput.close();
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
//	BOMFBLAppGenericImpl:237 -  and ctqlog:87 the cdsid follow :87
//BOMFBLAppGenericImpl:237 - And ctqlog:54 the cdsid follows ... End: but this is the timing only so skip this
//BOMFBLAppImpl: cdsid follow7s but ctqlog:54 skip since this is timing only
//BOMFBLAppGenericImpl:  cdsid follows but ctqlog:54 skip since this is timing only
//BOMFBLAppGenericImpl:237 -  cdsid follows but ctqlog:54 skip since this is timing only
//switch(strFoundSubmits.contains(""))
//{
//case: strFoundSubmits.contains("CDSID is")
//	strFoundSubmits.contains("CDSID is");
//default: 
//System.out.println("stop here");
//}


//				else if(strFoundSubmits.contains("ctqlog:87"))
//{
//if(strFoundSubmits.contains("BOMFBLAppImpl:1247"))
//{
//	strCDSID=null;
//	strCDSID=strFoundSubmits.substring(102,strFoundSubmits.length());
//	aryCDSID=strCDSID.split("-");
//	strCDSID=aryCDSID[1];
//	aryCDSID=strCDSID.split("_");
//	strCDSID=aryCDSID[0];
//}
//else if(strFoundSubmits.contains("BOMFBLAppImpl"))
//{
//		strCDSID=null;
//		strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
//		aryCDSID=strCDSID.split("-");
//		strCDSID=aryCDSID[0];
//		aryCDSID=strCDSID.split("_");
//		strCDSID=aryCDSID[0];
//		aryCDSID=strCDSID.split(":");
//		strCDSID=aryCDSID[1];
//		aryCDSID=strCDSID.split(" ");
//		strCDSID=aryCDSID[1].trim();
//}
//else
//{
//	strCDSID=null;
//	strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
//	aryCDSID=strCDSID.split("-");
//	strCDSID=aryCDSID[0];
//	aryCDSID=strCDSID.split("_");
//	strCDSID=aryCDSID[0];
//	aryCDSID=strCDSID.split(":");
//	strCDSID=aryCDSID[1];
//	aryCDSID=strCDSID.split(" ");
//	strCDSID=aryCDSID[1].trim();
//	
//}
//}	

//else if(strFoundSubmits.contains("BOMFBLAppImpl"))
//{
//	strCDSID=null;
//	strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
//	aryCDSID=strCDSID.split("-");
//	strCDSID=aryCDSID[0];
//	aryCDSID=strCDSID.split("_");
//	strCDSID=aryCDSID[0];
//	aryCDSID=strCDSID.split(":");
//	strCDSID=aryCDSID[1];
//	aryCDSID=strCDSID.split(" ");
//	strCDSID=aryCDSID[1].trim();
//	
////		strCDSID=null;
////		strCDSID=strFoundSubmits.substring(102,strFoundSubmits.length());
////		aryCDSID=strCDSID.split("-");
////		strCDSID=aryCDSID[0];
////		aryCDSID=strCDSID.split("_");
////		strCDSID=aryCDSID[0];
////		aryCDSID=strCDSID.split(":");
////		strCDSID=aryCDSID[1];
////		aryCDSID=strCDSID.split(" ");
////		strCDSID=aryCDSID[1].trim();
//}
//	
//else if(strFoundSubmits.contains("BOMFBLAppGenericImpl"))
//{
//	strCDSID=null;
//	strCDSID=strFoundSubmits.substring(27,strFoundSubmits.length());
//	aryCDSID=strCDSID.split("-");
//	strCDSID=aryCDSID[0];
//	aryCDSID=strCDSID.split("_");
//	strCDSID=aryCDSID[0];
//	aryCDSID=strCDSID.split(":");
//	strCDSID=aryCDSID[1];
//	aryCDSID=strCDSID.split(" ");
//	strCDSID=aryCDSID[1].trim();
//	
//}
	}
}	