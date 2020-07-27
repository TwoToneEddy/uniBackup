/*
PROCESS: 	Displayes the leaderboard for a given championship
FILE: 		leaderBoard.sql
PRE:		Database is relationally consistent
POST:		No change to database
IN:			COMPETITION_ID
OUT:		List of drivers and their points for a given championship listed in descending order	
LOGIC:		Join DRIVER and RACE_RECORD using DRIVER_ID
			Join RACE_RECORD to RACE using RACE_DATE
			Project the DRIVER_NAME and the sum of POINTS grouped by DRIVER_NAME and in descending order
*/

select DRIVER_NAME, sum(POINTS)
from DRIVER,RACE_RECORD,RACE 
	where ((DRIVER.DRIVER_ID = RACE_RECORD.DRIVER_ID) 
		and (RACE_RECORD.RACE_DATE = RACE.RACE_DATE)
		and (COMPETITION_ID = 001))
group by DRIVER_NAME
order by sum(POINTS) DESC;


