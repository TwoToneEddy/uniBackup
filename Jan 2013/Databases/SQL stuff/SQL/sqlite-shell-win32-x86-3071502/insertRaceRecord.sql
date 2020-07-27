/*
PROCESS: 	Simple race_record insertion
FILE: 		insertRaceRecord.sql
PRE:		Database is relationally consistent,
			Race with race_date must exist
			Driver ID must exist
POST:		race_record inserted
IN:			Driver_Id
			Race_Date
			Race_Time
			Points
OUT:		None, just database modification
LOGIC:		Simple insertion transaction
*/

insert into RACE_RECORD
	values(100,'17-Mar-2013','02:27:03.12',0);

