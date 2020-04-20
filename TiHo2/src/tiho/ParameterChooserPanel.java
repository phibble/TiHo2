package tiho;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private JCheckBox[] boxes;

	public ParameterChooserPanel(String[] parameters)
	{
		this.parameters = parameters;

		parameterPanel = new JPanel();

		selectAllButton = new JButton("alle auswählen");

		boxes = new JCheckBox[parameters.length];

		layOutComponents();

		selectAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				for(JCheckBox box : boxes)
				{
					box.setSelected(true);
				}
			}
		});
	}

	private void setPanel()
	{
		parameterPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;

		int counter = 0;

		for(int i = 0; i < parameters.length; i++)
		{
			gc.gridx = counter;
			boxes[i] = new JCheckBox(parameters[i]);
			parameterPanel.add(boxes[i], gc);
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
		gc.weighty = 0.1;

		gc.fill = GridBagConstraints.NONE;

		// FIRST LINE
		gc.gridy = 0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(selectAllButton, gc);

		setPanel();

		gc.fill = GridBagConstraints.BOTH;

		parameterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		// NEXT LINE
		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		JScrollPane scrollPane = new JScrollPane(parameterPanel);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(4);
		System.out.println(scrollPane.getVerticalScrollBar().getUnitIncrement());
		add(scrollPane, gc);

		// NEXT LINE
		JPanel panel = new JPanel();
		gc.gridy++;
		gc.weighty = (8 - Math.floor(parameters.length / 2));
		add(panel, gc);
	}
}
