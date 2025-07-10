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
            "       COUNT(DISTINCT r.Sadrzi_Disciplina_ID_DSC) AS broj_disciplina, " +
            "       SUM(CASE WHEN r.rank = 1 THEN 1 ELSE 0 END) AS zlato, " +
            "       SUM(CASE WHEN r.rank = 2 THEN 1 ELSE 0 END) AS srebro, " +
            "       SUM(CASE WHEN r.rank = 3 THEN 1 ELSE 0 END) AS bronza " +
            "FROM Takmicenje t " +
            "JOIN Takmici_se ts ON t.ID_TKM = ts.Takmicenje_ID_TKM " +
            "JOIN Clan c ON ts.Takmicar_ID_CLN = c.ID_CLN " +
            "LEFT JOIN ( " +
            "    SELECT r.*, " +
            "           RANK() OVER (PARTITION BY r.Sadrzi_Takmicenje_ID_TKM, r.Sadrzi_Disciplina_ID_DSC ORDER BY r.VRM_REZ ASC) AS rank " +
            "    FROM Rezultat r " +
            ") r ON r.Takmici_se_Takmicar_ID_CLN = ts.Takmicar_ID_CLN AND r.Takmici_se_Takmicenje_ID_TKM = ts.Takmicenje_ID_TKM " +
            "GROUP BY t.NAZ_TKM, c.IME_CLN, c.PRZ_CLN " +
            "ORDER BY zlato DESC, srebro DESC, bronza DESC, t.NAZ_TKM, takmicar";
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
}
