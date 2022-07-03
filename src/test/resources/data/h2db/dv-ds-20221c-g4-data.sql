--
-- Dumping data for table prendas
--

INSERT INTO prendas
(prd_id,
prd_descripcion,
prd_precio_base,
prd_tipo_prenda
)
VALUES
(1,'Camisa Celeste',10.24,'CAMISA'),
(2,'Camisa Blanca',100.50,'CAMISA'),
(3,'Saco Vestir',102.40,'SACO'),
(4,'Pantalón Gabardina Beige',1004.00,'PANTALON'),
(5,'Tapado Negro',3234.22,'TAPADO'),
(6,'Tapado de Piel',232.20,'TAPADO'),
(7,'Camisa Gris Claro',165.20,'CAMISA'),
(8,'Camisa Gris',1645.24,'CAMISA'),
(9,'Camisa Gris Oscuro',203.00,'CAMISA'),
(10,'Bufanda',34.45,'CAMISA'),
(11,'Media',9898.00,'MEDIA'),
(12,'Media Roja',9898.00,'MEDIA');


INSERT INTO CLIENTES
(cli_id,
cli_nombre,
cli_apellido)
VALUES
(1, 'Pepe1', 'Pepito1'),
(2, 'Pepe2', 'Pepito2'),
(3, 'Pepe3', 'Pepito3'),
(4, 'Pepe4', 'Pepito4');
