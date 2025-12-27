DROP TABLE IF EXISTS quiz_questions;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS protocols;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;


DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1000;


-- Пользователи(авторизация в системе)
CREATE TABLE users
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_name     VARCHAR NOT NULL, -- логин
    password      VARCHAR NOT NULL -- пароль
);
-- Пользователей с одинаковым логином нет
CREATE UNIQUE INDEX users_unique_user_name_idx ON users (user_name);

-- Таблица связь пользователей и ролей(many to many)
CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Вопросы
CREATE TABLE questions
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL -- текст вопроса
);
-- Все вопросы разные
CREATE UNIQUE INDEX questions_unique_name_idx ON questions (name);

-- Ответы
CREATE TABLE answers
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL, -- текст ответа
    right_answer BOOLEAN DEFAULT FALSE,
    question_id INTEGER NOT NULL, -- поле таблицы questions
    FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE
);
-- Все ответы на один вопрос разные
CREATE UNIQUE INDEX answers_unique_name_question_id_idx ON answers (name, question_id);

-- Протоколы проверки
CREATE TABLE protocols
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL, -- Имя ученика
    test_date TIMESTAMP NOT NULL, -- Дата и время тестирования
    quiz_name VARCHAR NOT NULL, -- Наименование теста
    percent INTEGER NOT NULL DEFAULT 0, -- Процент ответов
    status BOOLEAN default false, -- сдано / не сдано
    details TEXT NOT NULL, -- список вопросов и ответов(верно/неверно)
    min_score INTEGER NOT NULL DEFAULT 70 -- Проходной балл
);

-- Тесты
CREATE TABLE quizzes
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL, -- текст вопроса
    min_score INTEGER NOT NULL DEFAULT 70 -- Проходной балл
);
-- Все имена тестов разные
CREATE UNIQUE INDEX quizzes_unique_name_idx ON quizzes (name);

-- Таблица связь тестов и вопросов(many to many)
CREATE TABLE quiz_questions
(
    quiz_id INTEGER NOT NULL, -- поле таблицы quizzes
    question_id INTEGER NOT NULL, -- поле таблицы questions
    CONSTRAINT quiz_questions_idx UNIQUE (quiz_id, question_id), -- уникальная запись
    FOREIGN KEY (quiz_id) REFERENCES quizzes (id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE
);

