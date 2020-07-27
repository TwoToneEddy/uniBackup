create table MODULE (
CODE text primary key,
TITLE text,
STATUS text constraint mod_val check (STATUS in ('B', 'A')),
CREDIT numeric constraint mod_cred check (CREDIT in (1, 2)));

create table PREQ (
MCODE text,
PCODE text,
constraint pkey_is primary key (MCODE, PCODE),
constraint fkey1 foreign key (MCODE) references MODULE(CODE),
constraint fkey2 foreign key (PCODE) references MODULE(CODE));

create table STUDENT (
NAME text primary key,
SEX text constraint sex_value check (SEX in ('M', 'F')),
FIELD text not null,
CITY text,
ENTRY numeric);

create table REGISTER (
MCODE text,
SNAME text,
MARK integer constraint mark_val check (MARK between 0 and 100),
constraint pkey_rg primary key (MCODE, SNAME),
constraint fkey_rg1 foreign key (MCODE) references MODULE(CODE),
constraint fkey_rg2 foreign key (SNAME) references STUDENT(NAME));


