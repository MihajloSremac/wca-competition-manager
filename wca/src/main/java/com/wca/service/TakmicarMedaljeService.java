package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.TakmicarMedaljeDAO;
import com.wca.dto.TakmicarMedaljeDTO;
import java.sql.Connection;
import java.util.List;

public class TakmicarMedaljeService {
    public List<TakmicarMedaljeDTO> getTakmicarMedaljePoTakmicenju() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TakmicarMedaljeDAO dao = new TakmicarMedaljeDAO(conn);
            return dao.getTakmicarMedaljePoTakmicenju();
        }
    }
}
