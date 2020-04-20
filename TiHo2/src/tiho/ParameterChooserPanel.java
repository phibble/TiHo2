package tiho;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ParameterChooserPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private String[] parameters;

	private JButton selectAllButton;
	private JPanel parameterPanel;

	public ParameterChooserPanel(String[] parameters)
	{
		this.parameters = parameters;
		
		parameterPanel = new JPanel();
		
		selectAllButton = new JButton("alle auswählen");
		layOutComponents();
	}

	private void setPanel()
	{
		parameterPanel.setLayout(new GridBagLayout());

		JCheckBox checkBox = null;
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		
		int counter = 0;

		for(int i = 0; i < parameters.length; i++)
		{
			gc.gridx = counter;
			checkBox = new JCheckBox(parameters[i]);
			parameterPanel.add(checkBox, gc);
			if(counter == 0)
				counter = 1;
			else
			{
				gc.gridy++;
				counter = 0;
			}
		}
	}

	private void layOutComponents()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 1;

		gc.fill = GridBagConstraints.NONE;

		// FIRST LINE
		gc.gridy = 0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(selectAllButton, gc);
		
		setPanel();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		
		parameterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		// NEXT LINE
		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		JScrollPane scrollPane = new JScrollPane(parameterPanel);
		scrollPane.setBorder(null);
		add(scrollPane, gc);
	}
}
