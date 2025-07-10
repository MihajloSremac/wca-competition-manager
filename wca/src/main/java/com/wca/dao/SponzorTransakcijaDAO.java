package com.wca.dao;

import com.wca.dto.SponzorTransakcijaDTO;
import java.sql.*;

public class SponzorTransakcijaDAO {
    private Connection conn;
    public SponzorTransakcijaDAO(Connection conn) { this.conn = conn; }

    public void dodajSponzoraITakmicenje(SponzorTransakcijaDTO dto) throws Exception {
        try {
            conn.setAutoCommit(false);
            //Insert into Sponzor
            int idSponzor = -1;
            //nvl vraca default vrednost (u mom slucaju 0), ako imam NULL umesto neke vrednosti
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
            //Insert into Sponzorise
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
}
