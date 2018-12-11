package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixAux {
	
	List<String> criteria;
	ArrayList<String> criteria_comb;
	double[][] matrix;
	
	
	public MatrixAux(List<String> criteria) {
		//this.criteria_comb=criteria_comb;
		this.criteria=criteria;
		int n=this.criteria.size();
		this.matrix=new double[n][n];
		fillMiddle();
		
	}
	
	private void fillMiddle() {
		for(int i=0; i<criteria.size();i++) {
			matrix[i][i]=1.0;
		}
	
	}

	public void insertValue(double val, String combin) {
		String crt1 =combin.split(",")[0];
		String crt2 = combin.split(",")[1];
		int i=criteria.indexOf(crt1);
		int j=criteria.indexOf(crt2);
		
		int k=criteria.indexOf(crt2);
		int l=criteria.indexOf(crt1);
		
		//matrix[j][i]=val;
		//matrix[i][j]=1/val;
		
		if(val>0) {
			//System.out.println("this " + i + ","+j+": " + (val+1));
			//System.out.println("this " + j + ","+i+": " + (1/(val+1)));
			matrix[i][j]=1/(val+1);
			matrix[j][i]=val+1;
		}if(val<0) {
			//System.out.println("this " + i + ","+j+": " + -(val-1));
			//System.out.println("this " + j + ","+i+": " + -(1/(val-1)));
			matrix[j][i]=-(1/(val-1));
			matrix[i][j]=-(val-1);
		}else {
			matrix[j][i]=1.0;
			matrix[i][j]=1.0;
		}
		
		
		
		
	}
	
	
	
	public void printMatrix() {
		String s;
		int criteria_q= criteria.size();
		for (int i = 0; i < matrix.length; i++) {
			if(criteria_q==3) {
				System.out.println(criteria.get(i)+" || "+matrix[i][0] + "\t|" + matrix[i][1] + "\t|" + matrix[i][2]);
		
			}if(criteria_q==4) {
				System.out.println(criteria.get(i)+" || "+matrix[i][0] + "\t|" + matrix[i][1] + "\t|" + matrix[i][2]+ "\t|" + matrix[i][3]);
		
			}
		}
	}
	
	public static void main(String[] args) {
		List<String> criteria = Arrays.asList("CustoC","CustoF","CustoO","CustoP");
		MatrixAux m = new MatrixAux(criteria);
		m.insertValue(1, "CustoC,CustoF");
		m.insertValue(3, "CustoC,CustoP");
		m.insertValue(-3, "CustoF,CustoP");
		m.printMatrix(); 
		
	}

	public double[][] getMatrix() {
		
		return matrix;
	}
	

}
