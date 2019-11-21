package conversor;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RauLuar
 */
public class RomanoArabeViceVersa {

    public static String num400ou1000(String num) {
        int n;
        String resultado = "", auxN;
        if (num.length() == 3) {
            n = Integer.parseInt(num);
            if (n >= 400 && n <= 499) {
                auxN = num.substring(1, num.length());
                n = Integer.parseInt(auxN);

                resultado = "CD" + entre10A30("" + num.charAt(0) + "0", 4);
                if (n > 39) {
                    resultado += numerosDe40a99(auxN);

                } else {
                    resultado += numerosDe1a39(auxN);

                }
                return resultado;
            }
            if (n >= 900 && n <= 999) {
                auxN = num.substring(1, num.length());
                n = Integer.parseInt(auxN);

                resultado = "CM" + entre10A30("" + num.charAt(0) + "0", 4);
                if (n > 39) {
                    resultado += numerosDe40a99(auxN);

                } else {
                    resultado += numerosDe1a39(auxN);

                }
                return resultado;
            }
            if (n >= 500 && n <= 899) {
                if (n > 500) {

                    auxN = num.substring(1, num.length());
                    n = Integer.parseInt(auxN);

                    resultado = "D" + entre10A30("" + num.charAt(0) + "0", 4);
                    if (n > 39) {
                        resultado += numerosDe40a99(auxN);

                    } else {
                        resultado += numerosDe1a39(auxN);

                    }
                    return resultado;
                }

                return "D";
            }

        }

        return "";
    }

    public static String num1000ou4999(String num) {
        int n;
        String resultado = "", auxN;
        if (num.length() == 4) {
            //auxN = num.substring(0, num.length());
            if (num.charAt(1) == '0') {
                if (num.charAt(1) == '0' && num.charAt(2) == '0') {
                    String aux = "" + num.charAt(3);
                    resultado = entre10A30(num.charAt(0) + "0", 6) + "" + numerosDe1a39(aux);
                    return resultado;
                } else if (num.charAt(1) == '0' && num.charAt(2) != '0') {
                    String aux = "" + num.substring(2, num.length());
                    n = Integer.parseInt(aux);
                    if (n <= 39) {
                        resultado = entre10A30(num.charAt(0) + "0", 6) + "" + numerosDe1a39(aux);

                    } else {
                        resultado = entre10A30(num.charAt(0) + "0", 6) + "" + numerosDe40a99(aux);
                    }

                    return resultado;
                }
            } else {
                auxN = num.substring(1, num.length());
                n = Integer.parseInt(auxN);
                if (n <= 399) {
                    resultado = entre10A30(num.charAt(0) + "0", 6) + "" + num100a300(auxN);
                } else {
                    resultado = entre10A30(num.charAt(0) + "0", 6) + "" + num400ou1000(auxN);
                }
            }
            return resultado;
        }

        return "";
    }

    public static String entre1A3(String num) {
        int n, t;
        char c = Constante.SYSTEMA_ROMANANO[0];
        t = num.length();

        String concat = "";
        if (t == 1) {
            n = Integer.parseInt("" + num.charAt(0));
            if (n >= 1 && n <= 3) {
                for (int i = 1; i <= n; i++) {
                    concat += c;

                }
            }
            return concat;
        }
        return "";
    }

    public static String entre5A8(String num) {
        int n, t, sub;
        char c = Constante.SYSTEMA_ROMANANO[1];
        t = num.length();

        String concat = "";
        if (t == 1) {
            n = Integer.parseInt("" + num.charAt(0));
            if (n >= 5 && n <= 8) {
                sub = n - 5;
                num = String.valueOf(sub);
                concat += c + "" + entre1A3(num);
            }
            return concat;
        }
        return "";
    }

    public static String num4Ou9(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '4') {
                return "IV";
            }
            if (num.charAt(i) == '9') {
                return "IX";
            }

        }
        return "";
    }

    public static String entre10A30(String num, int posChar) {
        int n, t;
        char c = Constante.SYSTEMA_ROMANANO[posChar];
        t = num.length();
        int sub;

        String concat = "";
        if (t == 2) {
            n = Integer.parseInt("" + num.charAt(0));
            if (n >= 6 && n <= 8) {
                sub = n;
                n = sub - 5;
            }
            if (n >= 1 && n <= 4) {
                for (int i = 1; i <= n; i++) {
                    concat += c;

                }
            }
            return concat;
        }
        return "";
    }

    public static String num40ou100(String num) {
        int n;

        if (num.length() == 2) {
            n = Integer.parseInt(num);
            if (n >= 40 && n <= 49) {
                return "XL";
            }
            if (n >= 90 && n <= 99) {
                return "XC";
            }
            if (n >= 50 && n <= 89) {
                if (n > 50) {

                    return "L" + entre10A30(num, 2);
                }

                return "L";
            }

        }

        return "";
    }

    public static String numerosDe1a39(String num) {
        String auxNum = "";
        String resultado = "";
        if (num.length() == 1) {
            resultado = num4Ou9(num);
            if (resultado.length() == 0) {
                resultado = entre1A3(num);
                if (resultado.length() == 0) {
                    resultado = entre5A8(num);
                }
            }
        }
        if (num.length() == 2) {
            resultado = entre10A30(num, 2);
            auxNum = "" + num.charAt(1);
            if (num4Ou9(auxNum).length() == 0) {
                if (entre1A3(auxNum).length() != 0) {
                    resultado += entre1A3(auxNum);
                }
                if (entre5A8(auxNum).length() != 0) {
                    resultado += entre5A8(auxNum);
                }
            } else {
                resultado += num4Ou9(auxNum);
            }

        }
        return resultado;
    }

    public static String numerosDe40a99(String num) {
        String auxNum = "";
        String resultado = "";

        if (num.length() == 2) {
            resultado = num40ou100(num);
            auxNum = "" + num.charAt(1);
            if (resultado.length() != 0) {
                if (num4Ou9(auxNum).length() == 0) {
                    if (entre1A3(auxNum).length() != 0) {
                        resultado += entre1A3(auxNum);
                    }
                    if (entre5A8(auxNum).length() != 0) {
                        resultado += entre5A8(auxNum);
                    }
                } else {
                    resultado += num4Ou9(auxNum);
                }

            }

        }
        return resultado;
    }

    public static String num100a300(String num) {
        int n;
        String resultado = "";
        String auxN;
        if (num.length() == 3) {
            n = Integer.parseInt(num);

            if (n >= 100 && n <= 399) {
                if (n > 100) {
                    auxN = num.substring(1, num.length());
                    n = Integer.parseInt(auxN);

                    resultado = entre10A30("" + num.charAt(0) + "0", 4);
                    if (n > 39) {
                        resultado += numerosDe40a99(auxN);

                    } else {
                        resultado += numerosDe1a39(auxN);

                    }
                    return resultado;
                }

                return "C";
            }
        }

        return "";
    }

    public static String romano(String num) {
        int cont = 1;
        String n, numero;

        while (cont <= 4999) {
            numero = Conversor.converterArabe(cont);
            //System.out.println(cont+"-->"+numero);
            if (num.equalsIgnoreCase(numero)) {
                break;
            }
            cont++;
        }
        return String.valueOf(cont);
    }
}
