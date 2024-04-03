CREATE UNIQUE INDEX schools_slug_key ON schools(slug);
CREATE UNIQUE INDEX peoples_school_id_email_key ON peoples(school_id, email);
CREATE UNIQUE INDEX check_ins_people_id_key ON check_ins(people_id);