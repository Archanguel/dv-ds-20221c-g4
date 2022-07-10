package ar.edu.davinci.dvds20221cg4.service;

import java.util.Date;
import java.util.List;

import ar.edu.davinci.dvds20221cg4.domain.Negocio;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.exception.BusinessException;

public interface NegocioService {
	
	// Método de búsqueda.
	Negocio findById(Long id) throws BusinessException;
	
	// Metodos de listado
	List<Venta> list();

	List<Negocio> calcularGananciaPorDia(Date date);
}
