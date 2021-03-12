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
INNER JOIN luftgun.subcategoria 
    ON subcategoria.idsubcategoria = producto.idsubcategoria
		INNER JOIN luftgun.categoria
			ON categoria.idcategoria = subcategoria.idcategoria
