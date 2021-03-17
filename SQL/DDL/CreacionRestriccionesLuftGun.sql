--
-- TOC entry 2062 (class 2606 OID 76105)
-- Name: carrito carrito_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
ADD
    CONSTRAINT carrito_pkey PRIMARY KEY (idcarrito);

--
-- TOC entry 2050 (class 2606 OID 75579)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.categoria
ADD
    CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);

--
-- TOC entry 2052 (class 2606 OID 75584)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.cliente
ADD
    CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);

--
-- TOC entry 2054 (class 2606 OID 75589)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedido
ADD
    CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);

--
-- TOC entry 2060 (class 2606 OID 75655)
-- Name: pedpro pedpro_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedpro
ADD
    CONSTRAINT pedpro_pkey PRIMARY KEY (idpedido, idreferencia);

--
-- TOC entry 2058 (class 2606 OID 75645)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.producto
ADD
    CONSTRAINT producto_pkey PRIMARY KEY (idreferencia);

--
-- TOC entry 2056 (class 2606 OID 75599)
-- Name: subcategoria subcategoria_pkey; Type: CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.subcategoria
ADD
    CONSTRAINT subcategoria_pkey PRIMARY KEY (idsubcategoria);

--
-- TOC entry 2064 (class 2606 OID 75618)
-- Name: subcategoria FK_CATEGORIA_TO_SUBCATEGORIA; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.subcategoria
ADD
    CONSTRAINT "FK_CATEGORIA_TO_SUBCATEGORIA" FOREIGN KEY (idcategoria) REFERENCES luftgun.categoria(idcategoria) NOT VALID;

--
-- TOC entry 2069 (class 2606 OID 76111)
-- Name: carrito FK_CLIENTE_TO_CARRITO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
ADD
    CONSTRAINT "FK_CLIENTE_TO_CARRITO" FOREIGN KEY (idcliente) REFERENCES luftgun.cliente(idcliente);

--
-- TOC entry 2063 (class 2606 OID 75590)
-- Name: pedido FK_CLIENTE_TO_PEDIDO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedido
ADD
    CONSTRAINT "FK_CLIENTE_TO_PEDIDO" FOREIGN KEY (idcliente) REFERENCES luftgun.cliente(idcliente) NOT VALID;

--
-- TOC entry 2066 (class 2606 OID 75656)
-- Name: pedpro FK_PEDIDO_TO_PEDPRO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedpro
ADD
    CONSTRAINT "FK_PEDIDO_TO_PEDPRO" FOREIGN KEY (idpedido) REFERENCES luftgun.pedido(idpedido);

--
-- TOC entry 2068 (class 2606 OID 76106)
-- Name: carrito FK_PRODUCTO_TO_CARRITO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.carrito
ADD
    CONSTRAINT "FK_PRODUCTO_TO_CARRITO" FOREIGN KEY (idreferencia) REFERENCES luftgun.producto(idreferencia);

--
-- TOC entry 2067 (class 2606 OID 75661)
-- Name: pedpro FK_PRODUCTO_TO_PEDPRO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.pedpro
ADD
    CONSTRAINT "FK_PRODUCTO_TO_PEDPRO" FOREIGN KEY (idreferencia) REFERENCES luftgun.producto(idreferencia);

--
-- TOC entry 2065 (class 2606 OID 75646)
-- Name: producto FK_SUBCATEGORIA_TO_PRODUCTO; Type: FK CONSTRAINT; Schema: luftgun; Owner: a20-mgarde
--
ALTER TABLE
    ONLY luftgun.producto
ADD
    CONSTRAINT "FK_SUBCATEGORIA_TO_PRODUCTO" FOREIGN KEY (idsubcategoria) REFERENCES luftgun.subcategoria(idsubcategoria) NOT VALID;

-- Completed on 2021-03-17 18:48:57
--
-- PostgreSQL database dump complete
--