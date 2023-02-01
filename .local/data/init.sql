CREATE TABLE player(
    id serial PRIMARY KEY,
    first_name VARCHAR (100),
    last_name VARCHAR (100),
    email VARCHAR (100)
);

INSERT INTO player(first_name, last_name, email) VALUES
                ('Marcus', 'Rashford', 'marcus.rashford@man.utd'),
                ('Antonio', 'Marshall', 'antonio.marshall@man.utd'),
                ('Luke', 'Shaw', 'luke.shaw@man.utd')