package com.wca.dao;

import com.wca.dto.TakmicarMedaljeDTO;
import java.sql.*;
import java.util.*;

public class TakmicarMedaljeDAO {
    private Connection conn;
    public TakmicarMedaljeDAO(Connection conn) { this.conn = conn; }

    public List<TakmicarMedaljeDTO> getTakmicarMedaljePoTakmicenju() throws Exception {
        String sql =
            "SELECT t.NAZ_TKM, c.IME_CLN || ' ' || c.PRZ_CLN AS takmicar, " +
            "       COUNT(DISTINCT CASE WHEN r.POD_REZ IS NOT NULL THEN r.Sadrzi_Disciplina_ID_DSC END) AS broj_disciplina, " +
            "       COUNT(CASE WHEN r.POD_REZ = 'Zlatna' THEN 1 END) AS zlato, " +
            "       COUNT(CASE WHEN r.POD_REZ = 'Srebrna' THEN 1 END) AS srebro, " +
            "       COUNT(CASE WHEN r.POD_REZ = 'Bronzana' THEN 1 END) AS bronza, " +
            "       AVG(CASE WHEN r.POD_REZ IS NOT NULL THEN r.VRM_REZ END) AS prosecno_vreme " +
            "FROM Takmicenje t " +
            "JOIN Takmici_se ts ON t.ID_TKM = ts.Takmicenje_ID_TKM " +
            "JOIN Clan c ON ts.Takmicar_ID_CLN = c.ID_CLN " +
            "LEFT JOIN Rezultat r ON r.Takmici_se_Takmicar_ID_CLN = ts.Takmicar_ID_CLN " +
            "                     AND r.Takmici_se_Takmicenje_ID_TKM = ts.Takmicenje_ID_TKM " +
            "GROUP BY t.NAZ_TKM, c.IME_CLN, c.PRZ_CLN " +
            "HAVING COUNT(CASE WHEN r.POD_REZ IS NOT NULL THEN 1 END) > 0 " +
            "ORDER BY zlato DESC, srebro DESC, bronza DESC, prosecno_vreme ASC, t.NAZ_TKM, takmicar";
        List<TakmicarMedaljeDTO> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new TakmicarMedaljeDTO(
                    rs.getString("NAZ_TKM"),
                    rs.getString("takmicar"),
                    rs.getInt("broj_disciplina"),
                    rs.getInt("zlato"),
                    rs.getInt("srebro"),
                    rs.getInt("bronza")
                ));
            }
        }
        return result;
    }

    public void kreirajClana(String ime, String prezime, java.util.Date datumRodjenja, java.util.Date datumUclanjenja, String drzava) throws Exception {
        int id = 1;
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID_CLN),0)+1 FROM Clan")) {
            if (rs.next()) id = rs.getInt(1);
        }
        
        String sql = "INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, ime);
            ps.setString(3, prezime);
            ps.setDate(4, new java.sql.Date(datumRodjenja.getTime()));
            ps.setDate(5, new java.sql.Date(datumUclanjenja.getTime()));
            ps.setString(6, drzava);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getAllClanovi() throws Exception {
        String sql = "SELECT ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN FROM Clan ORDER BY PRZ_CLN, IME_CLN";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt("ID_CLN"),
                    rs.getString("IME_CLN"),
                    rs.getString("PRZ_CLN"),
                    rs.getDate("DT_RDJ_CLN"),
                    rs.getDate("DT_UCL_CLN"),
                    rs.getString("DRZ_CLN")
                });
            }
        }
        return result;
    }

    public void azurirajClana(int id, String ime, String prezime, java.util.Date datumRodjenja, java.util.Date datumUclanjenja, String drzava) throws Exception {
        String sql = "UPDATE Clan SET IME_CLN = ?, PRZ_CLN = ?, DT_RDJ_CLN = ?, DT_UCL_CLN = ?, DRZ_CLN = ? WHERE ID_CLN = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ime);
            ps.setString(2, prezime);
            ps.setDate(3, new java.sql.Date(datumRodjenja.getTime()));
            ps.setDate(4, new java.sql.Date(datumUclanjenja.getTime()));
            ps.setString(5, drzava);
            ps.setInt(6, id);
            ps.executeUpdate();
        }
    }

    public void obrisiClana(int id) throws Exception {
        String sql = "DELETE FROM Clan WHERE ID_CLN = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void kreirajTakmicaraOdClana(int clanId) throws Exception {
        String sql = "INSERT INTO Takmicar (ID_CLN) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clanId);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getAllTakmicari() throws Exception {
        String sql = "SELECT t.ID_CLN, c.IME_CLN, c.PRZ_CLN, c.DT_RDJ_CLN, c.DRZ_CLN " +
                    "FROM Takmicar t " +
                    "JOIN Clan c ON t.ID_CLN = c.ID_CLN " +
                    "ORDER BY c.PRZ_CLN, c.IME_CLN";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt("ID_CLN"),
                    rs.getString("IME_CLN"),
                    rs.getString("PRZ_CLN"),
                    rs.getDate("DT_RDJ_CLN"),
                    rs.getString("DRZ_CLN")
                });
            }
        }
        return result;
    }

    public void obrisiTakmicara(int clanId) throws Exception {
        String sql = "DELETE FROM Takmicar WHERE ID_CLN = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clanId);
            ps.executeUpdate();
        }
    }

    public void prijaviTakmicaraNaTakmicenje(int takmicarId, int takmicenjeId) throws Exception {
        String sql = "INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicarId);
            ps.setInt(2, takmicenjeId);
            ps.executeUpdate();
        }
    }

    public void odjaviTakmicaraSaTakmicenja(int takmicarId, int takmicenjeId) throws Exception {
        String sql = "DELETE FROM Takmici_se WHERE Takmicar_ID_CLN = ? AND Takmicenje_ID_TKM = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicarId);
            ps.setInt(2, takmicenjeId);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getTakmicariNaTakmicenju(int takmicenjeId) throws Exception {
        String sql = "SELECT c.ID_CLN, c.IME_CLN, c.PRZ_CLN, c.DRZ_CLN " +
                    "FROM Takmici_se ts " +
                    "JOIN Clan c ON ts.Takmicar_ID_CLN = c.ID_CLN " +
                    "WHERE ts.Takmicenje_ID_TKM = ? " +
                    "ORDER BY c.PRZ_CLN, c.IME_CLN";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicenjeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Object[]{
                        rs.getInt("ID_CLN"),
                        rs.getString("IME_CLN"),
                        rs.getString("PRZ_CLN"),
                        rs.getString("DRZ_CLN")
                    });
                }
            }
        }
        return result;
    }
}
