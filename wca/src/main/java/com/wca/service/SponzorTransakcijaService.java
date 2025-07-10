package com.wca.service;

import com.wca.config.DatabaseConnection;
import com.wca.dao.SponzorTransakcijaDAO;
import com.wca.dto.SponzorTransakcijaDTO;

public class SponzorTransakcijaService {
    public void dodajSponzoraITakmicenje(SponzorTransakcijaDTO dto) throws Exception {
        try (var conn = DatabaseConnection.getConnection()) {
            SponzorTransakcijaDAO dao = new SponzorTransakcijaDAO(conn);
            dao.dodajSponzoraITakmicenje(dto);
        }
    }
}
