package tiho;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class ChoosePanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JComboBox<String> fileName;
	private DefaultComboBoxModel<String> fileNameModel;
	private JLabel fileNameLabel;
	private JButton confirmButton;
	private JButton searchButton;
	private JFileChooser fileChooser;

	private JRadioButton allRadio;
	private JRadioButton someRadio;
	private ButtonGroup allRadioGroup;
	private JPanel radioPanel;

	public ChoosePanel()
	{
		fileName = new JComboBox<String>();
		fileNameLabel = new JLabel("Datei: ");
		confirmButton = new JButton("OK");
		searchButton = new JButton("...");
		fileChooser = new JFileChooser();

		allRadio = new JRadioButton("mit allen Parametern starten");
		someRadio = new JRadioButton("Parameter wählen");
		radioPanel = new JPanel();
		allRadioGroup = new ButtonGroup();
		allRadioGroup.add(allRadio);
		allRadioGroup.add(someRadio);
		allRadio.setSelected(true);

		radioPanel.setLayout(new BorderLayout());
		radioPanel.add(allRadio, BorderLayout.NORTH);
		radioPanel.add(someRadio, BorderLayout.SOUTH);
		radioPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

		fileNameModel = new DefaultComboBoxModel<>();
		fileNameModel.addElement("");
		fileName.setModel(fileNameModel);
		fileName.setSelectedIndex(0);
		fileName.setEditable(true);
		fileName.setPreferredSize(new Dimension(270, 20));

		searchButton.setPreferredSize(new Dimension(22, 19));

		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(fileChooser.showOpenDialog(ChoosePanel.this) == JFileChooser.APPROVE_OPTION)
				{
					fileNameModel.addElement(fileChooser.getSelectedFile().getAbsolutePath());
					fileNameModel.removeElementAt(0);
					fileName.setSelectedIndex(fileNameModel.getSize() - 1);
				}
			}
		});

		confirmButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

			}
		});

		Border innerBorder = BorderFactory.createEtchedBorder();
		Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		setPreferredSize(new Dimension(295, 195));

		setComponentLayout();
	}

	private void setComponentLayout()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 1;

		gc.fill = GridBagConstraints.NONE;

		// FIRST ROW
		gc.gridy = 0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(fileName, gc);

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(fileNameLabel, gc);

		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 2;
		add(searchButton, gc);

		// NEXT ROW
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.gridheight = 2;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(radioPanel, gc);

		// NEXT ROW
		gc.gridy += 2;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.weighty = 2;
		gc.anchor = GridBagConstraints.NORTH;
		add(confirmButton, gc);
	}
}
