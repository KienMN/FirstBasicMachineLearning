/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author KienM
 */
public class LinearRegression {

    private final int MAX = 100;
    private double[] x = new double[MAX];
    private double[] y = new double[MAX];
    private double alpha = 1;       //the learning rate
    private int m;                  //the size of training set
    private double phi0, phi1;
    private static LinearRegression computation = new LinearRegression();

    private LinearRegression() {
        m = 0;
        phi0 = 0;
        phi1 = 0;
    }

    //read a txt file which contains pair of x and y in each line
    private void readFile(String filename) {
        try {
            FileInputStream input = new FileInputStream(filename);
            Scanner sc = new Scanner(input);
            while (sc.hasNext()) {
                x[m] = sc.nextDouble();
                y[m] = sc.nextDouble();
                m++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private double sigma1(double phi0, double phi1) {        //compute Sigma(phi0 + phi1*x - y) from x1,y1 -> xn,yn
        double res = 0;
        for (int i = 0; i < m; i++) {
            res += phi0 + phi1 * x[i] - y[i];
        }
        return res;
    }

    private double sigma2(double phi0, double phi1) {        //compute Sigma((phi0 + phi1*x - y)*x) from x1,y1 -> xn,yn
        double res = 0;
        for (int i = 0; i < m; i++) {
            res += (phi0 + phi1 * x[i] - y[i]) * x[i];
        }
        return res;
    }

    private void computePhi() {
        double a, b;
        do {
            a = phi0;
            b = phi1;
            phi0 = phi0 - alpha * sigma1(phi0, phi1) / m;
            phi1 = phi1 - alpha * sigma2(phi0, phi1) / m;
        } while (Math.abs(a - phi0) < Math.pow(10, -10) && Math.abs(b - phi1) < Math.pow(10, -10));
    }

    public static void main(String[] args) {
        computation.readFile("TrainingSet.txt");
        computation.computePhi();
        System.out.println("phi0 = " + computation.phi0 + "; phi1 = " + computation.phi1);
    }
}
