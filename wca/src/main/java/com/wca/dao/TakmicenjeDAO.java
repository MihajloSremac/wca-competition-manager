package com.wca.dao;

import com.wca.dto.TakmicenjeBrojTakmicaraDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
