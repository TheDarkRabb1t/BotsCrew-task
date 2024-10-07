BEGIN;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM department) AND NOT EXISTS (SELECT 1 FROM lector) THEN
            INSERT INTO lector (id, full_name, degree, salary)
            VALUES (1, 'John Smith', 3, 70000),
                   (2, 'Emily Johnson', 2, 65000),
                   (3, 'Michael Brown', 1, 50000),
                   (4, 'Olivia Davis', 3, 72000),
                   (5, 'James Miller', 2, 64000),
                   (6, 'Sophia Wilson', 1, 52000),
                   (7, 'William Anderson', 3, 71000),
                   (8, 'Ava Thomas', 2, 63000),
                   (9, 'Ethan Martinez', 1, 48000),
                   (10, 'Isabella White', 3, 75000);

            INSERT INTO department (id, name, description, location, head_id)
            VALUES (1, 'Computer Science', 'Focuses on computing and software', 'Building A', 1),
                   (2, 'Mathematics', 'Covers pure and applied mathematics', 'Building B', 2),
                   (3, 'Physics', 'Department of theoretical and applied physics', 'Building C', 3),
                   (4, 'Biology', 'Studies of life and living organisms', 'Building D', 4),
                   (5, 'History', 'Explores past events and civilizations', 'Building E', 5);

            INSERT INTO department_lectors (department_id, lectors_id)
            VALUES (1, 1), (1, 6), (1, 7),
                   (2, 2), (2, 8),
                   (3, 3), (3, 9),
                   (4, 4), (4, 10),
                   (5, 5), (5, 6);

        END IF;
    END $$;

COMMIT;
