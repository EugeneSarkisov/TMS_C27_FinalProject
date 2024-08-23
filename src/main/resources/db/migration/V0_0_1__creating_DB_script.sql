BEGIN;

CREATE TABLE IF NOT EXISTS public.gender
(
    id integer NOT NULL,
    gender_name character varying(32) COLLATE pg_catalog."default",
    CONSTRAINT gender_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.hobbies
(
    id integer NOT NULL,
    hobbie_name character varying(64) COLLATE pg_catalog."default",
    CONSTRAINT hobbies_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.interested_in_gender
(
    id integer NOT NULL,
    user_account_id integer,
    gender_id integer,
    CONSTRAINT interested_in_gender_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.interested_in_relation
(
    id integer NOT NULL,
    user_account_id integer,
    relation_type_id integer,
    CONSTRAINT interested_in_relation_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.relation_type
(
    id integer NOT NULL,
    name character varying(64) COLLATE pg_catalog."default",
    CONSTRAINT relation_type_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.user_hobbies
(
    id integer NOT NULL,
    user_account_id integer,
    hobbie_id integer,
    CONSTRAINT user_hobbies_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.user_photo
(
    id integer NOT NULL,
    user_account_id integer,
    link text COLLATE pg_catalog."default",
    CONSTRAINT user_photo_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.user_creds
(
    id integer NOT NULL,
    user_account_id integer NOT NULL,
    user_username character varying(128) COLLATE pg_catalog."default" NOT NULL,
    user_password character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_password_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.user_account
(
    id integer NOT NULL,
    first_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    email character varying(128) COLLATE pg_catalog."default" NOT NULL,
    gender_id integer NOT NULL,
    about_me text COLLATE pg_catalog."default",
    grade numeric(3, 2) DEFAULT 1.00,
    confirmed boolean NOT NULL DEFAULT false,
    user_role character varying(16) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_account_pkey PRIMARY KEY (id)
    );

ALTER TABLE IF EXISTS public.interested_in_gender
    ADD FOREIGN KEY (user_account_id)
    REFERENCES public.user_account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.interested_in_gender
    ADD FOREIGN KEY (gender_id)
    REFERENCES public.gender (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.interested_in_relation
    ADD FOREIGN KEY (relation_type_id)
    REFERENCES public.relation_type (name) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.interested_in_relation
    ADD FOREIGN KEY (user_account_id)
    REFERENCES public.user_account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.user_hobbies
    ADD FOREIGN KEY (hobbie_id)
    REFERENCES public.hobbies (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.user_hobbies
    ADD FOREIGN KEY (user_account_id)
    REFERENCES public.user_account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.user_photo
    ADD FOREIGN KEY (user_account_id)
    REFERENCES public.user_account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.user_creds
    ADD FOREIGN KEY (user_account_id)
    REFERENCES public.user_account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

END;