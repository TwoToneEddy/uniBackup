/*
PROCESS: 	Simple driver insertion
FILE: 		insertDriver.sql
PRE:		Database is relationally consistent,
			Team name must exist
POST:		Driver inserted
IN:			Driver_name
			Driver_Id
			DOB
			Team_name
OUT:		None, just database modification
LOGIC:		Simple insertion transaction
*/

insert into DRIVER
	values(100,'Lee Hudson','13-08-1984','Scuderia Ferrari');

