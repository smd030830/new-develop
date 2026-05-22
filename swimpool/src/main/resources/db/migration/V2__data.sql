-- data.sql 파일은 스프링 실행시 sql 문장을 실행한다.
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (1,'서울시 은평구 은평로1','32-22','50M * 4, 100 * 2','은평구립수영장','350m2','02-1111-2222');
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (3,'서울시 은평구 은평로2','32-22','50M * 4, 100 * 2','은평구립수영장2','350m2','02-2222-2222');
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (4,'서울시 은평구 은평로3','32-22','50M * 4, 100 * 2','은평구립수영장3','350m2','02-1111-2222');
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (5,'서울시 은평구 은평로7','32-22','50M * 4, 100 * 2','은평구립수영장7','350m2','02-1111-2222');
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (6,'서울시 은평구 은평로7','32-22','50M * 4, 100 * 2','종로구립수영장1','350m2','02-1111-2222');
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (7,'서울시 은평구 명지로 5','32-22','50M * 4, 100 * 2','명지전문대수영장1','350m2','02-2222-3333');
INSERT INTO `swimpool`(id, addr1, addr2, lanes, name, size, tel) VALUES (8,'서울시 은평구 명지로 5','32-22','50M * 4, 100 * 2','명지전문대수영장1','350m2','02-2222-3333');


INSERT INTO `teacher`(id, birth_year, main, name, swimpool_id) VALUES (1,NULL,'자유형','박태환1',1);
INSERT INTO `teacher`(id, birth_year, main, name, swimpool_id) VALUES (2,NULL,'자유형','박태환2',3);
INSERT INTO `teacher`(id, birth_year, main, name, swimpool_id) VALUES (4,NULL,'자유형','박태환4',4);
INSERT INTO `teacher`(id, birth_year, main, name, swimpool_id) VALUES (6,NULL,'자유형','박태환6',1);
INSERT INTO `teacher`(id, birth_year, main, name, swimpool_id) VALUES (8,NULL,'자유형','박태환8',1);
