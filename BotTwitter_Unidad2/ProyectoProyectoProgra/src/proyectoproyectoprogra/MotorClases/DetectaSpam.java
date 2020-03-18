/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoproyectoprogra.MotorClases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author javie
 */
public class DetectaSpam {
    File archivo;
    BufferedReader br;
    public DetectaSpam() throws FileNotFoundException{
        archivo= new File("spamwords.txt");
        br = new BufferedReader(new FileReader(archivo));
    }
}
