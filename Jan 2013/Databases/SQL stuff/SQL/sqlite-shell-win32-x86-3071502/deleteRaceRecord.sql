/*
PROCESS: 	Removes a race record for a given driver and a given race
FILE: 		deleteRaceRecord.sql
PRE:		Database is relationally consistent, 
			the Race Record exists in the database
POST:		Race Record in question is removed from the database.
IN:			Driver ID (cannot do macros/prompts in SQLite3)
			Race Date
OUT:		None, just database modification
LOGIC:		Simple delete function of the given race record
*/
begin transaction;
delete from RACE_RECORD where ((DRIVER_ID = 003) AND ( RACE_DATE = '24-Nov-2013'));
commit;
