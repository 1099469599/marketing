package bz.sunlight.excel.util;





import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import net.sf.json.JSONObject;
import bz.sunlight.util.GsonUtil;

public class SimpleXlsxReader extends AbstractXlsxReader
{
    public SimpleXlsxReader(InputStream is)
    {
        super(is);
    }

    public SimpleXlsxReader(InputStream is, RowReader reader)
    {
        super(is, reader);
    }

    public SimpleXlsxReader(InputStream is, RowReader reader, boolean callBack)
    {
        super(is, reader, callBack);
    }

    public SimpleXlsxReader(File sourceFile)
    {
        super(sourceFile);
    }

    public SimpleXlsxReader(File sourceFile, RowReader reader)
    {
        super(sourceFile, reader);
    }

    public SimpleXlsxReader(File sourceFile, RowReader reader, boolean callBack)
    {
        super(sourceFile, reader, callBack);
    }

    @SuppressWarnings({"unused", "resource"})
    public static void main(String[] args) throws Exception
    {
        File xlsxFile = new File("C:/Users/Administrator/Desktop/excelad.xlsx");
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
//                StringBuffer bf = new StringBuffer();
                for(String str : list)
                {
//                    bf.append(str + ",");
//                	System.out.println(str);
                	JSONObject fromJson = GsonUtil.fromJson(str, JSONObject.class);
                	System.out.println(fromJson.get("AD"));
                }
//                System.out.println(bf.toString());
            }
        }
        else
        {
            System.out.println(excelData);
        }

    }

}
