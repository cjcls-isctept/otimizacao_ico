package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;

public class DialogComponent extends JFrame {
	JSlider qualPreferes;
	JButton confirmButton;
	int selectedValue;
	// double[][] matrix;
	MatrixAux matrix;
	JPanel topPanel;
	JLabel rdTitle;
	JButton buttonClose;
	JPanel panelLabels;
	JDialog d;

	static final int CONTROLS_Q = 20;
	static final int MIN_VALUE = -8;
	static final int MAX_VALUE = 8;
	static final int INIT_VALUE = 0;
	static final int NUM_QUESTIONS = 3;
	List<String> sub_criteria = Arrays.asList("Custo de capital", "Custo de formação", "Custo de operação",
			"Custo de pessoal");
	List<String> criteria = Arrays.asList("Custo Total", "Risco", "Similaridade");
	ArrayList<JSlider> criteria_sliders = new ArrayList<>();
	ArrayList<JSlider> sub_criteria_sliders = new ArrayList<>();

	public DialogComponent() {
		this.setTitle("What now?");
		createView();
		pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(1340, 400);

	}

	/**
	 * Creates the main window
	 * 
	 */
	private void createView() {
		// Making panels and adding them to window*

		createComponents();

		// criteria components
		MatrixAux main_matrix = new MatrixAux(criteria);
		MatrixAux subC_matrix = new MatrixAux(sub_criteria);

		resultDialog("result here");

		confirmButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < criteria_sliders.size(); i++) {

					main_matrix.insertValue(criteria_sliders.get(i).getValue(), criteria_sliders.get(i).getName());

				}
				for (int i = 0; i < sub_criteria_sliders.size(); i++) {

					subC_matrix.insertValue(sub_criteria_sliders.get(i).getValue(),
							sub_criteria_sliders.get(i).getName());

				}

				// main_matrix.printMatrix();
				// System.out.println("__________________________________________________________");
				// subC_matrix.printMatrix();

				// set visibility of dialog
				d.setVisible(true);

				// dispose();
			}
		});

		
		JLabel instructions = new JLabel("Escolha qual prefere, numa escala de 1 a 9, de entre os pares seguintes:", JLabel.CENTER);
		JPanel instrContainer = new JPanel((new BorderLayout()));
		Font font = instructions.getFont();
		Font boldFont = new Font(font.getFontName(), Font.ITALIC, font.getSize()+5);
		
		instructions.setFont(boldFont);
		instrContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 1, 15));
		instrContainer.add(instructions);
		topPanel.add(instrContainer, BorderLayout.NORTH);
		
		JPanel buttonContainer = new JPanel((new BorderLayout()));
		buttonContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		buttonContainer.add(confirmButton);
		topPanel.add(buttonContainer, BorderLayout.SOUTH);
		topPanel.add(panelLabels);
		this.add(topPanel);
		

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Closed");
				e.getWindow().dispose();
			}
		});
		
	}

	/**
	 * Generates a dialog with the result according to the AHP and preferences
	 * settings
	 * 
	 * @param result the controllers that must be used to optimal results
	 * 
	 */
	private void resultDialog(String result) {

		// result dialog
		d = new JDialog(DialogComponent.this, "dialog Box");
		d.setSize(600, 200);
		createDialog(d, result);

	}

	/**
	 * Creates the main window components
	 * 
	 * 
	 */
	private void createComponents() {

		topPanel = new JPanel(new BorderLayout());
		
		panelLabels = new JPanel();
	
		List<String> criteria_combination = combinations("criteria");
		List<String> sub_criteria_combination = combinations("sub");
		addSlider(panelLabels, criteria_combination, "criteria");
		addSlider(panelLabels, sub_criteria_combination, "sub");
		confirmButton = new JButton("OK");
		
	}

	/**
	 * Generates the dialog windows components
	 * 
	 * @param d      JDialog window
	 * @param result
	 * 
	 */
	private void createDialog(JDialog dialog, String result) {
		
		dialog.setLayout(new BorderLayout(10, 10));
		JPanel containerPanel = new JPanel((new BorderLayout()));
		dialog.add(containerPanel);
		containerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
		String resutl_aux = "11000110101110000000";
		String[] result_data = resutl_aux.split("");

		JPanel tableContainer = new JPanel((new BorderLayout()));
		tableContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JTable table = new JTable(2, CONTROLS_Q);
		tableContainer.add(table);
		for (int i = 0; i < CONTROLS_Q; i++) {
			table.setValueAt("C" + (i + 1), 0, i);

			if (result_data[i].equals("1")) {
				table.setValueAt("X", 1, i);
			}

		}
		containerPanel.add(tableContainer, BorderLayout.CENTER);

		JPanel buttonContainer = new JPanel((new BorderLayout()));
		buttonContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JButton close = new JButton("OK");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * try { Thread.sleep(500); } catch (InterruptedException e1) { // TODO
				 * Auto-generated catch block e1.printStackTrace(); }
				 */
				dispose();
			}
		});
		buttonContainer.add(close);
		containerPanel.add(buttonContainer, BorderLayout.SOUTH);

		// create a label
		JPanel labelContainer = new JPanel((new BorderLayout()));
		labelContainer.setBorder(BorderFactory.createEmptyBorder(0,0 , 0, 0));
		JLabel label = new JLabel("Esta é a configuração ótima dos controladores tendo em conta as prefereências selecionadas:",JLabel.CENTER);
		Font font = label.getFont();
		Font boldFont = new Font(font.getFontName(), Font.ITALIC , font.getSize());
		label.setFont(boldFont);
		labelContainer.add(label);

		containerPanel.add(labelContainer, BorderLayout.NORTH);

		// setsize of dialog

	}

	/**
	 * adds the preference sliders
	 * 
	 * @param option               must be "criteria" or "subcriteria"
	 * @param criteria_combination the pairing of the criteria
	 * @param globalPanel          the panel in which the sliders are inserted
	 **/
	private void addSlider(JPanel globalPanel, List<String> criteria_combination, String option) {
		JPanel slidersPanel = new JPanel(new GridLayout(5, 1));

		for (int i = 0; i < criteria_combination.size(); i++) {
			qualPreferes = new JSlider(MIN_VALUE, MAX_VALUE, INIT_VALUE);
			qualPreferes.setMinorTickSpacing(1);
			qualPreferes.setMajorTickSpacing(9);
			qualPreferes.setPaintTicks(true);
			qualPreferes.setPaintLabels(true);

			Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
			// labelTable.put(9, new JLabel("Neutro"));
			int k = 0;
			for (int j = 0; j > -10; j--) {

				labelTable.put(j + 1, new JLabel(k + ""));
				labelTable.put(k + 1, new JLabel(k + 2 + ""));
				k++;

			}
			labelTable.put(0, new JLabel("1"));
			qualPreferes.setLabelTable(labelTable);
			qualPreferes.setName(criteria_combination.get(i));
			JPanel panelLabels = new JPanel();

			panelLabels.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
			panelLabels.add(new JLabel(criteria_combination.get(i).split(",")[0]));
			panelLabels.add(qualPreferes);
			panelLabels.add(new JLabel(criteria_combination.get(i).split(",")[1]));

			if (option.equals("criteria")) {
				criteria_sliders.add(qualPreferes);
			}
			if (option.equals("sub")) {
				sub_criteria_sliders.add(qualPreferes);
			}

			slidersPanel.add(panelLabels);

		}
		globalPanel.add(slidersPanel);

	}

	/**
	 * Given a option, combines the criteria in pairs without repetition
	 * 
	 * @param option "criteria" or "subcriteria"
	 * @return the list with the combinations
	 */
	private ArrayList<String> combinations(String option) {

		ArrayList<String> criteria_new = new ArrayList<>();
		if (option.equals("criteria")) {
			for (int i = 0; i < criteria.size() - 1; i++) {
				for (int j = i + 1; j < criteria.size(); j++) {
					criteria_new.add(criteria.get(i) + "," + criteria.get(j));
					// System.out.println(criteria.get(i) + "," + criteria.get(j));

				}

			}
		}
		if (option.equals("sub")) {
			for (int i = 0; i < sub_criteria.size(); i++) {
				for (int j = i + 1; j < sub_criteria.size(); j++) {
					criteria_new.add(sub_criteria.get(i) + "," + sub_criteria.get(j));
					// System.out.println(cost_criteria.get(i) + "," + cost_criteria.get(j));

				}
			}
		}
		return criteria_new;

	}

	public static void main(String[] args) {
		new DialogComponent();

	}
}
