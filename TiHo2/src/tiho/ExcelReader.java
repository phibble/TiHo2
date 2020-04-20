package tiho;

import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	private String path;
	
	private XSSFSheet sheet;
	private XSSFWorkbook workbook;
	
	private ExcelWriter exWriter;
	
	public ExcelReader(String path)
	{
		this.path = path;
		
		getExcelSheet();
		removeExcelSheets(workbook);
		
		readExcelFile();
	}
	
	private void readExcelFile()
	{
		Row row = null;
		Cell cell = null;
		
		int rowCounter = 0;
		int cellCounter = 0;
		
		Probe probe = new Probe();
		
		for(int rowIterator = 0; rowIterator <= sheet.getLastRowNum(); rowIterator++)
		{
			row = sheet.getRow(rowIterator);
			
			if(isRowEmpty(row))
			{
				continue;
			}
			
			for(int cellIterator = 0; cellIterator <= row.getLastCellNum(); cellIterator++)
			{
				cell = row.getCell(cellIterator);
				
				if(cell == null)
				{
					continue;
				}
				
				if(cell.getCellTypeEnum() == CellType.STRING)
				{
					if(cell.getStringCellValue().trim().toLowerCase().contains("probe") && rowCounter != 0)
					{
						rowCounter = 0;
						cellCounter = 0;
					}
					
					if(rowCounter == 0 && cellCounter == 0)
					{
						probe.setNumber(cell.getStringCellValue());
					} else if(rowCounter == 0 && cellCounter == 1)
					{
						probe.setName(cell.getStringCellValue());
					} else if(cellCounter == 0 && rowCounter != 0)
					{
						writeExcelSheet();
					}
				}
			}
		}
	}
	
	private void writeExcelSheet()
	{
		exWriter = new ExcelWriter();
	}
	
	private void getExcelSheet()
	{
		try
		{
			FileInputStream file = new FileInputStream(path);
			
			workbook = new XSSFWorkbook(file);
			
			sheet = workbook.getSheetAt(0);
		} catch(NotOfficeXmlFileException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Die ausgewählte Datei ist keine Excel Datei!");
		} catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Die Datei konnte nicht geladen werden!");
			System.exit(0);
		}
	}
	
	public void removeExcelSheets(XSSFWorkbook workbook)
	{
		int noSheet = workbook.getNumberOfSheets() - 1;
		for(int i = noSheet; i > 0; i--)
		{
			workbook.removeSheetAt(i);
		}
	}
	
	public boolean isRowEmpty(Row row)
	{
		if(row == null)
		{
			return true;
		}
		for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); ++i)
		{
			Cell cell = row.getCell(i);
			if(cell == null || cell.getCellTypeEnum() == CellType._NONE)
			{
				return true;
			}
		}
		return false;
	}
}
