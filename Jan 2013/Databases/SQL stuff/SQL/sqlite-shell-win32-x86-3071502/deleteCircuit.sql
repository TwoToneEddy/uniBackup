/*
PROCESS: 	Removes a circuit from the database
FILE: 		deleteCircuit.sql
PRE:		Database is relationally consistent, 
			the Circuit exists in the database
POST:		The circuit removed from the database
			All races on that circuit removed
			All instances of Race Record on that circuit removed
IN:			Circuit_Name (cannot do macros/prompts in SQLite3)
OUT:		None, just database modification
LOGIC:		Delete all instances of Race Record where the race date 
			corresponds to a race that was on the circuit to be deleted
			Delete all races on that circuit
			Delete the circuit
*/
begin transaction;
delete from RACE_RECORD where exists (			
	select * from RACE
		where RACE.RACE_DATE = RACE_RECORD.RACE_DATE 
		and RACE.CIRCUIT_NAME = 'Bahrain International Circuit');
delete from RACE where (CIRCUIT_NAME = 'Bahrain International Circuit');
delete from CIRCUIT where (CIRCUIT_NAME = 'Bahrain International Circuit');
commit;	
		




