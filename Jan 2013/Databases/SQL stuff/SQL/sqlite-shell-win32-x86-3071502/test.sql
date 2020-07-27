
select * from RACE_RECORD where exists(
	select * from RACE 
	where RACE.RACE_DATE = RACE_RECORD.RACE_DATE  
	and RACE.COMPETITION_ID = 002);