--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

-- Started on 2017-06-24 16:11:46 BRT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 12397)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 16401)
-- Name: funcionarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE funcionarios (
    id serial NOT NULL,
    nome character varying(30) NOT NULL,
    cpf character varying(14) NOT NULL,
    email character varying(35),
    nascimento date,
    telefone character varying(20)
);


ALTER TABLE funcionarios OWNER TO postgres;

--
-- TOC entry 2135 (class 0 OID 16401)
-- Dependencies: 181
-- Data for Name: funcionarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO funcionarios (nome, cpf, email, nascimento, telefone) VALUES ('Gabriel', '123.456.789-10', 'gabriel@gmail.com', '1994-05-06', '6299991234');
INSERT INTO funcionarios (nome, cpf, email, nascimento, telefone) VALUES ('Paulo', '123.456.789-11', 'paulo@gmail.com', '1995-10-10', '6299991235');
INSERT INTO funcionarios (nome, cpf, email, nascimento, telefone) VALUES ('Thiago', '123.456.789-12', 'thiago@gmail.com', '1994-02-10', '6299991236');


--
-- TOC entry 2020 (class 2606 OID 16405)
-- Name: cpf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionarios
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-06-24 16:11:46 BRT

--
-- PostgreSQL database dump complete
--

