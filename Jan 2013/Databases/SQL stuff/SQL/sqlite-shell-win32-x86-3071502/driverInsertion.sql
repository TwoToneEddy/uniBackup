/*
select CIRCUIT_NAME,RACE_TIME from RACE where exists(
select RACE_DATE from RACE where exists(
	select DRIVER_ID, RACE_TIME,RACE_DATE from RACE_RECORD group by RACE_DATE));*/
/*shows fastest time on a given date*
select CIRCUIT_NAME from RACE where
select min(RACE_TIME),RACE_DATE from RACE_RECORD group by RACE_DATE;*/

select CIRCUIT_NAME,min(RACE_TIME) from RACE,RACE_RECORD where RACE.RACE_DATE = RACE_RECORD.RACE_DATE group by RACE_RECORD.RACE_DATE;