/*
PROCESS: 	Displays average points per race for each driver
FILE: 		averagePoints.sql
PRE:		Database is relationally consistent
POST:		No change to database
IN:			None
OUT:		Table of all drivers that have completed a race and their respective 
			average amount of points per race	
LOGIC:		Join Driver and Race_Record entities using DRIVER_ID
			Display the average points grouped by the driver name
*/

select DRIVER_NAME, avg(POINTS) 
from DRIVER,RACE_RECORD 
	where DRIVER.DRIVER_ID = RACE_RECORD.DRIVER_ID 
group by DRIVER_NAME;


