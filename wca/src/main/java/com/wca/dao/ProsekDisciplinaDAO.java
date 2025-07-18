package com.wca.dao;

import com.wca.dto.ProsekDisciplinaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProsekDisciplinaDAO {
    private Connection conn;

    public ProsekDisciplinaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<ProsekDisciplinaDTO> getProsekPoDiscipliniTakmicenju() throws Exception {
        String sql = "SELECT t.NAZ_TKM, d.NAZ_DSC, ROUND(AVG(r.VRM_REZ), 2) AS prosecno_vreme, COUNT(r.ID_REZ) AS broj_rezultata " +
                "FROM Takmicenje t " +
                "JOIN Sadrzi s ON t.ID_TKM = s.Takmicenje_ID_TKM " +
                "JOIN Disciplina d ON s.Disciplina_ID_DSC = d.ID_DSC " +
                "LEFT JOIN Rezultat r ON r.Sadrzi_Takmicenje_ID_TKM = t.ID_TKM AND r.Sadrzi_Disciplina_ID_DSC = d.ID_DSC " +
                "GROUP BY t.NAZ_TKM, d.NAZ_DSC " +
                "HAVING COUNT(r.ID_REZ) > 1 " +
                "ORDER BY prosecno_vreme ASC";
        List<ProsekDisciplinaDTO> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new ProsekDisciplinaDTO(
                    rs.getString("NAZ_TKM"),
                    rs.getString("NAZ_DSC"),
                    rs.getDouble("prosecno_vreme"),
                    rs.getInt("broj_rezultata")
                ));
            }
        }
        return result;
    }

    public void kreirajDisciplinu(String naziv) throws Exception {
        int id = 1;
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID_DSC),0)+1 FROM Disciplina")) {
            if (rs.next()) id = rs.getInt(1);
        }
        
        String sql = "INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, naziv);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getAllDiscipline() throws Exception {
        String sql = "SELECT ID_DSC, NAZ_DSC FROM Disciplina ORDER BY NAZ_DSC";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt("ID_DSC"),
                    rs.getString("NAZ_DSC")
                });
            }
        }
        return result;
    }

    public void azurirajDisciplinu(int id, String naziv) throws Exception {
        String sql = "UPDATE Disciplina SET NAZ_DSC = ? WHERE ID_DSC = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, naziv);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void obrisiDisciplinu(int id) throws Exception {
        String sql = "DELETE FROM Disciplina WHERE ID_DSC = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void kreirajRezultat(double vreme, String medalja, int takmicarId, int takmicenjeId, int disciplinaId) throws Exception {
        int id = 1;
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID_REZ),0)+1 FROM Rezultat")) {
            if (rs.next()) id = rs.getInt(1);
        }
        
        String sql = "INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setDouble(2, vreme);
            ps.setString(3, medalja);
            ps.setInt(4, takmicarId);
            ps.setInt(5, takmicenjeId);
            ps.setInt(6, takmicenjeId);
            ps.setInt(7, disciplinaId);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getAllRezultati() throws Exception {
        String sql = "SELECT r.ID_REZ, r.VRM_REZ, r.POD_REZ, " +
                    "c.IME_CLN || ' ' || c.PRZ_CLN AS takmicar, " +
                    "t.NAZ_TKM, d.NAZ_DSC " +
                    "FROM Rezultat r " +
                    "JOIN Clan c ON r.Takmici_se_Takmicar_ID_CLN = c.ID_CLN " +
                    "JOIN Takmicenje t ON r.Takmici_se_Takmicenje_ID_TKM = t.ID_TKM " +
                    "JOIN Disciplina d ON r.Sadrzi_Disciplina_ID_DSC = d.ID_DSC " +
                    "ORDER BY r.VRM_REZ";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt("ID_REZ"),
                    rs.getDouble("VRM_REZ"),
                    rs.getString("POD_REZ"),
                    rs.getString("takmicar"),
                    rs.getString("NAZ_TKM"),
                    rs.getString("NAZ_DSC")
                });
            }
        }
        return result;
    }

    public void azurirajRezultat(int id, double vreme, String medalja) throws Exception {
        String sql = "UPDATE Rezultat SET VRM_REZ = ?, POD_REZ = ? WHERE ID_REZ = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, vreme);
            ps.setString(2, medalja);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void obrisiRezultat(int id) throws Exception {
        String sql = "DELETE FROM Rezultat WHERE ID_REZ = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void dodajDisciplinuNaTakmicenje(int takmicenjeId, int disciplinaId) throws Exception {
        String sql = "INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicenjeId);
            ps.setInt(2, disciplinaId);
            ps.executeUpdate();
        }
    }

    public void ukloniDisciplinuSaTakmicenja(int takmicenjeId, int disciplinaId) throws Exception {
        String sql = "DELETE FROM Sadrzi WHERE Takmicenje_ID_TKM = ? AND Disciplina_ID_DSC = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicenjeId);
            ps.setInt(2, disciplinaId);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getDisciplineNaTakmicenju(int takmicenjeId) throws Exception {
        String sql = "SELECT d.ID_DSC, d.NAZ_DSC " +
                    "FROM Sadrzi s " +
                    "JOIN Disciplina d ON s.Disciplina_ID_DSC = d.ID_DSC " +
                    "WHERE s.Takmicenje_ID_TKM = ? " +
                    "ORDER BY d.NAZ_DSC";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicenjeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Object[]{
                        rs.getInt("ID_DSC"),
                        rs.getString("NAZ_DSC")
                    });
                }
            }
        }
        return result;
    }
}
