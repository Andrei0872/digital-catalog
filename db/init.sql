CREATE TABLE teacher (
id SERIAL PRIMARY KEY,
email VARCHAR(50) NOT NULL,
name VARCHAR(100) NOT NULL,
age INT NOT NULL
);

CREATE TABLE student (
id SERIAL PRIMARY KEY,
email VARCHAR(30) NOT NULL,
name VARCHAR(100) NOT NULL,
age INT NOT NULL
);

CREATE TABLE class (
  id SERIAL PRIMARY KEY,
  subject VARCHAR(100) NOT NULL
);

CREATE TABLE grade (
  id SERIAL PRIMARY KEY,
  value INT NOT NULL,
  inserted_at DATE NOT NULL,
  modified_at DATE NOT NULL
);

CREATE TABLE teacher_class (
  teacher_id INT NOT NULL,
  class_id INT NOT NULL,
  CONSTRAINT fk_teacher_id FOREIGN KEY(teacher_id) REFERENCES teacher(id) ON DELETE CASCADE,
  CONSTRAINT fk_class_id FOREIGN KEY(class_id) REFERENCES class(id) ON DELETE CASCADE,
  PRIMARY KEY(teacher_id, class_id)
);

CREATE TABLE class_student (
  teacher_id INT NOT NULL,
  class_id INT NOT NULL,
  student_id INT NOT NULL,
  CONSTRAINT fk_teacher_id FOREIGN KEY(teacher_id) REFERENCES teacher(id) ON DELETE CASCADE,
  CONSTRAINT fk_class_id FOREIGN KEY(class_id) REFERENCES class(id) ON DELETE CASCADE,
  CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES student(id) ON DELETE CASCADE,
  PRIMARY KEY(teacher_id, class_id, student_id)
);

create table student_grade (
  teacher_id INT NOT NULL,
  class_id INT NOT NULL,
  student_id INT NOT NULL,
  grade_id INT NOT NULL,
  CONSTRAINT fk_teacher_id FOREIGN KEY(teacher_id) REFERENCES teacher(id) ON DELETE CASCADE,
  CONSTRAINT fk_class_id FOREIGN KEY(class_id) REFERENCES class(id) ON DELETE CASCADE,
  CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES student(id) ON DELETE CASCADE,
  CONSTRAINT fk_grade_id FOREIGN KEY(grade_id) REFERENCES grade(id) ON DELETE CASCADE,
  PRIMARY KEY(teacher_id, class_id, student_id, grade_id)
);