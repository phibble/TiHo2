package tiho;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private ChoosePanel choosePanel;

	public MainFrame()
	{
		setFrameSettings();
		
		choosePanel = new ChoosePanel();
		
		setComponentLayout();
	}
	
	private void setFrameSettings()
	{
		setTitle("Dateiauswahl");
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		setSize(360, 130);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setComponentLayout()
	{
		setLayout(new BorderLayout());
		
		add(choosePanel, BorderLayout.CENTER);
	}
}