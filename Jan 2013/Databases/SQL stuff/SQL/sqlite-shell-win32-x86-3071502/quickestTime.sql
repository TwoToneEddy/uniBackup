/*
PROCESS: 	Quickest times for each circuit
FILE: 		quickestTime.sql
PRE:		Database is relationally consistent
POST:		No change to database
IN:			None
OUT:		Table of all circuits which have had a race take place on them with the
			circuits quickest lap times. 	
LOGIC:		Join Race and Race_Record using Race_Date
			Project circuit name and and the quickest time grouped my circuit name
*/

select CIRCUIT_NAME,min(RACE_TIME) 
from RACE,RACE_RECORD 
	where RACE.RACE_DATE = RACE_RECORD.RACE_DATE 
group by RACE.CIRCUIT_NAME;


