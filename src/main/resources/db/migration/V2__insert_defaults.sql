DO
$$
    BEGIN
        IF (SELECT COUNT(*) FROM department) = 0 AND (SELECT COUNT(*) FROM lector) = 0 THEN
            INSERT INTO lector (full_name, degree, salary, creation_date, updated_date)
            VALUES ('John Doe', 1, 55000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Jane Smith', 2, 62000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Alice Johnson', 1, 50000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Robert Brown', 3, 70000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Michael Clark', 2, 58000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

            INSERT INTO department (name, description, location, creation_date, updated_date)
            VALUES ('Computer Science', 'Department of Computer Science', 'Building A', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
                   ('Physics', 'Department of Physics', 'Building B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Mathematics', 'Department of Mathematics', 'Building C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Biology', 'Department of Biology', 'Building D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                   ('Chemistry', 'Department of Chemistry', 'Building E', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

            INSERT INTO department_lectors (department_id, lectors_id)
            SELECT d.id, l.id
            FROM department d,
                 lector l
            WHERE d.name = 'Computer Science'
              AND l.full_name IN ('John Doe', 'Jane Smith');

            INSERT INTO department_lectors (department_id, lectors_id)
            SELECT d.id, l.id
            FROM department d,
                 lector l
            WHERE d.name = 'Physics'
              AND l.full_name = 'Alice Johnson';

        END IF;
    END
$$;