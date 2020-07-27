/*
PROCESS: 	Removes a Team from the database
FILE: 		deleteTeam.sql
PRE:		Database is relationally consistent, 
			the Team exists in the database
POST:		Team in question is removed from the database.
			Any driver belonging to that team is allocated another
			predefined team
IN:			Team name to be deleted (cannot do macros/prompts in SQLite3)
			Team name to allocate drivers from the deleted team
OUT:		None, just database modification
LOGIC:		Allocate another team for all drivers that belong to the
			team to be deleted.
			Delete the team
*/
begin transaction;
update DRIVER 
set TEAM_NAME = 'Vodafone McLaren Mercedes'
where TEAM_NAME = 'Infiniti Red Bull Racing';
delete from TEAM where( TEAM_NAME = 'Infiniti Red Bull Racing');
commit;
