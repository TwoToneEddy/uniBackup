/*
PROCESS: 	Removes a competition from the database
FILE: 		deleteCompetition.sql
PRE:		Database is relationally consistent, 
			the competition exists in the database
POST:		The competition removed from the database
			All races in that competition removed
			All instances of Race Record in that competition removed
IN:			Competition_ID (cannot do macros/prompts in SQLite3)
OUT:		None, just database modification
LOGIC:		Delete all instances of Race Record where the race date 
			corresponds to a race that was part of the competition to be deleted
			Delete all races in that competition
			Delete the competition
*/
begin transaction;
delete from RACE_RECORD where exists(
	select * from RACE 
	where RACE.RACE_DATE = RACE_RECORD.RACE_DATE  
	and RACE.COMPETITION_ID = 002);
delete from RACE where (COMPETITION_ID = 002);
delete from COMPETITION where (COMPETITION_ID = 002);
commit;	
		



