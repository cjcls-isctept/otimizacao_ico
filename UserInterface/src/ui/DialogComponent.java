package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
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

				//main_matrix.printMatrix();
				//System.out.println("__________________________________________________________");
				//subC_matrix.printMatrix();

				// set visibility of dialog
				d.setVisible(true);

				// dispose();
			}
		});

		topPanel.add(confirmButton, BorderLayout.SOUTH);
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
		createDialog(d);

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
	 * @param d JDialog window
	 * 
	 */
	private void createDialog(JDialog d) {

		d.setLayout(new BorderLayout());

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

		d.add(close, BorderLayout.SOUTH);

		// create a label
		JLabel label = new JLabel("this is a dialog box");

		d.add(label, BorderLayout.CENTER);

		// setsize of dialog
		d.setSize(600, 400);

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
