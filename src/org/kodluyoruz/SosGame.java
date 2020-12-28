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
        String  word;
        Boolean checkEmpty;
        do {
            System.out.print("Tablonun boyutunu giriniz : ");
            tableSize = scan.nextInt();
            if(tableSize < 3 || tableSize > 7){
                System.out.println("Lütfen 3x3 ile 7x7 arasýnda bi boyut giriniz. Ýþlem Tekrar yapýlýyor.");
            }
        }while (tableSize < 3 || tableSize > 7);

        sosMatrix = new String[tableSize][tableSize];

        for (int i = 0; i < sosMatrix.length; i++) {
            for (int j = 0; j < sosMatrix[0].length; j++) {
                sosMatrix[i][j]= "-";
            }
        }

        printTable(sosMatrix);

        while (!isGameOver()){
            do {
                System.out.print("Konum için satýrý giriniz. : ");
                row = scanLocation();
                System.out.print("Konum için sütunu giriniz. : ");
                column = scanLocation();
                System.out.print("S veya O harfi giriniz. : ");
                word = scanWord();
                checkEmpty = checkEmpty(row, column);
                if (checkEmpty){
                    sosMatrix[row][column] = word;
                    printTable(sosMatrix);
                }
            } while (!checkEmpty);
            do {
                if (!isGameOver()){
                    System.out.print("Bilgisayar Oynuyor");
                    row = new Random().nextInt(sosMatrix.length);
                    column = new Random().nextInt(sosMatrix[0].length);
                    word = new Random().nextInt(2) == 0 ? "S" : "O";
                                                                //Ýlk kimin baþlýyacagý random belli olmasý
                    checkEmpty = checkEmpty(row, column);
                    if (checkEmpty){
                        System.out.println("Bilgisayar " + row + " " + column + " konumuna " + word + " karakterini yerleþtirdi.");
                        sosMatrix[row][column] = word;
                        printTable(sosMatrix);
                    }
                }
            } while (!checkEmpty);
        }

        printTable(sosMatrix);

        System.out.println("Benim Puaným : " + myScore);
        System.out.println("Bilgisayar Puaný : " + pcScore);

        System.out.println(myScore > pcScore ? "Kazanan Benim" : "Kazanan Bilgisayar");
    }

    /*public static void checkRow (String[][] sosMatrix, int x, int y ,char deger) {
        x<2 y<2 ve y>5 puan kontrolüne girmez
        x>5 ise y<2 ve y>5 puan kontrolüne gimrez.
        if(x < sosMatrix.length-1 && x>1){
            if(y<sosMatrix[0].length-1 && y >1){
                y-1 ken x her durum
                        y+1 ken her durum
                            y sabitken x-1 ve x+1
            }
            else if(y<=1){
               y sabitken x -1 ve +1 olacak,
                y+1 ken x her durum
            }
            else
                y sabitken x-1 ve x+1
                    y-1 ken x her durum
        }
4
    }*/

    public static boolean isGameOver (){
        for (int i = 0; i < sosMatrix.length; i++) {
            for (int j = 0; j < sosMatrix[0].length; j++) {
                if (sosMatrix[i][j] == "-" ){
                    System.out.println("oyun bitmedi");
                    return false;
                }
            }
        }
        System.out.println("Oyun Bitti.");
        return true;
    }

    public static boolean checkEmpty (int row, int column) {
        if (row < 0 || sosMatrix.length <= row || sosMatrix[0].length <= column || column < 0) {
            System.out.println("*****************");
            System.out.println("Girdiðiniz deðerler tabloyu aþtý. Tekrar giriniz.");
            return false;
        }else {
            System.out.println("-------------");
            System.out.println(sosMatrix[row][column] + " " + row + " " + column);
            if (sosMatrix[row][column] != "-") {
                System.out.println("//////////////////");
                System.out.println("Girdiðiniz konum doludur. Lütfen boþ alaný seçiniz. Ýþlem tekrar yapýlýyor.");
                return false;
            }
        }
        return true;
    }

    public static void printTable(String[][] sosMatrix) {
        System.out.println();
        for (int i = 0; i < sosMatrix.length; i++) {
            for (int j = 0; j < sosMatrix[0].length; j++) {
                System.out.print(sosMatrix[i][j]);
                System.out.print("   ");
            }
            System.out.println("");
        }
    }

    public static int scanLocation(){
        return scan.nextInt();
    }

    public static String scanWord(){
        return scan.next();
    }


}
