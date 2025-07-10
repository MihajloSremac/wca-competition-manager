package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.TakmicenjeDAO;
import com.wca.dto.TakmicenjeBrojTakmicaraDTO;
import java.sql.Connection;
import java.util.List;

public class TakmicenjeService {
    public List<TakmicenjeBrojTakmicaraDTO> getBrojTakmicaraPoTakmicenju() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicenjeDAO dao = new TakmicenjeDAO(conn);
            return dao.getBrojTakmicaraPoTakmicenju();
        }
    }
}
