package ui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ControllerTableModel extends AbstractTableModel {

	// aqui transformei em coluna cada propriedade de Funcionario
	// que eu quero que seja exibida na tabela
	private String colunas[];
	private String dados;

	static final int CONTROLS_Q = 20;

	public ControllerTableModel(String dados) {
		this.dados = dados;

		for (int i = 0; i < CONTROLS_Q; i++) {
			colunas[i] = "C" + (i + 1);
		}
	}

	// retorna se a c�lula � edit�vel ou n�o
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	// retorna o total de itens(que virar�o linhas) da nossa lista
	@Override
	public int getRowCount() {
		return 2;
	}

	// retorna o total de colunas da tabela
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	// retorna o nome da coluna de acordo com seu indice
	@Override
	public String getColumnName(int indice) {
		return colunas[indice];
	}

	// determina o tipo de dado da coluna conforme seu indice
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return String.class;

	}

	// preenche cada c�lula da tabela
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		/*Funcionario funcionario = this.funcionarios.get(rowIndex);

		switch (columnIndex) {
		case COLUNA_NOME:
			return funcionario.getNome();
		case COLUNA_IDADE:
			return funcionario.getIdade();
		case COLUNA_MATRICULA:
			return funcionario.getMatricula();
		case COLUNA_ADMITIDO:
			return funcionario.isAdmitido();
		}*/
		return null;
	}

	// altera o valor do objeto de acordo com a c�lula editada
	// e notifica a altera��o da tabela, para que ela seja atualizada na tela
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// o argumento recebido pelo m�todo � do tipo Object
		// mas como nossa tabela � de funcion�rios, � seguro(e at� recomend�vel) fazer o
		// cast de suas propriedades
		/*Funcionario funcionario = this.funcionarios.get(rowIndex);
		// de acordo com a coluna, ele preenche a c�lula com o valor
		// respectivo do objeto de mesmo indice na lista
		switch (columnIndex) {
		case COLUNA_NOME:
			funcionario.setNome(String.valueOf(aValue));
			break;
		case COLUNA_IDADE:
			funcionario.setIdade((int) aValue);
			break;
		case COLUNA_MATRICULA:
			funcionario.setMatricula((int) aValue);
			break;
		case COLUNA_ADMITIDO:
			funcionario.setAdmitido((boolean) aValue);
		}*/
		// este m�todo � que notifica a tabela que houve altera��o de dados
		//fireTableDataChanged();
	}

}
