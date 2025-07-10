package com.wca.dto;

public class TakmicarMedaljeDTO {
    private String takmicenje;
    private String takmicar;
    private int brojDisciplina;
    private int zlato;
    private int srebro;
    private int bronza;

    public TakmicarMedaljeDTO(String takmicenje, String takmicar, int brojDisciplina, int zlato, int srebro, int bronza) {
        this.takmicenje = takmicenje;
        this.takmicar = takmicar;
        this.brojDisciplina = brojDisciplina;
        this.zlato = zlato;
        this.srebro = srebro;
        this.bronza = bronza;
    }

    public String getTakmicenje() { return takmicenje; }
    public String getTakmicar() { return takmicar; }
    public int getBrojDisciplina() { return brojDisciplina; }
    public int getZlato() { return zlato; }
    public int getSrebro() { return srebro; }
    public int getBronza() { return bronza; }

    @Override
    public String toString() {
        return String.format("%-25s | %-20s | %3d | %2d | %2d | %2d", takmicenje, takmicar, brojDisciplina, zlato, srebro, bronza);
    }
}
