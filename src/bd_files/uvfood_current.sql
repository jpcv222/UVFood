PGDMP     0                    w            uvfood    10.9    10.9 X    k           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            l           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            m           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            n           1262    24765    uvfood    DATABASE     �   CREATE DATABASE uvfood WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE uvfood;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            o           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            p           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            n           1247    24932    modality_program    DOMAIN     �   CREATE DOMAIN public.modality_program AS character varying(20)
	CONSTRAINT modality_program_check CHECK (((VALUE)::text = ANY ((ARRAY['DIURNA'::character varying, 'NOCTURNA'::character varying, 'VESPERTINA'::character varying])::text[])));
 %   DROP DOMAIN public.modality_program;
       public       postgres    false    3            a           1247    24897    status_account    DOMAIN     u   CREATE DOMAIN public.status_account AS integer
	CONSTRAINT status_account_check CHECK ((VALUE = ANY (ARRAY[1, 0])));
 #   DROP DOMAIN public.status_account;
       public       postgres    false    3            u           1247    24959    status_student_program    DOMAIN     �   CREATE DOMAIN public.status_student_program AS character varying(20)
	CONSTRAINT status_student_program_check CHECK (((VALUE)::text = ANY ((ARRAY['ACTIVO'::character varying, 'INACTIVO'::character varying, 'EGRESADO'::character varying])::text[])));
 +   DROP DOMAIN public.status_student_program;
       public       postgres    false    3            �            1259    24924    uvfood_faculty    TABLE     x   CREATE TABLE public.uvfood_faculty (
    idfaculty integer NOT NULL,
    namefaculty character varying(100) NOT NULL
);
 "   DROP TABLE public.uvfood_faculty;
       public         postgres    false    3            �            1259    24922    uvfood_faculty_idfaculty_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_faculty_idfaculty_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.uvfood_faculty_idfaculty_seq;
       public       postgres    false    3    204            q           0    0    uvfood_faculty_idfaculty_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.uvfood_faculty_idfaculty_seq OWNED BY public.uvfood_faculty.idfaculty;
            public       postgres    false    203            �            1259    24991    uvfood_keys    TABLE     �   CREATE TABLE public.uvfood_keys (
    idkey integer NOT NULL,
    idmodule integer NOT NULL,
    namekey character varying(100) NOT NULL
);
    DROP TABLE public.uvfood_keys;
       public         postgres    false    3            �            1259    24989    uvfood_keys_idkey_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_keys_idkey_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.uvfood_keys_idkey_seq;
       public       postgres    false    211    3            r           0    0    uvfood_keys_idkey_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.uvfood_keys_idkey_seq OWNED BY public.uvfood_keys.idkey;
            public       postgres    false    210            �            1259    24981    uvfood_modules    TABLE     v   CREATE TABLE public.uvfood_modules (
    idmodule integer NOT NULL,
    namemodule character varying(100) NOT NULL
);
 "   DROP TABLE public.uvfood_modules;
       public         postgres    false    3            �            1259    24979    uvfood_modules_idmodule_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_modules_idmodule_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.uvfood_modules_idmodule_seq;
       public       postgres    false    3    209            s           0    0    uvfood_modules_idmodule_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.uvfood_modules_idmodule_seq OWNED BY public.uvfood_modules.idmodule;
            public       postgres    false    208            �            1259    24936    uvfood_program    TABLE       CREATE TABLE public.uvfood_program (
    idprogram integer NOT NULL,
    univallecode character(4) NOT NULL,
    idsede integer NOT NULL,
    idfaculty integer NOT NULL,
    program_name character varying(200) NOT NULL,
    modality public.modality_program NOT NULL
);
 "   DROP TABLE public.uvfood_program;
       public         postgres    false    622    3            �            1259    24934    uvfood_program_idprogram_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_program_idprogram_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.uvfood_program_idprogram_seq;
       public       postgres    false    3    206            t           0    0    uvfood_program_idprogram_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.uvfood_program_idprogram_seq OWNED BY public.uvfood_program.idprogram;
            public       postgres    false    205            �            1259    24914    uvfood_sede    TABLE     o   CREATE TABLE public.uvfood_sede (
    idsede integer NOT NULL,
    namesede character varying(100) NOT NULL
);
    DROP TABLE public.uvfood_sede;
       public         postgres    false    3            �            1259    24912    uvfood_sede_idsede_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_sede_idsede_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.uvfood_sede_idsede_seq;
       public       postgres    false    3    202            u           0    0    uvfood_sede_idsede_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.uvfood_sede_idsede_seq OWNED BY public.uvfood_sede.idsede;
            public       postgres    false    201            �            1259    24961    uvfood_student_program    TABLE     �   CREATE TABLE public.uvfood_student_program (
    iduser integer NOT NULL,
    idprogram integer NOT NULL,
    status public.status_student_program
);
 *   DROP TABLE public.uvfood_student_program;
       public         postgres    false    3    629            �            1259    24889    uvfood_typeuser    TABLE     y   CREATE TABLE public.uvfood_typeuser (
    id_typeuser integer NOT NULL,
    type_user character varying(100) NOT NULL
);
 #   DROP TABLE public.uvfood_typeuser;
       public         postgres    false    3            �            1259    24887    uvfood_typeuser_id_typeuser_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_typeuser_id_typeuser_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.uvfood_typeuser_id_typeuser_seq;
       public       postgres    false    3    199            v           0    0    uvfood_typeuser_id_typeuser_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.uvfood_typeuser_id_typeuser_seq OWNED BY public.uvfood_typeuser.id_typeuser;
            public       postgres    false    198            �            1259    24872    uvfood_user    TABLE     L  CREATE TABLE public.uvfood_user (
    iduser integer NOT NULL,
    username character(7) NOT NULL,
    firstname character varying(200) NOT NULL,
    surname character varying(200) NOT NULL,
    birth_date date,
    email character varying(100) NOT NULL,
    password_user character varying(200) NOT NULL,
    creation_date date
);
    DROP TABLE public.uvfood_user;
       public         postgres    false    3            �            1259    24899    uvfood_user_extended    TABLE     �   CREATE TABLE public.uvfood_user_extended (
    iduser integer NOT NULL,
    id_typeuser integer NOT NULL,
    status public.status_account NOT NULL
);
 (   DROP TABLE public.uvfood_user_extended;
       public         postgres    false    609    3            �            1259    24870    uvfood_user_iduser_seq    SEQUENCE     �   CREATE SEQUENCE public.uvfood_user_iduser_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.uvfood_user_iduser_seq;
       public       postgres    false    3    197            w           0    0    uvfood_user_iduser_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.uvfood_user_iduser_seq OWNED BY public.uvfood_user.iduser;
            public       postgres    false    196            �            1259    25006    uvfood_user_key    TABLE     a   CREATE TABLE public.uvfood_user_key (
    iduser integer NOT NULL,
    idkey integer NOT NULL
);
 #   DROP TABLE public.uvfood_user_key;
       public         postgres    false    3            �
           2604    24927    uvfood_faculty idfaculty    DEFAULT     �   ALTER TABLE ONLY public.uvfood_faculty ALTER COLUMN idfaculty SET DEFAULT nextval('public.uvfood_faculty_idfaculty_seq'::regclass);
 G   ALTER TABLE public.uvfood_faculty ALTER COLUMN idfaculty DROP DEFAULT;
       public       postgres    false    204    203    204            �
           2604    24994    uvfood_keys idkey    DEFAULT     v   ALTER TABLE ONLY public.uvfood_keys ALTER COLUMN idkey SET DEFAULT nextval('public.uvfood_keys_idkey_seq'::regclass);
 @   ALTER TABLE public.uvfood_keys ALTER COLUMN idkey DROP DEFAULT;
       public       postgres    false    211    210    211            �
           2604    24984    uvfood_modules idmodule    DEFAULT     �   ALTER TABLE ONLY public.uvfood_modules ALTER COLUMN idmodule SET DEFAULT nextval('public.uvfood_modules_idmodule_seq'::regclass);
 F   ALTER TABLE public.uvfood_modules ALTER COLUMN idmodule DROP DEFAULT;
       public       postgres    false    209    208    209            �
           2604    24939    uvfood_program idprogram    DEFAULT     �   ALTER TABLE ONLY public.uvfood_program ALTER COLUMN idprogram SET DEFAULT nextval('public.uvfood_program_idprogram_seq'::regclass);
 G   ALTER TABLE public.uvfood_program ALTER COLUMN idprogram DROP DEFAULT;
       public       postgres    false    206    205    206            �
           2604    24917    uvfood_sede idsede    DEFAULT     x   ALTER TABLE ONLY public.uvfood_sede ALTER COLUMN idsede SET DEFAULT nextval('public.uvfood_sede_idsede_seq'::regclass);
 A   ALTER TABLE public.uvfood_sede ALTER COLUMN idsede DROP DEFAULT;
       public       postgres    false    202    201    202            �
           2604    24892    uvfood_typeuser id_typeuser    DEFAULT     �   ALTER TABLE ONLY public.uvfood_typeuser ALTER COLUMN id_typeuser SET DEFAULT nextval('public.uvfood_typeuser_id_typeuser_seq'::regclass);
 J   ALTER TABLE public.uvfood_typeuser ALTER COLUMN id_typeuser DROP DEFAULT;
       public       postgres    false    198    199    199            �
           2604    24875    uvfood_user iduser    DEFAULT     x   ALTER TABLE ONLY public.uvfood_user ALTER COLUMN iduser SET DEFAULT nextval('public.uvfood_user_iduser_seq'::regclass);
 A   ALTER TABLE public.uvfood_user ALTER COLUMN iduser DROP DEFAULT;
       public       postgres    false    196    197    197            `          0    24924    uvfood_faculty 
   TABLE DATA               @   COPY public.uvfood_faculty (idfaculty, namefaculty) FROM stdin;
    public       postgres    false    204   �k       g          0    24991    uvfood_keys 
   TABLE DATA               ?   COPY public.uvfood_keys (idkey, idmodule, namekey) FROM stdin;
    public       postgres    false    211   �k       e          0    24981    uvfood_modules 
   TABLE DATA               >   COPY public.uvfood_modules (idmodule, namemodule) FROM stdin;
    public       postgres    false    209   l       b          0    24936    uvfood_program 
   TABLE DATA               l   COPY public.uvfood_program (idprogram, univallecode, idsede, idfaculty, program_name, modality) FROM stdin;
    public       postgres    false    206   *l       ^          0    24914    uvfood_sede 
   TABLE DATA               7   COPY public.uvfood_sede (idsede, namesede) FROM stdin;
    public       postgres    false    202   Gl       c          0    24961    uvfood_student_program 
   TABLE DATA               K   COPY public.uvfood_student_program (iduser, idprogram, status) FROM stdin;
    public       postgres    false    207   dl       [          0    24889    uvfood_typeuser 
   TABLE DATA               A   COPY public.uvfood_typeuser (id_typeuser, type_user) FROM stdin;
    public       postgres    false    199   �l       Y          0    24872    uvfood_user 
   TABLE DATA               |   COPY public.uvfood_user (iduser, username, firstname, surname, birth_date, email, password_user, creation_date) FROM stdin;
    public       postgres    false    197   �l       \          0    24899    uvfood_user_extended 
   TABLE DATA               K   COPY public.uvfood_user_extended (iduser, id_typeuser, status) FROM stdin;
    public       postgres    false    200   �l       h          0    25006    uvfood_user_key 
   TABLE DATA               8   COPY public.uvfood_user_key (iduser, idkey) FROM stdin;
    public       postgres    false    212   �l       x           0    0    uvfood_faculty_idfaculty_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.uvfood_faculty_idfaculty_seq', 1, false);
            public       postgres    false    203            y           0    0    uvfood_keys_idkey_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.uvfood_keys_idkey_seq', 1, false);
            public       postgres    false    210            z           0    0    uvfood_modules_idmodule_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.uvfood_modules_idmodule_seq', 1, false);
            public       postgres    false    208            {           0    0    uvfood_program_idprogram_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.uvfood_program_idprogram_seq', 1, false);
            public       postgres    false    205            |           0    0    uvfood_sede_idsede_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.uvfood_sede_idsede_seq', 1, false);
            public       postgres    false    201            }           0    0    uvfood_typeuser_id_typeuser_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.uvfood_typeuser_id_typeuser_seq', 1, false);
            public       postgres    false    198            ~           0    0    uvfood_user_iduser_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.uvfood_user_iduser_seq', 1, false);
            public       postgres    false    196            �
           2606    25000 !   uvfood_keys ak_uvfood_keys_unique 
   CONSTRAINT     i   ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT ak_uvfood_keys_unique UNIQUE (idmodule, namekey);
 K   ALTER TABLE ONLY public.uvfood_keys DROP CONSTRAINT ak_uvfood_keys_unique;
       public         postgres    false    211    211            �
           2606    24948 '   uvfood_program ak_uvfood_program_unique 
   CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT ak_uvfood_program_unique UNIQUE (univallecode, idsede, idfaculty, program_name, modality);
 Q   ALTER TABLE ONLY public.uvfood_program DROP CONSTRAINT ak_uvfood_program_unique;
       public         postgres    false    206    206    206    206    206            �
           2606    24968 7   uvfood_student_program ak_uvfood_student_program_unique 
   CONSTRAINT        ALTER TABLE ONLY public.uvfood_student_program
    ADD CONSTRAINT ak_uvfood_student_program_unique UNIQUE (iduser, idprogram);
 a   ALTER TABLE ONLY public.uvfood_student_program DROP CONSTRAINT ak_uvfood_student_program_unique;
       public         postgres    false    207    207            �
           2606    25010 )   uvfood_user_key ak_uvfood_user_key_unique 
   CONSTRAINT     m   ALTER TABLE ONLY public.uvfood_user_key
    ADD CONSTRAINT ak_uvfood_user_key_unique UNIQUE (iduser, idkey);
 S   ALTER TABLE ONLY public.uvfood_user_key DROP CONSTRAINT ak_uvfood_user_key_unique;
       public         postgres    false    212    212            �
           2606    24931 -   uvfood_faculty uvfood_faculty_namefaculty_key 
   CONSTRAINT     o   ALTER TABLE ONLY public.uvfood_faculty
    ADD CONSTRAINT uvfood_faculty_namefaculty_key UNIQUE (namefaculty);
 W   ALTER TABLE ONLY public.uvfood_faculty DROP CONSTRAINT uvfood_faculty_namefaculty_key;
       public         postgres    false    204            �
           2606    24929 "   uvfood_faculty uvfood_faculty_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.uvfood_faculty
    ADD CONSTRAINT uvfood_faculty_pkey PRIMARY KEY (idfaculty);
 L   ALTER TABLE ONLY public.uvfood_faculty DROP CONSTRAINT uvfood_faculty_pkey;
       public         postgres    false    204            �
           2606    24998 #   uvfood_keys uvfood_keys_namekey_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT uvfood_keys_namekey_key UNIQUE (namekey);
 M   ALTER TABLE ONLY public.uvfood_keys DROP CONSTRAINT uvfood_keys_namekey_key;
       public         postgres    false    211            �
           2606    24996    uvfood_keys uvfood_keys_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT uvfood_keys_pkey PRIMARY KEY (idkey);
 F   ALTER TABLE ONLY public.uvfood_keys DROP CONSTRAINT uvfood_keys_pkey;
       public         postgres    false    211            �
           2606    24988 ,   uvfood_modules uvfood_modules_namemodule_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.uvfood_modules
    ADD CONSTRAINT uvfood_modules_namemodule_key UNIQUE (namemodule);
 V   ALTER TABLE ONLY public.uvfood_modules DROP CONSTRAINT uvfood_modules_namemodule_key;
       public         postgres    false    209            �
           2606    24986 "   uvfood_modules uvfood_modules_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.uvfood_modules
    ADD CONSTRAINT uvfood_modules_pkey PRIMARY KEY (idmodule);
 L   ALTER TABLE ONLY public.uvfood_modules DROP CONSTRAINT uvfood_modules_pkey;
       public         postgres    false    209            �
           2606    24944 "   uvfood_program uvfood_program_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT uvfood_program_pkey PRIMARY KEY (idprogram);
 L   ALTER TABLE ONLY public.uvfood_program DROP CONSTRAINT uvfood_program_pkey;
       public         postgres    false    206            �
           2606    24946 .   uvfood_program uvfood_program_univallecode_key 
   CONSTRAINT     q   ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT uvfood_program_univallecode_key UNIQUE (univallecode);
 X   ALTER TABLE ONLY public.uvfood_program DROP CONSTRAINT uvfood_program_univallecode_key;
       public         postgres    false    206            �
           2606    24921 $   uvfood_sede uvfood_sede_namesede_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.uvfood_sede
    ADD CONSTRAINT uvfood_sede_namesede_key UNIQUE (namesede);
 N   ALTER TABLE ONLY public.uvfood_sede DROP CONSTRAINT uvfood_sede_namesede_key;
       public         postgres    false    202            �
           2606    24919    uvfood_sede uvfood_sede_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.uvfood_sede
    ADD CONSTRAINT uvfood_sede_pkey PRIMARY KEY (idsede);
 F   ALTER TABLE ONLY public.uvfood_sede DROP CONSTRAINT uvfood_sede_pkey;
       public         postgres    false    202            �
           2606    24894 $   uvfood_typeuser uvfood_typeuser_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.uvfood_typeuser
    ADD CONSTRAINT uvfood_typeuser_pkey PRIMARY KEY (id_typeuser);
 N   ALTER TABLE ONLY public.uvfood_typeuser DROP CONSTRAINT uvfood_typeuser_pkey;
       public         postgres    false    199            �
           2606    24896 -   uvfood_typeuser uvfood_typeuser_type_user_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.uvfood_typeuser
    ADD CONSTRAINT uvfood_typeuser_type_user_key UNIQUE (type_user);
 W   ALTER TABLE ONLY public.uvfood_typeuser DROP CONSTRAINT uvfood_typeuser_type_user_key;
       public         postgres    false    199            �
           2606    24884 !   uvfood_user uvfood_user_email_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_email_key UNIQUE (email);
 K   ALTER TABLE ONLY public.uvfood_user DROP CONSTRAINT uvfood_user_email_key;
       public         postgres    false    197            �
           2606    24886 )   uvfood_user uvfood_user_password_user_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_password_user_key UNIQUE (password_user);
 S   ALTER TABLE ONLY public.uvfood_user DROP CONSTRAINT uvfood_user_password_user_key;
       public         postgres    false    197            �
           2606    24880    uvfood_user uvfood_user_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_pkey PRIMARY KEY (iduser);
 F   ALTER TABLE ONLY public.uvfood_user DROP CONSTRAINT uvfood_user_pkey;
       public         postgres    false    197            �
           2606    24882 $   uvfood_user uvfood_user_username_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.uvfood_user
    ADD CONSTRAINT uvfood_user_username_key UNIQUE (username);
 N   ALTER TABLE ONLY public.uvfood_user DROP CONSTRAINT uvfood_user_username_key;
       public         postgres    false    197            �
           2606    24954 #   uvfood_program fk_uvfood_idfaculty1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT fk_uvfood_idfaculty1 FOREIGN KEY (idfaculty) REFERENCES public.uvfood_faculty(idfaculty) ON UPDATE CASCADE ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.uvfood_program DROP CONSTRAINT fk_uvfood_idfaculty1;
       public       postgres    false    204    2753    206            �
           2606    25016     uvfood_user_key fk_uvfood_idkey1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_user_key
    ADD CONSTRAINT fk_uvfood_idkey1 FOREIGN KEY (idkey) REFERENCES public.uvfood_keys(idkey) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.uvfood_user_key DROP CONSTRAINT fk_uvfood_idkey1;
       public       postgres    false    212    211    2771            �
           2606    25001    uvfood_keys fk_uvfood_idmodule1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_keys
    ADD CONSTRAINT fk_uvfood_idmodule1 FOREIGN KEY (idmodule) REFERENCES public.uvfood_modules(idmodule) ON UPDATE CASCADE ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.uvfood_keys DROP CONSTRAINT fk_uvfood_idmodule1;
       public       postgres    false    209    2765    211            �
           2606    24974 +   uvfood_student_program fk_uvfood_idprogram1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_student_program
    ADD CONSTRAINT fk_uvfood_idprogram1 FOREIGN KEY (idprogram) REFERENCES public.uvfood_program(idprogram) ON UPDATE CASCADE ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.uvfood_student_program DROP CONSTRAINT fk_uvfood_idprogram1;
       public       postgres    false    207    2757    206            �
           2606    24949     uvfood_program fk_uvfood_idsede1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_program
    ADD CONSTRAINT fk_uvfood_idsede1 FOREIGN KEY (idsede) REFERENCES public.uvfood_sede(idsede) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.uvfood_program DROP CONSTRAINT fk_uvfood_idsede1;
       public       postgres    false    2749    206    202            �
           2606    24969 (   uvfood_student_program fk_uvfood_iduser2    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_student_program
    ADD CONSTRAINT fk_uvfood_iduser2 FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.uvfood_student_program DROP CONSTRAINT fk_uvfood_iduser2;
       public       postgres    false    207    2739    197            �
           2606    25011 !   uvfood_user_key fk_uvfood_iduser3    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_user_key
    ADD CONSTRAINT fk_uvfood_iduser3 FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.uvfood_user_key DROP CONSTRAINT fk_uvfood_iduser3;
       public       postgres    false    212    2739    197            �
           2606    24907 (   uvfood_user_extended fk_uvfood_typeuser1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_user_extended
    ADD CONSTRAINT fk_uvfood_typeuser1 FOREIGN KEY (id_typeuser) REFERENCES public.uvfood_typeuser(id_typeuser) ON UPDATE CASCADE ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.uvfood_user_extended DROP CONSTRAINT fk_uvfood_typeuser1;
       public       postgres    false    2743    199    200            �
           2606    24902 $   uvfood_user_extended fk_uvfood_user1    FK CONSTRAINT     �   ALTER TABLE ONLY public.uvfood_user_extended
    ADD CONSTRAINT fk_uvfood_user1 FOREIGN KEY (iduser) REFERENCES public.uvfood_user(iduser) ON UPDATE CASCADE ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.uvfood_user_extended DROP CONSTRAINT fk_uvfood_user1;
       public       postgres    false    197    200    2739            `      x������ � �      g      x������ � �      e      x������ � �      b      x������ � �      ^      x������ � �      c      x������ � �      [      x������ � �      Y      x������ � �      \      x������ � �      h      x������ � �     