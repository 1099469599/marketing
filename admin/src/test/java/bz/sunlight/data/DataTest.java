package bz.sunlight.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import bz.sunlight.excel.util.AbstractXlsxReader;
import bz.sunlight.excel.util.RowReader;
import bz.sunlight.excel.util.SimpleRowReader;
import bz.sunlight.excel.util.SimpleXlsxReader;

public class DataTest {

	private String excelURL="C:/Users/Administrator/Desktop/excel.xlsx";
	
	@Test
	public void importData() throws FileNotFoundException{
		File xlsxFile = new File(excelURL);
        RowReader rowReader = new SimpleRowReader();
        InputStream in = new FileInputStream(xlsxFile);
        // new ZipPackage(in, PackageAccess.READ_WRITE);
        // OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ)
        // AbstractXlsxReader reader = new SimpleXlsxReader(xlsxFile, rowReader, true);
        AbstractXlsxReader reader = new SimpleXlsxReader(xlsxFile, null, false);
        reader.process();
        List<List<String>> excelData = reader.getExcelData();
        if(excelData != null)
        {
            for(List<String> list : excelData)
            {
                StringBuffer bf = new StringBuffer();
                for(String str : list)
                {
                    bf.append(str + ",");
                }
                System.out.println(bf.toString());
            }
        }
        else
        {
            System.out.println(excelData);
        }
	}
}
