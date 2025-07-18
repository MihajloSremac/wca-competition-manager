package com.wca.dao;

import com.wca.dto.TakmicenjeBrojTakmicaraDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TakmicenjeDAO {
    private Connection conn;

    public TakmicenjeDAO(Connection conn) {
        this.conn = conn;
    }

    public List<TakmicenjeBrojTakmicaraDTO> getBrojTakmicaraPoTakmicenju() throws Exception {
        String sql = "SELECT t.NAZ_TKM, COUNT(ts.Takmicar_ID_CLN) AS broj_takmicara " +
                "FROM Takmicenje t " +
                "JOIN Takmici_se ts ON t.ID_TKM = ts.Takmicenje_ID_TKM " +
                "GROUP BY t.NAZ_TKM " +
                "ORDER BY broj_takmicara DESC";
        List<TakmicenjeBrojTakmicaraDTO> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new TakmicenjeBrojTakmicaraDTO(
                    rs.getString("NAZ_TKM"),
                    rs.getInt("broj_takmicara")
                ));
            }
        }
        return result;
    }

    public void kreirajTakmicenje(String naziv, Date datum, String adresa, String drzava, int cena) throws Exception {
        int id = 1;
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID_TKM),0)+1 FROM Takmicenje")) {
            if (rs.next()) id = rs.getInt(1);
        }
        
        String sql = "INSERT INTO Takmicenje (ID_TKM, NAZ_TKM, DT_TKM, ADR_TKM, DRZ_TKM, CEN_TKM) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, naziv);
            ps.setDate(3, new java.sql.Date(datum.getTime()));
            ps.setString(4, adresa);
            ps.setString(5, drzava);
            ps.setInt(6, cena);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getAllTakmicenja() throws Exception {
        String sql = "SELECT ID_TKM, NAZ_TKM, DT_TKM, ADR_TKM, DRZ_TKM, CEN_TKM FROM Takmicenje ORDER BY DT_TKM";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt("ID_TKM"),
                    rs.getString("NAZ_TKM"),
                    rs.getDate("DT_TKM"),
                    rs.getString("ADR_TKM"),
                    rs.getString("DRZ_TKM"),
                    rs.getInt("CEN_TKM")
                });
            }
        }
        return result;
    }

    public void azurirajTakmicenje(int id, String naziv, Date datum, String adresa, String drzava, int cena) throws Exception {
        String sql = "UPDATE Takmicenje SET NAZ_TKM = ?, DT_TKM = ?, ADR_TKM = ?, DRZ_TKM = ?, CEN_TKM = ? WHERE ID_TKM = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, naziv);
            ps.setDate(2, new java.sql.Date(datum.getTime()));
            ps.setString(3, adresa);
            ps.setString(4, drzava);
            ps.setInt(5, cena);
            ps.setInt(6, id);
            ps.executeUpdate();
        }
    }

    public void obrisiTakmicenje(int id) throws Exception {
        String sql = "DELETE FROM Takmicenje WHERE ID_TKM = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Object[] getTakmicenjeById(int id) throws Exception {
        String sql = "SELECT ID_TKM, NAZ_TKM, DT_TKM, ADR_TKM, DRZ_TKM, CEN_TKM FROM Takmicenje WHERE ID_TKM = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                        rs.getInt("ID_TKM"),
                        rs.getString("NAZ_TKM"),
                        rs.getDate("DT_TKM"),
                        rs.getString("ADR_TKM"),
                        rs.getString("DRZ_TKM"),
                        rs.getInt("CEN_TKM")
                    };
                }
            }
        }
        return null;
    }
}
