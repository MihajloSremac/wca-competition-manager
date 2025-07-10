package com.wca.dao;

import com.wca.dto.ProsekDisciplinaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
