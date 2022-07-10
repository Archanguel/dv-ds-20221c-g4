package ar.edu.davinci.dvds20221cg4.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20221cg4.domain.Cliente;
import ar.edu.davinci.dvds20221cg4.domain.Negocio;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.exception.BusinessException;
import ar.edu.davinci.dvds20221cg4.repository.ClienteRepository;
import ar.edu.davinci.dvds20221cg4.repository.NegocioRepository;

@Service
public class NegocioServiceImpl implements NegocioService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(NegocioServiceImpl.class);

	private NegocioRepository negocioRepository;
	
	@Autowired
	public NegocioServiceImpl(final NegocioRepository negocioRepository) {
		this.negocioRepository = negocioRepository;
	}

	private List<Venta> listAllByDate(Date fecha, Negocio negocio) {
		return negocio.getVentas().stream().filter(venta -> venta.getFecha().equals(fecha)).collect(Collectors.toList());
	}

	@Override
	public List<Negocio> calcularGananciaPorDia(Date date) {
		LOGGER.debug("Calcular ganancia por dia" + date);
		List<Negocio> negocios = negocioRepository.findAll();

		for (Negocio negocio : negocios) {
			List<Venta> ventas = listAllByDate(date, negocio);
			BigDecimal ganancia = BigDecimal.ZERO;
			for (Venta venta : ventas) {
				ganancia = ganancia.add(venta.importeFinal());
				LOGGER.debug("ganancia" + ganancia);
			}
			LOGGER.debug("Ventas" + ventas);
			negocio.setGanancia(ganancia);
		}
		LOGGER.debug("Negocios" + negocios);
		return negocios;
	}

	@Override
	public Negocio findById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venta> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
