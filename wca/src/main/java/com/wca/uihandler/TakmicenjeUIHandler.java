package com.wca.uihandler;

import com.wca.dto.TakmicenjeBrojTakmicaraDTO;
import com.wca.service.TakmicenjeService;
import com.wca.service.ProsekDisciplinaService;
import com.wca.service.TakmicarMedaljeService;
import com.wca.service.SponzorTransakcijaService;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TakmicenjeUIHandler {
    private TakmicenjeService service = new TakmicenjeService();
    private ProsekDisciplinaService disciplinaService = new ProsekDisciplinaService();
    private TakmicarMedaljeService takmicarService = new TakmicarMedaljeService();
    private SponzorTransakcijaService sponzorService = new SponzorTransakcijaService();
    private final java.util.Scanner sc = new java.util.Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

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
            var lista = disciplinaService.getProsekPoDiscipliniTakmicenju();
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
            var lista = takmicarService.getTakmicarMedaljePoTakmicenju();
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
            sponzorService.dodajSponzoraITakmicenje(dto);
            System.out.println("Sponzor i povezivanje sa takmicenjem su uspesno evidentirani!");
        } catch (Exception e) {
            System.err.println("Greska: " + e.getMessage());
        }
    }

    public void crudTakmicenja() {
        while (true) {
            System.out.println("\nUpravljanje takmicenjima:");
            System.out.println("1. Prikazi sva takmicenja");
            System.out.println("2. Dodaj novo takmicenje");
            System.out.println("3. Azuriraj takmicenje");
            System.out.println("4. Obrisi takmicenje");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> takmicenja = service.getAllTakmicenja();
                    System.out.println("\nSva takmicenja:");
                    System.out.printf("%-5s | %-25s | %-12s | %-20s | %-15s | %8s\n", "ID", "Naziv", "Datum", "Adresa", "Drzava", "Cena");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    for (Object[] t : takmicenja) {
                        System.out.printf("%-5d | %-25s | %-12s | %-20s | %-15s | %8d\n", 
                            t[0], t[1], t[2], t[3], t[4], t[5]);
                    }
                } else if (izbor.equals("2")) {
                    System.out.print("Unesi naziv takmicenja: ");
                    String naziv = sc.nextLine();
                    System.out.print("Unesi datum (dd.MM.yyyy): ");
                    Date datum = dateFormat.parse(sc.nextLine());
                    System.out.print("Unesi adresu: ");
                    String adresa = sc.nextLine();
                    System.out.print("Unesi drzavu: ");
                    String drzava = sc.nextLine();
                    System.out.print("Unesi cenu: ");
                    int cena = Integer.parseInt(sc.nextLine());
                    service.kreirajTakmicenje(naziv, datum, adresa, drzava, cena);
                    System.out.println("Takmicenje je uspesno kreiran!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID takmicenja za azuriranje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Object[] postojece = service.getTakmicenjeById(id);
                    if (postojece == null) {
                        System.out.println("Takmicenje sa datim ID-om ne postoji!");
                        continue;
                    }
                    System.out.printf("Trenutni podaci: %s, %s, %s, %s, %d\n", 
                        postojece[1], postojece[2], postojece[3], postojece[4], postojece[5]);
                    System.out.print("Unesi novi naziv: ");
                    String naziv = sc.nextLine();
                    System.out.print("Unesi novi datum (dd.MM.yyyy): ");
                    Date datum = dateFormat.parse(sc.nextLine());
                    System.out.print("Unesi novu adresu: ");
                    String adresa = sc.nextLine();
                    System.out.print("Unesi novu drzavu: ");
                    String drzava = sc.nextLine();
                    System.out.print("Unesi novu cenu: ");
                    int cena = Integer.parseInt(sc.nextLine());
                    service.azurirajTakmicenje(id, naziv, datum, adresa, drzava, cena);
                    System.out.println("Takmicenje je uspesno azurirano!");
                } else if (izbor.equals("4")) {
                    System.out.print("Unesi ID takmicenja za brisanje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    service.obrisiTakmicenje(id);
                    System.out.println("Takmicenje je uspesno obrisano!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    public void crudDiscipline() {
        while (true) {
            System.out.println("\nUpravljanje disciplinama:");
            System.out.println("1. Prikazi sve discipline");
            System.out.println("2. Dodaj novu disciplinu");
            System.out.println("3. Azuriraj disciplinu");
            System.out.println("4. Obrisi disciplinu");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> discipline = disciplinaService.getAllDiscipline();
                    System.out.println("\nSve discipline:");
                    System.out.printf("%-5s | %-30s\n", "ID", "Naziv");
                    System.out.println("----------------------------------------");
                    for (Object[] d : discipline) {
                        System.out.printf("%-5d | %-30s\n", d[0], d[1]);
                    }
                } else if (izbor.equals("2")) {
                    System.out.print("Unesi naziv discipline: ");
                    String naziv = sc.nextLine();
                    disciplinaService.kreirajDisciplinu(naziv);
                    System.out.println("Disciplina je uspesno kreirana!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID discipline za azuriranje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Unesi novi naziv discipline: ");
                    String naziv = sc.nextLine();
                    disciplinaService.azurirajDisciplinu(id, naziv);
                    System.out.println("Disciplina je uspesno azurirana!");
                } else if (izbor.equals("4")) {
                    System.out.print("Unesi ID discipline za brisanje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    disciplinaService.obrisiDisciplinu(id);
                    System.out.println("Disciplina je uspesno obrisana!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    public void crudClanovi() {
        while (true) {
            System.out.println("\nUpravljanje clanovima:");
            System.out.println("1. Prikazi sve clanove");
            System.out.println("2. Dodaj novog clana");
            System.out.println("3. Azuriraj clana");
            System.out.println("4. Obrisi clana");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> clanovi = takmicarService.getAllClanovi();
                    System.out.println("\nSvi clanovi:");
                    System.out.printf("%-5s | %-15s | %-15s | %-12s | %-12s | %-15s\n", "ID", "Ime", "Prezime", "Datum rodj", "Datum ucl", "Drzava");
                    System.out.println("-------------------------------------------------------------------------------");
                    for (Object[] c : clanovi) {
                        System.out.printf("%-5d | %-15s | %-15s | %-12s | %-12s | %-15s\n", 
                            c[0], c[1], c[2], c[3], c[4], c[5]);
                    }
                } else if (izbor.equals("2")) {
                    System.out.print("Unesi ime: ");
                    String ime = sc.nextLine();
                    System.out.print("Unesi prezime: ");
                    String prezime = sc.nextLine();
                    System.out.print("Unesi datum rodjenja (dd.MM.yyyy): ");
                    Date datumRodjenja = dateFormat.parse(sc.nextLine());
                    System.out.print("Unesi datum uclanjenja (dd.MM.yyyy): ");
                    Date datumUclanjenja = dateFormat.parse(sc.nextLine());
                    System.out.print("Unesi drzavu: ");
                    String drzava = sc.nextLine();
                    takmicarService.kreirajClana(ime, prezime, datumRodjenja, datumUclanjenja, drzava);
                    System.out.println("Clan je uspesno kreiran!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID clana za azuriranje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Unesi novo ime: ");
                    String ime = sc.nextLine();
                    System.out.print("Unesi novo prezime: ");
                    String prezime = sc.nextLine();
                    System.out.print("Unesi novi datum rodjenja (dd.MM.yyyy): ");
                    Date datumRodjenja = dateFormat.parse(sc.nextLine());
                    System.out.print("Unesi novi datum uclanjenja (dd.MM.yyyy): ");
                    Date datumUclanjenja = dateFormat.parse(sc.nextLine());
                    System.out.print("Unesi novu drzavu: ");
                    String drzava = sc.nextLine();
                    takmicarService.azurirajClana(id, ime, prezime, datumRodjenja, datumUclanjenja, drzava);
                    System.out.println("Clan je uspesno azuriran!");
                } else if (izbor.equals("4")) {
                    System.out.print("Unesi ID clana za brisanje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    takmicarService.obrisiClana(id);
                    System.out.println("Clan je uspesno obrisan!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    public void crudSponzori() {
        while (true) {
            System.out.println("\nUpravljanje sponzorima:");
            System.out.println("1. Prikazi sve sponzore");
            System.out.println("2. Dodaj novog sponzora");
            System.out.println("3. Azuriraj sponzora");
            System.out.println("4. Obrisi sponzora");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> sponzori = sponzorService.getAllSponzori();
                    System.out.println("\nSvi sponzori:");
                    System.out.printf("%-5s | %-20s | %-20s | %-30s\n", "ID", "Naziv", "Vrsta", "Uslovi");
                    System.out.println("--------------------------------------------------------------------------");
                    for (Object[] s : sponzori) {
                        System.out.printf("%-5d | %-20s | %-20s | %-30s\n", s[0], s[1], s[2], s[3]);
                    }
                } else if (izbor.equals("2")) {
                    System.out.print("Unesi naziv sponzora: ");
                    String naziv = sc.nextLine();
                    System.out.print("Unesi vrstu sponzora: ");
                    String vrsta = sc.nextLine();
                    System.out.print("Unesi uslove sponzorstva: ");
                    String uslovi = sc.nextLine();
                    sponzorService.kreirajSponzora(naziv, vrsta, uslovi);
                    System.out.println("Sponzor je uspesno kreiran!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID sponzora za azuriranje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Unesi novi naziv: ");
                    String naziv = sc.nextLine();
                    System.out.print("Unesi novu vrstu: ");
                    String vrsta = sc.nextLine();
                    System.out.print("Unesi nove uslove: ");
                    String uslovi = sc.nextLine();
                    sponzorService.azurirajSponzora(id, naziv, vrsta, uslovi);
                    System.out.println("Sponzor je uspesno azuriran!");
                } else if (izbor.equals("4")) {
                    System.out.print("Unesi ID sponzora za brisanje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    sponzorService.obrisiSponzora(id);
                    System.out.println("Sponzor je uspesno obrisan!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n=== WCA Competition Management System ===");
            System.out.println("Izaberi opciju:");
            System.out.println("--- IZVESTAJI ---");
            System.out.println("1. Prikazi broj takmicara po takmicenju");
            System.out.println("2. Prosek vremena po disciplini i takmicenju");
            System.out.println("3. Takmicari - broj disciplina i medalja po takmicenju");
            System.out.println("4. Dodaj sponzora i povezi sa takmicenjem");
            System.out.println("--- CRUD OPERACIJE ---");
            System.out.println("5. Upravljanje takmicenjima");
            System.out.println("6. Upravljanje disciplinama");
            System.out.println("7. Upravljanje clanovima");
            System.out.println("8. Upravljanje sponzorima");
            System.out.println("9. Upravljanje takmicarima");
            System.out.println("10. Upravljanje rezultatima");
            System.out.println("11. Upravljanje disciplinama na takmicenjima");
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
            } else if (izbor.equals("5")) {
                crudTakmicenja();
            } else if (izbor.equals("6")) {
                crudDiscipline();
            } else if (izbor.equals("7")) {
                crudClanovi();
            } else if (izbor.equals("8")) {
                crudSponzori();
            } else if (izbor.equals("9")) {
                crudTakmicari();
            } else if (izbor.equals("10")) {
                crudRezultati();
            } else if (izbor.equals("11")) {
                crudDisciplineNaTakmicenjima();
            } else if (izbor.equals("0")) {
                System.out.println("Kraj programa.");
                break;
            } else {
                System.out.println("Nepoznata opcija, pokusaj ponovo.");
            }
        }
        sc.close();
    }

    public void crudTakmicari() {
        while (true) {
            System.out.println("\nUpravljanje takmicarima:");
            System.out.println("1. Prikazi sve takmicari");
            System.out.println("2. Kreiraj takmicara od postojeceg clana");
            System.out.println("3. Obrisi takmicara");
            System.out.println("4. Prijavi takmicara na takmicenje");
            System.out.println("5. Odjavi takmicara sa takmicenja");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> takmicari = takmicarService.getAllTakmicari();
                    System.out.println("\nSvi takmicari:");
                    System.out.printf("%-5s | %-15s | %-15s | %-12s | %-15s\n", "ID", "Ime", "Prezime", "Datum rodj", "Drzava");
                    System.out.println("-----------------------------------------------------------------------");
                    for (Object[] t : takmicari) {
                        System.out.printf("%-5d | %-15s | %-15s | %-12s | %-15s\n", 
                            t[0], t[1], t[2], t[3], t[4]);
                    }
                } else if (izbor.equals("2")) {
                    List<Object[]> clanovi = takmicarService.getAllClanovi();
                    System.out.println("\nDostupni clanovi:");
                    System.out.printf("%-5s | %-15s | %-15s\n", "ID", "Ime", "Prezime");
                    System.out.println("----------------------------------------");
                    for (Object[] c : clanovi) {
                        System.out.printf("%-5d | %-15s | %-15s\n", c[0], c[1], c[2]);
                    }
                    System.out.print("Unesi ID clana koji zelite da postavite kao takmicara: ");
                    int clanId = Integer.parseInt(sc.nextLine());
                    takmicarService.kreirajTakmicaraOdClana(clanId);
                    System.out.println("Takmicar je uspesno kreiran!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID takmicara za brisanje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    takmicarService.obrisiTakmicara(id);
                    System.out.println("Takmicar je uspesno obrisan!");
                } else if (izbor.equals("4")) {
                    List<Object[]> takmicari = takmicarService.getAllTakmicari();
                    System.out.println("\nDostupni takmicari:");
                    for (Object[] t : takmicari) {
                        System.out.printf("%-5d | %s %s\n", t[0], t[1], t[2]);
                    }
                    System.out.print("Unesi ID takmicara: ");
                    int takmicarId = Integer.parseInt(sc.nextLine());
                    
                    List<Object[]> takmicenja = service.getAllTakmicenja();
                    System.out.println("\nDostupna takmicenja:");
                    for (Object[] tk : takmicenja) {
                        System.out.printf("%-5d | %s\n", tk[0], tk[1]);
                    }
                    System.out.print("Unesi ID takmicenja: ");
                    int takmicenjeId = Integer.parseInt(sc.nextLine());
                    
                    takmicarService.prijaviTakmicaraNaTakmicenje(takmicarId, takmicenjeId);
                    System.out.println("Takmicar je uspesno prijavljen na takmicenje!");
                } else if (izbor.equals("5")) {
                    System.out.print("Unesi ID takmicara: ");
                    int takmicarId = Integer.parseInt(sc.nextLine());
                    System.out.print("Unesi ID takmicenja: ");
                    int takmicenjeId = Integer.parseInt(sc.nextLine());
                    takmicarService.odjaviTakmicaraSaTakmicenja(takmicarId, takmicenjeId);
                    System.out.println("Takmicar je uspesno odjavljen sa takmicenja!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    public void crudRezultati() {
        while (true) {
            System.out.println("\nUpravljanje rezultatima:");
            System.out.println("1. Prikazi sve rezultate");
            System.out.println("2. Dodaj novi rezultat");
            System.out.println("3. Azuriraj rezultat");
            System.out.println("4. Obrisi rezultat");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> rezultati = disciplinaService.getAllRezultati();
                    System.out.println("\nSvi rezultati:");
                    System.out.printf("%-5s | %-8s | %-10s | %-20s | %-20s | %-15s\n", "ID", "Vreme", "Medalja", "Takmicar", "Takmicenje", "Disciplina");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    for (Object[] r : rezultati) {
                        System.out.printf("%-5d | %-8.2f | %-10s | %-20s | %-20s | %-15s\n", 
                            r[0], r[1], r[2], r[3], r[4], r[5]);
                    }
                } else if (izbor.equals("2")) {
                    List<Object[]> takmicari = takmicarService.getAllTakmicari();
                    System.out.println("\nDostupni takmicari:");
                    for (Object[] t : takmicari) {
                        System.out.printf("%-5d | %s %s\n", t[0], t[1], t[2]);
                    }
                    System.out.print("Unesi ID takmicara: ");
                    int takmicarId = Integer.parseInt(sc.nextLine());
                    
                    List<Object[]> takmicenja = service.getAllTakmicenja();
                    System.out.println("\nDostupna takmicenja:");
                    for (Object[] tk : takmicenja) {
                        System.out.printf("%-5d | %s\n", tk[0], tk[1]);
                    }
                    System.out.print("Unesi ID takmicenja: ");
                    int takmicenjeId = Integer.parseInt(sc.nextLine());
                    
                    List<Object[]> discipline = disciplinaService.getAllDiscipline();
                    System.out.println("\nDostupne discipline:");
                    for (Object[] d : discipline) {
                        System.out.printf("%-5d | %s\n", d[0], d[1]);
                    }
                    System.out.print("Unesi ID discipline: ");
                    int disciplinaId = Integer.parseInt(sc.nextLine());
                    
                    System.out.print("Unesi vreme: ");
                    double vreme = Double.parseDouble(sc.nextLine());
                    System.out.print("Unesi medalju (Zlatna/Srebrna/Bronzana ili prazno): ");
                    String medalja = sc.nextLine();
                    if (medalja.trim().isEmpty()) medalja = null;
                    
                    disciplinaService.kreirajRezultat(vreme, medalja, takmicarId, takmicenjeId, disciplinaId);
                    System.out.println("Rezultat je uspesno kreiran!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID rezultata za azuriranje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Unesi novo vreme: ");
                    double vreme = Double.parseDouble(sc.nextLine());
                    System.out.print("Unesi novu medalju (Zlatna/Srebrna/Bronzana ili prazno): ");
                    String medalja = sc.nextLine();
                    if (medalja.trim().isEmpty()) medalja = null;
                    disciplinaService.azurirajRezultat(id, vreme, medalja);
                    System.out.println("Rezultat je uspesno azuriran!");
                } else if (izbor.equals("4")) {
                    System.out.print("Unesi ID rezultata za brisanje: ");
                    int id = Integer.parseInt(sc.nextLine());
                    disciplinaService.obrisiRezultat(id);
                    System.out.println("Rezultat je uspesno obrisan!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    public void crudDisciplineNaTakmicenjima() {
        while (true) {
            System.out.println("\nUpravljanje disciplinama na takmicenjima:");
            System.out.println("1. Prikazi discipline na takmicenju");
            System.out.println("2. Dodaj disciplinu na takmicenje");
            System.out.println("3. Ukloni disciplinu sa takmicenja");
            System.out.println("0. Povratak na glavni meni");
            System.out.print("> ");
            String izbor = sc.nextLine();

            try {
                if (izbor.equals("1")) {
                    List<Object[]> takmicenja = service.getAllTakmicenja();
                    System.out.println("\nDostupna takmicenja:");
                    for (Object[] tk : takmicenja) {
                        System.out.printf("%-5d | %s\n", tk[0], tk[1]);
                    }
                    System.out.print("Unesi ID takmicenja: ");
                    int takmicenjeId = Integer.parseInt(sc.nextLine());
                    
                    List<Object[]> discipline = disciplinaService.getDisciplineNaTakmicenju(takmicenjeId);
                    System.out.println("\nDiscipline na takmicenju:");
                    System.out.printf("%-5s | %-30s\n", "ID", "Naziv");
                    System.out.println("----------------------------------------");
                    for (Object[] d : discipline) {
                        System.out.printf("%-5d | %-30s\n", d[0], d[1]);
                    }
                } else if (izbor.equals("2")) {
                    List<Object[]> takmicenja = service.getAllTakmicenja();
                    System.out.println("\nDostupna takmicenja:");
                    for (Object[] tk : takmicenja) {
                        System.out.printf("%-5d | %s\n", tk[0], tk[1]);
                    }
                    System.out.print("Unesi ID takmicenja: ");
                    int takmicenjeId = Integer.parseInt(sc.nextLine());
                    
                    List<Object[]> discipline = disciplinaService.getAllDiscipline();
                    System.out.println("\nDostupne discipline:");
                    for (Object[] d : discipline) {
                        System.out.printf("%-5d | %s\n", d[0], d[1]);
                    }
                    System.out.print("Unesi ID discipline: ");
                    int disciplinaId = Integer.parseInt(sc.nextLine());
                    
                    disciplinaService.dodajDisciplinuNaTakmicenje(takmicenjeId, disciplinaId);
                    System.out.println("Disciplina je uspesno dodana na takmicenje!");
                } else if (izbor.equals("3")) {
                    System.out.print("Unesi ID takmicenja: ");
                    int takmicenjeId = Integer.parseInt(sc.nextLine());
                    System.out.print("Unesi ID discipline: ");
                    int disciplinaId = Integer.parseInt(sc.nextLine());
                    disciplinaService.ukloniDisciplinuSaTakmicenja(takmicenjeId, disciplinaId);
                    System.out.println("Disciplina je uspesno uklonjena sa takmicenja!");
                } else if (izbor.equals("0")) {
                    break;
                } else {
                    System.out.println("Nepoznata opcija!");
                }
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }
}
