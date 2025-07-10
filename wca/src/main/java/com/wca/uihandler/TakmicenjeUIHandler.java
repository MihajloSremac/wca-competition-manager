package com.wca.uihandler;

import com.wca.dto.TakmicenjeBrojTakmicaraDTO;
import com.wca.service.TakmicenjeService;
import java.util.List;


public class TakmicenjeUIHandler {
    private TakmicenjeService service = new TakmicenjeService();
    private final java.util.Scanner sc = new java.util.Scanner(System.in);

    public void prikaziBrojTakmicaraPoTakmicenju() {
        try {
            List<TakmicenjeBrojTakmicaraDTO> lista = service.getBrojTakmicaraPoTakmicenju();
            System.out.println("\nTakmicenja i broj takmicara:");
            System.out.println("-----------------------------------------------");
            System.out.printf("%-30s | %s\n", "Naziv takmicenja", "Broj takmicara");
            System.out.println("-----------------------------------------------");
            for (TakmicenjeBrojTakmicaraDTO dto : lista) {
                System.out.println(dto);
            }
            System.out.println("-----------------------------------------------");
        } catch (Exception e) {
            System.err.println("Greska prilikom dohvatanja izvestaja: " + e.getMessage());
        }
    }

    public void prikaziProsekPoDiscipliniTakmicenju() {
        try {
            com.wca.service.ProsekDisciplinaService service = new com.wca.service.ProsekDisciplinaService();
            var lista = service.getProsekPoDiscipliniTakmicenju();
            System.out.println("\nProsek vremena po disciplini i takmicenju (samo gde ima vise od 1 rezultata):");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-25s | %-15s | %8s | %3s\n", "Takmicenje", "Disciplina", "Prosek", "BrRez");
            System.out.println("---------------------------------------------------------------");
            for (var dto : lista) {
                System.out.println(dto);
            }
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            System.err.println("Greska prilikom izvestaja: " + e.getMessage());
        }
    }

    public void prikaziMedaljeTakmicaraPoTakmicenju() {
        try {
            com.wca.service.TakmicarMedaljeService service = new com.wca.service.TakmicarMedaljeService();
            var lista = service.getTakmicarMedaljePoTakmicenju();
            System.out.println("\nTakmicari - broj disciplina i medalja po takmicenju:");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.printf("%-25s | %-20s | %3s | %2s | %2s | %2s\n", "Takmicenje", "Takmicar", "Dis", "Au", "Ag", "Br");
            System.out.println("-------------------------------------------------------------------------------");
            for (var dto : lista) {
                System.out.println(dto);
            }
            System.out.println("-------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.err.println("Greska prilikom izvestaja: " + e.getMessage());
        }
    }

    public void dodajSponzoraITakmicenje() {
        try {
            // Prikazi sva takmicenja sa ID-jevima
            System.out.println("Lista takmicenja:");
            java.sql.Connection conn = com.wca.config.DatabaseConnection.getConnection();
            try (java.sql.Statement st = conn.createStatement();
                 java.sql.ResultSet rs = st.executeQuery("SELECT ID_TKM, NAZ_TKM FROM Takmicenje ORDER BY ID_TKM")) {
                while (rs.next()) {
                    System.out.printf("%3d | %s\n", rs.getInt(1), rs.getString(2));
                }
            }
            conn.close();
            System.out.print("Unesi naziv sponzora: ");
            String naziv = sc.nextLine();
            System.out.print("Unesi vrstu sponzora: ");
            String vrsta = sc.nextLine();
            System.out.print("Unesi uslove sponzora: ");
            String uslovi = sc.nextLine();
            System.out.print("Unesi ID takmicenja: ");
            int takmicenjeId = Integer.parseInt(sc.nextLine());
            com.wca.dto.SponzorTransakcijaDTO dto = new com.wca.dto.SponzorTransakcijaDTO(naziv, vrsta, uslovi, takmicenjeId);
            com.wca.service.SponzorTransakcijaService service = new com.wca.service.SponzorTransakcijaService();
            service.dodajSponzoraITakmicenje(dto);
            System.out.println("Sponzor i povezivanje sa takmicenjem su uspesno evidentirani!");
        } catch (Exception e) {
            System.err.println("Greska: " + e.getMessage());
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nIzaberi opciju:");
            System.out.println("1. Prikazi broj takmicara po takmicenju");
            System.out.println("2. Prosek vremena po disciplini i takmicenju");
            System.out.println("3. Takmicari - broj disciplina i medalja po takmicenju");
            System.out.println("4. Dodaj sponzora i povezi sa takmicenjem");
            System.out.println("0. Izlaz");
            System.out.print("> ");
            String izbor = sc.nextLine();
            if (izbor.equals("1")) {
                prikaziBrojTakmicaraPoTakmicenju();
            } else if (izbor.equals("2")) {
                prikaziProsekPoDiscipliniTakmicenju();
            } else if (izbor.equals("3")) {
                prikaziMedaljeTakmicaraPoTakmicenju();
            } else if (izbor.equals("4")) {
                dodajSponzoraITakmicenje();
            } else if (izbor.equals("0")) {
                System.out.println("Kraj programa.");
                break;
            } else {
                System.out.println("Nepoznata opcija, pokusaj ponovo.");
            }
        }
        sc.close();
    }
}
