-- spotadevboot/releases/version/1.0/db/0001_create_tables.sql

BEGIN WORK;

CREATE Sequence spotadev_sequence START 10001;

-- =================================================================================================

CREATE TABLE address (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    first_line varchar(100) NOT NULL,
    fourth_line varchar(100) NULL,
    post_code varchar(100) NULL,
    second_line varchar(100) NULL,
    third_line varchar(100) NULL,
    town_or_city varchar(100) NOT NULL,
    fk_address_type int8 NOT NULL,
    fk_country int8 NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE address_type (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(20) NOT NULL,
    CONSTRAINT address_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE algorithms (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    fk_profile int8 NOT NULL,
    fk_technology int8 NOT NULL,
    CONSTRAINT algorithms_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE annotation (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    annotation varchar(500) NOT NULL,
    annotation_line_number int8 NOT NULL,
    fk_path int8 NOT NULL,
    CONSTRAINT annotation_pkey PRIMARY KEY (id)
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
    username varchar(100) NOT NULL,
    fk_domain_organisation int8 NOT NULL,
    CONSTRAINT app_user_pkey PRIMARY KEY (id),
    CONSTRAINT app_user_email_unique UNIQUE (email)
);

-- =================================================================================================

CREATE TABLE availability (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    available_from timestamptz NOT NULL,
    note varchar(200) NULL,
    CONSTRAINT availability_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE availability_job_type (
    availability_id int8 NOT NULL,
    job_type_id int8 NOT NULL,
    CONSTRAINT availability_job_type_pkey PRIMARY KEY (availability_id, job_type_id)
);

-- =================================================================================================

CREATE TABLE code_repository_type (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(20) NOT NULL,
    CONSTRAINT code_repository_type_pkey PRIMARY KEY (id)
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

CREATE TABLE design_patterns (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    fk_profile int8 NOT NULL,
    fk_technology int8 NOT NULL,
    CONSTRAINT design_patterns_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE dev (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    created timestamptz NOT NULL,
    enabled bool NOT NULL,
    icon_path varchar(4096) NULL,
    public_profile bytea NULL,
    fk_address int8 NULL,
    fk_availability int8 NULL,
    fk_user int8 NULL,
    CONSTRAINT dev_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE dev_project (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    enabled bool NOT NULL,
    fk_dev int8 NOT NULL,
    fk_project int8 NOT NULL,
    CONSTRAINT dev_project_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE dev_repository_module (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    enabled bool NOT NULL,
    fk_dev int8 NOT NULL,
    fk_repository_module int8 NOT NULL,
    CONSTRAINT dev_repository_module_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE dev_review (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    review varchar(1024) NOT NULL,
    fk_author_dev int8 NOT NULL,
    fk_dev int8 NOT NULL,
    CONSTRAINT dev_review_pkey PRIMARY KEY (id)
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

CREATE TABLE experience (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    end_date timestamp NULL,
    organisation_name varchar(100) NOT NULL,
    "position" varchar(100) NOT NULL,
    start_date timestamp NOT NULL,
    summary varchar(500) NOT NULL,
    fk_experience_type int8 NOT NULL,
    fk_profile int8 NOT NULL,
    CONSTRAINT experience_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE experience_type (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(25) NOT NULL,
    CONSTRAINT experience_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE git_https_settings (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "password" varchar(20) NOT NULL,
    url varchar(255) NOT NULL,
    username varchar(100) NOT NULL,
    fk_repository_module int8 NOT NULL,
    CONSTRAINT git_https_settings_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE git_ssh_settings (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    private_key_path varchar(255) NOT NULL,
    public_key_path varchar(255) NOT NULL,
    fk_repository_module int8 NOT NULL,
    CONSTRAINT git_ssh_settings_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE interest (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(500) NOT NULL,
    "ordering" int4 NULL,
    fk_profile int8 NOT NULL,
    CONSTRAINT interest_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE job_type (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(100) NOT NULL,
    CONSTRAINT job_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE missing_technology (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    created timestamptz NOT NULL,
    description varchar(500) NULL,
    "name" varchar(100) NOT NULL,
    fk_technology_group int8 NOT NULL,
    CONSTRAINT missing_technology_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE notification_type (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    description varchar(500) NOT NULL,
    "name" varchar(100) NOT NULL,
    fk_notification int8 NOT NULL,
    CONSTRAINT notification_type_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE "path" (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    file_changed bool NOT NULL,
    file_contents varchar(10240) NULL,
    file_contents_path varchar(4096) NULL,
    file_deleted bool NOT NULL,
    file_path varchar(4096) NOT NULL,
    hash_file varchar(255) NULL,
    selected bool NOT NULL,
    CONSTRAINT path_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE path_mapping (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    fk_admin int8 NULL,
    fk_child_path int8 NOT NULL,
    fk_dev int8 NOT NULL,
    fk_parent_path int8 NULL,
    fk_repository_module int8 NOT NULL,
    CONSTRAINT path_mapping_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE profile (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    abstract_summary varchar(1000) NOT NULL,
    active bool NOT NULL,
    hidden_note varchar(500) NOT NULL,
    link varchar(255) NOT NULL,
    "name" varchar(100) NOT NULL,
    path_generated_cv varchar(1024) NULL,
    fk_dev int8 NOT NULL,
    CONSTRAINT profile_pkey PRIMARY KEY (id),
    CONSTRAINT profile_link_unique UNIQUE (link)
);

-- =================================================================================================

CREATE TABLE profile_project (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    description varchar(500) NOT NULL,
    "name" varchar(100) NOT NULL,
    fk_dev_project int8 NOT NULL,
    fk_profile int8 NOT NULL,
    CONSTRAINT profile_project_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE profile_repository_module (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    module_description varchar(500) NULL,
    module_name varchar(100) NOT NULL,
    fk_dev_repository_module int8 NOT NULL,
    fk_profile_project int8 NOT NULL,
    CONSTRAINT profile_repository_module_pkey PRIMARY KEY (id)
);

-- =================================================================================================

-- this one is a join table
CREATE TABLE profile_repositorymodule (
    profile_id int8 NOT NULL,
    repository_module_id int8 NOT NULL,
    CONSTRAINT profile_repositorymodule_pkey PRIMARY KEY (profile_id, repository_module_id)
);

-- =================================================================================================

CREATE TABLE profile_review (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    out_of_date bool NOT NULL,
    review varchar(1024) NOT NULL,
    fk_dev_review int8 NOT NULL,
    fk_profile int8 NOT NULL,
    CONSTRAINT profile_review_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE project (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    description varchar(500) NOT NULL,
    "name" varchar(100) NOT NULL,
    website_url varchar(255) NULL,
    CONSTRAINT project_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE project_advert (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    module_description varchar(500) NULL,
    expiry_date timestamp NOT NULL,
    number_experts int4 NULL,
    number_newbies int4 NULL,
    number_skiled int4 NULL,
    fk_dev int8 NOT NULL,
    fk_project int8 NOT NULL,
    CONSTRAINT project_advert_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE project_advert_comment (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "comment" varchar(1024) NULL,
    fk_project_advert_comment int8 NULL,
    fk_project_advert int8 NULL,
    CONSTRAINT project_advert_comment_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE projectadvert_technology (
    projectadvert_id int8 NOT NULL,
    technology_id int8 NOT NULL,
    CONSTRAINT projectadvert_technology_pkey PRIMARY KEY (projectadvert_id, technology_id)
);

-- =================================================================================================

CREATE TABLE repo_admin (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    enabled bool NOT NULL,
    fk_user int8 NOT NULL,
    CONSTRAINT repo_admin_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE repoadmin_repositorymodule (
    repository_module_id int8 NOT NULL,
    repo_admin_id int8 NOT NULL,
    CONSTRAINT repo_admin_repository_module_pkey PRIMARY KEY (repository_module_id, repo_admin_id)
);

-- =================================================================================================

CREATE TABLE repository_metrics (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    first_commit timestamptz NULL,
    last_commit timestamptz NULL,
    number_commits int4 NULL,
    fk_dev int8 NOT NULL,
    fk_repository_module int8 NOT NULL,
    CONSTRAINT repository_metrics_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE repository_module (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    cloned bool NOT NULL,
    deleted bool NOT NULL,
    enabled bool NOT NULL,
    module_description varchar(500) NULL,
    module_name varchar(100) NOT NULL,
    pull_directory varchar(4096) NULL,
    fk_domain_organisation int8 NOT NULL,
    fk_project int8 NULL,
    fk_repository_type int8 NOT NULL,
    CONSTRAINT repository_module_pkey PRIMARY KEY (id)
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

CREATE TABLE role_group (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    description varchar(120) NULL,
    "name" varchar(60) NOT NULL,
    CONSTRAINT role_group_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE rolegroup_role (
    rolegroup_id int8 NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT rolegroup_role_pkey PRIMARY KEY (rolegroup_id, role_id)
);

-- =================================================================================================

CREATE TABLE task_responsibility (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    description varchar(500) NOT NULL,
    "ordering" int4 NULL,
    fk_experience int8 NOT NULL,
    CONSTRAINT task_responsibility_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE technology (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(100) NOT NULL,
    fk_technology_group int8 NOT NULL,
    CONSTRAINT technology_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE technology_group (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(100) NOT NULL,
    show_version bool NOT NULL,
    CONSTRAINT technology_group_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE technology_note (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    note varchar(100) NULL,
    "version" varchar(100) NULL,
    fk_experience int8 NULL,
    fk_missing_technology int8 NULL,
    fk_profile int8 NULL,
    fk_technology int8 NULL,
    CONSTRAINT technology_note_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE technology_tag (
    id int8 NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    "name" varchar(100) NOT NULL,
    CONSTRAINT technology_tag_pkey PRIMARY KEY (id)
);

-- =================================================================================================

CREATE TABLE technology_technologytag (
    technology_id int8 NOT NULL,
    technology_tag_id int8 NOT NULL,
    CONSTRAINT technology_technologytag_pkey PRIMARY KEY (technology_id, technology_tag_id)
);

-- =================================================================================================

CREATE TABLE user_role (
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
);

-- =================================================================================================

ALTER TABLE address ADD CONSTRAINT address_fk_address_type FOREIGN KEY (fk_address_type) REFERENCES address_type(id);
ALTER TABLE address ADD CONSTRAINT address_fk_country FOREIGN KEY (fk_country) REFERENCES country(id);

ALTER TABLE algorithms ADD CONSTRAINT algorithms_fk_profile FOREIGN KEY (fk_profile) REFERENCES profile(id);
ALTER TABLE algorithms ADD CONSTRAINT algorithms_fk_technology FOREIGN KEY (fk_technology) REFERENCES technology(id);

ALTER TABLE annotation ADD CONSTRAINT annotation_fk_path FOREIGN KEY (fk_path) REFERENCES path(id);

ALTER TABLE app_user ADD CONSTRAINT user_fk_domain_organization FOREIGN KEY (fk_domain_organisation) REFERENCES domain_organisation(id);

ALTER TABLE availability_job_type ADD CONSTRAINT availability_job_type_fk_availability FOREIGN KEY (availability_id) REFERENCES availability(id);
ALTER TABLE availability_job_type ADD CONSTRAINT availability_job_type_fk_job_type FOREIGN KEY (job_type_id) REFERENCES job_type(id);

ALTER TABLE design_patterns ADD CONSTRAINT design_patterns_fk_design_patterns FOREIGN KEY (fk_technology) REFERENCES technology(id);
ALTER TABLE design_patterns ADD CONSTRAINT design_patterns_fk_profile FOREIGN KEY (fk_profile) REFERENCES profile(id);

ALTER TABLE dev ADD CONSTRAINT dev_fk_address FOREIGN KEY (fk_address) REFERENCES address(id);
ALTER TABLE dev ADD CONSTRAINT dev_fk_availability FOREIGN KEY (fk_availability) REFERENCES availability(id);
ALTER TABLE dev ADD CONSTRAINT dev_fk_user FOREIGN KEY (fk_user) REFERENCES app_user(id);

ALTER TABLE dev_project ADD CONSTRAINT dev_project_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);
ALTER TABLE dev_project ADD CONSTRAINT dev_project_fk_project FOREIGN KEY (fk_project) REFERENCES project(id);
 
ALTER TABLE dev_repository_module ADD CONSTRAINT dev_repository_module_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);
ALTER TABLE dev_repository_module ADD CONSTRAINT dev_repository_module_fk_repository_module FOREIGN KEY (fk_repository_module) REFERENCES repository_module(id);

ALTER TABLE dev_review ADD CONSTRAINT dev_review_fk_author_dev FOREIGN KEY (fk_author_dev) REFERENCES dev(id);
ALTER TABLE dev_review ADD CONSTRAINT dev_review_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);

ALTER TABLE experience ADD CONSTRAINT experience_fk_experience_type FOREIGN KEY (fk_experience_type) REFERENCES experience_type(id);
ALTER TABLE experience ADD CONSTRAINT experience_fk_profile FOREIGN KEY (fk_profile) REFERENCES profile(id);

ALTER TABLE git_https_settings ADD CONSTRAINT git_https_settings_fk_repository_module FOREIGN KEY (fk_repository_module) REFERENCES repository_module(id);

ALTER TABLE git_ssh_settings ADD CONSTRAINT git_ssh_settings_fk_repository_module FOREIGN KEY (fk_repository_module) REFERENCES repository_module(id);

ALTER TABLE interest ADD CONSTRAINT interest_fk_profile FOREIGN KEY (fk_profile) REFERENCES profile(id);

ALTER TABLE missing_technology ADD CONSTRAINT missing_technology_fk_technology_group FOREIGN KEY (fk_technology_group) REFERENCES technology_group(id);

ALTER TABLE path_mapping ADD CONSTRAINT path_mapping_fk_admin FOREIGN KEY (fk_admin) REFERENCES repo_admin(id);
ALTER TABLE path_mapping ADD CONSTRAINT path_mapping_fk_child_path FOREIGN KEY (fk_child_path) REFERENCES path(id);
ALTER TABLE path_mapping ADD CONSTRAINT path_mapping_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);
ALTER TABLE path_mapping ADD CONSTRAINT path_mapping_fk_parent_path FOREIGN KEY (fk_parent_path) REFERENCES path(id);
ALTER TABLE path_mapping ADD CONSTRAINT path_mapping_fk_repository_module FOREIGN KEY (fk_repository_module) REFERENCES repository_module(id);

ALTER TABLE profile ADD CONSTRAINT dev_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);

ALTER TABLE profile_project ADD CONSTRAINT profile_project_fk_profile FOREIGN KEY (fk_dev_project) REFERENCES dev_project(id);

ALTER TABLE profile_repository_module ADD CONSTRAINT profile_repository_module_fk_dev_repository_module FOREIGN KEY (fk_dev_repository_module) REFERENCES dev_repository_module(id);
ALTER TABLE profile_repository_module ADD CONSTRAINT profile_repository_module_fk_profile_project FOREIGN KEY (fk_profile_project) REFERENCES profile_project(id);

ALTER TABLE profile_repositorymodule ADD CONSTRAINT profile_repositorymodule_fk_profile FOREIGN KEY (profile_id) REFERENCES profile(id);
ALTER TABLE profile_repositorymodule ADD CONSTRAINT profile_repositorymodule_fk_repository_module FOREIGN KEY (repository_module_id) REFERENCES repository_module(id);

ALTER TABLE profile_review ADD CONSTRAINT dev_fk_dev_review FOREIGN KEY (fk_dev_review) REFERENCES dev_review(id);
ALTER TABLE profile_review ADD CONSTRAINT dev_fk_profile FOREIGN KEY (fk_profile) REFERENCES profile(id);

ALTER TABLE project_advert ADD CONSTRAINT project_advert_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);
ALTER TABLE project_advert ADD CONSTRAINT project_advert_fk_project FOREIGN KEY (fk_project) REFERENCES project(id);

ALTER TABLE project_advert_comment ADD CONSTRAINT project_advert_comment_fk_project_advert FOREIGN KEY (fk_project_advert) REFERENCES project_advert(id);
ALTER TABLE project_advert_comment ADD CONSTRAINT project_advert_comment_fk_project_advert_comment FOREIGN KEY (fk_project_advert_comment) REFERENCES project_advert_comment(id);

ALTER TABLE projectadvert_technology ADD CONSTRAINT projectadvert_technology_fk_projectadvert FOREIGN KEY (projectadvert_id) REFERENCES project_advert(id);
ALTER TABLE projectadvert_technology ADD CONSTRAINT projectadvert_technology_fk_technology FOREIGN KEY (technology_id) REFERENCES technology(id);

ALTER TABLE repo_admin ADD CONSTRAINT repo_admin_fk_user FOREIGN KEY (fk_user) REFERENCES app_user(id);

ALTER TABLE repoadmin_repositorymodule ADD CONSTRAINT repoadmin_repositorymodule_fk_repo_admin FOREIGN KEY (repo_admin_id) REFERENCES repo_admin(id);
ALTER TABLE repoadmin_repositorymodule ADD CONSTRAINT repoadmin_repositorymodule_fk_repository_module FOREIGN KEY (repository_module_id) REFERENCES repository_module(id);

ALTER TABLE repository_metrics ADD CONSTRAINT repository_metrics_fk_dev FOREIGN KEY (fk_dev) REFERENCES dev(id);
ALTER TABLE repository_metrics ADD CONSTRAINT repository_metrics_fk_repository_module FOREIGN KEY (fk_repository_module) REFERENCES repository_module(id);

ALTER TABLE repository_module ADD CONSTRAINT repository_module_fk_domain_organisation FOREIGN KEY (fk_domain_organisation) REFERENCES domain_organisation(id);
ALTER TABLE repository_module ADD CONSTRAINT repository_module_fk_project FOREIGN KEY (fk_project) REFERENCES project(id);
ALTER TABLE repository_module ADD CONSTRAINT repository_module_fk_repository_type FOREIGN KEY (fk_repository_type) REFERENCES code_repository_type(id);

ALTER TABLE public.rolegroup_role ADD CONSTRAINT role_group_fk_role FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE public.rolegroup_role ADD CONSTRAINT role_group_fk_rolegroup FOREIGN KEY (rolegroup_id) REFERENCES role_group(id);

ALTER TABLE task_responsibility ADD CONSTRAINT task_responsibility_fk_experience FOREIGN KEY (fk_experience) REFERENCES experience(id);

ALTER TABLE technology ADD CONSTRAINT technology_fk_technology_group FOREIGN KEY (fk_technology_group) REFERENCES technology_group(id);

ALTER TABLE technology_note ADD CONSTRAINT technology_note_fk_experience FOREIGN KEY (fk_experience) REFERENCES experience(id);
ALTER TABLE technology_note ADD CONSTRAINT technology_note_fk_missing_technology FOREIGN KEY (fk_missing_technology) REFERENCES missing_technology(id);
ALTER TABLE technology_note ADD CONSTRAINT technology_note_fk_profile FOREIGN KEY (fk_profile) REFERENCES profile(id);
ALTER TABLE technology_note ADD CONSTRAINT technology_note_fk_technology FOREIGN KEY (fk_technology) REFERENCES technology(id);

ALTER TABLE technology_technologytag ADD CONSTRAINT technology_technologytag_fk_technology FOREIGN KEY (technology_id) REFERENCES technology(id);
ALTER TABLE technology_technologytag ADD CONSTRAINT technology_technologytag_fk_technology_tag FOREIGN KEY (technology_tag_id) REFERENCES technology_tag(id);

ALTER TABLE user_role ADD CONSTRAINT user_role_fk_role FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE user_role ADD CONSTRAINT user_role_fk_user FOREIGN KEY (user_id) REFERENCES app_user(id);

COMMIT;
