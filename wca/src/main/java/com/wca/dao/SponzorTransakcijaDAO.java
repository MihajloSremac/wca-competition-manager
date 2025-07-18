package com.wca.dao;

import com.wca.dto.SponzorTransakcijaDTO;
import java.sql.*;
import java.util.*;

public class SponzorTransakcijaDAO {
    private Connection conn;
    public SponzorTransakcijaDAO(Connection conn) { this.conn = conn; }

    public void dodajSponzoraITakmicenje(SponzorTransakcijaDTO dto) throws Exception {
        try {
            conn.setAutoCommit(false);
            int idSponzor = -1;
            try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID_SPZ),0)+1 FROM Sponzor")) {
                if (rs.next()) idSponzor = rs.getInt(1);
            }
            try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Sponzor (ID_SPZ, NAZ_SPZ, VRS_SPZ, USL_SPZ) VALUES (?, ?, ?, ?)")
            ) {
                ps.setInt(1, idSponzor);
                ps.setString(2, dto.getNaziv());
                ps.setString(3, dto.getVrsta());
                ps.setString(4, dto.getUslovi());
                ps.executeUpdate();
            }
            try (PreparedStatement ps2 = conn.prepareStatement(
                "INSERT INTO Sponzorise (Sponzor_ID_SPZ, Takmicenje_ID_TKM) VALUES (?, ?)")) {
                ps2.setInt(1, idSponzor);
                ps2.setInt(2, dto.getTakmicenjeId());
                ps2.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void kreirajSponzora(String naziv, String vrsta, String uslovi) throws Exception {
        int id = 1;
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID_SPZ),0)+1 FROM Sponzor")) {
            if (rs.next()) id = rs.getInt(1);
        }
        
        String sql = "INSERT INTO Sponzor (ID_SPZ, NAZ_SPZ, VRS_SPZ, USL_SPZ) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, naziv);
            ps.setString(3, vrsta);
            ps.setString(4, uslovi);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getAllSponzori() throws Exception {
        String sql = "SELECT ID_SPZ, NAZ_SPZ, VRS_SPZ, USL_SPZ FROM Sponzor ORDER BY NAZ_SPZ";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt("ID_SPZ"),
                    rs.getString("NAZ_SPZ"),
                    rs.getString("VRS_SPZ"),
                    rs.getString("USL_SPZ")
                });
            }
        }
        return result;
    }

    public void azurirajSponzora(int id, String naziv, String vrsta, String uslovi) throws Exception {
        String sql = "UPDATE Sponzor SET NAZ_SPZ = ?, VRS_SPZ = ?, USL_SPZ = ? WHERE ID_SPZ = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, naziv);
            ps.setString(2, vrsta);
            ps.setString(3, uslovi);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    public void obrisiSponzora(int id) throws Exception {
        String sql = "DELETE FROM Sponzor WHERE ID_SPZ = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void dodajSponzorstvaNaTakmicenje(int sponzorId, int takmicenjeId) throws Exception {
        String sql = "INSERT INTO Sponzorise (Sponzor_ID_SPZ, Takmicenje_ID_TKM) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sponzorId);
            ps.setInt(2, takmicenjeId);
            ps.executeUpdate();
        }
    }

    public void ukloniSponzorstvaSaTakmicenja(int sponzorId, int takmicenjeId) throws Exception {
        String sql = "DELETE FROM Sponzorise WHERE Sponzor_ID_SPZ = ? AND Takmicenje_ID_TKM = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sponzorId);
            ps.setInt(2, takmicenjeId);
            ps.executeUpdate();
        }
    }

    public List<Object[]> getSponzoriTakmicenja(int takmicenjeId) throws Exception {
        String sql = "SELECT s.ID_SPZ, s.NAZ_SPZ, s.VRS_SPZ, s.USL_SPZ " +
                    "FROM Sponzorise sp " +
                    "JOIN Sponzor s ON sp.Sponzor_ID_SPZ = s.ID_SPZ " +
                    "WHERE sp.Takmicenje_ID_TKM = ? " +
                    "ORDER BY s.NAZ_SPZ";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, takmicenjeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Object[]{
                        rs.getInt("ID_SPZ"),
                        rs.getString("NAZ_SPZ"),
                        rs.getString("VRS_SPZ"),
                        rs.getString("USL_SPZ")
                    });
                }
            }
        }
        return result;
    }

    public List<Object[]> getTakmicenjaSponzora(int sponzorId) throws Exception {
        String sql = "SELECT t.ID_TKM, t.NAZ_TKM, t.DT_TKM, t.ADR_TKM, t.DRZ_TKM " +
                    "FROM Sponzorise sp " +
                    "JOIN Takmicenje t ON sp.Takmicenje_ID_TKM = t.ID_TKM " +
                    "WHERE sp.Sponzor_ID_SPZ = ? " +
                    "ORDER BY t.DT_TKM";
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sponzorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Object[]{
                        rs.getInt("ID_TKM"),
                        rs.getString("NAZ_TKM"),
                        rs.getDate("DT_TKM"),
                        rs.getString("ADR_TKM"),
                        rs.getString("DRZ_TKM")
                    });
                }
            }
        }
        return result;
    }
}
