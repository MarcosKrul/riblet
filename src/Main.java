package src;

import java.util.Scanner;


public class Main {
  public static void main(String[] argv) {
    try (Scanner scanner = new Scanner(System.in)) {

      System.out.println("Insira o tamanho da amostra: ");
      int sampleLength = scanner.nextInt();

      System.out.println("Insira a altura de cada riblet: ");
      double height = scanner.nextDouble();
      
      System.out.println("Insira o ângulo: ");
      double angle = scanner.nextDouble();
      
      System.out.println("Insira a distância entre os centros: ");
      double distanceBetween = scanner.nextDouble();
      
      System.out.println("Insira a distância do início ao primeiro centro: ");
      double distanceFirst = scanner.nextDouble();

      try {
        Riblet riblet = new Riblet("Estrutura Riblet", sampleLength, height, angle, distanceBetween, distanceFirst);
        riblet.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
  }

}
