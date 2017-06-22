package bz.sunlight.excel.util;





import java.util.List;

public interface RowReader
{
    public void getRows(int sheetIndex, String sheetName, int curRow, List<String> rowlist);
}
