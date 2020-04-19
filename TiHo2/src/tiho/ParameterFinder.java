package tiho;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParameterFinder
{
	private FileInputStream f;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<String> parameters;
	private String[] parameterArray;
	
	public ParameterFinder(String path)
	{
		getFileWBSheet(path);
		
		parameters = new ArrayList<String>();
		
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		while(rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			Cell cell = row.getCell(0);
			
			if(cell.getCellTypeEnum() == CellType.STRING)
			{
				String cellValue = cell.getStringCellValue();
				if(!(cellValue.toLowerCase().contains("probe")))
				{
					if(!(parameters.contains(cellValue.trim())))
					{
						parameters.add(cellValue.trim());
					}
				}
			}
		}
		
		parameterArray = convertToArray(parameters, parameterArray);
	}
	
	private String[] convertToArray(List<String> parameterList, String[] parameterArray)
	{
		parameterArray = new String[parameterList.size()];
		parameterArray = (String[]) parameterList.toArray();
		
		return parameterArray;
	}
	
	private void getFileWBSheet(String path)
	{
		try
		{
			f = new FileInputStream(path);
			
			workbook = new XSSFWorkbook(f);
			
			sheet = workbook.getSheetAt(0);
			
		} catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Die Datei konnte nicht geladen werden!");
			e.printStackTrace();
		}
	}
	
	public String[] getParameters()
	{
		return parameterArray;
	}
}