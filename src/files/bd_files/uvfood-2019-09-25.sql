--
-- PostgreSQL database dump
--

-- Dumped from database version 10.9
-- Dumped by pg_dump version 10.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: is_active_domain; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.is_active_domain AS integer
	CONSTRAINT is_active_domain_check CHECK ((VALUE = ANY (ARRAY[1, 0])));


ALTER DOMAIN public.is_active_domain OWNER TO postgres;

--
-- Name: is_active_domain2; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.is_active_domain2 AS integer NOT NULL
	CONSTRAINT is_active_domain2_check CHECK ((VALUE = ANY (ARRAY[0, 1])));


ALTER DOMAIN public.is_active_domain2 OWNER TO postgres;

--
-- Name: is_active_domain3; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.is_active_domain3 AS integer NOT NULL DEFAULT 1
	CONSTRAINT is_active_domain3_check CHECK ((VALUE = ANY (ARRAY[0, 1])));


ALTER DOMAIN public.is_active_domain3 OWNER TO postgres;

--
-- Name: is_active_domain4; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.is_active_domain4 AS integer NOT NULL DEFAULT 3
	CONSTRAINT is_active_domain4_check CHECK ((VALUE = ANY (ARRAY[0, 1])));


ALTER DOMAIN public.is_active_domain4 OWNER TO postgres;

--
-- Name: modality_program; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.modality_program AS character varying(20)
	CONSTRAINT modality_program_check CHECK (((VALUE)::text = ANY ((ARRAY['DIURNA'::character varying, 'NOCTURNA'::character varying, 'VESPERTINA'::character varying])::text[])));


ALTER DOMAIN public.modality_program OWNER TO postgres;

--
-- Name: status_account; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.status_account AS integer NOT NULL DEFAULT 1
	CONSTRAINT status_account_check CHECK ((VALUE = ANY (ARRAY[1, 0])));


ALTER DOMAIN public.status_account OWNER TO postgres;

--
-- Name: status_student_program; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.status_student_program AS character varying(20)
	CONSTRAINT status_student_program_check CHECK (((VALUE)::text = ANY ((ARRAY['ACTIVO'::character varying, 'INACTIVO'::character varying, 'EGRESADO'::character varying])::text[])));


ALTER DOMAIN public.status_student_program OWNER TO postgres;

--
-- Name: funcaddloginsertfaculty(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddloginsertfaculty() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('INSERT',CONCAT('INSERT INTO uvfood_faculty ', NEW.namefaculty), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddloginsertfaculty() OWNER TO postgres;

--
-- Name: funcaddloginsertprogram(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddloginsertprogram() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('INSERT',CONCAT('INSERT INTO uvfood_program ', NEW.univallecode), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddloginsertprogram() OWNER TO postgres;

--
-- Name: funcaddloginsertsede(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddloginsertsede() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('INSERT',CONCAT('INSERT INTO uvfood_sede ', NEW.namesede), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddloginsertsede() OWNER TO postgres;

--
-- Name: funcaddloginsertuser(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddloginsertuser() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('INSERT',CONCAT('INSERT INTO uvfood_user ',  NEW.username), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddloginsertuser() OWNER TO postgres;

--
-- Name: funcaddloginsertuserextended(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddloginsertuserextended() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('INSERT',CONCAT('INSERT INTO uvfood_user_extended ', NEW.iduser, ' ', NEW.id_typeuser), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddloginsertuserextended() OWNER TO postgres;

--
-- Name: funcaddlogupdatefaculty(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddlogupdatefaculty() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('UPDATE',CONCAT('UPDATE uvfood_faculty SET ',OLD.namefaculty), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddlogupdatefaculty() OWNER TO postgres;

--
-- Name: funcaddlogupdateprogram(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddlogupdateprogram() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('UPDATE',CONCAT('UPDATE uvfood_program SET ',OLD.univallecode), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddlogupdateprogram() OWNER TO postgres;

--
-- Name: funcaddlogupdatesede(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddlogupdatesede() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('UPDATE',CONCAT('UPDATE uvfood_sede SET ',OLD.namesede), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddlogupdatesede() OWNER TO postgres;

--
-- Name: funcaddlogupdateuser(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddlogupdateuser() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('UPDATE',CONCAT('UPDATE uvfood_user SET  ',OLD.username), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddlogupdateuser() OWNER TO postgres;

--
-- Name: funcaddlogupdateuserextended(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddlogupdateuserextended() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ BEGIN
 INSERT INTO uvfood_logs (actionbd, whatsdone, date_insert)VALUES 
 ('UPDATE',CONCAT('UPDATE uvfood_user SET ',OLD.iduser, ' ', OLD.id_typeuser), CURRENT_TIMESTAMP);
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddlogupdateuserextended() OWNER TO postgres;

--
-- Name: funcaddrecorduserticket(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaddrecorduserticket() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 IF (SELECT type_user FROM uvfood_typeuser WHERE type_user IN
     (SELECT type_user FROM uvfood_typeuser WHERE id_typeuser = NEW.id_typeuser)) IS NOT NULL  THEN BEGIN
        INSERT INTO uvfood_user_tickets(iduser)VALUES (NEW.iduser);
    END; END IF;
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaddrecorduserticket() OWNER TO postgres;

--
-- Name: funcaditionrecorduserticket(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcaditionrecorduserticket() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 UPDATE uvfood_user_tickets SET count_tickets = count_tickets + NEW.tickets  WHERE uvfood_user_tickets.iduser = NEW.created_to;
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcaditionrecorduserticket() OWNER TO postgres;

--
-- Name: funcsubstractrecorduserticket(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcsubstractrecorduserticket() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
 UPDATE uvfood_user_tickets SET count_tickets = count_tickets - 1 WHERE uvfood_user_tickets.iduser = NEW.iduser;
 RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcsubstractrecorduserticket() OWNER TO postgres;

--
-- Name: funcvalidatediscount(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.funcvalidatediscount() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
  IF (SELECT iduser FROM uvfood_user_discount WHERE iduser = NEW.iduser AND is_active = 1) IS NOT NULL  THEN BEGIN
        UPDATE uvfood_user_discount SET is_active = 0 WHERE iduser = NEW.iduser AND is_active = 1;
    END; END IF;
RETURN NEW;
 END;
 $$;


ALTER FUNCTION public.funcvalidatediscount() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: uvfood_consumption_user_ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_consumption_user_ticket (
    id_consumption integer NOT NULL,
    iduser integer NOT NULL,
    date_consumption date DEFAULT '2019-09-23'::date NOT NULL,
    time_consumption timestamp without time zone DEFAULT (now())::timestamp without time zone NOT NULL
);


ALTER TABLE public.uvfood_consumption_user_ticket OWNER TO postgres;

--
-- Name: uvfood_consumption_user_ticket_id_consumption_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_consumption_user_ticket_id_consumption_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_consumption_user_ticket_id_consumption_seq OWNER TO postgres;

--
-- Name: uvfood_consumption_user_ticket_id_consumption_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_consumption_user_ticket_id_consumption_seq OWNED BY public.uvfood_consumption_user_ticket.id_consumption;


--
-- Name: uvfood_discount; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_discount (
    iddiscount integer NOT NULL,
    namediscount character varying(100) NOT NULL,
    price_discount integer NOT NULL
);


ALTER TABLE public.uvfood_discount OWNER TO postgres;

--
-- Name: uvfood_discount_iddiscount_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_discount_iddiscount_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_discount_iddiscount_seq OWNER TO postgres;

--
-- Name: uvfood_discount_iddiscount_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_discount_iddiscount_seq OWNED BY public.uvfood_discount.iddiscount;


--
-- Name: uvfood_faculty; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_faculty (
    idfaculty integer NOT NULL,
    namefaculty character varying(100) NOT NULL
);


ALTER TABLE public.uvfood_faculty OWNER TO postgres;

--
-- Name: uvfood_faculty_idfaculty_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_faculty_idfaculty_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_faculty_idfaculty_seq OWNER TO postgres;

--
-- Name: uvfood_faculty_idfaculty_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_faculty_idfaculty_seq OWNED BY public.uvfood_faculty.idfaculty;


--
-- Name: uvfood_images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_images (
    idimage integer NOT NULL,
    file_image character varying(250),
    publication_date date DEFAULT '2019-09-23'::date,
    type_image integer NOT NULL
);


ALTER TABLE public.uvfood_images OWNER TO postgres;

--
-- Name: uvfood_images_idimage_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_images_idimage_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_images_idimage_seq OWNER TO postgres;

--
-- Name: uvfood_images_idimage_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_images_idimage_seq OWNED BY public.uvfood_images.idimage;


--
-- Name: uvfood_keys; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_keys (
    idkey integer NOT NULL,
    idmodule integer NOT NULL,
    namekey character varying(100) NOT NULL
);


ALTER TABLE public.uvfood_keys OWNER TO postgres;

--
-- Name: uvfood_keys_idkey_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_keys_idkey_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_keys_idkey_seq OWNER TO postgres;

--
-- Name: uvfood_keys_idkey_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_keys_idkey_seq OWNED BY public.uvfood_keys.idkey;


--
-- Name: uvfood_logs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_logs (
    idlog integer NOT NULL,
    actionbd character varying(20) NOT NULL,
    whatsdone character varying(200) NOT NULL,
    date_insert timestamp without time zone NOT NULL
);


ALTER TABLE public.uvfood_logs OWNER TO postgres;

--
-- Name: uvfood_logs_idlog_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_logs_idlog_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_logs_idlog_seq OWNER TO postgres;

--
-- Name: uvfood_logs_idlog_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_logs_idlog_seq OWNED BY public.uvfood_logs.idlog;


--
-- Name: uvfood_modules; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_modules (
    idmodule integer NOT NULL,
    namemodule character varying(100) NOT NULL
);


ALTER TABLE public.uvfood_modules OWNER TO postgres;

--
-- Name: uvfood_modules_idmodule_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_modules_idmodule_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_modules_idmodule_seq OWNER TO postgres;

--
-- Name: uvfood_modules_idmodule_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_modules_idmodule_seq OWNED BY public.uvfood_modules.idmodule;


--
-- Name: uvfood_notice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_notice (
    idnotice integer NOT NULL,
    title character varying(100) NOT NULL,
    subtitle character varying(100) NOT NULL,
    body character varying(250) NOT NULL,
    created_by integer NOT NULL,
    is_active public.is_active_domain
);


ALTER TABLE public.uvfood_notice OWNER TO postgres;

--
-- Name: uvfood_notice_idnotice_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_notice_idnotice_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_notice_idnotice_seq OWNER TO postgres;

--
-- Name: uvfood_notice_idnotice_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_notice_idnotice_seq OWNED BY public.uvfood_notice.idnotice;


--
-- Name: uvfood_program; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_program (
    idprogram integer NOT NULL,
    univallecode character(4) NOT NULL,
    idsede integer NOT NULL,
    idfaculty integer NOT NULL,
    program_name character varying(200) NOT NULL,
    modality public.modality_program NOT NULL
);


ALTER TABLE public.uvfood_program OWNER TO postgres;

--
-- Name: uvfood_program_idprogram_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_program_idprogram_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_program_idprogram_seq OWNER TO postgres;

--
-- Name: uvfood_program_idprogram_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_program_idprogram_seq OWNED BY public.uvfood_program.idprogram;


--
-- Name: uvfood_sales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_sales (
    idticket integer NOT NULL,
    sale_date timestamp without time zone DEFAULT (now())::timestamp without time zone NOT NULL,
    created_by integer NOT NULL,
    created_to integer NOT NULL,
    tickets integer NOT NULL,
    total_price integer NOT NULL,
    cash integer NOT NULL,
    cash_change integer NOT NULL
);


ALTER TABLE public.uvfood_sales OWNER TO postgres;

--
-- Name: uvfood_sales_idticket_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_sales_idticket_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_sales_idticket_seq OWNER TO postgres;

--
-- Name: uvfood_sales_idticket_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_sales_idticket_seq OWNED BY public.uvfood_sales.idticket;


--
-- Name: uvfood_sede; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_sede (
    idsede integer NOT NULL,
    namesede character varying(100) NOT NULL
);


ALTER TABLE public.uvfood_sede OWNER TO postgres;

--
-- Name: uvfood_sede_idsede_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_sede_idsede_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_sede_idsede_seq OWNER TO postgres;

--
-- Name: uvfood_sede_idsede_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_sede_idsede_seq OWNED BY public.uvfood_sede.idsede;


--
-- Name: uvfood_sessions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_sessions (
    idsession integer NOT NULL,
    iduser integer NOT NULL,
    date_session timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.uvfood_sessions OWNER TO postgres;

--
-- Name: uvfood_sessions_idsession_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_sessions_idsession_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_sessions_idsession_seq OWNER TO postgres;

--
-- Name: uvfood_sessions_idsession_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_sessions_idsession_seq OWNED BY public.uvfood_sessions.idsession;


--
-- Name: uvfood_student_program; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_student_program (
    iduser integer NOT NULL,
    idprogram integer NOT NULL,
    status public.status_student_program
);


ALTER TABLE public.uvfood_student_program OWNER TO postgres;

--
-- Name: uvfood_type_image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_type_image (
    idtype integer NOT NULL,
    name_type character varying(250)
);


ALTER TABLE public.uvfood_type_image OWNER TO postgres;

--
-- Name: uvfood_type_image_idtype_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_type_image_idtype_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_type_image_idtype_seq OWNER TO postgres;

--
-- Name: uvfood_type_image_idtype_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_type_image_idtype_seq OWNED BY public.uvfood_type_image.idtype;


--
-- Name: uvfood_typeuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_typeuser (
    id_typeuser integer NOT NULL,
    type_user character varying(100) NOT NULL
);


ALTER TABLE public.uvfood_typeuser OWNER TO postgres;

--
-- Name: uvfood_typeuser_id_typeuser_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_typeuser_id_typeuser_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_typeuser_id_typeuser_seq OWNER TO postgres;

--
-- Name: uvfood_typeuser_id_typeuser_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_typeuser_id_typeuser_seq OWNED BY public.uvfood_typeuser.id_typeuser;


--
-- Name: uvfood_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_user (
    iduser integer NOT NULL,
    username character(20) NOT NULL,
    firstname character varying(200) NOT NULL,
    surname character varying(200) NOT NULL,
    birth_date date,
    email character varying(100) NOT NULL,
    password_user character varying(200) NOT NULL,
    creation_date date DEFAULT now() NOT NULL,
    is_active public.is_active_domain DEFAULT 1 NOT NULL
);


ALTER TABLE public.uvfood_user OWNER TO postgres;

--
-- Name: uvfood_user_discount; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_user_discount (
    iduser integer NOT NULL,
    iddiscount integer NOT NULL,
    date_asign timestamp without time zone DEFAULT '2019-09-24 05:25:26.522128'::timestamp without time zone NOT NULL,
    is_active public.is_active_domain DEFAULT 1
);


ALTER TABLE public.uvfood_user_discount OWNER TO postgres;

--
-- Name: uvfood_user_extended; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_user_extended (
    iduser integer NOT NULL,
    id_typeuser integer NOT NULL,
    status public.status_account NOT NULL
);


ALTER TABLE public.uvfood_user_extended OWNER TO postgres;

--
-- Name: uvfood_user_iduser_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uvfood_user_iduser_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.uvfood_user_iduser_seq OWNER TO postgres;

--
-- Name: uvfood_user_iduser_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uvfood_user_iduser_seq OWNED BY public.uvfood_user.iduser;


--
-- Name: uvfood_user_key; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_user_key (
    iduser integer NOT NULL,
    idkey integer NOT NULL
);


ALTER TABLE public.uvfood_user_key OWNER TO postgres;

--
-- Name: uvfood_user_tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uvfood_user_tickets (
    iduser integer NOT NULL,
    count_tickets integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.uvfood_user_tickets OWNER TO postgres;

--
-- Name: uvfood_consumption_user_ticket id_consumption; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_consumption_user_ticket ALTER COLUMN id_consumption SET DEFAULT nextval('public.uvfood_consumption_user_ticket_id_consumption_seq'::regclass);


--
-- Name: uvfood_discount iddiscount; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_discount ALTER COLUMN iddiscount SET DEFAULT nextval('public.uvfood_discount_iddiscount_seq'::regclass);


--
-- Name: uvfood_faculty idfaculty; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_faculty ALTER COLUMN idfaculty SET DEFAULT nextval('public.uvfood_faculty_idfaculty_seq'::regclass);


--
-- Name: uvfood_images idimage; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_images ALTER COLUMN idimage SET DEFAULT nextval('public.uvfood_images_idimage_seq'::regclass);


--
-- Name: uvfood_keys idkey; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_keys ALTER COLUMN idkey SET DEFAULT nextval('public.uvfood_keys_idkey_seq'::regclass);


--
-- Name: uvfood_logs idlog; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_logs ALTER COLUMN idlog SET DEFAULT nextval('public.uvfood_logs_idlog_seq'::regclass);


--
-- Name: uvfood_modules idmodule; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_modules ALTER COLUMN idmodule SET DEFAULT nextval('public.uvfood_modules_idmodule_seq'::regclass);


--
-- Name: uvfood_notice idnotice; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_notice ALTER COLUMN idnotice SET DEFAULT nextval('public.uvfood_notice_idnotice_seq'::regclass);


--
-- Name: uvfood_program idprogram; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_program ALTER COLUMN idprogram SET DEFAULT nextval('public.uvfood_program_idprogram_seq'::regclass);


--
-- Name: uvfood_sales idticket; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sales ALTER COLUMN idticket SET DEFAULT nextval('public.uvfood_sales_idticket_seq'::regclass);


--
-- Name: uvfood_sede idsede; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sede ALTER COLUMN idsede SET DEFAULT nextval('public.uvfood_sede_idsede_seq'::regclass);


--
-- Name: uvfood_sessions idsession; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sessions ALTER COLUMN idsession SET DEFAULT nextval('public.uvfood_sessions_idsession_seq'::regclass);


--
-- Name: uvfood_type_image idtype; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_type_image ALTER COLUMN idtype SET DEFAULT nextval('public.uvfood_type_image_idtype_seq'::regclass);


--
-- Name: uvfood_typeuser id_typeuser; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_typeuser ALTER COLUMN id_typeuser SET DEFAULT nextval('public.uvfood_typeuser_id_typeuser_seq'::regclass);


--
-- Name: uvfood_user iduser; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user ALTER COLUMN iduser SET DEFAULT nextval('public.uvfood_user_iduser_seq'::regclass);


--
-- Data for Name: uvfood_consumption_user_ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_consumption_user_ticket (id_consumption, iduser, date_consumption, time_consumption) FROM stdin;
1	17	2019-09-23	2019-09-23 21:40:57.235042
4	18	2019-09-23	2019-09-24 16:44:24.330524
\.


--
-- Data for Name: uvfood_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_discount (iddiscount, namediscount, price_discount) FROM stdin;
1	monitor	100
2	beca_alimentaria	2100
\.


--
-- Data for Name: uvfood_faculty; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_faculty (idfaculty, namefaculty) FROM stdin;
\.


--
-- Data for Name: uvfood_images; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_images (idimage, file_image, publication_date, type_image) FROM stdin;
\.


--
-- Data for Name: uvfood_keys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_keys (idkey, idmodule, namekey) FROM stdin;
3	1	users.select.csv
4	1	users.upload.csv
5	3	index.show.count.users
6	3	index.show.count.sessions
7	4	permissions.asign
8	4	permissions.show.view.asign
13	4	permissions.create.keys
14	4	permissions.create.modules
15	5	prueba.prueba1
16	2	sales.generate.user.graph
17	2	sales.generate.user.sale
\.


--
-- Data for Name: uvfood_logs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_logs (idlog, actionbd, whatsdone, date_insert) FROM stdin;
1	INSERT	INSERT INTO uvfood_usersuperad	2019-08-15 00:56:45.537235
2	UPDATE	UPDATE uvfood_user SET  superad	2019-08-15 00:59:28.939751
3	INSERT	INSERT INTO uvfood_sede CALI-MELENDEZ	2019-08-15 01:24:01.661582
4	INSERT	INSERT INTO uvfood_user sistemasUVFood      	2019-08-27 14:37:52.319577
5	INSERT	INSERT INTO uvfood_user_extended 5 1	2019-08-27 14:49:56.717944
6	INSERT	INSERT INTO uvfood_user kcopper             	2019-08-27 19:05:40.99525
7	INSERT	INSERT INTO uvfood_user_extended 6 3	2019-08-27 19:07:51.679916
8	UPDATE	UPDATE uvfood_user SET 6 3	2019-08-27 19:12:52.354899
9	INSERT	INSERT INTO uvfood_user vendedor1           	2019-08-28 00:35:52.984478
10	INSERT	INSERT INTO uvfood_user_extended 7 2	2019-08-28 00:36:56.90868
11	INSERT	INSERT INTO uvfood_user jp                  	2019-09-19 20:51:27.327373
12	UPDATE	UPDATE uvfood_user SET  kcopper             	2019-09-22 21:55:08.620036
13	UPDATE	UPDATE uvfood_user SET  kcopper             	2019-09-22 21:56:59.415256
14	UPDATE	UPDATE uvfood_user SET  jp                  	2019-09-22 21:57:17.006865
15	UPDATE	UPDATE uvfood_user SET  kcopper             	2019-09-22 21:59:04.6364
16	UPDATE	UPDATE uvfood_user SET  jp                  	2019-09-22 21:59:18.181621
17	UPDATE	UPDATE uvfood_user SET  kcopper             	2019-09-22 21:59:21.238502
18	UPDATE	UPDATE uvfood_user SET  kcopper             	2019-09-22 22:09:31.40271
19	UPDATE	UPDATE uvfood_user SET  jp                  	2019-09-22 22:09:47.146595
20	UPDATE	UPDATE uvfood_user SET  kcopper             	2019-09-22 22:12:05.276566
21	UPDATE	UPDATE uvfood_user SET  jp                  	2019-09-22 22:12:07.42381
22	UPDATE	UPDATE uvfood_user SET  jp                  	2019-09-22 22:12:14.737415
23	UPDATE	UPDATE uvfood_user SET  jp                  	2019-09-22 22:12:43.75847
24	INSERT	INSERT INTO uvfood_user_extended 6 3	2019-09-23 18:57:18.309997
25	INSERT	INSERT INTO uvfood_user nuevo               	2019-09-23 20:35:45.867132
26	INSERT	INSERT INTO uvfood_user nuevo2              	2019-09-23 20:41:30.709406
27	INSERT	INSERT INTO uvfood_user Nuevo3              	2019-09-23 20:43:18.274499
28	INSERT	INSERT INTO uvfood_user Nuevo4              	2019-09-23 20:52:33.07213
29	INSERT	INSERT INTO uvfood_user 1740792             	2019-09-24 14:06:06.696735
\.


--
-- Data for Name: uvfood_modules; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_modules (idmodule, namemodule) FROM stdin;
1	users
2	sales
3	index
4	permissions
5	prueba
\.


--
-- Data for Name: uvfood_notice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_notice (idnotice, title, subtitle, body, created_by, is_active) FROM stdin;
\.


--
-- Data for Name: uvfood_program; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_program (idprogram, univallecode, idsede, idfaculty, program_name, modality) FROM stdin;
\.


--
-- Data for Name: uvfood_sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_sales (idticket, sale_date, created_by, created_to, tickets, total_price, cash, cash_change) FROM stdin;
2	2019-09-23 21:38:36.464834	5	17	3	6000	6000	0
3	2019-09-23 21:39:04.322417	5	17	3	6000	6000	0
4	2019-09-23 21:39:04.91452	5	17	3	6000	6000	0
5	2019-09-24 16:10:15.492693	5	18	1	2100	2200	100
6	2019-09-24 16:11:18.010444	5	18	1	2100	2200	100
7	2019-09-24 16:11:33.755933	5	18	0	0	0	0
8	2019-09-24 16:11:48.762995	5	18	1	2100	2100	0
9	2019-09-24 16:13:35.992091	5	18	1	2100	2100	0
10	2019-09-24 16:14:38.332568	5	18	1	2100	2100	0
11	2019-09-24 16:14:43.008786	5	18	1	2100	2100	0
12	2019-09-24 16:14:47.098109	5	18	1	2100	2100	0
13	2019-09-24 16:27:19.278811	5	17	1	2000	2000	0
\.


--
-- Data for Name: uvfood_sede; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_sede (idsede, namesede) FROM stdin;
1	CALI-MELENDEZ
\.


--
-- Data for Name: uvfood_sessions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_sessions (idsession, iduser, date_session) FROM stdin;
1	5	2019-08-29 01:38:23.586107
2	7	2019-08-29 01:40:29.571127
3	5	2019-08-29 01:44:52.405405
4	5	2019-08-29 02:14:08.516188
5	5	2019-08-29 02:15:26.682847
6	5	2019-08-29 02:17:12.754731
7	7	2019-08-29 02:19:17.561684
8	5	2019-08-29 02:38:43.535812
9	5	2019-08-29 18:29:47.259264
10	5	2019-08-29 18:37:05.262525
11	5	2019-08-29 18:39:14.233265
12	5	2019-08-29 18:41:46.770598
13	5	2019-08-29 19:00:35.294705
14	5	2019-08-29 19:02:26.0855
15	5	2019-08-29 19:12:20.468362
16	5	2019-08-29 19:14:58.715074
17	5	2019-08-30 23:39:55.936079
18	5	2019-08-30 23:41:33.325106
19	5	2019-09-19 20:05:21.977993
20	5	2019-09-19 20:37:58.234809
21	5	2019-09-19 20:40:56.186939
22	5	2019-09-19 20:43:15.474501
23	5	2019-09-19 20:46:30.909717
24	5	2019-09-19 22:20:03.797334
25	5	2019-09-20 00:34:38.033376
26	5	2019-09-20 02:56:03.191839
27	5	2019-09-20 02:58:16.296956
28	5	2019-09-20 03:09:52.834752
29	5	2019-09-20 03:12:42.032018
30	5	2019-09-20 03:14:21.663807
31	5	2019-09-20 03:21:02.770034
32	5	2019-09-20 03:22:34.964966
33	5	2019-09-20 03:23:30.263098
34	5	2019-09-20 03:24:57.386184
35	5	2019-09-20 03:27:27.835665
36	5	2019-09-20 03:30:40.601875
37	5	2019-09-20 03:33:00.447942
38	5	2019-09-20 03:37:21.425574
39	5	2019-09-20 03:38:03.68607
40	5	2019-09-20 03:39:30.436409
41	5	2019-09-20 03:41:45.371921
42	5	2019-09-20 03:42:42.946721
43	5	2019-09-20 03:43:28.713334
44	5	2019-09-20 03:45:07.507177
45	5	2019-09-20 03:45:51.460782
46	5	2019-09-20 03:46:25.389801
47	5	2019-09-20 03:47:39.462269
48	5	2019-09-20 03:49:26.990596
49	5	2019-09-20 03:52:55.685762
50	5	2019-09-20 03:54:21.814512
51	5	2019-09-20 03:58:21.960549
52	5	2019-09-20 04:16:14.369853
53	5	2019-09-20 04:29:27.915628
54	5	2019-09-20 04:30:44.750408
55	5	2019-09-20 04:32:27.522523
56	5	2019-09-20 04:38:12.699885
57	5	2019-09-20 04:39:54.77573
58	5	2019-09-20 04:43:15.041227
59	5	2019-09-20 04:48:07.541919
60	5	2019-09-20 04:50:44.381673
61	5	2019-09-20 04:51:54.474831
62	5	2019-09-20 04:55:33.87267
63	5	2019-09-20 04:59:09.401565
64	5	2019-09-20 05:05:54.787756
65	5	2019-09-20 05:08:12.972659
66	5	2019-09-20 17:13:17.168568
67	5	2019-09-22 21:54:30.034804
68	5	2019-09-22 21:56:48.022903
69	5	2019-09-22 22:09:02.849972
70	5	2019-09-22 22:11:59.18521
71	5	2019-09-23 00:37:56.524774
72	5	2019-09-23 00:50:29.719207
73	5	2019-09-23 00:55:24.404666
74	5	2019-09-23 01:00:53.413565
75	5	2019-09-23 01:56:18.677456
76	5	2019-09-23 01:57:28.73725
77	5	2019-09-23 01:59:19.29148
78	5	2019-09-23 02:02:43.668403
79	5	2019-09-23 02:09:58.012224
80	5	2019-09-23 02:11:50.263862
81	5	2019-09-23 02:15:04.945998
82	5	2019-09-23 02:16:53.863734
83	5	2019-09-23 02:19:30.069836
84	5	2019-09-23 02:22:57.445621
85	5	2019-09-23 02:27:29.432963
86	5	2019-09-23 02:30:51.430505
87	5	2019-09-23 15:31:47.634613
88	5	2019-09-23 20:06:48.070433
89	5	2019-09-23 20:15:27.798729
90	5	2019-09-23 20:22:30.818502
91	5	2019-09-23 20:40:44.774933
92	5	2019-09-24 00:55:11.378559
93	5	2019-09-24 01:00:13.120446
94	5	2019-09-24 01:07:21.493084
95	5	2019-09-24 01:12:46.243222
96	5	2019-09-24 01:14:20.133098
97	5	2019-09-24 01:49:31.92938
98	5	2019-09-24 01:55:08.36793
99	5	2019-09-24 01:56:29.286281
100	5	2019-09-24 02:26:10.171577
101	5	2019-09-24 03:29:23.134849
102	5	2019-09-24 03:38:50.408897
103	5	2019-09-24 03:45:50.401822
104	5	2019-09-24 03:47:42.069543
105	5	2019-09-24 13:46:15.06574
106	5	2019-09-24 13:52:28.145064
107	5	2019-09-24 13:53:15.322808
108	5	2019-09-24 14:27:00.126288
109	5	2019-09-24 15:06:53.248855
110	5	2019-09-24 15:08:36.320257
111	5	2019-09-24 15:26:49.041083
112	5	2019-09-24 15:34:14.883227
113	5	2019-09-24 15:35:25.015574
114	5	2019-09-24 15:39:10.313642
115	5	2019-09-24 15:49:36.102151
116	5	2019-09-24 15:51:57.644201
117	5	2019-09-24 15:52:42.075677
118	5	2019-09-24 16:08:18.428032
119	5	2019-09-24 16:10:03.356653
120	5	2019-09-24 16:13:18.378247
121	5	2019-09-24 16:14:29.422615
122	5	2019-09-24 16:24:58.40719
123	5	2019-09-24 16:26:19.885642
124	5	2019-09-24 16:27:07.078798
125	5	2019-09-24 16:42:17.43589
126	5	2019-09-24 16:43:18.867564
127	5	2019-09-24 16:44:02.335442
\.


--
-- Data for Name: uvfood_student_program; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_student_program (iduser, idprogram, status) FROM stdin;
\.


--
-- Data for Name: uvfood_type_image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_type_image (idtype, name_type) FROM stdin;
1	Slider
2	Menu
\.


--
-- Data for Name: uvfood_typeuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_typeuser (id_typeuser, type_user) FROM stdin;
1	Administrador
2	Vendedor
3	Cliente
\.


--
-- Data for Name: uvfood_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_user (iduser, username, firstname, surname, birth_date, email, password_user, creation_date, is_active) FROM stdin;
5	sistemasUVFood      	Oficina de sistemas	UVFood	2000-12-12	uvfood.sistemas@gmail.com	sistemas	2019-08-27	1
7	vendedor1           	Vendedor	UVFood	2000-12-12	vendedor.uvfood@gmail.com	vendedor	2019-08-28	1
6	kcopper             	Hanier	Pena	1900-12-12	kcopper.uvfood@gmail.com	123	2019-08-27	1
8	jp                  	Juan	Pablo	2000-10-10	jp@gmail.com	jp	2019-09-19	0
14	nuevo               	Nuevo	Usuario	1999-10-10	juan@gmail.com	1234	2019-09-23	1
15	nuevo2              	Nuevo2	Usuario2	1999-10-12	nuevo2@gmail.com	1234	2019-09-23	1
16	Nuevo3              	Nuevo3	Usuario3	1999-10-12	nn@sdasd.com	1234	2019-09-23	1
17	Nuevo4              	Nuevo4	Usuario4	1999-12-12	jp@sd.co	1234	2019-09-23	1
18	1740792             	Juan Pablo	Castro	2000-02-02	juan.castro@correounivalle.edu.co	123	2019-09-24	1
\.


--
-- Data for Name: uvfood_user_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_user_discount (iduser, iddiscount, date_asign, is_active) FROM stdin;
16	1	2019-09-24 05:25:26.522128	0
16	1	2019-09-24 05:25:26.522128	1
17	1	2019-09-24 05:25:26.522128	1
\.


--
-- Data for Name: uvfood_user_extended; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_user_extended (iduser, id_typeuser, status) FROM stdin;
5	1	1
6	3	1
7	2	1
15	3	1
16	3	1
17	3	1
18	3	1
\.


--
-- Data for Name: uvfood_user_key; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_user_key (iduser, idkey) FROM stdin;
5	3
5	4
5	5
5	6
5	7
5	8
5	13
5	16
5	17
\.


--
-- Data for Name: uvfood_user_tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uvfood_user_tickets (iduser, count_tickets) FROM stdin;
6	0
17	3
18	6
\.


--
-- Name: uvfood_consumption_user_ticket_id_consumption_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_consumption_user_ticket_id_consumption_seq', 5, true);


--
-- Name: uvfood_discount_iddiscount_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_discount_iddiscount_seq', 2, true);


--
-- Name: uvfood_faculty_idfaculty_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_faculty_idfaculty_seq', 1, false);


--
-- Name: uvfood_images_idimage_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_images_idimage_seq', 1, false);


--
-- Name: uvfood_keys_idkey_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_keys_idkey_seq', 17, true);


--
-- Name: uvfood_logs_idlog_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_logs_idlog_seq', 29, true);


--
-- Name: uvfood_modules_idmodule_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_modules_idmodule_seq', 5, true);


--
-- Name: uvfood_notice_idnotice_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_notice_idnotice_seq', 1, false);


--
-- Name: uvfood_program_idprogram_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_program_idprogram_seq', 1, false);


--
-- Name: uvfood_sales_idticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_sales_idticket_seq', 13, true);


--
-- Name: uvfood_sede_idsede_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_sede_idsede_seq', 1, true);


--
-- Name: uvfood_sessions_idsession_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_sessions_idsession_seq', 127, true);


--
-- Name: uvfood_type_image_idtype_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_type_image_idtype_seq', 2, true);


--
-- Name: uvfood_typeuser_id_typeuser_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_typeuser_id_typeuser_seq', 3, true);


--
-- Name: uvfood_user_iduser_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uvfood_user_iduser_seq', 18, true);


--
-- Name: uvfood_keys ak_uvfood_keys_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT ak_uvfood_keys_unique UNIQUE (idmodule, namekey);


--
-- Name: uvfood_logs ak_uvfood_logs_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_logs
    ADD CONSTRAINT ak_uvfood_logs_unique UNIQUE (actionbd, whatsdone, date_insert);


--
-- Name: uvfood_program ak_uvfood_program_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT ak_uvfood_program_unique UNIQUE (univallecode, idsede, idfaculty, program_name, modality);


--
-- Name: uvfood_student_program ak_uvfood_student_program_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_student_program
    ADD CONSTRAINT ak_uvfood_student_program_unique UNIQUE (iduser, idprogram);


--
-- Name: uvfood_user_key ak_uvfood_user_key_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_key
    ADD CONSTRAINT ak_uvfood_user_key_unique UNIQUE (iduser, idkey);


--
-- Name: uvfood_images constraint_fechaunica; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_images
    ADD CONSTRAINT constraint_fechaunica PRIMARY KEY (idimage);


--
-- Name: uvfood_type_image constraint_idtype; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_type_image
    ADD CONSTRAINT constraint_idtype PRIMARY KEY (idtype);


--
-- Name: uvfood_user_discount fk_uniqe_user_discount; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_discount
    ADD CONSTRAINT fk_uniqe_user_discount UNIQUE (iduser, iddiscount, is_active);


--
-- Name: uvfood_consumption_user_ticket unique_consumption_date_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_consumption_user_ticket
    ADD CONSTRAINT unique_consumption_date_user UNIQUE (iduser, date_consumption);


--
-- Name: uvfood_discount unique_discount; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_discount
    ADD CONSTRAINT unique_discount UNIQUE (namediscount, price_discount);


--
-- Name: uvfood_user_extended unique_record_user_extended; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_extended
    ADD CONSTRAINT unique_record_user_extended UNIQUE (iduser, id_typeuser);


--
-- Name: uvfood_consumption_user_ticket uvfood_consumption_user_ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_consumption_user_ticket
    ADD CONSTRAINT uvfood_consumption_user_ticket_pkey PRIMARY KEY (id_consumption);


--
-- Name: uvfood_discount uvfood_discount_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_discount
    ADD CONSTRAINT uvfood_discount_pkey PRIMARY KEY (iddiscount);


--
-- Name: uvfood_faculty uvfood_faculty_namefaculty_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_faculty
    ADD CONSTRAINT uvfood_faculty_namefaculty_key UNIQUE (namefaculty);


--
-- Name: uvfood_faculty uvfood_faculty_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_faculty
    ADD CONSTRAINT uvfood_faculty_pkey PRIMARY KEY (idfaculty);


--
-- Name: uvfood_keys uvfood_keys_namekey_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT uvfood_keys_namekey_key UNIQUE (namekey);


--
-- Name: uvfood_keys uvfood_keys_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT uvfood_keys_pkey PRIMARY KEY (idkey);


--
-- Name: uvfood_logs uvfood_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_logs
    ADD CONSTRAINT uvfood_logs_pkey PRIMARY KEY (idlog);


--
-- Name: uvfood_modules uvfood_modules_namemodule_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_modules
    ADD CONSTRAINT uvfood_modules_namemodule_key UNIQUE (namemodule);


--
-- Name: uvfood_modules uvfood_modules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_modules
    ADD CONSTRAINT uvfood_modules_pkey PRIMARY KEY (idmodule);


--
-- Name: uvfood_notice uvfood_notice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_notice
    ADD CONSTRAINT uvfood_notice_pkey PRIMARY KEY (idnotice);


--
-- Name: uvfood_program uvfood_program_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT uvfood_program_pkey PRIMARY KEY (idprogram);


--
-- Name: uvfood_program uvfood_program_univallecode_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT uvfood_program_univallecode_key UNIQUE (univallecode);


--
-- Name: uvfood_sales uvfood_sales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sales
    ADD CONSTRAINT uvfood_sales_pkey PRIMARY KEY (idticket);


--
-- Name: uvfood_sede uvfood_sede_namesede_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sede
    ADD CONSTRAINT uvfood_sede_namesede_key UNIQUE (namesede);


--
-- Name: uvfood_sede uvfood_sede_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sede
    ADD CONSTRAINT uvfood_sede_pkey PRIMARY KEY (idsede);


--
-- Name: uvfood_sessions uvfood_sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sessions
    ADD CONSTRAINT uvfood_sessions_pkey PRIMARY KEY (idsession);


--
-- Name: uvfood_typeuser uvfood_typeuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_typeuser
    ADD CONSTRAINT uvfood_typeuser_pkey PRIMARY KEY (id_typeuser);


--
-- Name: uvfood_typeuser uvfood_typeuser_type_user_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_typeuser
    ADD CONSTRAINT uvfood_typeuser_type_user_key UNIQUE (type_user);


--
-- Name: uvfood_user uvfood_user_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_email_key UNIQUE (email);


--
-- Name: uvfood_user uvfood_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_pkey PRIMARY KEY (iduser);


--
-- Name: uvfood_user uvfood_user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_username_key UNIQUE (username);


--
-- Name: uvfood_user_extended tr_add_user_extended_record; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_add_user_extended_record AFTER INSERT ON public.uvfood_user_extended FOR EACH ROW EXECUTE PROCEDURE public.funcaddrecorduserticket();


--
-- Name: uvfood_sales tr_adition_user_ticket; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_adition_user_ticket AFTER INSERT ON public.uvfood_sales FOR EACH ROW EXECUTE PROCEDURE public.funcaditionrecorduserticket();


--
-- Name: uvfood_consumption_user_ticket tr_consumption_user_ticket; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_consumption_user_ticket AFTER INSERT ON public.uvfood_consumption_user_ticket FOR EACH ROW EXECUTE PROCEDURE public.funcsubstractrecorduserticket();


--
-- Name: uvfood_user tr_updlogs1; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs1 AFTER INSERT ON public.uvfood_user FOR EACH ROW EXECUTE PROCEDURE public.funcaddloginsertuser();


--
-- Name: uvfood_sede tr_updlogs10; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs10 AFTER UPDATE ON public.uvfood_sede FOR EACH ROW EXECUTE PROCEDURE public.funcaddlogupdatesede();


--
-- Name: uvfood_user tr_updlogs2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs2 AFTER UPDATE ON public.uvfood_user FOR EACH ROW EXECUTE PROCEDURE public.funcaddlogupdateuser();


--
-- Name: uvfood_program tr_updlogs5; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs5 AFTER INSERT ON public.uvfood_program FOR EACH ROW EXECUTE PROCEDURE public.funcaddloginsertprogram();


--
-- Name: uvfood_program tr_updlogs6; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs6 AFTER UPDATE ON public.uvfood_program FOR EACH ROW EXECUTE PROCEDURE public.funcaddlogupdateprogram();


--
-- Name: uvfood_faculty tr_updlogs7; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs7 AFTER INSERT ON public.uvfood_faculty FOR EACH ROW EXECUTE PROCEDURE public.funcaddloginsertfaculty();


--
-- Name: uvfood_faculty tr_updlogs8; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs8 AFTER UPDATE ON public.uvfood_faculty FOR EACH ROW EXECUTE PROCEDURE public.funcaddlogupdatefaculty();


--
-- Name: uvfood_sede tr_updlogs9; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_updlogs9 AFTER INSERT ON public.uvfood_sede FOR EACH ROW EXECUTE PROCEDURE public.funcaddloginsertsede();


--
-- Name: uvfood_user_discount tr_validate_discount; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_validate_discount BEFORE INSERT ON public.uvfood_user_discount FOR EACH ROW EXECUTE PROCEDURE public.funcvalidatediscount();


--
-- Name: uvfood_user_extended fk_constraint_iduser_uesr_extended; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_extended
    ADD CONSTRAINT fk_constraint_iduser_uesr_extended FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_user_extended fk_constraint_typeuser_uesr_extended; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_extended
    ADD CONSTRAINT fk_constraint_typeuser_uesr_extended FOREIGN KEY (id_typeuser) REFERENCES public.uvfood_typeuser(id_typeuser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_consumption_user_ticket fk_consumption_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_consumption_user_ticket
    ADD CONSTRAINT fk_consumption_user FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_user_tickets fk_count_tickets_iduser; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_tickets
    ADD CONSTRAINT fk_count_tickets_iduser FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_notice fk_created_by; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_notice
    ADD CONSTRAINT fk_created_by FOREIGN KEY (created_by) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_sales fk_created_by_sales; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sales
    ADD CONSTRAINT fk_created_by_sales FOREIGN KEY (created_by) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_sales fk_created_to_sales; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sales
    ADD CONSTRAINT fk_created_to_sales FOREIGN KEY (created_to) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_user_discount fk_discount; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_discount
    ADD CONSTRAINT fk_discount FOREIGN KEY (iddiscount) REFERENCES public.uvfood_discount(iddiscount) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_images fk_type_image; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_images
    ADD CONSTRAINT fk_type_image FOREIGN KEY (type_image) REFERENCES public.uvfood_type_image(idtype) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_user_discount fk_user_discount; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_discount
    ADD CONSTRAINT fk_user_discount FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_sessions fk_user_sessions; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_sessions
    ADD CONSTRAINT fk_user_sessions FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser);


--
-- Name: uvfood_program fk_uvfood_idfaculty1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT fk_uvfood_idfaculty1 FOREIGN KEY (idfaculty) REFERENCES public.uvfood_faculty(idfaculty) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_user_key fk_uvfood_idkey1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_key
    ADD CONSTRAINT fk_uvfood_idkey1 FOREIGN KEY (idkey) REFERENCES public.uvfood_keys(idkey) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_keys fk_uvfood_idmodule1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT fk_uvfood_idmodule1 FOREIGN KEY (idmodule) REFERENCES public.uvfood_modules(idmodule) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_student_program fk_uvfood_idprogram1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_student_program
    ADD CONSTRAINT fk_uvfood_idprogram1 FOREIGN KEY (idprogram) REFERENCES public.uvfood_program(idprogram) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_program fk_uvfood_idsede1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT fk_uvfood_idsede1 FOREIGN KEY (idsede) REFERENCES public.uvfood_sede(idsede) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_student_program fk_uvfood_iduser2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_student_program
    ADD CONSTRAINT fk_uvfood_iduser2 FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: uvfood_user_key fk_uvfood_iduser3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uvfood_user_key
    ADD CONSTRAINT fk_uvfood_iduser3 FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

