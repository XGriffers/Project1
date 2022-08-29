CREATE TABLE usersflname (
pk_userflname serial PRIMARY KEY,
userlastname varchar(50),
userfirstname varchar(50)

);

CREATE TABLE credentials(
pk_usercreds serial PRIMARY KEY,
userName varchar(50) UNIQUE,
"password" varchar(50),
email varchar(50) unique
);

CREATE TABLE reimbursements(
pk_reimbursement serial PRIMARY KEY,
reimbursementtype varchar(50),
reimbursementcost float,
reimbursementstatus varchar(50)
);

CREATE TABLE roles(
pk_role serial PRIMARY KEY,
roleid integer,
roletype varchar(50)
)