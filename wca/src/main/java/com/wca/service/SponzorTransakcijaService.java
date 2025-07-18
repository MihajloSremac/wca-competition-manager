package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.SponzorTransakcijaDAO;
import com.wca.dto.SponzorTransakcijaDTO;
import java.util.List;

public class SponzorTransakcijaService {
    public void dodajSponzoraITakmicenje(SponzorTransakcijaDTO dto) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.dodajSponzoraITakmicenje(dto);
        }
    }

    public void kreirajSponzora(String naziv, String vrsta, String uslovi) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.kreirajSponzora(naziv, vrsta, uslovi);
        }
    }

    public List<Object[]> getAllSponzori() throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            return dao.getAllSponzori();
        }
    }

    public void azurirajSponzora(int id, String naziv, String vrsta, String uslovi) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.azurirajSponzora(id, naziv, vrsta, uslovi);
        }
    }

    public void obrisiSponzora(int id) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.obrisiSponzora(id);
        }
    }

    public void dodajSponzorstvaNaTakmicenje(int sponzorId, int takmicenjeId) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.dodajSponzorstvaNaTakmicenje(sponzorId, takmicenjeId);
        }
    }

    public void ukloniSponzorstvaSaTakmicenja(int sponzorId, int takmicenjeId) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.ukloniSponzorstvaSaTakmicenja(sponzorId, takmicenjeId);
        }
    }

    public List<Object[]> getSponzoriTakmicenja(int takmicenjeId) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            return dao.getSponzoriTakmicenja(takmicenjeId);
        }
    }

    public List<Object[]> getTakmicenjaSponzora(int sponzorId) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            return dao.getTakmicenjaSponzora(sponzorId);
        }
    }
}
