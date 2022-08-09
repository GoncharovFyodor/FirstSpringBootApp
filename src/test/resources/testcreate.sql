CREATE TABLE answers(
    answer_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    correct BOOLEAN,
    question_id INTEGER NOT NULL REFERENCES questions,
    PRIMARY KEY (answer_id)
);
CREATE TABLE questions(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    num_of_correct INTEGER,
    profile_id INTEGER NOT NULL REFERENCES profiles,
    level_id INTEGER NOT NULL REFERENCES levels,
    PRIMARY KEY (id)
);
CREATE TABLE levels(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE profiles(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);