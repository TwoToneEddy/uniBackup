/*
PROCESS: 	Removes a driver from the database
FILE: 		deleteDriver.sql
PRE:		Database is relationally consistent, 
			the driver exists in the database
POST:		Driver in question is removed from the database along with 
			all references to him/her
IN:			Driver ID (cannot do macros/prompts in SQLite3)
OUT:		None, just database modification
LOGIC:		Every race record belonging to the given driver is removed
			The Driver is removed
*/
begin transaction;
delete from RACE_RECORD where (DRIVER_ID = 016);
delete from DRIVER where(DRIVER_ID = 016);
commit;
