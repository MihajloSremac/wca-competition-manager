--Clanovi
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (1, 'Marko', 'Petrović', DATE '1995-03-15', DATE '2020-01-10', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (2, 'Ana', 'Nikolić', DATE '1998-07-22', DATE '2021-03-05', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (3, 'Stefan', 'Jovanović', DATE '1992-11-08', DATE '2019-09-12', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (4, 'Milica', 'Stojanović', DATE '1996-01-30', DATE '2020-06-18', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (5, 'Nemanja', 'Mitrović', DATE '1994-05-12', DATE '2018-11-25', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (6, 'Jovana', 'Radić', DATE '1997-09-03', DATE '2021-02-14', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (7, 'Aleksandar', 'Stanković', DATE '1990-12-20', DATE '2017-04-08', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (8, 'Tijana', 'Marković', DATE '1999-04-17', DATE '2022-01-30', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (9, 'Miloš', 'Đorđević', DATE '1993-08-25', DATE '2019-07-11', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (10, 'Jelena', 'Popović', DATE '1991-06-14', DATE '2018-03-22', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (11, 'Luka', 'Vasić', DATE '2000-02-28', DATE '2023-05-15', 'Srbija');
INSERT INTO Clan (ID_CLN, IME_CLN, PRZ_CLN, DT_RDJ_CLN, DT_UCL_CLN, DRZ_CLN) VALUES (12, 'Marija', 'Ilić', DATE '2001-10-05', DATE '2023-08-20', 'Srbija');

-- Takmicenja
INSERT INTO Takmicenje (ID_TKM, NAZ_TKM, DT_TKM, ADR_TKM, DRZ_TKM, CEN_TKM) VALUES (1, 'WCA Serbia Open 2025', DATE '2025-03-21', 'Beograd', 'Srbija', 1500);
INSERT INTO Takmicenje (ID_TKM, NAZ_TKM, DT_TKM, ADR_TKM, DRZ_TKM, CEN_TKM) VALUES (2, 'Novi Sad Cube Day', DATE '2025-06-15', 'Novi Sad', 'Srbija', 1200);

-- Discipline (Rubikove kocke)
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (1, '3x3x3 Cube');
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (2, '2x2x2 Cube');
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (3, '4x4x4 Cube');
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (4, 'Pyraminx');
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (5, 'Skewb');
-- DODATNE DISCIPLINE
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (6, '5x5x5 Cube');
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (7, '6x6x6 Cube');
INSERT INTO Disciplina (ID_DSC, NAZ_DSC) VALUES (8, 'Megaminx');

-- Sale
INSERT INTO Sala (ID_SAL, NAZ_SAL, KAP_SAL) VALUES (1, 'Hala Sportova', 500);
INSERT INTO Sala (ID_SAL, NAZ_SAL, KAP_SAL) VALUES (2, 'Cube Arena', 300);

-- Oprema
INSERT INTO Oprema (ID_OPR, NAZ_OPR, STNJ_OPR, MDL_OPR) VALUES (1, 'Stackmat Timer', 'Novo', 'G4');
INSERT INTO Oprema (ID_OPR, NAZ_OPR, STNJ_OPR, MDL_OPR) VALUES (2, 'Rubik''s 3x3', 'Dobro', 'GAN 356');
INSERT INTO Oprema (ID_OPR, NAZ_OPR, STNJ_OPR, MDL_OPR) VALUES (3, 'Rubik''s 4x4', 'Novo', 'MoYu Aosu');

-- Sponzori
INSERT INTO Sponzor (ID_SPZ, NAZ_SPZ, VRS_SPZ, USL_SPZ) VALUES (1, 'CubeShop', 'Zlatni', 'Poklon vaučeri za pobednike');
INSERT INTO Sponzor (ID_SPZ, NAZ_SPZ, VRS_SPZ, USL_SPZ) VALUES (2, 'SpeedCube.rs', 'Srebrni', 'Obezbeđuje opremu');

-- Organizatori
INSERT INTO Organizator (ID_CLN) VALUES (1);
INSERT INTO Organizator (ID_CLN) VALUES (2);

INSERT INTO Takmicar (ID_CLN) VALUES (3);
INSERT INTO Takmicar (ID_CLN) VALUES (4);
INSERT INTO Takmicar (ID_CLN) VALUES (5);

INSERT INTO Takmicar (ID_CLN) VALUES (6);
INSERT INTO Takmicar (ID_CLN) VALUES (7);
INSERT INTO Takmicar (ID_CLN) VALUES (8);
INSERT INTO Takmicar (ID_CLN) VALUES (9);
INSERT INTO Takmicar (ID_CLN) VALUES (10);

-- Delegati
INSERT INTO Delegat (ID_CLN) VALUES (6);

-- Junior delegati
INSERT INTO Junior_delegat (ID_CLN) VALUES (7);

-- Organizuje
INSERT INTO Organizuje (Takmicenje_ID_TKM, Organizator_ID_CLN) VALUES (1, 1);
INSERT INTO Organizuje (Takmicenje_ID_TKM, Organizator_ID_CLN) VALUES (2, 2);

-- Sadrzi
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (1, 1);
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (1, 2);
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (2, 3);
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (2, 4);
-- DODATNI SADRZI
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (1, 6);
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (2, 7);
INSERT INTO Sadrzi (Takmicenje_ID_TKM, Disciplina_ID_DSC) VALUES (2, 8);

-- Odrzava_se
INSERT INTO Odrzava_se (Takmicenje_ID_TKM, Sala_ID_SAL) VALUES (1, 1);
INSERT INTO Odrzava_se (Takmicenje_ID_TKM, Sala_ID_SAL) VALUES (2, 2);

-- Sponzorise
INSERT INTO Sponzorise (Sponzor_ID_SPZ, Takmicenje_ID_TKM) VALUES (1, 1);
INSERT INTO Sponzorise (Sponzor_ID_SPZ, Takmicenje_ID_TKM) VALUES (2, 2);

-- Koristi
INSERT INTO Koristi (Organizator_ID_CLN, Oprema_ID_OPR) VALUES (1, 1);
INSERT INTO Koristi (Organizator_ID_CLN, Oprema_ID_OPR) VALUES (2, 2);

-- Takmici_se
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (3, 1);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (4, 1);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (5, 2);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (3, 2);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (4, 2);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (5, 1);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (6, 1);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (7, 1);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (8, 2);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (9, 2);
INSERT INTO Takmici_se (Takmicar_ID_CLN, Takmicenje_ID_TKM) VALUES (10, 2);

-- Rezultat
-- Rezultat (svi inserti idu nakon svih potrebnih Takmici_se)
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (1, 8.23, 'PB', 3, 1, 1, 1);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (2, 2.15, 'PB', 4, 1, 1, 2);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (3, 45.67, NULL, 5, 2, 2, 3);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (4, 9.01, NULL, 3, 1, 1, 1);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (5, 7.88, NULL, 4, 1, 1, 1);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (6, 2.30, NULL, 3, 1, 1, 2);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (7, 2.05, NULL, 4, 1, 1, 2);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (8, 44.12, NULL, 5, 2, 2, 3);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (9, 46.00, NULL, 3, 2, 2, 3);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (10, 8.50, NULL, 6, 1, 1, 1);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (11, 2.25, NULL, 7, 1, 1, 2);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (12, 44.50, NULL, 8, 2, 2, 3);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (13, 45.10, NULL, 9, 2, 2, 3);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (14, 8.10, NULL, 10, 2, 2, 3);
-- DODATNI REZULTATI ZA NOVE DISCIPLINE
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (15, 120.50, NULL, 6, 1, 1, 6);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (16, 118.30, NULL, 7, 1, 1, 6);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (17, 210.00, NULL, 8, 2, 2, 7);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (18, 205.75, NULL, 9, 2, 2, 7);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (19, 320.10, NULL, 10, 2, 2, 8);
INSERT INTO Rezultat (ID_REZ, VRM_REZ, POD_REZ, Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Sadrzi_Takmicenje_ID_TKM, Sadrzi_Disciplina_ID_DSC)
VALUES (20, 315.80, NULL, 8, 2, 2, 8);

-- Primer za Uplatu i Uplacuje
INSERT INTO Uplata (ID_UPL, IZN_UPL, DAT_UPL) VALUES (1, 1500, DATE '2025-03-01');
INSERT INTO Uplata (ID_UPL, IZN_UPL, DAT_UPL) VALUES (2, 1200, DATE '2025-06-01');
INSERT INTO Uplacuje (Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Uplata_ID_UPL) VALUES (3, 1, 1);
INSERT INTO Uplacuje (Takmici_se_Takmicar_ID_CLN, Takmici_se_Takmicenje_ID_TKM, Uplata_ID_UPL) VALUES (5, 2, 2);

COMMIT;