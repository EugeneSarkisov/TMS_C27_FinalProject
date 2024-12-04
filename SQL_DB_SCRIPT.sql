BEGIN;


CREATE TABLE IF NOT EXISTS public.user_account
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    first_name character varying(64) COLLATE pg_catalog."default",
    last_name character varying(64) COLLATE pg_catalog."default",
    email character varying(128) COLLATE pg_catalog."default",
    gender_id integer,
    about_me text COLLATE pg_catalog."default",
    grade numeric(3, 2) DEFAULT 1.00,
    confirmed boolean DEFAULT false,
    username character varying(32) COLLATE pg_catalog."default" NOT NULL,
    password character varying(128) COLLATE pg_catalog."default" NOT NULL,
    role character varying(16) COLLATE pg_catalog."default",
    CONSTRAINT user_account_pkey PRIMARY KEY (id),
    CONSTRAINT user_account_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS public.user_hobbies
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_account_id integer,
    hobbie_id integer,
    CONSTRAINT user_hobbies_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.user_photo
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_account_id integer,
    link text COLLATE pg_catalog."default",
    CONSTRAINT user_photo_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.gender
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    gender_name character varying(32) COLLATE pg_catalog."default",
    CONSTRAINT gender_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.hobbies
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    hobbie_name character varying(64) COLLATE pg_catalog."default",
    CONSTRAINT hobbies_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.interested_in_gender
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_account_id integer,
    gender_id integer,
    CONSTRAINT interested_in_gender_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.interested_in_relation
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_account_id integer,
    relation_type_id integer,
    CONSTRAINT interested_in_relation_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.relation_type
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(64) COLLATE pg_catalog."default",
    CONSTRAINT relation_type_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.user_account
    ADD FOREIGN KEY (id)
    REFERENCES public.interested_in_relation (user_account_id) MATCH SIMPLE
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


ALTER TABLE IF EXISTS public.gender
    ADD FOREIGN KEY (id)
    REFERENCES public.interested_in_gender (gender_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.gender
    ADD FOREIGN KEY (id)
    REFERENCES public.user_account (gender_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.hobbies
    ADD FOREIGN KEY (id)
    REFERENCES public.user_hobbies (hobbie_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.interested_in_relation
    ADD FOREIGN KEY (user_account_id)
    REFERENCES public.user_account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.relation_type
    ADD FOREIGN KEY (id)
    REFERENCES public.interested_in_relation (relation_type_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;