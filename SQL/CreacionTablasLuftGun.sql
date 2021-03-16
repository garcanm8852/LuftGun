SET
    statement_timeout = 0;

SET
    lock_timeout = 0;

SET
    idle_in_transaction_session_timeout = 0;

SET
    client_encoding = 'UTF8';

SET
    standard_conforming_strings = on;

SELECT
    pg_catalog.set_config('search_path', '', false);

SET
    check_function_bodies = false;

SET
    xmloption = content;

SET
    client_min_messages = warning;

SET
    row_security = off;

--
-- TOC entry 8 (class 2615 OID 75574)
-- Name: luftgun; Type: SCHEMA; Schema: -; Owner: a20-mgarde
--
CREATE SCHEMA luftgun;

ALTER SCHEMA luftgun OWNER TO "a20-mgarde";

--
-- TOC entry 198 (class 1255 OID 76234)
-- Name: datosclientesesion(character varying, character varying); Type: FUNCTION; Schema: luftgun; Owner: a20-mgarde
--
CREATE FUNCTION luftgun.datosclientesesion(
    busquedaemail character varying,
    busquedacontrasena character varying
) RETURNS TABLE(
    idcliente integer,
    nombre character varying,
    apellido character varying,
    email character varying,
    contrasena character varying
) LANGUAGE sql AS $ $
SELECT
    *
FROM
    luftgun.cliente
WHERE
    email = busquedaEmail
    AND contrasena = BusquedaContrasena;

$ $;

ALTER FUNCTION luftgun.datosclientesesion(
    busquedaemail character varying,
    busquedacontrasena character varying
) OWNER TO "a20-mgarde";

--
-- TOC entry 212 (class 1255 OID 75848)
-- Name: datosproductobusqueda(character varying); Type: FUNCTION; Schema: luftgun; Owner: a20-mgarde
--
CREATE FUNCTION luftgun.datosproductobusqueda(busquedareferencia character varying) RETURNS TABLE(
    idreferencia character varying,
    nombreproducto character varying,
    marca character varying,
    descripcion character varying,
    precio numeric,
    img bytea,
    stock smallint,
    idsubcategoria integer,
    nombresubcategoria character varying,
    idcategoria integer,
    nombrecategoria character varying
) LANGUAGE sql AS $ $
SELECT
    idreferencia,
    producto.nombre,
    marca,
    descripcion,
    precio,
    img,
    stock,
    producto.idsubcategoria,
    subcategoria.nombre,
    categoria.idcategoria,
    categoria.nombre
FROM
    luftgun.producto
    INNER JOIN luftgun.subcategoria ON subcategoria.idsubcategoria = producto.idsubcategoria
    INNER JOIN luftgun.categoria ON categoria.idcategoria = subcategoria.idcategoria
WHERE
    idreferencia = busquedaReferencia;

$ $;

ALTER FUNCTION luftgun.datosproductobusqueda(busquedareferencia character varying) OWNER TO "a20-mgarde";

--
-- TOC entry 197 (class 1255 OID 75830)
-- Name: datosproductoscompleto(); Type: FUNCTION; Schema: luftgun; Owner: a20-mgarde
--
CREATE FUNCTION luftgun.datosproductoscompleto() RETURNS TABLE(
    idreferencia character varying,
    nombreproducto character varying,
    marca character varying,
    descripcion character varying,
    precio numeric,
    stock smallint,
    idsubcategoria integer,
    nombresubcategoria character varying,
    idcategoria integer,
    nombrecategoria character varying
) LANGUAGE sql AS $ $
SELECT
    idreferencia,
    producto.nombre,
    marca,
    descripcion,
    precio,
    stock,
    producto.idsubcategoria,
    subcategoria.nombre,
    categoria.idcategoria,
    categoria.nombre
FROM
    luftgun.producto
    INNER JOIN luftgun.subcategoria ON subcategoria.idsubcategoria = producto.idsubcategoria
    INNER JOIN luftgun.categoria ON categoria.idcategoria = subcategoria.idcategoria $ $;

ALTER FUNCTION luftgun.datosproductoscompleto() OWNER TO "a20-mgarde";

--
-- TOC entry 211 (class 1255 OID 75832)
-- Name: datosproductoscompletobusqueda(integer); Type: FUNCTION; Schema: luftgun; Owner: a20-mgarde
--
CREATE FUNCTION luftgun.datosproductoscompletobusqueda(busquedacategoria integer) RETURNS TABLE(
    idreferencia character varying,
    nombreproducto character varying,
    marca character varying,
    descripcion character varying,
    precio numeric,
    img bytea,
    stock smallint,
    idsubcategoria integer,
    nombresubcategoria character varying,
    idcategoria integer,
    nombrecategoria character varying
) LANGUAGE sql AS $ $
SELECT
    idreferencia,
    producto.nombre,
    marca,
    descripcion,
    precio,
    img,
    stock,
    producto.idsubcategoria,
    subcategoria.nombre,
    categoria.idcategoria,
    categoria.nombre
FROM
    luftgun.producto
    INNER JOIN luftgun.subcategoria ON subcategoria.idsubcategoria = producto.idsubcategoria
    INNER JOIN luftgun.categoria ON categoria.idcategoria = subcategoria.idcategoria
WHERE
    categoria.idcategoria = busquedaCategoria;

$ $;

ALTER FUNCTION luftgun.datosproductoscompletobusqueda(busquedacategoria integer) OWNER TO "a20-mgarde";

--
-- TOC entry 213 (class 1255 OID 75850)
-- Name: datosproductosubcategoria(integer); Type: FUNCTION; Schema: luftgun; Owner: a20-mgarde
--
CREATE FUNCTION luftgun.datosproductosubcategoria(busquedasubcategoria integer) RETURNS TABLE(
    idreferencia character varying,
    nombreproducto character varying,
    marca character varying,
    descripcion character varying,
    precio numeric,
    img bytea,
    stock smallint,
    idsubcategoria integer,
    nombresubcategoria character varying,
    idcategoria integer,
    nombrecategoria character varying
) LANGUAGE sql AS $ $
SELECT
    idreferencia,
    producto.nombre,
    marca,
    descripcion,
    precio,
    img,
    stock,
    producto.idsubcategoria,
    subcategoria.nombre,
    categoria.idcategoria,
    categoria.nombre
FROM
    luftgun.producto
    INNER JOIN luftgun.subcategoria ON subcategoria.idsubcategoria = producto.idsubcategoria
    INNER JOIN luftgun.categoria ON categoria.idcategoria = subcategoria.idcategoria
WHERE
    subcategoria.idsubcategoria = busquedaSubcategoria;

$ $;

ALTER FUNCTION luftgun.datosproductosubcategoria(busquedasubcategoria integer) OWNER TO "a20-mgarde";

--
-- TOC entry 196 (class 1255 OID 75827)
-- Name: nombreProducto(); Type: FUNCTION; Schema: luftgun; Owner: a20-mgarde
--
CREATE FUNCTION luftgun."nombreProducto"() RETURNS TABLE(
    idreferencia character varying,
    nombre character varying
) LANGUAGE sql AS $ $
SELECT
    idreferencia,
    nombre
FROM
    luftgun.producto;

$ $;

ALTER FUNCTION luftgun."nombreProducto"() OWNER TO "a20-mgarde";

SET
    default_tablespace = '';

--
-- TOC entry 195 (class 1259 OID 76100)
-- Name: carrito; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.carrito (
    idcarrito integer NOT NULL,
    idreferencia character varying(11) NOT NULL,
    idcliente integer NOT NULL
);

ALTER TABLE
    luftgun.carrito OWNER TO "a20-mgarde";

--
-- TOC entry 194 (class 1259 OID 76098)
-- Name: carrito_idcarrito_seq; Type: SEQUENCE; Schema: luftgun; Owner: a20-mgarde
--
CREATE SEQUENCE luftgun.carrito_idcarrito_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER TABLE
    luftgun.carrito_idcarrito_seq OWNER TO "a20-mgarde";

--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 194
-- Name: carrito_idcarrito_seq; Type: SEQUENCE OWNED BY; Schema: luftgun; Owner: a20-mgarde
--
ALTER SEQUENCE luftgun.carrito_idcarrito_seq OWNED BY luftgun.carrito.idcarrito;

--
-- TOC entry 188 (class 1259 OID 75575)
-- Name: categoria; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.categoria (
    idcategoria integer NOT NULL,
    nombre character varying(20) NOT NULL
);

ALTER TABLE
    luftgun.categoria OWNER TO "a20-mgarde";

--
-- TOC entry 189 (class 1259 OID 75580)
-- Name: cliente; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.cliente (
    idcliente integer NOT NULL,
    nombre character varying(40) NOT NULL,
    apellido character varying(40) NOT NULL,
    email character varying(40) NOT NULL,
    contrasena character varying(24) NOT NULL
);

ALTER TABLE
    luftgun.cliente OWNER TO "a20-mgarde";

--
-- TOC entry 190 (class 1259 OID 75585)
-- Name: pedido; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.pedido (
    idpedido integer NOT NULL,
    idcliente integer NOT NULL,
    fecha date NOT NULL,
    tel integer NOT NULL,
    calle character varying(60) NOT NULL,
    provincia character varying(25) NOT NULL,
    localidad character varying(40) NOT NULL,
    cp integer NOT NULL,
    pais character varying(40) NOT NULL
);

ALTER TABLE
    luftgun.pedido OWNER TO "a20-mgarde";

--
-- TOC entry 193 (class 1259 OID 75651)
-- Name: pedpro; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.pedpro (
    idpedido integer NOT NULL,
    idreferencia character varying(11) NOT NULL
);

ALTER TABLE
    luftgun.pedpro OWNER TO "a20-mgarde";

--
-- TOC entry 192 (class 1259 OID 75638)
-- Name: producto; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.producto (
    idreferencia character varying(11) NOT NULL,
    nombre character varying(60) NOT NULL,
    marca character varying(30) NOT NULL,
    descripcion character varying(248) NOT NULL,
    precio numeric(6, 2) NOT NULL,
    img bytea,
    stock smallint NOT NULL,
    idsubcategoria integer NOT NULL
);

ALTER TABLE
    luftgun.producto OWNER TO "a20-mgarde";

--
-- TOC entry 191 (class 1259 OID 75595)
-- Name: subcategoria; Type: TABLE; Schema: luftgun; Owner: a20-mgarde
--
CREATE TABLE luftgun.subcategoria (
    idsubcategoria integer NOT NULL,
    nombre character varying(40) NOT NULL,
    idcategoria integer NOT NULL
);

ALTER TABLE
    luftgun.subcategoria OWNER TO "a20-mgarde";

--
-- TOC entry 2048 (class 2604 OID 76103)
-- Name: carrito idcarrito; Type: DEFAULT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
ALTER COLUMN
    idcarrito
SET
    DEFAULT nextval('luftgun.carrito_idcarrito_seq' :: regclass);