package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.TakmicenjeDAO;
import com.wca.dto.TakmicenjeBrojTakmicaraDTO;
import java.sql.Connection;
import java.util.List;
import java.util.Date;

public class TakmicenjeService {
    public List<TakmicenjeBrojTakmicaraDTO> getBrojTakmicaraPoTakmicenju() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            return dao.getBrojTakmicaraPoTakmicenju();
        }
    }

    public void kreirajTakmicenje(String naziv, Date datum, String adresa, String drzava, int cena) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            dao.kreirajTakmicenje(naziv, datum, adresa, drzava, cena);
        }
    }

    public List<Object[]> getAllTakmicenja() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            return dao.getAllTakmicenja();
        }
    }

    public void azurirajTakmicenje(int id, String naziv, Date datum, String adresa, String drzava, int cena) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            dao.azurirajTakmicenje(id, naziv, datum, adresa, drzava, cena);
        }
    }

    public void obrisiTakmicenje(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            dao.obrisiTakmicenje(id);
        }
    }

    public Object[] getTakmicenjeById(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            return dao.getTakmicenjeById(id);
        }
    }
}
