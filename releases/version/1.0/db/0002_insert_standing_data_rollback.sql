-- spotadevboot/releases/version/1.0/db/0002-insert_standing_data_rollback.sql

BEGIN WORK;

DELETE FROM address_type;

DELETE FROM job_type;

DELETE FROM domain_organisation;

DELETE FROM role;

DELETE FROM code_repository_type;

DELETE FROM technology_group;

DELETE FROM technology;

DELETE FROM technology_tag;

DELETE FROM experience_type;

DELETE FROM country;

DELETE FROM address;

DELETE FROM app_user;

DELETE FROM user_role;

DELETE FROM dev;

DELETE FROM role_group;

COMMIT;
