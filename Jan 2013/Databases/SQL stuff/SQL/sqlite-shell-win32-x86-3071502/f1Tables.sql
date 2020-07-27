/*Creation of the table DRIVER with the following specification:
DRIVER_ID		-integer	-	primary key	-	foreign key for RACE_RECORD table
DRIVER_NAME		-text		-	not null
DOB				-Date		-	not null
TEAM_NAME		-text		-	foreign key for TEAM table
*/
create table DRIVER (
DRIVER_ID integer not null primary key,
DRIVER_NAME text not null,
DOB date not null,
TEAM_NAME text,
constraint fkey1_dr foreign key(TEAM_NAME) references TEAM(TEAM_NAME)
constraint fkey2_dr foreign key(DRIVER_ID) references RACE_RECORD(DRIVER_ID));


/*Creation of the table TEAM with the following specification:
TEAM_NAME		-text		-	not null	-	primary key	
LOCATION		-text		-	not null
*/
create table TEAM (
TEAM_NAME text not null primary key,
LOCATION text not null);

/*Creation of the table RACE_RECORD with the following specification:
DRIVER_ID		-integer	-	not null	-	primary key	
RACE_DATE		-date		-	not null	-	primary key	-	foreign key for RACE table
RACE_TIME		-time		
*/
create table RACE_RECORD (
DRIVER_ID integer not null,
RACE_DATE date not null,
RACE_TIME time,
POINTS integer,
constraint pkey_is primary key (DRIVER_ID, RACE_DATE)
constraint fkey1_rr foreign key (RACE_DATE) references RACE(RACE_DATE));

/*Creation of the table RACE with the following specification:
RACE_DATE		-date		-	not null	-	primary key
COMPETITION_ID	-integeer	-	not null	-	foreign key for COMPETITION table
CIRCUIT_NAME	-text		-	not null	-	foreign key for CIRCUIT table
*/
create table RACE (
RACE_DATE date not null primary key,
COMPETITION_ID integer not null,
CIRCUIT_NAME text not null,
constraint fkey1_ra foreign key (COMPETITION_ID) references COMPETITION(COMPETITION_ID)
constraint fkey2_ra foreign key (CIRCUIT_NAME) references CIRCUIT(CIRCUIT_NAME));

/*Creation of the table CIRCUIT with the following specification:
CIRCUIT_NAME	-text	- 	not null	-	primary key	
LOCATION		-text	-	not null
*/
create table CIRCUIT (
CIRCUIT_NAME text not null primary key,
LOCATION text not null);

/*Creation of the table COMPETITION with the following specification:
COMPETITION_ID		-integer	-	not null	-	primary key
COMPETITION_NAME	-text		-	not null
*/
create table COMPETITION (
COMPETITION_ID integer not null primary key,
COMPETITION_NAME text not null);