
public class AHP {

	public void AHPRun(double[][] importanceMatrix) {

		int matrixLength = importanceMatrix.length;

		// 2
		double[] columnSum = new double[matrixLength];

		// 1
		for (int i = 0; i < importanceMatrix.length; i++) {
			double sum = 0;
			for (int m = 0; m < importanceMatrix[i].length; m++) {

				sum += importanceMatrix[m][i];

			}
			columnSum[i] = sum;
		}

		double[][] normalizedImportanceMatrix = new double[matrixLength][matrixLength];
		// 3
		for (int i = 0; i < normalizedImportanceMatrix.length; i++) {
			for (int m = 0; m < normalizedImportanceMatrix[i].length; m++) {

				normalizedImportanceMatrix[m][i] = importanceMatrix[m][i] / columnSum[i];

			}
		}

		// 4
		double[] lineSumNormalized = new double[matrixLength];

		for (int i = 0; i < normalizedImportanceMatrix.length; i++) {
			double lineSum = 0;
			for (int m = 0; m < normalizedImportanceMatrix[i].length; m++) {

				lineSum += normalizedImportanceMatrix[i][m];

			}
			lineSumNormalized[i] = lineSum;
		}

		// 5
		double fourVectorSum = 0;

		for (int i = 0; i < lineSumNormalized.length; i++) {
			fourVectorSum += lineSumNormalized[i];
		}

		// 6
		double[] normalizedPriorityVector = new double[matrixLength];
		for (int i = 0; i < lineSumNormalized.length; i++) {
			normalizedPriorityVector[i] = lineSumNormalized[i] / fourVectorSum;

		}

		// 7

		double lambda = 0;
		for (int i = 0; i < normalizedPriorityVector.length; i++) {
			lambda += normalizedPriorityVector[i] * columnSum[i];
		}

		double lambdaMinusN = lambda - fourVectorSum;
		double nMinusOne = matrixLength - 1;
		double Ic = lambdaMinusN / nMinusOne;
		double Ir = 0.58;
		double Rc = Ic / Ir;
		System.out.println(Rc);

	}

}
