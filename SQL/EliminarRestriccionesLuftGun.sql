--
-- TOC entry 2062 (class 2606 OID 76105)
-- Name: carrito carrito_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
DROP
    CONSTRAINT carrito_pkey;

--
-- TOC entry 2050 (class 2606 OID 75579)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.categoria
DROP
    CONSTRAINT categoria_pkey;

--
-- TOC entry 2052 (class 2606 OID 75584)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.cliente
DROP
    CONSTRAINT cliente_pkey;

--
-- TOC entry 2054 (class 2606 OID 75589)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedido
DROP
    CONSTRAINT pedido_pkey;

--
-- TOC entry 2060 (class 2606 OID 75655)
-- Name: pedpro pedpro_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedpro
DROP
    CONSTRAINT pedpro_pkey;

--
-- TOC entry 2058 (class 2606 OID 75645)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.producto
DROP
    CONSTRAINT producto_pkey;

--
-- TOC entry 2056 (class 2606 OID 75599)
-- Name: subcategoria subcategoria_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.subcategoria
DROP
    CONSTRAINT subcategoria_pkey;

--
-- TOC entry 2064 (class 2606 OID 75618)
-- Name: subcategoria FK_CATEGORIA_TO_SUBCATEGORIA; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.subcategoria
DROP
    CONSTRAINT "FK_CATEGORIA_TO_SUBCATEGORIA";

--
-- TOC entry 2069 (class 2606 OID 76111)
-- Name: carrito FK_CLIENTE_TO_CARRITO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
DROP
    CONSTRAINT "FK_CLIENTE_TO_CARRITO";

--
-- TOC entry 2063 (class 2606 OID 75590)
-- Name: pedido FK_CLIENTE_TO_PEDIDO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedido
DROP
    CONSTRAINT "FK_CLIENTE_TO_PEDIDO";

--
-- TOC entry 2066 (class 2606 OID 75656)
-- Name: pedpro FK_PEDIDO_TO_PEDPRO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABL
    ONLY luftgun.pedpro
DROP
    CONSTRAINT "FK_PEDIDO_TO_PEDPRO";

--
-- TOC entry 2068 (class 2606 OID 76106)
-- Name: carrito FK_PRODUCTO_TO_CARRITO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
DROP
    CONSTRAINT "FK_PRODUCTO_TO_CARRITO";

--
-- TOC entry 2067 (class 2606 OID 75661)
-- Name: pedpro FK_PRODUCTO_TO_PEDPRO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedpro
DROP
    CONSTRAINT "FK_PRODUCTO_TO_PEDPRO";

--
-- TOC entry 2065 (class 2606 OID 75646)
-- Name: producto FK_SUBCATEGORIA_TO_PRODUCTO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.producto
DROP
    CONSTRAINT "FK_SUBCATEGORIA_TO_PRODUCTO";