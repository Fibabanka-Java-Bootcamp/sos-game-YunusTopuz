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
				System.out.println("Lütfen 3x3 ile 7x7 arasýnda bi boyut giriniz. Ýþlem Tekrar yapýlýyor.");
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
				System.out.print("Konum için satýrý giriniz. : ");
				row = scanLocation();
				System.out.print("Konum için sütunu giriniz. : ");
				column = scanLocation();
				System.out.print("S veya O harfi giriniz. : ");
				word = scanWord();
				checkEmpty = checkEmpty(row, column);
				if (checkEmpty) {
					sosMatrix[row][column] = word;
					printTable(sosMatrix);
					if (checkTable(row, column, word, 0)) {
						System.err.println("SOS oldu. Ben bir puan aldým.");
						System.err.println("Benim Puaným : " + myScore);
						System.err.println("Bilgisayar Puaný : " + pcScore);
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
						System.out.println(" Bilgisayar " + row + " " + column + " konumuna " + word + " karakterini yerleþtirdi.");
						sosMatrix[row][column] = word;
						printTable(sosMatrix);
						if (checkTable(row, column, word, 1)) {
							System.err.println("SOS oldu. Bilgisayar bir puan aldý.");
							System.err.println("Benim Puaným : " + myScore);
							System.err.println("Bilgisayar Puaný : " + pcScore);
						}
					}
				}
			} while (!checkEmpty);
		}

		printTable(sosMatrix);

		System.err.println("Benim Puaným : " + myScore);
		System.err.println("Bilgisayar Puaný : " + pcScore);

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
			System.out.println("Girdiðiniz deðerler tabloyu aþtý. Girdi 0 ile " + String.valueOf(sosMatrix.length -1) + " arasýnda olmalýdýr. Tekrar giriniz.");
			return false;
		} else {
			if (sosMatrix[row][column] != "-") {
				System.out.println("Girdiðiniz konum doludur. Lütfen boþ alaný seçiniz. Ýþlem tekrar yapýlýyor.");
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
