package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Io {
	public String RTID ="rtid=";
	public String EID ="eid=";
	public String RID ="rid=";
	
	String dumpLine;
	String relationship="";
	String node="";
	String bridge="";
	
	public void read(String path){
		
		try {
			// Ouverture de dump
			InputStream ipsDump = new FileInputStream(path);
			InputStreamReader ipsrDump = new InputStreamReader(ipsDump);
			BufferedReader brDump = new BufferedReader(ipsrDump);
			
			while ((dumpLine = brDump.readLine()) != null) {
				if(dumpLine.startsWith(RID))
				{
					//write(dumpLine.replaceAll("rid=", "").replaceAll("n1=", "").replaceAll("n2=", "").replaceAll("t=", "").replaceAll("w=", "").replaceAll("\"", "") + "\n","bridge",true);
					write(dumpLine.replaceAll("rid=|n1=|n2=|t=|w=|\"", "")+ "\n","bridge",true);
				}
				else if(dumpLine.startsWith(EID))
				{
					//write(dumpLine.replaceAll("eid=", "").replaceAll("n=", "").replaceAll("t=", "").replaceAll("w=", "").replaceAll("\"", "") + "\n","node",true);
					write(dumpLine.replaceAll("eid=|n=|t=|w=|\"", "")+ "\n","node",true);
				}
				else if(dumpLine.startsWith(RTID))
				{
					//write(dumpLine.replaceAll("rtid=", "").replaceAll("name=", "").replaceAll("info=", "").replaceAll("\"", "")+ "\n","relationship",true);
					write(dumpLine.replaceAll("rtid=|name=|info=|\"", "")+ "\n","relationship",true);

				}
				//if (!(dumpLine.startsWith("//")) && ((dumpLine.startsWith(RTID)) || (dumpLine.startsWith(RID)) || (dumpLine.startsWith(EID)))) write(dumpLine.replaceAll("rid=|n1=|n2=|t=|w=|\"|eid=|n=|rtid=|name=|info=", "")+ "\n","dump",true);
			}
			
			brDump.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void write (String content, String ficName, boolean mode) {
		try {
			FileWriter fw = new FileWriter(ficName, mode);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fichierSortie = new PrintWriter(bw);
			fichierSortie.print(content);
			fichierSortie.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
