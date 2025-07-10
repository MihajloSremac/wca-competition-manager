package com.wca.dto;

public class ProsekDisciplinaDTO {
    private String nazivTakmicenja;
    private String nazivDiscipline;
    private double prosecnoVreme;
    private int brojRezultata;

    public ProsekDisciplinaDTO(String nazivTakmicenja, String nazivDiscipline, double prosecnoVreme, int brojRezultata) {
        this.nazivTakmicenja = nazivTakmicenja;
        this.nazivDiscipline = nazivDiscipline;
        this.prosecnoVreme = prosecnoVreme;
        this.brojRezultata = brojRezultata;
    }

    public String getNazivTakmicenja() { return nazivTakmicenja; }
    public String getNazivDiscipline() { return nazivDiscipline; }
    public double getProsecnoVreme() { return prosecnoVreme; }
    public int getBrojRezultata() { return brojRezultata; }

    @Override
    public String toString() {
        String prosekStr;
        if (prosecnoVreme >= 60.0) {
            int min = (int) (prosecnoVreme / 60);
            double sec = prosecnoVreme - min * 60;
            prosekStr = String.format("%d:%05.2f", min, sec);
        } else {
            prosekStr = String.format("%6.2f", prosecnoVreme);
        }
        return String.format("%-25s | %-15s | %8s | %3d", nazivTakmicenja, nazivDiscipline, prosekStr, brojRezultata);
    }
}
