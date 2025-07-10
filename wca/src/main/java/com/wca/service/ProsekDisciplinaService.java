package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.ProsekDisciplinaDAO;
import com.wca.dto.ProsekDisciplinaDTO;
import java.sql.Connection;
import java.util.List;

public class ProsekDisciplinaService {
    public List<ProsekDisciplinaDTO> getProsekPoDiscipliniTakmicenju() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ProsekDisciplinaDAO dao = new ProsekDisciplinaDAO(conn);
            return dao.getProsekPoDiscipliniTakmicenju();
        }
    }
}
