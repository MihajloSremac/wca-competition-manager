package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.ProsekDisciplinaDAO;
import com.wca.dto.ProsekDisciplinaDTO;
import java.sql.Connection;
import java.util.List;

public class ProsekDisciplinaService {
    public List<ProsekDisciplinaDTO> getProsekPoDiscipliniTakmicenju() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            return dao.getProsekPoDiscipliniTakmicenju();
        }
    }

    public void kreirajDisciplinu(String naziv) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.kreirajDisciplinu(naziv);
        }
    }

    public List<Object[]> getAllDiscipline() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            return dao.getAllDiscipline();
        }
    }

    public void azurirajDisciplinu(int id, String naziv) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.azurirajDisciplinu(id, naziv);
        }
    }

    public void obrisiDisciplinu(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.obrisiDisciplinu(id);
        }
    }

    public void kreirajRezultat(double vreme, String medalja, int takmicarId, int takmicenjeId, int disciplinaId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.kreirajRezultat(vreme, medalja, takmicarId, takmicenjeId, disciplinaId);
        }
    }

    public List<Object[]> getAllRezultati() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            return dao.getAllRezultati();
        }
    }

    public void azurirajRezultat(int id, double vreme, String medalja) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.azurirajRezultat(id, vreme, medalja);
        }
    }

    public void obrisiRezultat(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.obrisiRezultat(id);
        }
    }

    public void dodajDisciplinuNaTakmicenje(int takmicenjeId, int disciplinaId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.dodajDisciplinuNaTakmicenje(takmicenjeId, disciplinaId);
        }
    }

    public void ukloniDisciplinuSaTakmicenja(int takmicenjeId, int disciplinaId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            dao.ukloniDisciplinuSaTakmicenja(takmicenjeId, disciplinaId);
        }
    }

    public List<Object[]> getDisciplineNaTakmicenju(int takmicenjeId) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            return dao.getDisciplineNaTakmicenju(takmicenjeId);
        }
    }
}
