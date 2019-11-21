/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RauLuar
 */
public class Romano {

    public static String romano(String num) {
        int cont = 1;
        String n, numero ;

        while (cont <= 4999) {
            numero = Conversor1.converterArabe(cont);
            //System.out.println(cont+"-->"+numero);
            if (num.equalsIgnoreCase(numero)) {
                break;
            }
            cont++;
        }
        return String.valueOf(cont);
    }

}
