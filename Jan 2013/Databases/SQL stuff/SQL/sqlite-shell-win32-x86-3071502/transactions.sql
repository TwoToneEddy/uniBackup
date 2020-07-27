begin transaction;
delete from RACE_RECORD where RACE_RECORD.RACE_DATE = (			
	select RACE_RECORD.RACE_DATE from RACE,RACE_RECORD 
		where((RACE.RACE_DATE = RACE_RECORD.RACE_DATE) and(RACE.CIRCUIT_NAME = 'Bahrain International Circuit')));