package com.wca.dao;

import com.wca.dto.TakmicarMedaljeDTO;
import java.sql.*;
import java.util.*;

public class TakmicarMedaljeDAO {
    private Connection conn;
    public TakmicarMedaljeDAO(Connection conn) { this.conn = conn; }

    public List<TakmicarMedaljeDTO> getTakmicarMedaljePoTakmicenju() throws Exception {
        //Count ne broji null, zato kada vrati null nece ga izbrojati unutar COUNT(DISTINCT CASE WHEN r.POD_REZ IS NOT NULL THEN r.Sadrzi_Disciplina_ID_DSC END) 
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
}
