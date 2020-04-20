package tiho;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ParameterFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private String[] parameters;
	private ParameterChooserPanel parameterChooser;
	
	private JPanel mainPanel;
	private JButton confirmButton;

	public ParameterFrame(String[] parameters)
	{
		this.parameters = parameters;
		parameterChooser = new ParameterChooserPanel(this.parameters);
		confirmButton = new JButton("OK");
		
		mainPanel = new JPanel();
		Border innerBorder = BorderFactory.createEtchedBorder();
		Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		mainPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setFrame();
		setComponentLayout();
	}
	
	private void setComponentLayout()
	{
		mainPanel.setLayout(new GridBagLayout());
		
//		mainPanel.add(parameterChooser, BorderLayout.NORTH);
//		mainPanel.add(confirmButton, BorderLayout.SOUTH);
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.fill = GridBagConstraints.NONE;
		
		// FIRST LINE
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(parameterChooser, gc);
		
		// NEXT LINE
		gc.gridy++;
		
		gc.weighty = 0.1;
		
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(confirmButton, gc);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}

	private void setFrame()
	{
		setSize(200, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
