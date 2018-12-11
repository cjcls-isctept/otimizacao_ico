
public class main {

	public static void main(String[] args) {
		double[][] importanceMatrix = new double[3][3];
		
		importanceMatrix[0][0] = 1;
		importanceMatrix[0][1] = 2;
		importanceMatrix[0][2] = 0.25;
		importanceMatrix[1][0] = 0.5;
		importanceMatrix[1][1] = 1;
		importanceMatrix[1][2] = 0.142;
		importanceMatrix[2][0] = 4;
		importanceMatrix[2][1] = 7;
		importanceMatrix[2][2] = 1;
		AHP ahp = new AHP();
		ahp.AHPRun(importanceMatrix);
		

	}

}
