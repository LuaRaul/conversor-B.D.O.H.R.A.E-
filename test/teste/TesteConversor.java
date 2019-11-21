package teste;

import conversor.Conversor;
import conversor.Constante;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RauLuar
 */
public class TesteConversor {

    public static boolean isNumeroValido(String num, char sistema) {
        boolean v = false;//pode remover a atribuiçao;

        //se o primeiro caracter na string num for zero, deve se consideram que o zero a esquerda nao tem
        //valor, logo precisamos excluir o zero
        if (num.length() == 0) {
            return false;
        }
        if (num.charAt(0) == '0') {
            num = num.substring(1, num.length() - 1);
        }

        //v = isSistemaValido(sistema);
        if (v) {
            boolean b = false;
            if (sistema == Constante.BINARIO) {
                for (int i = 0; i < num.length(); i++) { //for que percorre a String num, para acessar cada caracter na String num
                    char c = num.charAt(i);//pegando um determinado caracter na String num
                    //b = existe(c, Constante.SYSTEMA_BINARIO);
                    if (b == false) {
                        break;
                    }
                }
                return b;
            }
            if (sistema == Constante.OCTAL) {
                for (int i = 0; i < num.length(); i++) { //for que percorre a String num, para acessar cada caracter na String num
                    char c = num.charAt(i);//pegando um determinado caracter na String num
                    //b = existe(c, Constante.SYSTEMA_OCTAL);
                    if (b == false) {
                        break;
                    }
                }
                return b;
            }
            if (sistema == Constante.DECIMAL) {
                for (int i = 0; i < num.length(); i++) { //for que percorre a String num, para acessar cada caracter na String num
                    char c = num.charAt(i);//pegando um determinado caracter na String num
                    //b = existe(c, Constante.SYSTEMA_DECIMAL);
                    if (b == false) {
                        break;
                    }
                }
                return b;
            }
            if (sistema == Constante.HEXADECIMAL) {
                for (int i = 0; i < num.length(); i++) { //for que percorre a String num, para acessar cada caracter na String num
                    char c = num.charAt(i);//pegando um determinado caracter na String num
                    //b = existe(c, Constante.SYSTEMA_HEXADECIMAL);
                    if (b == false) {
                        break;
                    }
                }
                return b;
            }
            if (sistema == Constante.ROMANANO) {
                for (int i = 0; i < num.length(); i++) { //for que percorre a String num, para acessar cada caracter na String num
                    char c = num.charAt(i);//pegando um determinado caracter na String num
                    //b = existe(c, Constante.SYSTEMA_ROMANANO);
                    if (b == false) {
                        break;
                    }
                }
                return b;

            }
        }
        return false;
    }

    public static void main(String[] args) {
        Conversor conversor = new Conversor();
        boolean b1 = Conversor.isSistemaValido('B');
        System.out.println(b1);
        boolean b2 = Conversor.isNumeroValido("XXX", 'R');
        System.out.println(b2);
        // String num = Conversor.converterDecimal("692013", 'H');
        String num = Conversor.extencao("100");
        /*String numer = Arabe.num1000ou4999("4000");
         System.out.println(numer);
         String num = Conversor1.converterRomano("1");
         int cont=1;
         String n = n = String.valueOf(cont);;
        
         while( cont <=4999){
           
              
         num = Conversor1.converterRomano(n); 
         System.out.println(cont+ " - "+num);
         cont++;
         n = String.valueOf(cont);
              
 
         }*/
        //num = Conversor1.entre10A30("10");
        //num = Conversor1.("999");
        //String tira = Conversor1.tirarZerosEsquerda(num);
        //String num = Conversor1.converter("123",'O', 'B');
        //1x16x16+2x16+3x1 32+16+3 48+3
        System.out.println(num);

        //System.out.println(Conversor1.similarConvert_O_H(tira, 'O'));
        //System.out.println(Integer.toOctalString(123));
    }
}
