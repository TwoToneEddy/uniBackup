/*
PROCESS: 	Removes a Race from the database
FILE: 		deleteRace.sql
PRE:		Database is relationally consistent, 
			the Race exists in the database
POST:		Race in question is removed from the database along with 
			all references to him/her
IN:			Race Date (cannot do macros/prompts in SQLite3)
OUT:		None, just database modification
LOGIC:		Every race record referencing the given Race is removed
			The Race is removed
*/
begin transaction;
delete from RACE_RECORD where (RACE_DATE = '17-Mar-2013');
delete from RACE where (RACE_DATE = '17-Mar-2013');
commit;
