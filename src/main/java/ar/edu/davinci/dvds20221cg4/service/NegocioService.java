package ar.edu.davinci.dvds20221cg4.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20221cg4.domain.Item;
import ar.edu.davinci.dvds20221cg4.domain.Negocio;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.domain.VentaEfectivo;
import ar.edu.davinci.dvds20221cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20221cg4.exception.BusinessException;

public interface NegocioService {
	
	// Método de búsqueda.
	Venta findById(Long id) throws BusinessException;
	
	// Método de listado.
	List<Venta> list();
	Page<Venta> list(Pageable pageable);
	
	// Método para contar cantidad de datos.
	long count();
	
}
