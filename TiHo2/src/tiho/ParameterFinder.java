package tiho;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	private List<String> parameters;
	private String[] parameterArray;

	public ParameterFinder(String path)
	{
		getFileWBSheet(path);
		retrieveParametersFromFile();
	}

	private void retrieveParametersFromFile()
	{
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

		sortParameters();
		
		parameterArray = convertToArray(parameters, parameterArray);
	}
	
	private void sortParameters()
	{
		List<String> temp = new ArrayList<String>();
		for(String str: parameters)
		{
			temp.add(str.toLowerCase());
		}
		
		Collections.sort(temp);
		List<String> temp2 = new ArrayList<String>();
		
		for(int i = 0; i < temp.size(); i++)
		{
			for(String par: parameters)
			{
				if(temp.get(i).equals(par.toLowerCase()))
				{
					temp2.add(par);
				}
			}
		}
		
		parameters = temp2;
		
		System.out.println(parameters);
	}

	private String[] convertToArray(List<String> parameterList, String[] parameterArray)
	{
		parameterArray = new String[parameterList.size()];

		for(int i = 0; i < parameterList.size(); i++)
		{
			parameterArray[i] = parameterList.get(i);
		}

		return parameterArray;
	}

	private void getFileWBSheet(String path)
	{
		try
		{
			FileInputStream f = new FileInputStream(path);

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