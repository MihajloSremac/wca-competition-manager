package com.wca.dto;

public class SponzorTransakcijaDTO {
    private String naziv;
    private String vrsta;
    private String uslovi;
    private int takmicenjeId;

    public SponzorTransakcijaDTO(String naziv, String vrsta, String uslovi, int takmicenjeId) {
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.uslovi = uslovi;
        this.takmicenjeId = takmicenjeId;
    }

    public String getNaziv() { return naziv; }
    public String getVrsta() { return vrsta; }
    public String getUslovi() { return uslovi; }
    public int getTakmicenjeId() { return takmicenjeId; }
}
