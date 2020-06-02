-- production data is in the following git module:   spotadevboot_data

BEGIN WORK;

-- address_type ------------------------------------------------------------------------------------
INSERT INTO address_type (id, name, created_at, updated_at) 
VALUES (1, 'User', current_timestamp, current_timestamp);

INSERT INTO address_type (id, name, created_at, updated_at) 
VALUES (2, 'Dev', current_timestamp, current_timestamp);

INSERT INTO address_type (id, name, created_at, updated_at) 
VALUES (3, 'Domain Organisation', current_timestamp, current_timestamp);

-- job_type ----------------------------------------------------------------------------------------
INSERT INTO job_type (id, name, created_at, updated_at) 
VALUES (1, 'Full Time', current_timestamp, current_timestamp);

INSERT INTO job_type (id, name, created_at, updated_at) 
VALUES (2, 'Part Time', current_timestamp, current_timestamp);

INSERT INTO job_type (id, name, created_at, updated_at) 
VALUES (3, 'Contract', current_timestamp, current_timestamp);

INSERT INTO job_type (id, name, created_at, updated_at) 
VALUES (4, 'Feature by Feature', current_timestamp, current_timestamp);

-- domain_organisation -----------------------------------------------------------------------------
INSERT INTO domain_organisation (id, name, domain, created_at, updated_at)
VALUES (1, 'Enigma', 'enigmascoredev.com', current_timestamp, current_timestamp);

INSERT INTO domain_organisation (id, name, domain, created_at, updated_at)
VALUES (2, 'Spotadev', 'spotadevweb.com', current_timestamp, current_timestamp);

-- role  -------------------------------------------------------------------------------------------
INSERT INTO role (id, name, description, created_at, updated_at)
VALUES (1, 'SuperAdmin', 'Role for super admin', current_timestamp, current_timestamp);

INSERT INTO role (id, name, description, created_at, updated_at) 
VALUES (2, 'EMAIL_VALIDATED', 'The user gets given this role after having their email validated', 
current_timestamp, current_timestamp);

INSERT INTO role (id, name, description, created_at, updated_at) 
VALUES (3, 'ADMIN', 'Role for admin', current_timestamp, current_timestamp);

INSERT INTO role (id, name, description, created_at, updated_at) 
VALUES (4, 'DEV', 'Role for developer', current_timestamp, current_timestamp);

-- repository_type ---------------------------------------------------------------------------------
INSERT INTO code_repository_type (id, name, created_at, updated_at) 
VALUES (1, 'Git', current_timestamp, current_timestamp);

-- technology_group --------------------------------------------------------------------------------
INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (1000, false, 'Algorithms', current_timestamp, current_timestamp);

INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (2000, true, 'Databases', current_timestamp, current_timestamp);

INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (3000, false, 'Design Patterns', current_timestamp, current_timestamp);

INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (4000, true, 'Frameworks', current_timestamp, current_timestamp);

INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (5000, false, 'Methodology', current_timestamp, current_timestamp);

INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (6000, true, 'Programming Languages', current_timestamp, current_timestamp);

INSERT INTO technology_group (id, show_version, name, created_at, updated_at) 
VALUES (7000, false, 'UML', current_timestamp, current_timestamp);

-- Algorithms --------------------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1000, 'Insertion sort', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1010, 'Selection sort', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1020, 'Bubble sort', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1030, 'Merge Sort', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1040, 'Quicksort', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1050, 'Binary Search', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1060, 'Breadth First Search (BFS)', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1070, 'Depth First Search (DFS)', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1080, 'Lee algorithm', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1090, 'Shortest path in a Maze', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1100, 'Flood fill Algorithm', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1110, 'Floyd’s Cycle Detection Algorithm', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1120, 'Kadane’s algorithm',1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1130, 'Longest Increasing Subsequence', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1140, 'Inorder, Preorder, Postorder Tree Traversals', 1000, 
current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1150, 'Heap Sort', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1160, 'Topological Sorting in a DAG', 1000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1170, 'Disjoint-Set Data Structure (Union-Find Algorithm)', 1000, 
current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1180, 'Kruskal’s Algorithm for finding Minimum Spanning Tree', 1000, 
current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1190, 'Single-Source Shortest Paths — Dijkstra’s Algorithm', 1000, 
current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (1200, 'All-Pairs Shortest Paths — Floyd Warshall Algorithm', 1000, 
current_timestamp, current_timestamp);

-- Databases  --------------------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (2000, 'MySql', 2000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (2010, 'Oracle', 2000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (2020, 'Postgress', 2000, current_timestamp, current_timestamp);

-- Design Patterns  --------------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3000, 'Behavioural: Chain of Responsibility', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3010, 'Behavioural: Command', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3020, 'Behavioural: Interpreter', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3030, 'Behavioural: Iterator', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3040, 'Behavioural: Mediator', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3050, 'Behavioural: Memento', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3060, 'Behavioural: Observer', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3070, 'Behavioural: State', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3080, 'Behavioural: Strategy', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3090, 'Behavioural: Template Method', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3100, 'Behavioural: Visitor', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3110, 'Creational: Abstract Factory', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3120, 'Creational: Builder', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3130, 'Creational: Factory Method', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3140, 'Creational: Prototype', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3150, 'Creational: Singleton', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3160, 'Structural: Adapter', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3170, 'Structural: Bridge', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3180, 'Structural: Composite', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3190, 'Structural: Decorator', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3200, 'Facade', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3210, 'Structural: Flyweight', 3000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (3220, 'Structural: Proxy', 3000, current_timestamp, current_timestamp);

-- Frameworks --------------------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (4000, 'Java - Spring Boot', 4000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (4010, 'Java - Spring Cloud', 4000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (4020, 'JavaScript - Angular', 4000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (4030, 'JavaScript - Vue JS', 4000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (4040, 'JavaScript - React JS', 4000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (4050, 'JavaScript - Node JS', 4000, current_timestamp, current_timestamp);

-- Methodology -------------------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (5000, 'Agile Methodology', 5000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (5010,'Pair Programming', 5000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (5020,'Scrum', 5000, current_timestamp, current_timestamp);

-- Programming Languages ---------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6000, 'C#', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6010, 'C', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6020, 'C++', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6030, 'Dart', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6040, 'Go', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6050, 'Haskell', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6060, 'JavaScript', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6070, 'Java', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6080, 'Kotlin', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6090, 'PHP', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6100, 'Python', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6110, 'Ruby', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6120, 'Rust', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6130, 'Swift', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6140, 'TypeScript', 6000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (6150, 'Unix Shell Scripting', 6000, current_timestamp, current_timestamp);

-- UML ---------------------------------------------------------------------------------------------
INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (7000, 'Class Diagram', 7000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (7010, 'Sequence Diagram', 7000, current_timestamp, current_timestamp);

INSERT INTO technology (id, name, fk_technology_group, created_at, updated_at) 
VALUES (7020, 'Use Case Diagram', 7000, current_timestamp, current_timestamp);

-- technology_tag ----------------------------------------------------------------------------------
INSERT INTO technology_tag (id, name, created_at, updated_at) 
VALUES (1, 'Backend', current_timestamp, current_timestamp);

INSERT INTO technology_tag (id, name, created_at, updated_at) 
VALUES (2, 'Frontend', current_timestamp, current_timestamp);

INSERT INTO technology_tag (id, name, created_at, updated_at) 
VALUES (3, 'Java', current_timestamp, current_timestamp);

INSERT INTO technology_tag (id, name, created_at, updated_at) 
VALUES (4, 'JavaScript', current_timestamp, current_timestamp);

INSERT INTO technology_tag (id, name, created_at, updated_at) 
VALUES (5, 'Spring', current_timestamp, current_timestamp);

INSERT INTO technology_tag (id, name, created_at, updated_at) 
VALUES (6, 'Web', current_timestamp, current_timestamp);

-- experience_type ---------------------------------------------------------------------------------
INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (1, 'Employment', current_timestamp, current_timestamp);

INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (2, 'Voluntary Employment', current_timestamp, current_timestamp);

INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (3, 'School', current_timestamp, current_timestamp);

INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (4, 'University', current_timestamp, current_timestamp);

INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (5, 'Study', current_timestamp, current_timestamp);

INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (6, 'Travel', current_timestamp, current_timestamp);

INSERT INTO experience_type (id, name, created_at, updated_at)
VALUES (7, 'Other', current_timestamp, current_timestamp);

-- country -----------------------------------------------------------------------------------------
-- https://countrycode.org/

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (1, 'Afghanistan','data_sql/country/afghanistan', '93', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (2, 'Albania', 'data_sql/country/albania', '355', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (3, 'Algeria', 'data_sql/country/algeria', '213', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (4, 'American Samoa', 'data_sql/country/american_samoa', '1-684', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (5, 'Andorra', 'data_sql/country/andorra', '376', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (6, 'Angola', 'data_sql/country/angola', '244', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (7, 'Anguilla', 'data_sql/country/anguilla', '1-268', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (8, 'Antarctica', 'data_sql/country/antarctica', '672', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (9, 'Antigua and Barbuda', 'data_sql/country/antigua_and_barbuda', '1-268', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (10, 'Argentina', 'data_sql/country/argentina', '54', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (11, 'Armenia', 'data_sql/country/armenia', '374', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (12, 'Aruba', 'data_sql/country/aruba', '297', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (13, 'Australia', 'data_sql/country/australia', '61', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (14, 'Austria', 'data_sql/country/austria', '43', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (15, 'Azerbaijan', 'data_sql/country/azerbaijan', '994', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (16, 'Bahamas', 'data_sql/country/bahamas', '1-242', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (17, 'Bahrain', 'data_sql/country/bahrain', '973', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (18, 'Bangladesh', 'data_sql/country/bangladesh', '880', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (19, 'Barbados', 'data_sql/country/barbados', '1-246', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (20, 'Belarus', 'data_sql/country/belarus', '375', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (21, 'Belgium', 'data_sql/country/belgium', '32', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (22, 'Belize', 'data_sql/country/belize', '501', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (23, 'Benin', 'data_sql/country/benin', '229', true, current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (24, 'Bermuda', 'data_sql/country/bermuda', '1-441', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (25, 'Bhutan', 'data_sql/country/bhutan', '975', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (26, 'Bolivia', 'data_sql/country/bolivia', '591', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (27, 'Bosnia and Herzegovina', 'data_sql/country/bosnia_and_herzegovina', '387', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (28, 'Botswana', 'data_sql/country/botswana', '267', true, 
current_timestamp, current_timestamp );

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (29, 'Brazil', 'data_sql/country/brazil', '55', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (30, 'British Indian Ocean Territory', 'data_sql/country/british_indian_ocean_territory', 
'246', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (31, 'British Virgin Islands', 'data_sql/country/british_virgin_islands', '1-284', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (32, 'Brunei', 'data_sql/country/brunei', '673', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (33, 'Bulgaria', 'data_sql/country/bulgaria', '359', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (34, 'Burkina Faso', 'data_sql/country/burkina_faso', '226', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (35, 'Burundi', 'data_sql/country/burundi', '257', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (36, 'Cambodia', 'data_sql/country/cambodia', '855', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (37, 'Cameroon', 'data_sql/country/cameroon', '237', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (38, 'Canada', 'data_sql/country/canada', '1', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (39, 'Cape Verde', 'data_sql/country/cape_verde', '238', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (40, 'Cayman Islands', 'data_sql/country/cayman_islands', '1-345', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (41, 'Central African Republic', 'data_sql/country/central_african_republic', '236', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (42, 'Chad', 'data_sql/country/chad', '235', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (43, 'Chile', 'data_sql/country/chile', '56', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (44, 'China', 'data_sql/country/china', '86', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (45, 'Christmas Island', 'data_sql/country/christmas_island', '61', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (46, 'Cocos Islands', 'data_sql/country/cocos_islands', '61', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (47, 'Colombia', 'data_sql/country/colombia', '57', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (48, 'Comoros', 'data_sql/country/comoros', '269', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (49, 'Cook Islands', 'data_sql/country/cook_islands', '1-682', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (50, 'Costa Rica', 'data_sql/country/costa_rica', '506', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (51, 'Croatia', 'data_sql/country/croatia', '385', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (52, 'Cuba', 'data_sql/country/cuba', '53', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (53, 'Curacao', 'data_sql/country/curacao', '599', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (54, 'Cyprus', 'data_sql/country/cyprus', '357', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (55, 'Czech Republic', 'data_sql/country/czech_republic', '420', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (56, 'Democratic Republic of the Congo', 'data_sql/country/democratic_republic_of_the_congo', 
'243', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (57, 'Denmark', 'data_sql/country/denmark', '45', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (58, 'Djibouti', 'data_sql/country/djibouti', '253', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (59, 'Dominica', 'data_sql/country/dominica', '1-767', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (60, 'Dominican Republic', 'data_sql/country/dominican_republic', '1-809, 1-829, 1-849', 
true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (61, 'East Timor', 'data_sql/country/east_timor', '670', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (62, 'Ecuador', 'data_sql/country/ecuador', '593', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (63, 'Egypt', 'data_sql/country/egypt', '20', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (64, 'El Salvador', 'data_sql/country/el_salvador', '503', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (65, 'Equatorial Guinea', 'data_sql/country/equatorial_guinea', '240', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (66, 'Eritrea', 'data_sql/country/eritrea', '291', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (67, 'Estonia', 'data_sql/country/estonia', '372', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (68, 'Ethiopia', 'data_sql/country/ethiopia', '251', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (69, 'Falkland Islands', 'data_sql/country/falkland_islands', '500', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (70, 'Faroe Islands', 'data_sql/country/faroe_islands', '298', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (71, 'Fiji', 'data_sql/country/fiji', '679', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (72, 'Finland', 'data_sql/country/finland', '358', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (73, 'France', 'data_sql/country/france', '33', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (74, 'French Polynesia', 'data_sql/country/french_polynesia', '689', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (75, 'Gabon', 'data_sql/country/gabon', '241', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (76, 'Gambia', 'data_sql/country/gambia', '220', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (77, 'Georgia', 'data_sql/country/georgia', '995', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (78, 'Germany', 'data_sql/country/germany', '49', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (79, 'Ghana', 'data_sql/country/ghana', '233', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (80, 'Gibraltar', 'data_sql/country/gibraltar', '350', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (81, 'Greece', 'data_sql/country/greece', '30', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (82, 'Greenland', 'data_sql/country/greenland', '299', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (83, 'Grenada', 'data_sql/country/grenada', '1-473', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (84, 'Guam', 'data_sql/country/guam', '1-671', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (85, 'Guatemala', 'data_sql/country/guatemala', '502', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (86, 'Guernsey', 'data_sql/country/guernsey', '44-1481', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (87, 'Guinea', 'data_sql/country/guinea', '24', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (88, 'Guinea-Bissau', 'data_sql/country/guinea-bissau', '245', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (89, 'Guyana', 'data_sql/country/guyana', '592', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (90, 'Haiti', 'data_sql/country/haiti', '509', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (91, 'Honduras', 'data_sql/country/honduras', '504', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (92, 'Hong Kong', 'data_sql/country/hong_kong', '852', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (93, 'Hungary', 'data_sql/country/hungary', '36', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (94, 'Iceland', 'data_sql/country/iceland', '354', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (95, 'India', 'data_sql/country/india', '91', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (96, 'Indonesia', 'data_sql/country/indonesia', '62', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (97, 'Iran', 'data_sql/country/iran', '98', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (98, 'Iraq', 'data_sql/country/iraq', '964', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (99, 'Ireland', 'data_sql/country/ireland', '353', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (100, 'Isle of Man', 'data_sql/country/isle_of_man', '44-1624', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (101, 'Israel', 'data_sql/country/israel', '972', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (102, 'Italy', 'data_sql/country/italy', '39', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (103, 'Ivory Coast', 'data_sql/country/ivory_coast', '225', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (104, 'Jamaica', 'data_sql/country/jamaica', '1-876', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (105, 'Japan', 'data_sql/country/japan', '81', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (106, 'Jersey', 'data_sql/country/jersey', '44-1534', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (107, 'Jordan', 'data_sql/country/jordan', '962', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (108, 'Kazakhstan', 'data_sql/country/kazakhstan', '7', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (109, 'Kenya', 'data_sql/country/kenya', '254', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (110, 'Kiribati', 'data_sql/country/Kiribati', '686', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (111, 'Kosovo', 'data_sql/country/kosovo', '383', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (112, 'Kuwait', 'data_sql/country/kuwait', '965', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (113, 'Kyrgyzstan', 'data_sql/country/kyrgyzstan', '996', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (114, 'Laos', 'data_sql/country/laos', '856', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (115, 'Latvia', 'data_sql/country/latvia', '371', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (116, 'Lebanon', 'data_sql/country/lebanon', '961', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (117, 'Lesotho', 'data_sql/country/lesotho', '266', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (118, 'Liberia', 'data_sql/country/liberia', '231', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (119, 'Libya', 'data_sql/country/libya', '218', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (120, 'Liechtenstein', 'data_sql/country/liechtenstein', '423', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (121, 'Lithuania', 'data_sql/country/lithuania', '370', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (122, 'Luxembourg', 'data_sql/country/luxembourg', '352', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (123, 'Macau', 'data_sql/country/macau', '853', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (124, 'Macedonia', 'data_sql/country/macedonia', '389', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (125, 'Madagascar', 'data_sql/country/madagascar', '261', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (126, 'Malawi', 'data_sql/country/malawi', '265', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (127, 'Malaysia', 'data_sql/country/malaysia', '60', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (128, 'Maldives', 'data_sql/country/maldives', '960', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (129, 'Mali', 'data_sql/country/mali', '223', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (130, 'Malta', 'data_sql/country/malta', '356', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (131, 'Marshall Islands', 'data_sql/country/marshall_islands', '692', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (132, 'Mauritania', 'data_sql/country/mauritania', '222', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (133, 'Mauritius', 'data_sql/country/mauritius', '230', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (134, 'Mayotte', 'data_sql/country/mayotte', '262', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (135, 'Mexico', 'data_sql/country/mexico', '52', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (136, 'Micronesia', 'data_sql/country/micronesia', '691', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (137, 'Moldova', 'data_sql/country/moldova', '373', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (138, 'Monaco', 'data_sql/country/monaco', '377', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (139, 'Mongolia', 'data_sql/country/mongolia', '976', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (140, 'Montenegro', 'data_sql/country/montenegro', '382', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (141, 'Montserrat', 'data_sql/country/montserrat', '1-664', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (142, 'Morocco', 'data_sql/country/morocco', '212', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (143, 'Mozambique', 'data_sql/country/mozambique', '258', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (144, 'Myanmar', 'data_sql/country/myanmar', '95', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (145, 'Namibia', 'data_sql/country/namibia', '264', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (146, 'Nauru', 'data_sql/country/nauru', '674', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (147, 'Nepal', 'data_sql/country/nepal', '977', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (148, 'Netherlands', 'data_sql/country/netherlands', '31', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (149, 'Netherlands Antilles', 'data_sql/country/netherlands_antilles', '599', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (150, 'New Caledonia', 'data_sql/country/new_caledonia', '687', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (151, 'New Zealand', 'data_sql/country/new_zealand', '64', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (152, 'Nicaragua', 'data_sql/country/nicaragua', '505', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (153, 'Niger', 'data_sql/country/niger', '227', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (154, 'Nigeria', 'data_sql/country/nigeria', '234', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (155, 'Niue', 'data_sql/country/niue', '683', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (156, 'North Korea', 'data_sql/country/north_korea', '850', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (157, 'Northern Mariana Islands', 'data_sql/country/northern_mariana_islands', '1-670', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (158, 'Norway', 'data_sql/country/norway', '47', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (159, 'Oman', 'data_sql/country/oman', '968', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (160, 'Pakistan', 'data_sql/country/pakistan', '92', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (161, 'Palau', 'data_sql/country/palau', '680', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (162, 'Palestine', 'data_sql/country/palestine', '970', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (163, 'Panama', 'data_sql/country/panama', '507', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (164, 'Papua New Guinea', 'data_sql/country/papua_new_guinea', '675', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (165, 'Paraguay', 'data_sql/country/paraguay', '595', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (166, 'Peru', 'data_sql/country/peru', '51', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (167, 'Philippines', 'data_sql/country/philippines', '63', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (168, 'Pitcairn', 'data_sql/country/pitcairn', '64', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (169, 'Poland', 'data_sql/country/poland', '48', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (170, 'Portugal', 'data_sql/country/portugal', '351', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (171, 'Puerto', 'data_sql/country/puerto', '1-787, 1-939', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (172, 'Qatar', 'data_sql/country/qatar', '974', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (173, 'Republic of the Congo', 'data_sql/country/republic_of_the_congo', '242', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (174, 'Reunion', 'data_sql/country/reunion', '262', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (175, 'Romania', 'data_sql/country/romania', '40', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (176, 'Russia', 'data_sql/country/russia', '7', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (177, 'Rwanda', 'data_sql/country/rwanda', '250', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (178, 'Saint Barthelemy', 'data_sql/country/saint_barthelemy', '590', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (179, 'Saint Helena', 'data_sql/country/saint_helena', '290', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (180, 'Saint Kitts and Nevis', 'data_sql/country/saint_kitts_and_nevis', '1-869', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (181, 'Saint Lucia', 'data_sql/country/saint_lucia', '1-758', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (182, 'Saint Martin', 'data_sql/country/saint_martin', '590', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (183, 'Saint Pierre and Miquelon', 'data_sql/country/saint_pierre_and_miquelon', '508', 
true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (184, 'Saint Vincent and the Grenadines', 'data_sql/country/saint_vincent_and_the_grenadines', 
'1-784', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (185, 'Samoa', 'data_sql/country/samoa', '685', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (186, 'San Marino', 'data_sql/country/san_marino', '378', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (187, 'Sao Tome and Principe', 'data_sql/country/sao_tome_and_principe', '239', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (188, 'Saudi Arabia', 'data_sql/country/saudi_arabia', '966', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (189, 'Senegal', 'data_sql/country/senegal', '221', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (190, 'Serbia', 'data_sql/country/serbia', '381', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (191, 'Seychelles', 'data_sql/country/seychelles', '248', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (192, 'Sierra Leone', 'data_sql/country/sierra_leone', '232', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (193, 'Singapore', 'data_sql/country/singapore', '65', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (194, 'Sint Maarten', 'data_sql/country/sint_aarten', '1-721', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (195, 'Slovakia', 'data_sql/country/slovakia', '421  ', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (196, 'Slovenia', 'data_sql/country/slovenia', '386', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (197, 'Solomon Islands', 'data_sql/country/solomon_islands', '677', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (198, 'Somalia', 'data_sql/country/somalia', '252', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (199, 'South Africa', 'data_sql/country/south_africa', '27', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (200, 'South Korea', 'data_sql/country/south_korea', '82', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (201, 'South Sudan', 'data_sql/country/south_sudan', '211', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (202, 'Spain', 'data_sql/country/spain', '34', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (203, 'Sri Lanka', 'data_sql/country/sri_lanka', '94', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (204, 'Sudan', 'data_sql/country/sudan', '249', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (205, 'Suriname', 'data_sql/country/suriname', '597', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (206, 'Svalbard and Jan Mayen', 'data_sql/country/svalbard_and_jan_mayen', '47', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (207, 'Swaziland', 'data_sql/country/swaziland', '268', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (208, 'Sweden', 'data_sql/country/sweden', '46', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (209, 'Switzerland', 'data_sql/country/switzerland', '41', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (210, 'Syria', 'data_sql/country/syria', '963', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (211, 'Taiwan', 'data_sql/country/taiwan', '886', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (212, 'Tajikistan', 'data_sql/country/tajikistan', '992', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (213, 'Tanzania', 'data_sql/country/tanzania', '255', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (214, 'Thailand', 'data_sql/country/thailand', '66', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (215, 'Togo', 'data_sql/country/togo', '228', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (216, 'Tokelau', 'data_sql/country/tokelau', '690', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (217, 'Tonga', 'data_sql/country/tonga', '676', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (218, 'Trinidad and Tobago', 'data_sql/country/trinidad_and_tobago', '1-868  ', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (219, 'Tunisia', 'data_sql/country/tunisia', '216', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (220, 'Turkey', 'data_sql/country/turkey', '90', true, current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (221, 'Turkmenistan', 'data_sql/country/turkmenistan', '993', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (222, 'Turks and Caicos Islands', 'data_sql/country/turks_and_caicos_islands', '1-649', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (223, 'Tuvalu', 'data_sql/country/tuvalu', '688', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (224, 'U.S. Virgin Islands', 'data_sql/country/us_virgin_islands', '1-340', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (225, 'Uganda', 'data_sql/country/uganda', '256', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (226, 'Ukraine', 'data_sql/country/ukraine', '380', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (227, 'United Arab Emirates', 'data_sql/country/united_arab_emirates', '971', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (228, 'United Kingdom', 'data_sql/country/united_kingdom', '44', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (229, 'United States', 'data_sql/country/united_states', '1', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (230, 'Uruguay', 'data_sql/country/uruguay', '598', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (231, 'Uzbekistan', 'data_sql/country/uzbekistan', '998', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (232, 'Vanuatu', 'data_sql/country/vanuatu', '678', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (233, 'Vatican', 'data_sql/country/vatican', '379', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (234, 'Venezuela', 'data_sql/country/venezuela', '58', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (235, 'Vietnam', 'data_sql/country/vietnam', '84', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (236, 'Wallis and Futuna', 'data_sql/country/wallis_and_futuna', '681', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (237, 'Western Sahara', 'data_sql/country/western_sahara', '212', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (238, 'Yemen', 'data_sql/country/yemen', '967', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (239, 'Zambia', 'data_sql/country/zambia', '260', true, 
current_timestamp, current_timestamp);

INSERT INTO country (id, name, rb_key, country_code, enabled, created_at, updated_at)
VALUES (240, 'Zimbabwe', 'data_sql/country/zimbabwe', '263', true, 
current_timestamp, current_timestamp);

-- address -----------------------------------------------------------------------------------------
INSERT INTO address (id, first_line, second_line, third_line, fourth_line, town_or_city, post_code, 
fk_country, fk_address_type, created_at, updated_at)
VALUES (1, 'first line', 'second line', 'third line', 'fourth line', 'Kisumu', '40100', 1, 1,
current_timestamp, current_timestamp);

INSERT INTO address (id, first_line, second_line, third_line, fourth_line, town_or_city, post_code, 
fk_country, fk_address_type, created_at, updated_at)
VALUES (2, 'first line', 'second line', 'third line', 'fourth line', 'New-York', 'Some Postal Code',
2, 2, current_timestamp, current_timestamp);

-- admin app_user - password enigma ----------------------------------------------------------------
INSERT INTO app_user (id, first_name, last_name, username, email, enabled, password_hash, 
email_activation_code, fk_domain_organisation, created_at, updated_at)
VALUES (1, 'Admin', 'Admin', 'admin', 'admin@gmail.com', true, 
'$2a$10$Z65pHQUCOGWXBDjDNu/Xf.Db06XuACBO1S5FjeXfqDAHw2oDU5D/C', 'active', 1, 
current_timestamp, current_timestamp );

-- dev app_user - password enigma ------------------------------------------------------------------
INSERT INTO app_user (id, first_name, last_name, username, email, enabled, password_hash, 
email_activation_code, fk_domain_organisation, created_at, updated_at)
VALUES (2, 'Dev', 'Dev', 'dev', 'dev@gmail.com', true,
'$2a$10$Z65pHQUCOGWXBDjDNu/Xf.Db06XuACBO1S5FjeXfqDAHw2oDU5D/C', 'active', 1, 
current_timestamp, current_timestamp );

-- user_role ---------------------------------------------------------------------------------------
INSERT INTO user_role (user_id, role_id) 
VALUES (1, 1 );

INSERT INTO user_role (user_id, role_id) 
VALUES (1, 2);

INSERT INTO user_role (user_id, role_id) 
VALUES (2, 2);

INSERT INTO user_role (user_id, role_id ) 
VALUES (2, 4);

-- dev ---------------------------------------------------------------------------------------------
INSERT INTO dev (id, created, enabled, icon_path, fk_user, fk_address, created_at, updated_at)
VALUES (1, current_timestamp, true, 'Path to Icon location', 2, 1, 
current_timestamp, current_timestamp);

-- role_group --------------------------------------------------------------------------------------
INSERT INTO role_group (id, description, name, created_at, updated_at)
VALUES (1,'Profile for grouping Admin Roles','ADMIN', current_timestamp, current_timestamp);

INSERT INTO role_group (id, description, name, created_at, updated_at)
VALUES (2, 'Profile for grouping Dev Roles','DEV', current_timestamp, current_timestamp);

-- role_group --------------------------------------------------------------------------------------
INSERT INTO rolegroup_role (rolegroup_id, role_id )
VALUES (1,3);

INSERT INTO rolegroup_role (rolegroup_id, role_id)
VALUES (2,4);

COMMIT;
