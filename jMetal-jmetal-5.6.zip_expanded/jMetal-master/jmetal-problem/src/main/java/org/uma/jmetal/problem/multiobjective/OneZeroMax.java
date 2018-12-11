package org.uma.jmetal.problem.multiobjective;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.JMetalException;

import java.util.BitSet;

/**
 * Class representing problem OneZeroMax. The problem consist of maximizing the
 * number of '1's and '0's in a binary string.
 */
@SuppressWarnings("serial")
public class OneZeroMax extends AbstractBinaryProblem {
  private int bits ;
  private int[] impact = new int[10];
  private double[][] controls = new double[20][10];
  private int loss=0;
  private int[] cost = new int[20];
  
  /** Constructor */
  public OneZeroMax() throws JMetalException {
    this(20);
    
    //tabela 3
    impact[0]=1490;
    impact[1]=1517;
    impact[2]=316;
    impact[3]=3367;
    impact[4]=9397;
    impact[5]=2429;
    impact[6]=1129;
    impact[7]=461;
    impact[8]=2794;
    impact[9]=3643;
    
    //Valor inicial da perda L (1)
    for (int i=0;i<impact.length;i++) {
    	loss+=impact[i];
    }
    
    //tabela 2
    cost[0]=60;
    cost[1]=60;
    cost[2]=70;
    cost[3]=60;
    cost[4]=50;
    cost[5]=50;
    cost[6]=40;
    cost[7]=60;
    cost[8]=50;
    cost[9]=40;
    cost[10]=80;
    cost[11]=50;
    cost[12]=50;
    cost[13]=60;
    cost[14]=50;
    cost[15]=50;
    cost[16]=80;
    cost[17]=50;
    cost[18]=70;
    cost[19]=80;
    
    
    
    //tabela 1
    controls[0][0]=0;
    controls[0][1]=0.5;
    controls[0][2]=0.1;
    controls[0][3]=0;
    controls[0][4]=0.5;
    controls[0][5]=0.5;
    controls[0][6]=0.5;
    controls[0][7]=0.1;
    controls[0][8]=0.1;
    controls[0][9]=0;
    
    controls[1][0]=0.5;
    controls[1][1]=0.5;
    controls[1][2]=0.1;
    controls[1][3]=0;
    controls[1][4]=0;
    controls[1][5]=0;
    controls[1][6]=0;
    controls[1][7]=0.1;
    controls[1][8]=0.1;
    controls[1][9]=0.5;
    
    controls[2][0]=0.5;
    controls[2][1]=0.1;
    controls[2][2]=0;
    controls[2][3]=0;
    controls[2][4]=0;
    controls[2][5]=0;
    controls[2][6]=0;
    controls[2][7]=0.1;
    controls[2][8]=0.1;
    controls[2][9]=0.5;
    
    controls[3][0]=0.9;
    controls[3][1]=0.1;
    controls[3][2]=0;
    controls[3][3]=0;
    controls[3][4]=0;
    controls[3][5]=0;
    controls[3][6]=0;
    controls[3][7]=0;
    controls[3][8]=0;
    controls[3][9]=0.5;
    
    controls[4][0]=0;
    controls[4][1]=0.9;
    controls[4][2]=0;
    controls[4][3]=0;
    controls[4][4]=0;
    controls[4][5]=0;
    controls[4][6]=0;
    controls[4][7]=0;
    controls[4][8]=0.9;
    controls[4][9]=0;
    
    controls[5][0]=0.5;
    controls[5][1]=0.9;
    controls[5][2]=0.5;
    controls[5][3]=0;
    controls[5][4]=0;
    controls[5][5]=0.5;
    controls[5][6]=0;
    controls[5][7]=0.5;
    controls[5][8]=0.5;
    controls[5][9]=0.5;
    
    controls[6][0]=0.5;
    controls[6][1]=0.9;
    controls[6][2]=0;
    controls[6][3]=0;
    controls[6][4]=0;
    controls[6][5]=0;
    controls[6][6]=0;
    controls[6][7]=0;
    controls[6][8]=0;
    controls[6][9]=0;
    
    controls[7][0]=0.9;
    controls[7][1]=0.9;
    controls[7][2]=0;
    controls[7][3]=0;
    controls[7][4]=0;
    controls[7][5]=0;
    controls[7][6]=0;
    controls[7][7]=0;
    controls[7][8]=0;
    controls[7][9]=0;
    
    controls[8][0]=0.5;
    controls[8][1]=0;
    controls[8][2]=0.5;
    controls[8][3]=0;
    controls[8][4]=0;
    controls[8][5]=0;
    controls[8][6]=0;
    controls[8][7]=0;
    controls[8][8]=0;
    controls[8][9]=0;
    
    controls[9][0]=0;
    controls[9][1]=0;
    controls[9][2]=0;
    controls[9][3]=0;
    controls[9][4]=0;
    controls[9][5]=0;
    controls[9][6]=0;
    controls[9][7]=0.1;
    controls[9][8]=0;
    controls[9][9]=0;
    
    controls[10][0]=0.5;
    controls[10][1]=0.1;
    controls[10][2]=0.5;
    controls[10][3]=0;
    controls[10][4]=0;
    controls[10][5]=0;
    controls[10][6]=0;
    controls[10][7]=0;
    controls[10][8]=0;
    controls[10][9]=0;
    
    controls[11][0]=0.5;
    controls[11][1]=0.1;
    controls[11][2]=0.5;
    controls[11][3]=0;
    controls[11][4]=0;
    controls[11][5]=0;
    controls[11][6]=0;
    controls[11][7]=0;
    controls[11][8]=0;
    controls[11][9]=0;
    
    controls[12][0]=0.5;
    controls[12][1]=0;
    controls[12][2]=0;
    controls[12][3]=0;
    controls[12][4]=0;
    controls[12][5]=0;
    controls[12][6]=0;
    controls[12][7]=0;
    controls[12][8]=0;
    controls[12][9]=0;
    
    controls[13][0]=0;
    controls[13][1]=0.5;
    controls[13][2]=0;
    controls[13][3]=0;
    controls[13][4]=0;
    controls[13][5]=0.5;
    controls[13][6]=0.5;
    controls[13][7]=0;
    controls[13][8]=0.5;
    controls[13][9]=0;
    
    controls[14][0]=0.5;
    controls[14][1]=0;
    controls[14][2]=0;
    controls[14][3]=0;
    controls[14][4]=0;
    controls[14][5]=0;
    controls[14][6]=0;
    controls[14][7]=0;
    controls[14][8]=0;
    controls[14][9]=0;
    
    controls[15][0]=0.1;
    controls[15][1]=0.5;
    controls[15][2]=0;
    controls[15][3]=0;
    controls[15][4]=0.5;
    controls[15][5]=0.5;
    controls[15][6]=0;
    controls[15][7]=0.1;
    controls[15][8]=0.5;
    controls[15][9]=0;
    
    controls[16][0]=0.1;
    controls[16][1]=0.9;
    controls[16][2]=0;
    controls[16][3]=0;
    controls[16][4]=0.5;
    controls[16][5]=0.5;
    controls[16][6]=0.9;
    controls[16][7]=0.5;
    controls[16][8]=0.9;
    controls[16][9]=0;
    
    controls[17][0]=0.9;
    controls[17][1]=0;
    controls[17][2]=0;
    controls[17][3]=0;
    controls[17][4]=0;
    controls[17][5]=0;
    controls[17][6]=0;
    controls[17][7]=0;
    controls[17][8]=0;
    controls[17][9]=0;
    
    controls[18][0]=0.9;
    controls[18][1]=0.1;
    controls[18][2]=0.5;
    controls[18][3]=0;
    controls[18][4]=0;
    controls[18][5]=0;
    controls[18][6]=0.1;
    controls[18][7]=0.5;
    controls[18][8]=0.1;
    controls[18][9]=0.5;
    
    controls[19][0]=0.9;
    controls[19][1]=0.1;
    controls[19][2]=0;
    controls[19][3]=0;
    controls[19][4]=0;
    controls[19][5]=0;
    controls[19][6]=0;
    controls[19][7]=0;
    controls[19][8]=0;
    controls[19][9]=0.9;
	
  }

  /** Constructor */
  public OneZeroMax(Integer numberOfBits) throws JMetalException {
    setNumberOfVariables(1);
    setNumberOfObjectives(3);
    setName("OneZeroMax");

    bits = numberOfBits ;
  }

  @Override
  protected int getBitsPerVariable(int index) {
  	if (index != 0) {
  		throw new JMetalException("Problem OneZeroMax has only a variable. Index = " + index) ;
  	}
  	return bits ;
  }

  @Override
  public BinarySolution createSolution() {
    return new DefaultBinarySolution(this) ;
  }

  /** Evaluate() method */
  @Override
    public void evaluate(BinarySolution solution) {
	  
	  
	

    BitSet bitset = solution.getVariableValue(0) ;
    
    double px = 0;
    int solution_cost =0;
    
    //(2)
    for (int i = 0; i < 20; i++) {
      if (bitset.get(i)) {
    	  for(int k=0;k<controls[i].length;k++) {
    		  px+=impact[k]*controls[i][k];
    	  }
    	  //(4)
    	  solution_cost+=cost[i];
      } else {
       
      }
    }
    
    //(3)
    double rx = loss-px;
    
    //(6)
    double denominator = 2*Math.pow(20, 2);
    double fraction = 1/denominator;
    double[][] qls = new double[controls.length][controls.length];


    
    for (int l=0;l<controls.length;l++) {
    	for (int s=l+1;s<controls.length;s++ ) {
    		
    		double aux=0;
    		
    		for (int t=0; t<controls[l].length-1;t++) {
    			for (int k=t+1;k<controls[k].length-1;k++) {
        			aux+=(controls[l][t]- controls[l][k])*(controls[s][t] - controls[s][k]);
    			}
    		}
    		aux = fraction*aux;
    		qls[l][s]=aux;
    	}
    }
    
    
    //(7)
    
    double similarity = 0;
    
    for (int l=0; l<controls.length;l++) {
    	for (int s=l+1;s<controls.length;s++) {
    		if (bitset.get(l)) {
    			if(bitset.get(s)) {
    				similarity+=qls[l][s];
    			}
    		}
    	}
    }
    
    similarity=Math.sqrt(similarity);
    
    // OneZeroMax is a maximization problem: multiply by -1 to minimize
    solution.setObjective(0, rx);
    solution.setObjective(1, solution_cost);
    solution.setObjective(2, similarity);
    
 
    
    // Qls= Iterar por todos os sets de controlos 2 a 2, e na formula iterar por pares de vulnerabilidades, o primeiro ( ) significa por exemplo controlo 1 vulnerabildiade x1 e x2 e o segundo ( ) significa controlo 2 vulnerabilidade x1 e x2
    
    
  }
}
