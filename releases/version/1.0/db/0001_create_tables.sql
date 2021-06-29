-- examinprogressboot/releases/version/1.0/db/0001_create_tables.sql

BEGIN WORK;

CREATE Sequence examinprogress_sequence START 10001;

-- =================================================================================================

CREATE TABLE address_type (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	"name" varchar(20) NOT NULL,
	CONSTRAINT address_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE country (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    country_code varchar(19) NOT NULL,
    enabled bool NOT NULL,
    "name" varchar(100) NOT NULL,
    rb_key varchar(100) NOT NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE address (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	first_line varchar(100) NOT NULL,
	post_code varchar(100) NULL,
	second_line varchar(100) NULL,
	town_or_city varchar(100) NOT NULL,
	fk_address_type int8 NOT NULL,
	fk_country int8 NOT NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT address_fk_address_type FOREIGN KEY (fk_address_type) REFERENCES address_type(id),
	CONSTRAINT address_fk_country FOREIGN KEY (fk_country) REFERENCES country(id)
);

-- =================================================================================================

CREATE TABLE contact_query (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	email varchar(255) NOT NULL,
	message varchar(255) NOT NULL,
	"name" varchar(100) NOT NULL,
	CONSTRAINT contact_query_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE mailing_list (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT mailing_list_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE domain_organisation (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	"domain" varchar(254) NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT domain_organisation_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE app_user (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	email varchar(255) NOT NULL,
	email_activation_code varchar(255) NULL,
	enabled bool NOT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	password_hash varchar(255) NOT NULL,
	fk_domain_organisation int8 NOT NULL,
	CONSTRAINT app_user_pkey PRIMARY KEY (id),
	CONSTRAINT uk_1j9d9a06i600gd43uu3km82jw UNIQUE (email),
	CONSTRAINT user_fk_domain_organization FOREIGN KEY (fk_domain_organisation) REFERENCES domain_organisation(id)
);

-- =================================================================================================

CREATE TABLE student (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	fk_user int8 NOT NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id),
	CONSTRAINT student_fk_user FOREIGN KEY (fk_user) REFERENCES app_user(id)
);

-- =================================================================================================

CREATE TABLE teacher (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	tokens int4 NOT NULL,
	fk_user int8 NOT NULL,
	CONSTRAINT teacher_pkey PRIMARY KEY (id),
	CONSTRAINT teacher_fk_user FOREIGN KEY (fk_user) REFERENCES app_user(id)
);

-- =================================================================================================

CREATE TABLE forgotten_password (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	active bool NOT NULL,
	code varchar(255) NOT NULL,
	fk_user int8 NULL,
	CONSTRAINT forgotten_password_pkey PRIMARY KEY (id),
	CONSTRAINT forgotten_password_fk_user FOREIGN KEY (fk_user) REFERENCES app_user(id)
);

-- =================================================================================================

CREATE TABLE exam_timer_type (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	"name" varchar(50) NOT NULL,
	CONSTRAINT exam_timer_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE exam (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	description varchar(1024) NOT NULL,
	duration int8 NULL,
	"name" varchar(100) NOT NULL,
	total_exam_time int8 NOT NULL,
	fk_exam_timer_type int8 NOT NULL,
	fk_teacher int8 NOT NULL,
	CONSTRAINT exam_pkey PRIMARY KEY (id),
	CONSTRAINT exam_fk_exam_timer_type FOREIGN KEY (fk_exam_timer_type) REFERENCES exam_timer_type(id),
	CONSTRAINT exam_fk_teacher FOREIGN KEY (fk_teacher) REFERENCES teacher(id)
);

-- =================================================================================================

CREATE TABLE "section" (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	description varchar(1024) NULL,
	duration int8 NULL,
	"name" varchar(100) NOT NULL,
	fk_exam int8 NOT NULL,
	CONSTRAINT section_pkey PRIMARY KEY (id),
	CONSTRAINT section_fk_exam FOREIGN KEY (fk_exam) REFERENCES exam(id)
);

-- =================================================================================================

CREATE TABLE question_type (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	"name" varchar(50) NOT NULL,
	CONSTRAINT question_type_pkey PRIMARY KEY (id),
	CONSTRAINT uk_7ei21extes3bkiv5byn0pfrg5 UNIQUE (name)
);

-- =================================================================================================

CREATE TABLE answer_type (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	"name" varchar(50) NOT NULL,
	CONSTRAINT answer_type_pkey PRIMARY KEY (id),
	CONSTRAINT uk_sbv87enokln13eu84ta9r8dij UNIQUE (name)
);

-- =================================================================================================

CREATE TABLE question (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	duration int8 NULL,
	question_text text NOT NULL,
	score int4 NULL,
	fk_answer_type int8 NOT NULL,
	fk_comprehension_question int8 NULL,
	fk_question_type int8 NOT NULL,
	fk_section int8 NOT NULL,
	CONSTRAINT question_pkey PRIMARY KEY (id),
	CONSTRAINT question_fk_answer_type FOREIGN KEY (fk_answer_type) REFERENCES answer_type(id),
	CONSTRAINT question_fk_comprehension_question FOREIGN KEY (fk_comprehension_question) REFERENCES question(id),
	CONSTRAINT question_fk_question_type FOREIGN KEY (fk_question_type) REFERENCES question_type(id),
	CONSTRAINT question_fk_section FOREIGN KEY (fk_section) REFERENCES "section"(id)
);

-- =================================================================================================

CREATE TABLE result_type (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	"name" varchar(50) NOT NULL,
	CONSTRAINT result_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE "result" (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	percent_score float4 NULL,
	point_score int4 NULL,
	total_score int4 NOT NULL,
	fk_exam int8 NULL,
	fk_question int8 NULL,
	fk_result_type int8 NOT NULL,
	fk_section int8 NULL,
	fk_student int8 NOT NULL,
	CONSTRAINT result_pkey PRIMARY KEY (id),
	CONSTRAINT result_fk_exam FOREIGN KEY (fk_exam) REFERENCES exam(id),
	CONSTRAINT result_fk_question FOREIGN KEY (fk_question) REFERENCES question(id),
	CONSTRAINT result_fk_result_type FOREIGN KEY (fk_result_type) REFERENCES result_type(id),
	CONSTRAINT result_fk_section FOREIGN KEY (fk_section) REFERENCES "section"(id),
	CONSTRAINT result_fk_student FOREIGN KEY (fk_student) REFERENCES student(id)
);

-- =================================================================================================

CREATE TABLE answer (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	answer_text varchar(1024) NOT NULL,
	fk_answer_type int8 NOT NULL,
	fk_question int8 NOT NULL,
	fk_answer int8 NULL,
	CONSTRAINT answer_pkey PRIMARY KEY (id),
	CONSTRAINT answer_fk_answer_type FOREIGN KEY (fk_answer_type) REFERENCES answer_type(id),
	CONSTRAINT answer_fk_question FOREIGN KEY (fk_question) REFERENCES question(id),
	CONSTRAINT question_fk_answer FOREIGN KEY (fk_answer) REFERENCES question(id)
);

-- =================================================================================================

CREATE TABLE question_status (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	complete bool NOT NULL,
	fetched bool NOT NULL,
	text_answer text NULL,
	fk_question int8 NOT NULL,
	fk_student int8 NOT NULL,
	CONSTRAINT question_status_pkey PRIMARY KEY (id),
	CONSTRAINT question_complete_fk_question FOREIGN KEY (fk_question) REFERENCES question(id),
	CONSTRAINT question_complete_fk_student FOREIGN KEY (fk_student) REFERENCES student(id)
);

-- =================================================================================================

CREATE TABLE section_status (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	complete bool NOT NULL,
	time_spent int8 NOT NULL,
	fk_section int8 NOT NULL,
	fk_student int8 NOT NULL,
	CONSTRAINT section_status_pkey PRIMARY KEY (id),
	CONSTRAINT section_status_fk_section FOREIGN KEY (fk_section) REFERENCES "section"(id),
	CONSTRAINT section_status_fk_student FOREIGN KEY (fk_student) REFERENCES student(id)
);

-- =================================================================================================

CREATE TABLE invite (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	exam_end_date date NULL,
	exam_start_date date NOT NULL,
	exam_start_time time NULL,
	invite_code varchar(128) NOT NULL,
	"name" varchar(100) NOT NULL,
	pausable bool NOT NULL,
	fk_exam int8 NOT NULL,
	CONSTRAINT invite_pkey PRIMARY KEY (id),
	CONSTRAINT uk_ibxt77vs9f86hlh3r4jnlxuro UNIQUE (invite_code),
	CONSTRAINT invite_fk_exam FOREIGN KEY (fk_exam) REFERENCES exam(id)
);

-- =================================================================================================

CREATE TABLE exam_token (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	cheating_attempts int4 NOT NULL,
	email varchar(256) NOT NULL,
	"token" varchar(128) NOT NULL,
	fk_invite int8 NOT NULL,
	fk_student int8 NULL,
	CONSTRAINT exam_token_pkey PRIMARY KEY (id),
	CONSTRAINT uk_ceo9cmsrer13ve2btmpuy775j UNIQUE (token),
	CONSTRAINT exam_token_fk_invite FOREIGN KEY (fk_invite) REFERENCES invite(id),
	CONSTRAINT exam_token_fk_student FOREIGN KEY (fk_student) REFERENCES student(id)
);

-- =================================================================================================

CREATE TABLE exam_status (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	complete bool NOT NULL,
	paused bool NOT NULL,
	paused_at timestamptz NULL,
	scoring_complete bool NOT NULL,
	started bool NOT NULL,
	time_spent int8 NOT NULL,
	fk_exam int8 NOT NULL,
	fk_student int8 NOT NULL,
	CONSTRAINT exam_status_pkey PRIMARY KEY (id),
	CONSTRAINT section_complete_fk_exam FOREIGN KEY (fk_exam) REFERENCES exam(id),
	CONSTRAINT section_complete_fk_student FOREIGN KEY (fk_student) REFERENCES student(id)
);

-- =================================================================================================

CREATE TABLE token_consumed (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	email varchar(255) NOT NULL,
	fk_exam int8 NOT NULL,
	fk_student int8 NULL,
	fk_teacher int8 NOT NULL,
	CONSTRAINT token_consumed_pkey PRIMARY KEY (id),
	CONSTRAINT exam_fk_token_consumed FOREIGN KEY (fk_exam) REFERENCES exam(id),
	CONSTRAINT student_fk_token_consumed FOREIGN KEY (fk_student) REFERENCES student(id),
	CONSTRAINT teacher_fk_token_consumed FOREIGN KEY (fk_teacher) REFERENCES teacher(id)
);

-- =================================================================================================

CREATE TABLE payment_history (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	amount_paid numeric(19,2) NOT NULL,
	currency varchar(100) NOT NULL,
	flw_ref varchar(255) NOT NULL,
	tokens_bought int4 NOT NULL,
	transaction_id int4 NOT NULL,
	tx_ref varchar(255) NOT NULL,
	fk_teacher int8 NOT NULL,
	CONSTRAINT payment_history_pkey PRIMARY KEY (id),
	CONSTRAINT payment_history_fk_teacher FOREIGN KEY (fk_teacher) REFERENCES teacher(id)
);

-- =================================================================================================

CREATE TABLE "role" (
	id int8 NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	description varchar(120) NOT NULL,
	"name" varchar(60) NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE user_role (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT user_role_fk_role FOREIGN KEY (role_id) REFERENCES "role"(id),
	CONSTRAINT user_role_fk_user FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- =================================================================================================

CREATE TABLE answer_students (
	answer_id int8 NOT NULL,
	students_id int8 NOT NULL,
	CONSTRAINT answer_students_pkey PRIMARY KEY (answer_id, students_id),
	CONSTRAINT answer_fk_student FOREIGN KEY (answer_id) REFERENCES answer(id),
	CONSTRAINT fk2ofse4dp59fmnq8qaewk63uf5 FOREIGN KEY (students_id) REFERENCES student(id)
);

COMMIT;
