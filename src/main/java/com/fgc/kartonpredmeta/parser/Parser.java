package com.fgc.kartonpredmeta.parser;

import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetnaObavezaRequestDTO;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Parser {
    private final List<Token> tokens;
    private int trenutni=0;

    private final Set<TokenType> parsovanaPolja = EnumSet.noneOf(TokenType.class);

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public PredmetRequestDTO parse() {
        PredmetRequestDTO dto = new PredmetRequestDTO();

        while (trenutniToken().getType() != TokenType.EOF) {
            parsePolje(dto);
        }
        return dto;
        
    }

    private void parsePolje(PredmetRequestDTO dto) {
        Token kljucnaRec = konzumirajToken();
        proveriDuplikat(kljucnaRec);
        match(TokenType.COLON);

        switch(kljucnaRec.getType()){
            case KEYWORD_NAZIV -> dto.setNaziv(match(TokenType.STRING).getValue());
            case KEYWORD_SIFRA -> dto.setSifra(match(TokenType.STRING).getValue());
            case KEYWORD_ESPB -> dto.setEspb(Integer.parseInt(match(TokenType.NUMBER).getValue()));
            case KEYWORD_GODINA -> dto.setGodinaStudija(Integer.parseInt(match(TokenType.NUMBER).getValue()));
            case KEYWORD_SEMESTAR -> dto.setSemestar(Integer.parseInt(match(TokenType.NUMBER).getValue()));
            case KEYWORD_OBAVEZE -> dto.setObaveze(parseObavezePolje());
            default -> throw new RuntimeException(String.format("Sintaksna greška na liniji %d, neočekivan token %s",
                    kljucnaRec.getLine(),kljucnaRec.getValue()));
        }
    }

    private void proveriDuplikat(Token kljucnaRec) {
        if(!parsovanaPolja.add(kljucnaRec.getType())){
            throw new RuntimeException(String.format("Polje '%s' je već zadato", kljucnaRec.getValue()));
        }
    }

    private @Valid List<PredmetnaObavezaRequestDTO> parseObavezePolje() {
        List<PredmetnaObavezaRequestDTO> obaveze = new ArrayList<>();
        match(TokenType.LBRACE);

        while(trenutniToken().getType()!=TokenType.RBRACE && trenutniToken().getType()!=TokenType.EOF){
            obaveze.add(parseObaveza());
        }

        if (trenutniToken().getType() == TokenType.EOF) {
            throw new RuntimeException("Niste zatvorili odeljak OBAVEZE odgovarajućom zagradom '}'");
        }

        match(TokenType.RBRACE);
        return obaveze;
    }

    private PredmetnaObavezaRequestDTO parseObaveza() {
        PredmetnaObavezaRequestDTO dto = new PredmetnaObavezaRequestDTO();
        dto.setNaziv(match(TokenType.STRING).getValue());
        match(TokenType.COLON);
        dto.setMaxPoena(parseBroj());
        return dto;
    }

    private Integer parseBroj() {
        Token broj = match(TokenType.NUMBER);
        try
        {
            return Integer.parseInt(broj.getValue());
        } catch (NumberFormatException e){
            throw new RuntimeException(String.format("'%s' nije validan ceo broj", broj.getValue()));
        }
    }


    private Token match(TokenType ocekivani) {
        Token token = trenutniToken();
        if(token.getType()!=ocekivani){
            throw new RuntimeException(String.format("Sintaksna greška na liniji %d, očekivan je token %s, a tu je %s ('%s')",
                    token.getLine(),ocekivani,token.getType(),token.getValue()));
        }
        return konzumirajToken();
    }


    private Token trenutniToken(){
        return tokens.get(trenutni);
    }
    private Token konzumirajToken(){
        return tokens.get(trenutni++);
    }

    public static void main(String [] args){
        String proba = """
                NAZIV:"Napredne Java tehnologije"
                GODINA:3
                ESPB:5
                OBAVEZE :{
                    "kolokvijum 1" 30
                    "ispit":100
                }
                SEMESTAR:6
                SIFRA:"NJTT\"""";

        Lexer l = new Lexer(proba);
        List<Token> lista=l.tokenize();
        Parser p = new Parser(lista);
        PredmetRequestDTO predmet = p.parse();
        System.out.println(predmet.getObaveze().get(1).getMaxPoena());

    }
}

