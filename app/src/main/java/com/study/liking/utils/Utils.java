package com.study.liking.utils;

import com.study.liking.exceptions.DateParseLocalException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Locale;

public class Utils {

    public static boolean isCpfValid(String cpf, boolean allowCheck) {
        if (allowCheck) {
            // considera-se erro CPF's formados por uma sequencia de numeros iguais
            if (cpf.equals("00000000000") ||
                    cpf.equals("11111111111") ||
                    cpf.equals("22222222222") || cpf.equals("33333333333") ||
                    cpf.equals("44444444444") || cpf.equals("55555555555") ||
                    cpf.equals("66666666666") || cpf.equals("77777777777") ||
                    cpf.equals("88888888888") || cpf.equals("99999999999") ||
                    (cpf.length() != 11))
                return false;

            char dig10, dig11;
            int sm, i, r, num, peso;

            // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
                // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    // converte o i-esimo caractere do cpf em um numero:
                    // por exemplo, transforma o caractere '0' no inteiro 0
                    // (48 eh a posicao de '0' na tabela ASCII)
                    num = (int) (cpf.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

                // Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = (int) (cpf.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else dig11 = (char) (r + 48);

                // Verifica se os digitos calculados conferem com os digitos informados.
                return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
            } catch (InputMismatchException erro) {
                return false;
            }

        }
        return true;
    }

    public static boolean isBirthDateInvalid(String birthDate) throws DateParseLocalException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
            Calendar date = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH);
            date.setTime(sdf.parse(birthDate));

            Calendar actualDate = Calendar.getInstance();

            return date.compareTo(actualDate) >= 0 || date.get(Calendar.YEAR) < (actualDate.get(Calendar.YEAR) - 100)
                    || !checkDayAndMonth(Integer.parseInt(birthDate.substring(2,4)), Integer.parseInt(birthDate.substring(0,2)), date.get(Calendar.YEAR));
        } catch (ParseException e) {
            throw new DateParseLocalException("Erro no formato de data!");
        }
    }

    public static boolean isPhoneValid(String phone) {
        return phone.charAt(2) == '9' || phone.charAt(2) == '8';
    }

    private static boolean checkDayAndMonth(int month, int day, int year) {
        boolean isTrueDate = true;
        if (month > 12) {
            isTrueDate = false;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day <= 31) {
                isTrueDate = true;
            } else if (day >= 31) {
                isTrueDate = false;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day <= 30) {
                isTrueDate = true;
            } else if (day >= 30) {
                isTrueDate = false;
            }

        } else if (month == 2) // February check
        {
            if (year % 4 == 0) // Leap year check for February
            {
                if (day <= 29) {
                    isTrueDate = true;
                } else if (day >= 29) {
                    isTrueDate = false;
                }
            } else if (year % 4 != 0) {
                if (day <= 28) {
                    isTrueDate = true;
                } else if (day >= 28) {
                    isTrueDate = false;
                }
            }
        }
        return isTrueDate;
    }

}
