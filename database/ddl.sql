CREATE TABLE Login (
id_login INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL
id_account INT UNSIGNED NOT NULL,
CONSTRAINT FK_AccountLogin FOREIGN KEY (id_account) REFERENCES Account(id_account)
);
CREATE TABLE Account (
id_account INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_basic INT UNSIGNED NOT NULL,
id_organization INT UNSIGNED NOT NULL,
id_role INT UNSIGNED NOT NULL,
CONSTRAINT FK_BasicAccount FOREIGN KEY (id_basic) REFERENCES Basic(id_basic),
CONSTRAINT FK_OrganizationAccount FOREIGN KEY (id_organization) REFERENCES Organization(id_organization),
CONSTRAINT FK_RoleAccount FOREIGN KEY (id_role) REFERENCES Role(id_role),
);
CREATE TABLE Basic (
id_basic INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
fn_name VARCHAR(100) NOT NULL,
mn_name VARCHAR(100),
ln_name VARCHAR(100) NOT NULL,
nn_name VARCHAR(100),
pof_name VARCHAR(100),
email VARCHAR(100),
contact_number VARCHAR(20),
);
CREATE TABLE Role (
id_role INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
role_name VARCHAR(100) NOT NULL,
role_level INT UNSIGNED NOT NULL,
);
CREATE TABLE Organization (
id_organization INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
organization_name VARCHAR(100) NOT NULL,
organization_table_prefix VARCHAR(100) NOT NULL
);