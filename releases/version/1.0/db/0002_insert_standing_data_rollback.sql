-- spotadevboot/releases/version/1.0/db/0002-insert_standing_data_rollback.sql

BEGIN WORK;

DELETE FROM address_type;

DELETE FROM domain_organisation;

DELETE FROM role;

DELETE FROM country;

DELETE FROM user_role;

DELETE FROM answer_students;

DELETE FROM app_user;

DELETE FROM teacher;

DELETE FROM student;

DELETE FROM address;

DELETE FROM answer_type;

DELETE FROM answer;

DELETE FROM question_type;

DELETE FROM question;

DELETE FROM question_status;

DELETE FROM section;

DELETE FROM section_status;

DELETE FROM exam_timer_type;

DELETE FROM exam;

DELETE FROM token_consumed;

DELETE FROM exam_status;

DELETE FROM invite;

DELETE FROM exam_token;

DELETE FROM payment_history;

DELETE FROM contact_query;

DELETE FROM mailing_list;

DELETE FROM result_type;

DELETE FROM result;

COMMIT;
