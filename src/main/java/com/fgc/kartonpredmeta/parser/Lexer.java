package com.fgc.kartonpredmeta.parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String input;
    private int pos=0;
    private int line=1;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize(){
        List<Token> tokens=new ArrayList<>();
        while(pos<input.length()){
            char trenutni=input.charAt(pos);

            if(trenutni=='\n')
            {
                line++;
                pos++;
                continue;
            }
            if(Character.isWhitespace(trenutni)){
                pos++;
                continue;
            }
            if(trenutni=='{'){
                tokens.add(new Token(TokenType.LBRACE,"{",line));
                pos++;
                continue;
            }
            if(trenutni=='}'){
                tokens.add(new Token(TokenType.RBRACE,"}",line));
                pos++;
                continue;
            }
            if(trenutni==':'){
                tokens.add(new Token(TokenType.COLON, ":", line));
                pos++;
                continue;
            }
            if(trenutni=='"'){
                tokens.add(readString());
                continue;
            }
            if(Character.isDigit(trenutni)){
                tokens.add(readNumber());
                continue;
            }
            if(Character.isLetter(trenutni)){
                tokens.add(readKeyword());
                continue;
            }

            throw new RuntimeException("Nepoznat karakter: " + trenutni + " na liniji: " + line);

        }
        tokens.add(new Token(TokenType.EOF, "", line));
        return tokens;
    }

    private Token readString(){
        pos++;
        int pocetak = pos;
        while(pos<input.length() && input.charAt(pos)!='"'){
            if(input.charAt(pos)=='\n') line++;
            pos++;
        }
        if(pos>=input.length()) throw new RuntimeException("Nije zatvoren string navodnikom na liniji: " + line);
        String vrednost= input.substring(pocetak, pos);
        pos++;
        return new Token(TokenType.STRING, vrednost, line);
    }

    private Token readNumber(){
        int pocetak = pos;
        while(pos<input.length() && Character.isDigit(input.charAt(pos))){
            pos++;
        }
        String vrednost= input.substring(pocetak, pos);
        return new Token(TokenType.NUMBER, vrednost, line);
    }

    private Token readKeyword(){
        int pocetak = pos;
        while(pos<input.length() && (Character.isLetter(input.charAt(pos)) || input.charAt(pos)=='_')){
            pos++;
        }
        String vrednost= input.substring(pocetak, pos).toUpperCase();

        return switch(vrednost){
            case "NAZIV"-> new Token(TokenType.KEYWORD_NAZIV, vrednost, line);
            case "SIFRA" -> new Token(TokenType.KEYWORD_SIFRA, vrednost, line);
            case "ESPB" -> new Token(TokenType.KEYWORD_ESPB, vrednost, line);
            case "GODINA" -> new Token(TokenType.KEYWORD_GODINA, vrednost, line);
            case "SEMESTAR" -> new Token(TokenType.KEYWORD_SEMESTAR, vrednost, line);
            case "OBAVEZE" -> new Token(TokenType.KEYWORD_OBAVEZE,vrednost,line);
            default -> throw new RuntimeException("Nepoznata ključna reč: " + vrednost + " na liniji: " + line);
        };
    }

    public static void main(String [] args){
        String proba = """
                NAZIV:"Napredne Java tehnologije"
                GODINA:3
                ESPB:5
                OBAVEZE {
                    "kolokvijum 1": 30
                    "ispit":100
                }
                SEMESTAR:6
                SIFRA:"NJTT\"""";

        Lexer l = new Lexer(proba);
        List<Token> lista=l.tokenize();
        for (Token t: lista){
            System.out.println(t.getType()+", vrednost je: "+t.getValue());
        }

    }
}
