package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class SosGame {

	static Scanner scan = new Scanner(System.in);
	static String sosMatrix[][];
	static int myScore;
	static int pcScore;

	public static void main(String[] args) {

		int tableSize, row, column;
		String word;
		Boolean checkEmpty;
		do {
			System.out.print("Tablonun boyutunu giriniz : ");
			tableSize = scan.nextInt();
			if (tableSize < 3 || tableSize > 7) {
				System.out.println("L�tfen 3x3 ile 7x7 aras�nda bi boyut giriniz. ��lem Tekrar yap�l�yor.");
			}
		} while (tableSize < 3 || tableSize > 7);

		sosMatrix = new String[tableSize][tableSize];

		for (int i = 0; i < sosMatrix.length; i++) {
			for (int j = 0; j < sosMatrix[0].length; j++) {
				sosMatrix[i][j] = "-";
			}
		}

		printTable(sosMatrix);

		while (!isGameOver()) {
			do {
				System.out.print("Konum i�in sat�r� giriniz. : ");
				row = scanLocation();
				System.out.print("Konum i�in s�tunu giriniz. : ");
				column = scanLocation();
				System.out.print("S veya O harfi giriniz. : ");
				word = scanWord();
				checkEmpty = checkEmpty(row, column);
				if (checkEmpty) {
					sosMatrix[row][column] = word;
					printTable(sosMatrix);
					if (checkTable(row, column, word, 0)) {
						System.err.println("SOS oldu. Ben bir puan ald�m.");
						System.err.println("Benim Puan�m : " + myScore);
						System.err.println("Bilgisayar Puan� : " + pcScore);
					}
				}
			} while (!checkEmpty);
			do {
				if (!isGameOver()) {
					System.out.print("Bilgisayar Oynuyor");
					row = new Random().nextInt(sosMatrix.length);
					column = new Random().nextInt(sosMatrix[0].length);
					word = new Random().nextInt(2) == 0 ? "S" : "O";
					checkEmpty = checkEmpty(row, column);
					if (checkEmpty) {
						System.out.println(" Bilgisayar " + row + " " + column + " konumuna " + word + " karakterini yerle�tirdi.");
						sosMatrix[row][column] = word;
						printTable(sosMatrix);
						if (checkTable(row, column, word, 1)) {
							System.err.println("SOS oldu. Bilgisayar bir puan ald�.");
							System.err.println("Benim Puan�m : " + myScore);
							System.err.println("Bilgisayar Puan� : " + pcScore);
						}
					}
				}
			} while (!checkEmpty);
		}

		printTable(sosMatrix);

		System.err.println("Benim Puan�m : " + myScore);
		System.err.println("Bilgisayar Puan� : " + pcScore);

		System.err.println(myScore > pcScore ? "Kazanan Benim" : "Kazanan Bilgisayar");
	}

	public static boolean checkTable(int row, int column, String value, int order) {
		boolean successful = false;
		if (value == "O") {
			if (row - 1 >= 0 && column + 1 < sosMatrix.length) {
				if (((sosMatrix[row - 1][column]) + (sosMatrix[row][column]) + (sosMatrix[row + 1][column])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
			}

			if (column - 1 >= 0 && column + 1 < sosMatrix.length) {
				if (((sosMatrix[row][column - 1]) + (sosMatrix[row][column]) + (sosMatrix[row][column + 1])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
			}
			if ((column - 1 >= 0 && row - 1 >= 0) && (column + 1 < sosMatrix.length && row + 1 < sosMatrix.length)) {
				if (((sosMatrix[row - 1][column - 1]) + (sosMatrix[row][column]) + (sosMatrix[row + 1][column + 1])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
			}

			if ((row - 1 >= 0 && column + 1 < sosMatrix.length) && (column - 1 >= 0 && row + 1 < sosMatrix.length)) {
				if (((sosMatrix[row - 1][column + 1]) + (sosMatrix[row][column]) + (sosMatrix[row + 1][column - 1])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
			}
		} else {
			if (row - 2 >= 0) {
				if (((sosMatrix[row - 2][column]) + (sosMatrix[row - 1][column]) + (sosMatrix[row][column])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
				if (column - 2 >= 0) {
					if (((sosMatrix[row - 2][column - 2]) + (sosMatrix[row - 1][column - 1]) + (sosMatrix[row][column])).equals("SOS")) {
						if (order == 0) {
							myScore++;
							successful = true;
						} else {
							pcScore++;
							successful = true;
						}
					}
				}
			}
			if (row + 2 < sosMatrix.length) {
				if (((sosMatrix[row][column]) + (sosMatrix[row + 1][column]) + (sosMatrix[row + 2][column])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
				if (column + 2 < sosMatrix.length) {
					if (((sosMatrix[row][column]) + (sosMatrix[row + 1][column + 1]) + (sosMatrix[row + 2][column + 2])).equals("SOS")) {
						if (order == 0) {
							myScore++;
							successful = true;
						} else {
							pcScore++;
							successful = true;
						}
					}
				}
			}
			if (column - 2 >= 0) {
				if (((sosMatrix[row][column - 2]) + (sosMatrix[row][column - 1]) + (sosMatrix[row][column])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
				if (row + 2 < sosMatrix.length) {
					if (((sosMatrix[row][column]) + (sosMatrix[row + 1][column - 1]) + (sosMatrix[row + 2][column - 2])).equals("SOS")) {
						if (order == 0) {
							myScore++;
							successful = true;
						} else {
							pcScore++;
							successful = true;
						}
					}
				}
			}
			if (column + 2 < sosMatrix.length) {
				if (((sosMatrix[row][column]) + (sosMatrix[row][column + 1]) + (sosMatrix[row][column + 2])).equals("SOS")) {
					if (order == 0) {
						myScore++;
						successful = true;
					} else {
						pcScore++;
						successful = true;
					}
				}
				if (row - 2 >= 0) {
					if (((sosMatrix[row][column]) + (sosMatrix[row - 1][column + 1]) + (sosMatrix[row - 2][column + 2])).equals("SOS")) {
						if (order == 0) {
							myScore++;
							successful = true;
						} else {
							pcScore++;
							successful = true;
						}
					}
				}
			}
		}
		return successful;
	}

	public static boolean isGameOver() {
		for (int i = 0; i < sosMatrix.length; i++) {
			for (int j = 0; j < sosMatrix[0].length; j++) {
				if (sosMatrix[i][j] == "-") {
					return false;
				}
			}
		}
		System.out.println("Oyun Bitti.");
		return true;
	}

	public static boolean checkEmpty(int row, int column) {
		if (row < 0 || sosMatrix.length <= row || sosMatrix[0].length <= column || column < 0) {
			System.out.println("Girdi�iniz de�erler tabloyu a�t�. Girdi 0 ile " + String.valueOf(sosMatrix.length -1) + " aras�nda olmal�d�r. Tekrar giriniz.");
			return false;
		} else {
			if (sosMatrix[row][column] != "-") {
				System.out.println("Girdi�iniz konum doludur. L�tfen bo� alan� se�iniz. ��lem tekrar yap�l�yor.");
				return false;
			}
		}
		return true;
	}

	public static void printTable(String[][] sosMatrix2) {
		System.out.println();
		for (int i = 0; i < sosMatrix2.length; i++) {
			for (int j = 0; j < sosMatrix2[0].length; j++) {
				System.out.print(sosMatrix2[i][j]);
				System.out.print("   ");
			}
			System.out.println("");
		}
	}

	public static int scanLocation() {
		return scan.nextInt();
	}

	public static String scanWord() {
		return scan.next();
	}

}
