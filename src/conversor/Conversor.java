package conversor;

import java.util.Scanner;

/*
 *
 * @author RauLuar
 */
import conversor.Constante;

public class Conversor {

    //função que verifica se um dado carácter representa uma base válida 
    public static boolean isSistemaValido(char sistema) {
        //o caracter pode estar somente em minusculo ou maisculo ou em ambos os casos? 
        char s = sistema;
        if (s == Constante.BINARIO || s == Constante.OCTAL || s == Constante.DECIMAL || s == Constante.HEXADECIMAL || s == Constante.ROMANANO) {
            return true;
        }
        return false;

    }
    //funcao que verifica se um dado valor é um número válido num

    public static boolean verificaExistCaracter(char c, char s[]) {
        int cont = 0;
        while (cont < s.length) {
            char c1 = s[cont];
            if (c == c1) {//compara o carater existente String num com os caracteres aceite no sistema
                return true;
            }
            cont++;
        }
        return false;
    }

    //Busca saber se os caracteres na String num, fazem parte do Sistema
    public static boolean caractersDoSistema(String num, char[] sistema) {
        boolean b = false;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            b = verificaExistCaracter(c, sistema);
            if (b == false) {
                break;
            }
        }
        return b;
    }

    public static char[] sistema(char s) {
        if (s == Constante.BINARIO) {
            return Constante.SYSTEMA_BINARIO;
        }
        if (s == Constante.OCTAL) {
            return Constante.SYSTEMA_OCTAL;
        }
        if (s == Constante.DECIMAL) {
            return Constante.SYSTEMA_DECIMAL;
        }
        if (s == Constante.HEXADECIMAL) {
            return Constante.SYSTEMA_HEXADECIMAL;
        }
        if (s == Constante.ROMANANO) {
            return Constante.SYSTEMA_ROMANANO;
        }
        return null;
    }

    public static boolean isNumeroValido(String num, char sistema) {

        if (num.length() == 0) {
            return false;
        }
        if (num.charAt(0) == '0') {
            num = num.substring(1, num.length() - 1);
        }
        boolean veja, exist;

        veja = isSistemaValido(sistema);
        if (veja) {
            char s[] = sistema(sistema);
            exist = caractersDoSistema(num, s);
            return exist;
        }
        return false;
    }

    public static String tirarZerosEsquerda(String num) {

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0' && num.charAt(i + 1) == '1') {
                return num.substring(i + 1, num.length());
            }
        }
        return "";
    }

    public static String potencia(String num, char sistema) {

        int qtd = num.length() - 1;
        int soma = 0, n, b = 0, pos;
        String auxN, resultado;
        if (sistema == Constante.BINARIO) {
            b = 2;
        }
        if (sistema == Constante.OCTAL) {
            b = 8;
        }
        if (sistema == Constante.HEXADECIMAL) {
            b = 16;
        }
        for (int i = 0; i < num.length(); i++) {
            auxN = "" + num.charAt(i);
            pos = posLetraHexadecidal(num.charAt(i));
            if (pos >= 10 && pos <= 15) {
                n = pos;
            } else {
                n = Integer.parseInt(auxN);
            }
            for (int j = 1; j <= qtd; j++) {
                n = n * b;
            }
            qtd--;
            soma = soma + n;
        }
        resultado = String.valueOf(soma);
        return resultado;

    }

    public static String converterBinario(String num, char sistemaDestino) {
        int n = 0, qtd;
        String resultado;
        if (sistemaDestino == Constante.DECIMAL) {
            qtd = num.length() - 1;
            int soma = 0;
            String auxN;
            for (int i = 0; i < num.length(); i++) {
                auxN = "" + num.charAt(i);
                n = Integer.parseInt(auxN);
                for (int j = 1; j <= qtd; j++) {
                    n = n * 2;
                }
                qtd--;
                soma = soma + n;
            }
            System.out.println();
            System.out.println();
            resultado = String.valueOf(soma);
            String ext = "[" + extencao(resultado) + "]";
            return resultado + ext;

        }
        if (sistemaDestino == Constante.OCTAL) {
            resultado = similarConvert_O_H(num, Constante.OCTAL);
            String ext = "[" + extencao(resultado) + "]";
            return resultado + ext;

        }
        if (sistemaDestino == Constante.HEXADECIMAL) {
            return similarConvert_O_H(num, Constante.HEXADECIMAL);
        }
        return "";

    }

    private static String auxConverterOctal(String num, char sistemaDestino) {
        int n = 0, qtd, resto, b = 2;
        String resultado = "", junta = "";
        if (sistemaDestino == Constante.BINARIO) {
            String auxN;
            for (int i = 0; i < num.length(); i++) {
                auxN = "" + num.charAt(i);
                n = Integer.parseInt(auxN);
                if (n == 0) {
                    resultado = resultado + "000";
                }
                if (n == 1) {
                    resultado = resultado + "001";
                } else {
                    //essa condição só se cumprime perfeitamente quando o n é maior 3
                    while (n > 0) {

                        resto = n % b;
                        n = n / b;
                        System.out.println(auxN + "--" + resto);
                        junta = junta + resto;

                    }
                    //para fazer com que nao haja erro de conversao quando n é, 2,3, 
                    //acrescenta-se,0 na primeira posicao da variavel junta
                    if (auxN.charAt(0) == '2' || auxN.charAt(0) == '3') {
                        junta = junta + "0";
                    }

                }
                resultado = resultado + invertSequencia(junta);
                junta = "";

            }
            return resultado;

        }
        return "";
    }

    public static String converterOctal(String num, char sistemaDestino) {

        String resultado = "";
        if (sistemaDestino == Constante.BINARIO) {
            resultado = auxConverterOctal(num, sistemaDestino);
            return resultado;
        }
        if (sistemaDestino == Constante.DECIMAL) {
            resultado = potencia(num, 'O');
            String ext = "[" + extencao(resultado) + "]";
            return resultado + ext;
        }
        if (sistemaDestino == Constante.HEXADECIMAL) {
            String r = auxConverterOctal(num, 'B');
            resultado = similarConvert_O_H(r, sistemaDestino);

            return resultado;
        }
        return "";
    }

    //busca a posicao da letra existente no sistema hexa, para saber o seu equivalente em binario
    public static int posLetraHexadecidal(char l) {
        char c;
        char hexa[] = Constante.SYSTEMA_HEXADECIMAL;
        for (int i = 0; i < hexa.length; i++) {
            c = hexa[i];
            if (c == l) {
                return i;
            }
        }
        return -1;
    }

    public static String converterHexadecimal(String num, char sistemaDestino) {
        int n = 0, p;
        char l;
        String resultado = "", junta = "";
        if (sistemaDestino == Constante.BINARIO) {
            //percorre toda string num
            while (n < num.length()) {
                l = num.charAt(n);//pega cada letra da string
                p = Conversor.posLetraHexadecidal(l);//pega a posicao da letra dentro do sistema hexadecima
                junta = Conversor.converterDecimal("" + p, Constante.BINARIO);
                //algumas conversoes de decimal para binario virao com poucos digitos
                if (p >= 0 && p <= 7) {
                    if (p == 1) {
                        junta = "000" + junta;//acrestanto digitos  
                    } else if (p == 2 || p == 3) {
                        junta = "00" + junta; //acrescentando digito 
                    } else {
                        junta = "0" + junta;//acrescentando digito 
                    }

                }

                resultado = resultado + "" + junta;
                n++;
            }

            return resultado;

        }
        if (sistemaDestino == Constante.DECIMAL) {
            resultado = potencia(num, 'H');
            String ext = "[" + extencao(resultado) + "]";
            return resultado + ext;
        }
        if (sistemaDestino == Constante.OCTAL) {
            resultado = Conversor.converterHexadecimal(num, 'B');
            //String tira = Conversor.tirarZerosEsquerda(num);
            resultado = Conversor.similarConvert_O_H(resultado, 'O');
            String ext = "[" + extencao(resultado) + "]";
            return resultado + ext;
        }

        return "";

    }

    //funcao que converte de decimal para B,O,H
    public static String similarConvert_B_O_H(String num, char sistema) {
        int n = 0, resto, qtd, b = 0;
        String resultado = "";
        if (sistema == Constante.BINARIO) {
            b = 2;
        }
        if (sistema == Constante.OCTAL) {
            b = 8;
        }
        if (sistema == Constante.HEXADECIMAL) {
            b = 16;
        }

        n = Integer.parseInt(num);

        while (n >= 1) {
            resto = n % b;
            n = n / b;
            if (resto >= 10 && resto <= 15) {
                resultado = resultado + Constante.SYSTEMA_HEXADECIMAL[resto];

            } else {
                resultado = resultado + resto;
            }
        }
        resultado = invertSequencia(resultado);
        return resultado;
    }

    public static String converterDecimal(String num, char sistemaDestino) {
        int n = 0, resto, qtd;
        String resultado = "";
        if (sistemaDestino == Constante.BINARIO) {
            resultado = similarConvert_B_O_H(num, sistemaDestino);
            return resultado;
        }
        if (sistemaDestino == Constante.OCTAL) {
            resultado = similarConvert_B_O_H(num, sistemaDestino);
            String ext = "[" + extencao(resultado) + "]";
            return resultado + ext;
        }
        if (sistemaDestino == Constante.HEXADECIMAL) {
            resultado = similarConvert_B_O_H(num, sistemaDestino);
            return resultado;
        }
        return "";

    }

    public static String invertSequencia(String seq) {
        String s = "";
        for (int i = seq.length() - 1; i >= 0; i--) {
            s = s + "" + seq.charAt(i);

        }
        return s;
    }

    //funcao que faz duas conversao similar, de binario para octal ou hexadeciamal.
    public static String similarConvert_O_H(String num, char sistema) {
        int qtd = num.length() - 1;
        int soma = 0, n = 0, reparte = 0;
        String auxN, parte = "", invertParte = "", concat = "", resultado = "";
        if (sistema == Constante.OCTAL) {
            reparte = 3;
        }
        if (sistema == Constante.HEXADECIMAL) {
            reparte = 4;
        }
        while (qtd >= 0) {
            //repartir ou agrupar o numero numa sequencia de 3(octal) ou 4(hexadecimal) digitos
            for (int i = 1; i <= reparte; i++) {
                if (qtd >= 0) {
                    parte = parte + "" + num.charAt(qtd);
                } else {
                    parte = parte + "0";

                }
                qtd--;
            }

            //inverter a sequencia
            invertParte = invertSequencia(parte);
            System.out.println();
            int p = reparte - 1, s = 0;
            //operar nos tres digitos
            for (int i = 0; i < invertParte.length(); i++) {
                auxN = "" + invertParte.charAt(i);
                n = Integer.parseInt(auxN);
                for (int j = p; j >= 1; j--) {
                    n = n * 2;
                }
                s = s + n;
                p--;
            }
            if (s >= 10 && s <= 15) {
                concat = concat + "" + Constante.SYSTEMA_HEXADECIMAL[s];
            } else {
                concat = concat + "" + s;
            }
            System.out.println();
            parte = "";
            invertParte = "";
        }

        resultado = invertSequencia(concat);
        return resultado;
    }

    public static String converterRomano(String num) {
        boolean v = isNumeroValido(num, 'R');
        String resultado;
        if (v) {
            resultado = RomanoArabeViceVersa.romano(num);
            return resultado;
        }
        return "";
    }

    public static String converterArabe(int num) {
        String auxNum = String.valueOf(num);
        boolean v = isNumeroValido(auxNum, 'D');
        String resultado;
        if (v) {
            int n = Integer.parseInt(auxNum);
            if (n <= 39) {
                resultado = RomanoArabeViceVersa.numerosDe1a39(auxNum);
            } else {
                resultado = RomanoArabeViceVersa.numerosDe40a99(auxNum);
                if (resultado.length() == 0) {
                    resultado = RomanoArabeViceVersa.num100a300(auxNum);
                    if (resultado.length() == 0) {
                        resultado = RomanoArabeViceVersa.num400ou1000(auxNum);
                        if (resultado.length() == 0) {
                            resultado = RomanoArabeViceVersa.num1000ou4999(auxNum);
                        }
                    }
                }
            }
            return resultado;
        }
        return "";
    }

    public static String converter(String num, char sistemaOrigem, char sistemaDestino) {
        boolean v1 = isSistemaValido(sistemaOrigem);
        boolean v2 = isSistemaValido(sistemaDestino);
        boolean v3 = isNumeroValido(num, sistemaOrigem);
        String resultado = "";
        if (v1 == true && v2 == true && v3 == true) {
            if (sistemaOrigem == Constante.BINARIO) {
                System.out.println("");
                System.out.println("");

                resultado = converterBinario(num, sistemaDestino);
                return resultado;
            }
            if (sistemaOrigem == Constante.OCTAL) {
                System.out.println("");
                System.out.println("");

                resultado = converterOctal(num, sistemaDestino);
                return resultado;
            }
            if (sistemaOrigem == Constante.DECIMAL) {
                System.out.println("");
                System.out.println("");

                resultado = converterDecimal(num, sistemaDestino);
                return resultado;
            }
            if (sistemaOrigem == Constante.HEXADECIMAL) {
                System.out.println("");
                resultado = converterHexadecimal(num, sistemaDestino);
                return resultado;
            }

        } else {
            if (v1 == false && v2 == false && v3 == false) {
                resultado = "O numero informado não é valido"
                        + "\n,o sistema de origem e de destino também não são valido ";
                return resultado;

            }
            if (v3 == false && v2 == false) {
                resultado = "O numero informado não é valido"
                        + "\n,o sistema de destino também não é valido ";
                return resultado;

            }
            if (v1 == false && v3 == false) {
                resultado = "O numero informado não é valido"
                        + "\n,o sistema de origem também não é valido ";
                return resultado;

            }
            if (v1 == false && v2 == false) {
                resultado = "O sistema de origem e de destino não são valido ";
                return resultado;

            }

            if (v3 == false) {
                resultado = "O numero informado não é valido";
                return resultado;

            }
            if (v2 == false) {
                resultado = "O sistema de destino não é valido ";
                return resultado;
            }
            if (v1 == false) {
                resultado = "O sistema de origem não é valido ";
                return resultado;

            }

        }
        return resultado;
    }

    public static void subMenu(String converterOri, String converterDest1, String converterDest2, String converterDest3) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("             Digite o numero : \n");
        String numero, resultado = "";
        numero = teclado.next().toUpperCase();
        if (converterOri.length() == 0) {
            if (converterDest1.equalsIgnoreCase("Arabe")) {
                int auxNumero = Integer.parseInt(numero);
                resultado = Conversor.converterArabe(auxNumero);
                if (resultado.length() == 0) {
                    System.out.println("             Numero invalido");
                    return;
                }
                System.out.println("             Resultado : " + numero + "-->" + resultado);

            }
            if (converterDest1.equalsIgnoreCase("Romano")) {

                resultado = Conversor.converterRomano(numero);
                if (resultado.length() == 0) {
                    System.out.println("             Numero invalido");
                    return;
                }
                System.out.println("             Resultado : " + numero + "-->" + resultado);

            }

        } else {
            int op1 = -1;
            do {
                System.out.println("             Menu do sistema " + converterOri + "\n"
                        + "           1- Converter de " + converterOri + " para " + converterDest1 + "\n           2- Converter de " + converterOri + " para " + converterDest2 + "\n"
                        + "           3-Converter de " + converterOri + " para " + converterDest3 + "\n           0-Voltar");
                System.out.print("             Degite a opcao: ");

                op1 = teclado.nextInt();
                switch (op1) {
                    case 1:
                        resultado = converter(numero, converterOri.charAt(0), converterDest1.charAt(0));
                        break;
                    case 2:
                        resultado = converter(numero, converterOri.charAt(0), converterDest2.charAt(0));
                        break;
                    case 3:
                        resultado = converter(numero, converterOri.charAt(0), converterDest3.charAt(0));
                        break;
                }
                System.out.println("             Resultado : " + numero + "-->" + resultado);
            } while (op1 != 0);
        }
    }

    public static void menuPrincipal() {
        Scanner teclado = new Scanner(System.in);
        int op;
        String numero, resultado = "";

        do {
            System.out.println("Conversor de Sistema de Numeração\n             Menu\n           1-Binario\n"
                    + "           2-Octal\n           3-Decimal\n           4-Hexadecima\n           5-Romano para Arabe\n           6-Arabe para Romano\n           0-Sair");
            System.out.print("             Degite a opcao: ");
            op = teclado.nextInt();
            System.out.println("\n\n\n\n");
            int op1 = -1;
            switch (op) {
                case 1:
                    subMenu("Binario", "Octal", "Decimal", "Hexadecimal");
                    break;
                case 2:
                    subMenu("Octal", "Binario", "Decimal", "Hexadecimal");

                    break;
                case 3:
                    subMenu("Decimal", "Binario", "Octal", "Hexadecimal");
                    break;
                case 4:
                    subMenu("Hexadecimal", "Binario", "Octal", "Decimal");
                    //System.out.println("             Implementacao pendente");

                    break;
                case 5:
                    subMenu("", "Romano", "", "");

                    //System.out.println("             Implementacao pendente");
                    break;
                case 6:
                    subMenu("", "Arabe", "", "");

                    //System.out.println("             Implementacao pendente");
                    break;
                case 0:
                    System.out.println("             Programa encerrado com sucesso");

                    break;
                default:
                    System.out.println("             Opcao invalida");
                    break;
            }

        } while (op != 0);

    }

    public static String toExtencao1(String num) {
        if (num.length() == 1) {
            int n = Integer.parseInt(num);
            return Constante.EXTENSO1[n];

        }
        return "";
    }

    public static String toExtencao1_1(String num, boolean v) {
        if (num.length() == 2) {
            int n = Integer.parseInt(num);

            if (n >= 10 && n <= 19) {
                if (v) {
                    return Constante.E + Constante.EXTENSO1_1[n - 10];
                }
                return Constante.EXTENSO1_1[n - 10];
            }
            n = Integer.parseInt("" + num.charAt(0));
            int n1;
            String conc = "";
            if (num.charAt(1) != '0') {
                n1 = Integer.parseInt("" + num.charAt(1));
                conc = Constante.E + toExtencao1(String.valueOf(n1));
            }
            return Constante.EXTENSO2[n - 2] + conc;

        }
        return "";
    }

    public static String toExtencao2(String num) {
        if (num.length() == 3) {
            String to = "";
            int n = Integer.parseInt(num), n1;
            String concat = " e ";
            String p1 = "" + num.charAt(0);
            String p2 = num.substring(1, num.length());
            n1 = Integer.parseInt(p1);
            if(n==100){
                Constante.EXTENSO3[0] = "Cem";
            }
            if (n >= 101) {
                System.err.println(n);
                if (n <= 199) {
                    to = "to";
                }

            }
            if (num.charAt(1) == '0' && num.charAt(2) == '0') {
                return Constante.EXTENSO3[n1 - 1] + to;
            }
            if (num.charAt(1) == '0' && num.charAt(2) != '0') {
                p2 = "" + num.charAt(2);
                return Constante.EXTENSO3[n1 - 1] + to + Constante.E + toExtencao1(p2);
            }
            if (num.charAt(2) == '0') {
                concat = Constante.E;
            }
            return Constante.EXTENSO3[n1 - 1] + to + concat + toExtencao1_1(p2,true);
        }
        return "";
    }

    public static String toExtencao3(String num) {

        if (num.length() == 4) {
            int n = Integer.parseInt(num);
            String concat = " e ";

            String p1 = "" + num.charAt(0);
            String p2 = num.substring(1, num.length());
            n = Integer.parseInt(p1);
            int n1;//1102
            if (num.charAt(1) == '0' && num.charAt(2) == '0' && num.charAt(3) == '0') {
                n1 = Integer.parseInt(p1);
                return Constante.EXTENSO4[n1 - 1];
            }

            if (num.charAt(1) == '0' && num.charAt(2) == '0' && num.charAt(3) != '0') {
                p2 = "" + num.charAt(3);
                System.out.println(p2);
                return Constante.EXTENSO4[n - 1] + Constante.E + toExtencao1(p2);
            }

            return Constante.EXTENSO4[n - 1] + concat + extencao(p2);
        }
        return "";
    }

    public static String extencao(String num) {
        String ext = "";
        if (num.length() > 0) {
            ext = toExtencao1(num);
            if (ext.length() == 0) {
                ext = toExtencao1_1(num, false);
                if (ext.length() == 0) {
                    ext = toExtencao2(num);
                    if (ext.length() == 0) {
                        ext = toExtencao3(num);
                    }
                }
            }
        }

        return ext;
    }
}
