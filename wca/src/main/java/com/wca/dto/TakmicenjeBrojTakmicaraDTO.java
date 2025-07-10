package com.wca.dto;

public class TakmicenjeBrojTakmicaraDTO {
    private String nazivTakmicenja;
    private int brojTakmicara;

    public TakmicenjeBrojTakmicaraDTO(String nazivTakmicenja, int brojTakmicara) {
        this.nazivTakmicenja = nazivTakmicenja;
        this.brojTakmicara = brojTakmicara;
    }

    public String getNazivTakmicenja() {
        return nazivTakmicenja;
    }

    public int getBrojTakmicara() {
        return brojTakmicara;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %3d", nazivTakmicenja, brojTakmicara);
    }
}
