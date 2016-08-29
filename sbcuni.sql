--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.5.3

-- Started on 2016-08-29 16:59:38

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 48062)
-- Name: anexo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE anexo (
    id bigint NOT NULL,
    arquivo text,
    extensao character varying(255) NOT NULL,
    lida boolean NOT NULL,
    noarquivo character varying(50) NOT NULL,
    tamanho bigint NOT NULL,
    tipo character varying(30) NOT NULL,
    mensagem_id bigint
);


ALTER TABLE anexo OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 48068)
-- Name: avaliacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE avaliacao (
    idavaliacao bigint NOT NULL,
    avaliacao boolean,
    usuario bigint
);


ALTER TABLE avaliacao OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 48071)
-- Name: avaliacao_comentario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE avaliacao_comentario (
    avaliacoes_idavaliacao bigint NOT NULL,
    comentarios_idcomentario bigint NOT NULL
);


ALTER TABLE avaliacao_comentario OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 48074)
-- Name: avaliacao_topico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE avaliacao_topico (
    avaliacaos_idavaliacao bigint NOT NULL,
    topicos_idtopico bigint NOT NULL
);


ALTER TABLE avaliacao_topico OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 48077)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE categoria (
    idcategoria bigint NOT NULL,
    decategoria character varying(20) NOT NULL
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 48080)
-- Name: comentario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE comentario (
    idcomentario bigint NOT NULL,
    descricao character varying(2048) NOT NULL,
    dtcriacao timestamp without time zone NOT NULL,
    dtultimaatualizacao timestamp without time zone,
    topico bigint,
    usuario bigint
);


ALTER TABLE comentario OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 48086)
-- Name: grupoestudo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE grupoestudo (
    idgrupoestudo bigint NOT NULL,
    dtcriacao timestamp without time zone NOT NULL,
    dtultimaatualizacao timestamp without time zone,
    nogrupo character varying(50) NOT NULL,
    professor_idusuario bigint
);


ALTER TABLE grupoestudo OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 48089)
-- Name: grupoestudo_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE grupoestudo_usuario (
    grupos_idgrupoestudo bigint NOT NULL,
    alunos_idusuario bigint NOT NULL
);


ALTER TABLE grupoestudo_usuario OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 48092)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 48094)
-- Name: mensagem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE mensagem (
    id bigint NOT NULL,
    dtenvio timestamp without time zone,
    favorito boolean,
    lido boolean,
    mensagem character varying(1024) NOT NULL,
    tipo integer NOT NULL,
    titulo character varying(50),
    destinatario_idusuario bigint,
    remetente_idusuario bigint,
    idmensagem bigint NOT NULL
);


ALTER TABLE mensagem OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 48100)
-- Name: topico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE topico (
    idtopico bigint NOT NULL,
    descricao character varying(90000) NOT NULL,
    dtcriacao timestamp without time zone NOT NULL,
    dtultimaatualizacao timestamp without time zone,
    nuvisualizacoes integer,
    titulo character varying(128) NOT NULL,
    grupoestudo_idgrupoestudo bigint,
    usuario bigint
);


ALTER TABLE topico OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 48106)
-- Name: topico_categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE topico_categoria (
    topicos_idtopico bigint NOT NULL,
    categorias_idcategoria bigint NOT NULL
);


ALTER TABLE topico_categoria OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 48109)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuario (
    idusuario bigint NOT NULL,
    avatar character varying(255),
    cidade character varying(255),
    cpf character varying(11),
    dtcadastro timestamp without time zone NOT NULL,
    dtultimoacesso timestamp without time zone,
    email character varying(50) NOT NULL,
    estado character varying(255),
    matricula character varying(13) NOT NULL,
    nome character varying(58) NOT NULL,
    perfil integer NOT NULL,
    senha character varying(8),
    sobremim character varying(255),
    telcelular character varying(255),
    telfixo character varying(255)
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 2012 (class 0 OID 48062)
-- Dependencies: 171
-- Data for Name: anexo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2013 (class 0 OID 48068)
-- Dependencies: 172
-- Data for Name: avaliacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO avaliacao VALUES (109, true, 1);
INSERT INTO avaliacao VALUES (115, true, 1);
INSERT INTO avaliacao VALUES (119, false, 1);
INSERT INTO avaliacao VALUES (122, true, 74);
INSERT INTO avaliacao VALUES (123, true, 74);
INSERT INTO avaliacao VALUES (130, true, 1);
INSERT INTO avaliacao VALUES (139, true, 1);


--
-- TOC entry 2014 (class 0 OID 48071)
-- Dependencies: 173
-- Data for Name: avaliacao_comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO avaliacao_comentario VALUES (115, 114);
INSERT INTO avaliacao_comentario VALUES (122, 111);
INSERT INTO avaliacao_comentario VALUES (123, 121);
INSERT INTO avaliacao_comentario VALUES (130, 126);
INSERT INTO avaliacao_comentario VALUES (139, 136);


--
-- TOC entry 2015 (class 0 OID 48074)
-- Dependencies: 174
-- Data for Name: avaliacao_topico; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO avaliacao_topico VALUES (109, 104);
INSERT INTO avaliacao_topico VALUES (119, 113);


--
-- TOC entry 2016 (class 0 OID 48077)
-- Dependencies: 175
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO categoria VALUES (1, 'Java');
INSERT INTO categoria VALUES (2, 'JSF');
INSERT INTO categoria VALUES (3, 'HTML');
INSERT INTO categoria VALUES (4, 'JBoss');
INSERT INTO categoria VALUES (5, 'CSS');
INSERT INTO categoria VALUES (6, 'Javascript');
INSERT INTO categoria VALUES (7, 'Jquery');
INSERT INTO categoria VALUES (8, 'Bootstrap');
INSERT INTO categoria VALUES (9, 'Eclipse');
INSERT INTO categoria VALUES (10, 'C#');
INSERT INTO categoria VALUES (11, 'ASP');
INSERT INTO categoria VALUES (12, 'ASP.NET');
INSERT INTO categoria VALUES (14, 'Redes');
INSERT INTO categoria VALUES (15, 'Infraestrutura');
INSERT INTO categoria VALUES (16, 'Banco de Dados');
INSERT INTO categoria VALUES (17, 'PostgreSQL');
INSERT INTO categoria VALUES (18, 'MySQL');
INSERT INTO categoria VALUES (19, 'DB2');
INSERT INTO categoria VALUES (20, 'Modelagem de Dados');
INSERT INTO categoria VALUES (21, 'DER');
INSERT INTO categoria VALUES (22, 'MER');
INSERT INTO categoria VALUES (23, 'Topologia de Redes');
INSERT INTO categoria VALUES (133, 'Primefaces');


--
-- TOC entry 2017 (class 0 OID 48080)
-- Dependencies: 176
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO comentario VALUES (111, 'dfhdfh', '2016-03-24 16:17:43.264', '2016-03-24 16:17:43.264', 83, 1);
INSERT INTO comentario VALUES (114, 'Editado', '2016-03-24 16:19:04.39', '2016-03-24 16:19:24.879', 113, 1);
INSERT INTO comentario VALUES (121, 'teste', '2016-03-24 16:21:31.286', '2016-03-24 16:21:31.286', 83, 74);
INSERT INTO comentario VALUES (126, 'sadasdasd', '2016-05-10 10:41:27.22', '2016-05-10 10:41:27.22', 125, 1);
INSERT INTO comentario VALUES (127, 'asdasd', '2016-05-10 10:41:28.811', '2016-05-10 10:41:28.811', 125, 1);
INSERT INTO comentario VALUES (136, 'Fábio é sinistro', '2016-08-29 16:50:27.623', '2016-08-29 16:50:27.623', 135, 1);
INSERT INTO comentario VALUES (140, 'teste', '2016-08-29 16:52:14.739', '2016-08-29 16:52:14.739', 112, 1);


--
-- TOC entry 2018 (class 0 OID 48086)
-- Dependencies: 177
-- Data for Name: grupoestudo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO grupoestudo VALUES (89, '2014-10-07 16:40:25.613', NULL, 'Fundamentos de Sistemas de Informação', 1);
INSERT INTO grupoestudo VALUES (90, '2014-10-07 16:40:44.44', NULL, 'Linguagem de Programação', 1);
INSERT INTO grupoestudo VALUES (91, '2014-10-07 16:40:59.175', NULL, 'Tecnologia Web', 1);
INSERT INTO grupoestudo VALUES (92, '2014-10-07 16:41:25.205', NULL, 'Programação para dispositivos móveis', 1);
INSERT INTO grupoestudo VALUES (93, '2014-10-07 16:41:50.892', NULL, 'Engenharia da Usabilidade', 1);
INSERT INTO grupoestudo VALUES (94, '2014-10-07 16:42:10.783', NULL, 'Governança de TI', 1);
INSERT INTO grupoestudo VALUES (95, '2014-10-07 16:42:36.345', NULL, 'Padrões de Projeto', 1);
INSERT INTO grupoestudo VALUES (96, '2014-10-07 16:43:04.282', NULL, 'Desenvolvimento WEB', 1);


--
-- TOC entry 2019 (class 0 OID 48089)
-- Dependencies: 178
-- Data for Name: grupoestudo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO grupoestudo_usuario VALUES (89, 81);
INSERT INTO grupoestudo_usuario VALUES (89, 69);
INSERT INTO grupoestudo_usuario VALUES (89, 73);
INSERT INTO grupoestudo_usuario VALUES (89, 74);
INSERT INTO grupoestudo_usuario VALUES (89, 77);
INSERT INTO grupoestudo_usuario VALUES (89, 79);
INSERT INTO grupoestudo_usuario VALUES (89, 66);
INSERT INTO grupoestudo_usuario VALUES (89, 68);
INSERT INTO grupoestudo_usuario VALUES (89, 70);
INSERT INTO grupoestudo_usuario VALUES (89, 78);
INSERT INTO grupoestudo_usuario VALUES (93, 75);
INSERT INTO grupoestudo_usuario VALUES (93, 76);
INSERT INTO grupoestudo_usuario VALUES (93, 73);
INSERT INTO grupoestudo_usuario VALUES (93, 74);
INSERT INTO grupoestudo_usuario VALUES (93, 77);
INSERT INTO grupoestudo_usuario VALUES (93, 70);
INSERT INTO grupoestudo_usuario VALUES (93, 78);
INSERT INTO grupoestudo_usuario VALUES (92, 75);
INSERT INTO grupoestudo_usuario VALUES (92, 76);
INSERT INTO grupoestudo_usuario VALUES (92, 81);
INSERT INTO grupoestudo_usuario VALUES (92, 73);
INSERT INTO grupoestudo_usuario VALUES (92, 74);
INSERT INTO grupoestudo_usuario VALUES (92, 67);
INSERT INTO grupoestudo_usuario VALUES (92, 77);
INSERT INTO grupoestudo_usuario VALUES (92, 72);
INSERT INTO grupoestudo_usuario VALUES (92, 66);
INSERT INTO grupoestudo_usuario VALUES (92, 71);
INSERT INTO grupoestudo_usuario VALUES (92, 68);
INSERT INTO grupoestudo_usuario VALUES (92, 80);
INSERT INTO grupoestudo_usuario VALUES (92, 78);
INSERT INTO grupoestudo_usuario VALUES (91, 69);
INSERT INTO grupoestudo_usuario VALUES (91, 67);
INSERT INTO grupoestudo_usuario VALUES (91, 66);
INSERT INTO grupoestudo_usuario VALUES (91, 68);
INSERT INTO grupoestudo_usuario VALUES (94, 81);
INSERT INTO grupoestudo_usuario VALUES (94, 69);
INSERT INTO grupoestudo_usuario VALUES (94, 73);
INSERT INTO grupoestudo_usuario VALUES (94, 67);
INSERT INTO grupoestudo_usuario VALUES (94, 79);
INSERT INTO grupoestudo_usuario VALUES (94, 72);
INSERT INTO grupoestudo_usuario VALUES (94, 68);
INSERT INTO grupoestudo_usuario VALUES (90, 75);
INSERT INTO grupoestudo_usuario VALUES (90, 76);
INSERT INTO grupoestudo_usuario VALUES (90, 81);
INSERT INTO grupoestudo_usuario VALUES (90, 69);
INSERT INTO grupoestudo_usuario VALUES (90, 74);
INSERT INTO grupoestudo_usuario VALUES (90, 67);
INSERT INTO grupoestudo_usuario VALUES (90, 77);
INSERT INTO grupoestudo_usuario VALUES (90, 79);
INSERT INTO grupoestudo_usuario VALUES (90, 72);
INSERT INTO grupoestudo_usuario VALUES (90, 71);
INSERT INTO grupoestudo_usuario VALUES (95, 76);
INSERT INTO grupoestudo_usuario VALUES (95, 81);
INSERT INTO grupoestudo_usuario VALUES (95, 73);
INSERT INTO grupoestudo_usuario VALUES (95, 74);
INSERT INTO grupoestudo_usuario VALUES (95, 67);
INSERT INTO grupoestudo_usuario VALUES (95, 77);
INSERT INTO grupoestudo_usuario VALUES (95, 79);
INSERT INTO grupoestudo_usuario VALUES (95, 72);
INSERT INTO grupoestudo_usuario VALUES (95, 66);
INSERT INTO grupoestudo_usuario VALUES (95, 71);
INSERT INTO grupoestudo_usuario VALUES (95, 68);
INSERT INTO grupoestudo_usuario VALUES (95, 70);
INSERT INTO grupoestudo_usuario VALUES (95, 78);
INSERT INTO grupoestudo_usuario VALUES (96, 75);
INSERT INTO grupoestudo_usuario VALUES (96, 67);
INSERT INTO grupoestudo_usuario VALUES (96, 66);


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 179
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 141, true);


--
-- TOC entry 2021 (class 0 OID 48094)
-- Dependencies: 180
-- Data for Name: mensagem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2022 (class 0 OID 48100)
-- Dependencies: 181
-- Data for Name: topico; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO topico VALUES (82, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vulputate iaculis arcu ac tincidunt. Maecenas vel orci a lectus gravida convallis et sit amet mauris. Morbi pellentesque viverra auctor. Nunc nulla nibh, tincidunt at finibus quis, gravida ac diam. Nunc feugiat porttitor nulla sed sodales. Nam faucibus tortor quis metus ultricies, ut porttitor magna tincidunt. Nam consectetur, enim non faucibus bibendum, ex ex venenatis nisi, at sagittis sapien quam eget neque.</span><br></p>', '2014-10-07 16:37:31.694', NULL, 0, 'Lorem ipsum dolor sit amet.', NULL, 1);
INSERT INTO topico VALUES (84, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce blandit sit amet felis in tempor. Cras sed enim aliquet, sollicitudin felis posuere, ornare odio. Vestibulum ut augue justo. Nam vel orci sit amet arcu fringilla dictum non eget nunc. Nam leo nunc, posuere gravida lectus ac, aliquet blandit leo. Sed dapibus nibh lorem, ac eleifend dui feugiat eget. In eleifend ac magna a sagittis. Quisque dui tellus, vulputate vitae facilisis ac, tincidunt eu tellus. Vivamus massa dui, volutpat id sem quis, dictum interdum eros. Praesent mattis metus ut mi faucibus pharetra. Suspendisse non fringilla justo. Aliquam erat volutpat. Mauris efficitur, nisi a varius consequat, dolor tellus porta lorem, ut accumsan enim mi sit amet massa. Integer lacinia ut arcu et rutrum. Sed ante nunc, consectetur eu diam nec, hendrerit blandit erat.</span><br></p>', '2014-10-07 16:38:16.068', NULL, 0, 'Duis semper, nisl ut facilisis.', NULL, 1);
INSERT INTO topico VALUES (85, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">In in interdum enim, quis consectetur sapien. Ut turpis odio, rutrum sit amet cursus et, bibendum id velit. Nullam blandit volutpat eros, eu porta ex ullamcorper id. In bibendum sodales hendrerit. Proin sit amet leo et turpis tincidunt imperdiet quis sit amet libero. Aliquam erat volutpat. Nulla dictum, ante sit amet finibus vehicula, metus lectus venenatis ipsum, ut malesuada diam ligula et ante.</span><br></p>', '2014-10-07 16:38:34.068', NULL, 0, 'Quisque vitae ante eget ex.', NULL, 1);
INSERT INTO topico VALUES (86, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Mauris sit amet leo lacinia, auctor nibh eu, consequat odio. Sed vestibulum diam id sapien fermentum, a mollis lorem ultricies. Vivamus nunc erat, faucibus id nunc ac, volutpat fermentum quam. Donec purus orci, dignissim id est ac, vulputate dignissim purus. Nullam a libero lobortis, interdum est ac, elementum ex. Curabitur aliquam pellentesque euismod. Pellentesque sed ultrices nunc. Vestibulum ullamcorper nisl et nisi tempus, nec aliquam lectus tristique. Vivamus quis lobortis ligula. Pellentesque commodo, quam eu elementum iaculis, sapien purus facilisis arcu, nec cursus ante nisl a nibh. Vestibulum tempor orci at vehicula porta. Suspendisse sodales ultrices erat id tristique. Aliquam sit amet eros nulla.</span><br></p>', '2014-10-07 16:38:54.974', NULL, 0, 'Curabitur consequat nisl ut nibh.', NULL, 1);
INSERT INTO topico VALUES (87, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Nam eu sem malesuada, ultrices eros et, ultrices enim. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum leo enim, fermentum eget bibendum eget, consectetur vitae arcu. Vestibulum non nisl eu nulla pellentesque dignissim. Aenean tempor turpis in ex placerat, in molestie dolor molestie. Etiam quis iaculis quam, vel convallis mi. Phasellus in dolor aliquam, faucibus felis sed, commodo sapien. Praesent posuere nulla nec accumsan dictum.</span><br></p>', '2014-10-07 16:39:11.63', NULL, 0, 'Sed in vehicula erat, cursus.', NULL, 1);
INSERT INTO topico VALUES (88, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Nunc nec porttitor nunc. Proin congue blandit suscipit. Quisque et purus eget sapien egestas suscipit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean vestibulum felis eget varius rutrum. Mauris arcu arcu, posuere eu metus vel, molestie sollicitudin est. Etiam viverra nulla et laoreet pharetra. Etiam eu pulvinar ante, vitae porta velit. Aliquam dapibus rutrum eros vel dapibus. Aliquam porta euismod augue, ac cursus ipsum malesuada sit amet. Curabitur aliquet urna orci, non fermentum felis consequat sed. Vivamus eu fermentum odio, non faucibus eros.</span><br></p>', '2014-10-07 16:39:33.004', NULL, 0, 'Cras nibh nibh, ultricies in.', NULL, 1);
INSERT INTO topico VALUES (97, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Donec tristique tincidunt purus, eget tristique dolor scelerisque at. Vivamus eleifend porta metus, sit amet suscipit mi viverra sed. Sed auctor tempor mi at interdum. Vivamus porttitor non elit vitae tincidunt. Donec feugiat pulvinar dictum. Duis et lacinia dui. Aenean porta, quam vel lacinia convallis, magna magna vehicula magna, id elementum dui ex a neque. Proin pharetra est non erat ornare egestas. Duis ac tincidunt orci. Aenean elementum turpis et nunc cursus ullamcorper. Donec eget metus diam. Maecenas ac diam ac arcu posuere maximus eget quis ligula.</span><br></p>', '2014-10-07 16:43:43.5', NULL, 0, 'Morbi condimentum suscipit nibh. Duis.', 89, 1);
INSERT INTO topico VALUES (98, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Aenean tristique faucibus velit, rhoncus luctus odio faucibus commodo. Suspendisse aliquam ipsum non turpis maximus facilisis. Nulla at tellus in eros sollicitudin varius sed quis urna. Phasellus tempor placerat risus, vitae rutrum enim cursus mollis. Quisque vehicula urna nisl. Phasellus placerat quis dolor id tristique. Integer fermentum tellus eget augue tincidunt, convallis tempus sapien iaculis. Nunc facilisis, augue et eleifend lacinia, dui odio commodo odio, non ultricies enim diam quis urna. Donec eu tortor in eros faucibus placerat.</span><br></p>', '2014-10-07 16:44:02.78', NULL, 0, 'Praesent hendrerit posuere velit. Pellentesque.', 89, 1);
INSERT INTO topico VALUES (99, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Donec commodo hendrerit sem, vel porta purus posuere a. Quisque in urna vel tellus faucibus sagittis. Sed tristique rhoncus tellus, id blandit nisi iaculis ut. Praesent sed mauris at sem vestibulum consequat ut sed arcu. Pellentesque ut est congue, pharetra quam tristique, accumsan felis. Ut ullamcorper congue aliquam. Etiam risus nisl, fringilla quis pretium sit amet, rutrum eu eros. Morbi pellentesque lobortis nibh vitae scelerisque. Suspendisse ultricies blandit risus a maximus. Quisque pulvinar in ex quis bibendum. Mauris blandit pretium dapibus. Donec pretium nulla vel ante feugiat, vel auctor lectus bibendum.</span><br></p>', '2014-10-07 16:44:19.233', NULL, 0, 'Sed imperdiet velit tellus, quis.', 89, 1);
INSERT INTO topico VALUES (101, '<p>Testando o sistema faloo  </p>', '2016-03-24 16:12:41.758', '2016-03-24 16:12:41.758', 0, 'Bootstrap ', NULL, 1);
INSERT INTO topico VALUES (100, '<p><span style="color: rgb(0, 0, 0); font-family: ''Times New Roman''; font-size: medium; line-height: normal;">   O empenho em analisar o fenômeno da Internet oferece uma interessante oportunidade para verificação das novas proposições. O incentivo ao avanço tecnológico, assim como a consulta aos diversos militantes não pode mais se dissociar dos níveis de motivação departamental. Não obstante, a adoção de políticas descentralizadoras representa uma abertura para a melhoria do retorno esperado a longo prazo. É claro que a revolução dos costumes é uma das consequências das diretrizes de desenvolvimento para o futuro. </span><br></p>', '2014-10-07 16:45:28.185', NULL, 2, 'Teste', 90, 1);
INSERT INTO topico VALUES (113, '<p>asdas</p>', '2016-03-24 16:18:55.734', '2016-03-24 16:18:55.734', 1, 'grupo 01', 92, 1);
INSERT INTO topico VALUES (83, '<p><span style="color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans; font-size: 11px; line-height: 14px; text-align: justify;">Curabitur porttitor tristique ipsum at tempor. Suspendisse non turpis sit amet risus suscipit congue. Suspendisse malesuada imperdiet turpis, quis vulputate turpis dictum id. Nam sit amet venenatis mauris. Curabitur at velit eu augue porta ultrices a sed ligula. Nunc porta est at enim rhoncus iaculis. Quisque iaculis ante tortor, elementum vehicula ipsum imperdiet id. Aenean et ultrices nisi. Aenean at lobortis erat. Phasellus lobortis purus dolor, eget cursus velit ornare nec. Quisque vitae dolor orci. Vestibulum quis sem malesuada mauris rhoncus porttitor. Proin euismod vestibulum est ac venenatis. Cras pulvinar, urna et finibus mattis, purus lorem congue nibh, vitae gravida quam ipsum sit amet tortor. Vestibulum euismod justo ut magna varius, id tincidunt neque faucibus.</span><br></p>', '2014-10-07 16:37:59.584', NULL, 6, 'Mauris convallis gravida velit vel.', NULL, 1);
INSERT INTO topico VALUES (104, '<p>teasd</p>', '2016-03-24 16:16:27.451', '2016-03-24 16:16:27.451', 3, 'Teste', 94, 1);
INSERT INTO topico VALUES (124, '<p>gdfgdfgdfg</p>', '2016-05-10 10:40:30.421', '2016-05-10 10:40:30.421', 0, 'hfdgdfgdf', NULL, 1);
INSERT INTO topico VALUES (125, '<p>asfasfasf</p>', '2016-05-10 10:41:19.772', '2016-05-10 10:41:19.772', 1, 'asfasfasfsafasfasf', 95, 1);
INSERT INTO topico VALUES (135, '<p>Fazendo de <span style="font-style: italic;">forma </span>simples uma <span style="font-weight: bold;">gambiarra</span>.</p><p>Testando todas as formata��es <br></p><ul><li>� teste</li><li>teste</li><li>ste</li><li>sads</li><li>da</li><li>asd</li></ul><p>Testes alterado<br></p>', '2016-08-29 16:48:14.981', '2016-08-29 16:51:52.302', 1, 'Como fazer gambiarra', NULL, 1);
INSERT INTO topico VALUES (112, '<p>asdasdasdsad</p>', '2016-03-24 16:18:30.237', '2016-03-24 16:18:30.237', 1, 'Testeassd', NULL, 1);


--
-- TOC entry 2023 (class 0 OID 48106)
-- Dependencies: 182
-- Data for Name: topico_categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO topico_categoria VALUES (82, 1);
INSERT INTO topico_categoria VALUES (82, 2);
INSERT INTO topico_categoria VALUES (83, 3);
INSERT INTO topico_categoria VALUES (83, 8);
INSERT INTO topico_categoria VALUES (83, 9);
INSERT INTO topico_categoria VALUES (84, 12);
INSERT INTO topico_categoria VALUES (84, 16);
INSERT INTO topico_categoria VALUES (84, 22);
INSERT INTO topico_categoria VALUES (85, 14);
INSERT INTO topico_categoria VALUES (85, 20);
INSERT INTO topico_categoria VALUES (85, 23);
INSERT INTO topico_categoria VALUES (86, 4);
INSERT INTO topico_categoria VALUES (86, 5);
INSERT INTO topico_categoria VALUES (86, 10);
INSERT INTO topico_categoria VALUES (87, 3);
INSERT INTO topico_categoria VALUES (87, 5);
INSERT INTO topico_categoria VALUES (87, 6);
INSERT INTO topico_categoria VALUES (88, 3);
INSERT INTO topico_categoria VALUES (88, 4);
INSERT INTO topico_categoria VALUES (88, 5);
INSERT INTO topico_categoria VALUES (88, 6);
INSERT INTO topico_categoria VALUES (88, 7);
INSERT INTO topico_categoria VALUES (135, 1);
INSERT INTO topico_categoria VALUES (135, 5);
INSERT INTO topico_categoria VALUES (135, 11);
INSERT INTO topico_categoria VALUES (112, 1);
INSERT INTO topico_categoria VALUES (112, 7);
INSERT INTO topico_categoria VALUES (112, 8);
INSERT INTO topico_categoria VALUES (99, 5);
INSERT INTO topico_categoria VALUES (99, 1);
INSERT INTO topico_categoria VALUES (99, 2);
INSERT INTO topico_categoria VALUES (98, 8);
INSERT INTO topico_categoria VALUES (98, 6);
INSERT INTO topico_categoria VALUES (98, 7);
INSERT INTO topico_categoria VALUES (97, 5);
INSERT INTO topico_categoria VALUES (97, 4);
INSERT INTO topico_categoria VALUES (97, 2);
INSERT INTO topico_categoria VALUES (113, 1);
INSERT INTO topico_categoria VALUES (104, 8);
INSERT INTO topico_categoria VALUES (100, 1);
INSERT INTO topico_categoria VALUES (100, 23);
INSERT INTO topico_categoria VALUES (125, 6);
INSERT INTO topico_categoria VALUES (101, 2);
INSERT INTO topico_categoria VALUES (101, 8);
INSERT INTO topico_categoria VALUES (101, 9);
INSERT INTO topico_categoria VALUES (101, 20);
INSERT INTO topico_categoria VALUES (101, 23);
INSERT INTO topico_categoria VALUES (124, 6);
INSERT INTO topico_categoria VALUES (124, 8);


--
-- TOC entry 2024 (class 0 OID 48109)
-- Dependencies: 183
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario VALUES (66, 'avatar1.png', NULL, '11111111111', '2014-10-07 16:24:27.475', NULL, 'jpereira@faloo.com', NULL, '20149178501', 'José Barbosa Pereira', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (67, 'avatar2.png', NULL, '22222222222', '2014-10-07 16:24:43.506', NULL, 'gsousa@faloo.com', NULL, '20149365531', 'Guilherme Alves Sousa', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (68, 'avatar3.png', NULL, '33333333333', '2014-10-07 16:25:01.865', NULL, 'lazevedo@faloo.com', NULL, '20149529741', 'Laura Almeida Azevedo', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (69, 'avatar4.png', NULL, '44444444444', '2014-10-07 16:25:20.974', NULL, 'dfernandes@faloo.com', NULL, '20149116151', 'Diogo Alves Fernandes', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (70, 'avatar5.png', NULL, '55555555555', '2014-10-07 16:25:35.052', NULL, 'msousa@gmail.com', NULL, '20149288331', 'Manuela Fernandes Sousa', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (71, 'avatar6.png', NULL, '66666666666', '2014-10-07 16:26:01.52', NULL, 'kpinto@faloo.com', NULL, '20149424271', 'Kauê Cavalcanti Pinto', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (72, 'avatar7.png', NULL, '77777777777', '2014-10-07 16:26:15.02', NULL, 'jrodrigues@faloo.com', NULL, '2014981601', 'João Ribeiro Rodrigues', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (73, 'avatar8.png', NULL, '88888888888', '2014-10-07 16:26:28.926', NULL, 'fsantos@faloo.com', NULL, '20149238321', 'Fábio Araujo Santos', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (75, 'avatar10.png', NULL, '56165165165', '2014-10-07 16:30:37.468', NULL, 'acarvalho@faloo.com', NULL, '20149295931', 'Arthur Correia Carvalho', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (76, 'avatar11.png', NULL, '34534534534', '2014-10-07 16:30:49.546', NULL, 'bcosta@faloo.com', NULL, '20149434211', 'Beatrice Silva Costa', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (77, 'avatar12.png', NULL, '45657687687', '2014-10-07 16:31:04.952', NULL, 'galmeida@faloo.com', NULL, '2014957771', 'Guilherme Cardoso Almeida', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (78, 'avatar13.png', NULL, '90789879789', '2014-10-07 16:31:35.56', NULL, 'ncavalcanti@faloo.com', NULL, '20149248891', 'Nicole Correia Cavalcanti', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (79, 'avatar14.png', NULL, '87567574564', '2014-10-07 16:31:52.654', NULL, 'isouza@faloo.com', NULL, '2014945291', 'Isabella Santos Souza', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (80, 'avatar15.png', NULL, '23534576567', '2014-10-07 16:32:10.732', NULL, 'lbarbosa@faloo.com', NULL, '2014908571', 'Lavinia Cunha Barbosa', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (81, 'avatar16.png', NULL, '45908340958', '2014-10-07 16:32:30.497', NULL, 'bbarros@faloo.com', NULL, '20149224661', 'Beatriz Gomes Barros', 1, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (74, 'avatar9.png', NULL, '12312312312', '2014-10-07 16:30:21.265', '2016-08-29 16:44:28.281', 'ffernandes@faloo.com', NULL, '20167504711', 'Fernanda Pereira Fernandes', 1, 'eu243156', NULL, NULL, NULL);
INSERT INTO usuario VALUES (134, 'avatarp.jpg', NULL, '11971795771', '2016-08-29 16:47:18.873', NULL, 'busato@gmail.com', NULL, '2016788152', 'Michel Busato', 2, NULL, NULL, NULL, NULL);
INSERT INTO usuario VALUES (1, 'avatar11.png', NULL, '11971795771', '2014-09-09 00:00:00', '2016-08-29 16:45:24.956', 'administrador@gmail.com', NULL, '1', 'Administrador', 4, '1', NULL, NULL, NULL);


--
-- TOC entry 1873 (class 2606 OID 48116)
-- Name: anexo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anexo
    ADD CONSTRAINT anexo_pkey PRIMARY KEY (id);


--
-- TOC entry 1875 (class 2606 OID 48118)
-- Name: avaliacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao
    ADD CONSTRAINT avaliacao_pkey PRIMARY KEY (idavaliacao);


--
-- TOC entry 1877 (class 2606 OID 48120)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);


--
-- TOC entry 1879 (class 2606 OID 48122)
-- Name: comentario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (idcomentario);


--
-- TOC entry 1881 (class 2606 OID 48124)
-- Name: grupoestudo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupoestudo
    ADD CONSTRAINT grupoestudo_pkey PRIMARY KEY (idgrupoestudo);


--
-- TOC entry 1883 (class 2606 OID 48126)
-- Name: mensagem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT mensagem_pkey PRIMARY KEY (id);


--
-- TOC entry 1885 (class 2606 OID 48128)
-- Name: topico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topico
    ADD CONSTRAINT topico_pkey PRIMARY KEY (idtopico);


--
-- TOC entry 1887 (class 2606 OID 48130)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);


--
-- TOC entry 1897 (class 2606 OID 48131)
-- Name: fk_1rpnvuhgyg74kwckiacup0878; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupoestudo_usuario
    ADD CONSTRAINT fk_1rpnvuhgyg74kwckiacup0878 FOREIGN KEY (grupos_idgrupoestudo) REFERENCES grupoestudo(idgrupoestudo);


--
-- TOC entry 1896 (class 2606 OID 48136)
-- Name: fk_36x4cwudmga778y5h58eihv0h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupoestudo
    ADD CONSTRAINT fk_36x4cwudmga778y5h58eihv0h FOREIGN KEY (professor_idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 1903 (class 2606 OID 48141)
-- Name: fk_4nkgfo70hf10jnrin1s8p0onh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topico_categoria
    ADD CONSTRAINT fk_4nkgfo70hf10jnrin1s8p0onh FOREIGN KEY (categorias_idcategoria) REFERENCES categoria(idcategoria);


--
-- TOC entry 1898 (class 2606 OID 48146)
-- Name: fk_5dpnpgl20np3pc4vh1dhayy01; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupoestudo_usuario
    ADD CONSTRAINT fk_5dpnpgl20np3pc4vh1dhayy01 FOREIGN KEY (alunos_idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 1892 (class 2606 OID 48151)
-- Name: fk_8q1ught15rw2n9b3be58c8gar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_topico
    ADD CONSTRAINT fk_8q1ught15rw2n9b3be58c8gar FOREIGN KEY (topicos_idtopico) REFERENCES topico(idtopico);


--
-- TOC entry 1899 (class 2606 OID 48156)
-- Name: fk_8rqegcsp2j3b2q86u7hd2le5c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_8rqegcsp2j3b2q86u7hd2le5c FOREIGN KEY (remetente_idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 1894 (class 2606 OID 48161)
-- Name: fk_d2q1iavws94oxfendnvtgl6bg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comentario
    ADD CONSTRAINT fk_d2q1iavws94oxfendnvtgl6bg FOREIGN KEY (topico) REFERENCES topico(idtopico);


--
-- TOC entry 1889 (class 2606 OID 48166)
-- Name: fk_gvh96p2f1ytdknlce1raxsssd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao
    ADD CONSTRAINT fk_gvh96p2f1ytdknlce1raxsssd FOREIGN KEY (usuario) REFERENCES usuario(idusuario);


--
-- TOC entry 1888 (class 2606 OID 48171)
-- Name: fk_i82jjmi4yyumgnm8jyyv8vw9o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anexo
    ADD CONSTRAINT fk_i82jjmi4yyumgnm8jyyv8vw9o FOREIGN KEY (mensagem_id) REFERENCES mensagem(id);


--
-- TOC entry 1893 (class 2606 OID 48176)
-- Name: fk_k5j18si7xy6enhhpvvrxfcejr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_topico
    ADD CONSTRAINT fk_k5j18si7xy6enhhpvvrxfcejr FOREIGN KEY (avaliacaos_idavaliacao) REFERENCES avaliacao(idavaliacao);


--
-- TOC entry 1890 (class 2606 OID 48181)
-- Name: fk_kvvy96t0vaymfx0bjwmrkd2qu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_comentario
    ADD CONSTRAINT fk_kvvy96t0vaymfx0bjwmrkd2qu FOREIGN KEY (comentarios_idcomentario) REFERENCES comentario(idcomentario);


--
-- TOC entry 1900 (class 2606 OID 48186)
-- Name: fk_m9ommro7bg34sckoru2aupll6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_m9ommro7bg34sckoru2aupll6 FOREIGN KEY (destinatario_idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 1891 (class 2606 OID 48191)
-- Name: fk_n8ldvnship993cawman3l0071; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_comentario
    ADD CONSTRAINT fk_n8ldvnship993cawman3l0071 FOREIGN KEY (avaliacoes_idavaliacao) REFERENCES avaliacao(idavaliacao);


--
-- TOC entry 1901 (class 2606 OID 48196)
-- Name: fk_n94052n4hsimygxbntfbunt89; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topico
    ADD CONSTRAINT fk_n94052n4hsimygxbntfbunt89 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);


--
-- TOC entry 1902 (class 2606 OID 48201)
-- Name: fk_npv2ve8k9bqt9a6oqobad9g6g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topico
    ADD CONSTRAINT fk_npv2ve8k9bqt9a6oqobad9g6g FOREIGN KEY (grupoestudo_idgrupoestudo) REFERENCES grupoestudo(idgrupoestudo);


--
-- TOC entry 1904 (class 2606 OID 48206)
-- Name: fk_o8vul4i6rcvkeikmfkit60mf3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY topico_categoria
    ADD CONSTRAINT fk_o8vul4i6rcvkeikmfkit60mf3 FOREIGN KEY (topicos_idtopico) REFERENCES topico(idtopico);


--
-- TOC entry 1895 (class 2606 OID 48211)
-- Name: fk_oiixjxu68q4840q8c5ud3tt2g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comentario
    ADD CONSTRAINT fk_oiixjxu68q4840q8c5ud3tt2g FOREIGN KEY (usuario) REFERENCES usuario(idusuario);


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-08-29 16:59:38

--
-- PostgreSQL database dump complete
--

