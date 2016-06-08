import java.util.Scanner;

public class Project_Main_Final {

	protected static Scanner in = new Scanner(System.in);
	public static void main(String args []){

		// Asking the User for User Input
		System.out.println("Please enter the max investment amount: ");
		double investment = in.nextDouble();

		// Storage Arrays etc
		String data [] = new FileIO().load("StockData.txt");
		int rows = data.length, cols = data[0].split("\t").length;
		double [][] storageArray = new double [rows][cols];
		double[] stockPrices = new double [cols];
		double volatilities[] = new double[cols];
		double tempStorage[] = new double[rows];

		// Loop through and fill the Arrays
		for(int i = 1; i < rows-1; i++)
			for(int j = 1; j < cols; j++){
				stockPrices[j] = Double.parseDouble(data[rows - 1].split("\t")[j]);
				storageArray[i][j] = Double.parseDouble(data[i].split("\t")[j]);
			}


		for(int i = 0; i < cols; i++){

			for(int j = 0; j < rows;j++)
				tempStorage[j] = storageArray[j][i];

			volatilities[i] = standardDeviation(tempStorage, tempStorage.length);
		}

		double [] finalAmounts = new double[cols];

		for(int i = 0; i < finalAmounts.length;i++)
			finalAmounts[i]= 0;

		double sum = 0, temporary = 0;
		for(int j = 0;j < (investment*.001); j++){
			temporary = sum;
			for(int i = 1; i < cols; i++){
				if(i == 212 && (sum + (stockPrices[i]*11)) < investment){
					sum+=stockPrices[i]*11;
					finalAmounts[i]+=11;
				}
				else if(volatilities[i]< 1.51 && (sum + stockPrices[i]) < investment){
					sum+=stockPrices[i];
					finalAmounts[i]++;
				}
			}
			if(temporary==sum){

				break;
			}
		}

		for(int i = 1;i< finalAmounts.length;i++){
			
			System.out.print((int)finalAmounts[i] + "\t");
		}
		
		System.out.println();
		
		for(int i = 1;i< finalAmounts.length;i++){

			System.out.print((int)finalAmounts[i] + "" + ",");
			
		}


	}

	protected static double standardDeviation(double array [], int number){

		double sum = 0;
		double sq_sum = 0;
		for(int i = 0; i < number; ++i) {
			sum += array[i];
			sq_sum += array[i] * array[i];
		}
		double mean = sum / number;
		double variance = sq_sum / number - mean * mean;
		return Math.sqrt(variance);
	}
}
