package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.TakmicarMedaljeDAO;
import com.wca.dto.TakmicarMedaljeDTO;
import java.sql.Connection;
import java.util.List;

public class TakmicarMedaljeService {
    public List<TakmicarMedaljeDTO> getTakmicarMedaljePoTakmicenju() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            return dao.getTakmicarMedaljePoTakmicenju();
        }
    }

    public void kreirajClana(String ime, String prezime, java.util.Date datumRodjenja, java.util.Date datumUclanjenja, String drzava) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.kreirajClana(ime, prezime, datumRodjenja, datumUclanjenja, drzava);
        }
    }

    public List<Object[]> getAllClanovi() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            return dao.getAllClanovi();
        }
    }

    public void azurirajClana(int id, String ime, String prezime, java.util.Date datumRodjenja, java.util.Date datumUclanjenja, String drzava) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.azurirajClana(id, ime, prezime, datumRodjenja, datumUclanjenja, drzava);
        }
    }

    public void obrisiClana(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.obrisiClana(id);
        }
    }

    public void kreirajTakmicaraOdClana(int clanId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.kreirajTakmicaraOdClana(clanId);
        }
    }

    public List<Object[]> getAllTakmicari() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            return dao.getAllTakmicari();
        }
    }

    public void obrisiTakmicara(int clanId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.obrisiTakmicara(clanId);
        }
    }

    public void prijaviTakmicaraNaTakmicenje(int takmicarId, int takmicenjeId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.prijaviTakmicaraNaTakmicenje(takmicarId, takmicenjeId);
        }
    }

    public void odjaviTakmicaraSaTakmicenja(int takmicarId, int takmicenjeId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            dao.odjaviTakmicaraSaTakmicenja(takmicarId, takmicenjeId);
        }
    }

    public List<Object[]> getTakmicariNaTakmicenju(int takmicenjeId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            return dao.getTakmicariNaTakmicenju(takmicenjeId);
        }
    }
}
