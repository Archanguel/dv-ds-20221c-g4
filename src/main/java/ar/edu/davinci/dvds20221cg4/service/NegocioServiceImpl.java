package ar.edu.davinci.dvds20221cg4.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20221cg4.domain.Cliente;
import ar.edu.davinci.dvds20221cg4.domain.Item;
import ar.edu.davinci.dvds20221cg4.domain.Prenda;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.domain.VentaEfectivo;
import ar.edu.davinci.dvds20221cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20221cg4.exception.BusinessException;
import ar.edu.davinci.dvds20221cg4.repository.VentaEfectivoRepository;
import ar.edu.davinci.dvds20221cg4.repository.VentaRepository;
import ar.edu.davinci.dvds20221cg4.repository.VentaTarjetaRepository;

@Service
public class NegocioServiceImpl implements NegocioService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(VentaServiceImpl.class);

	private final VentaRepository ventaRepository;

	private final VentaEfectivoRepository ventaEfectivoRepository;
	
	private final VentaTarjetaRepository ventaTarjetaRepository;
	
	private final ClienteService clienteService;

	private final PrendaService prendaService;
	
	private final ItemService itemService;

	
	@Autowired
	public NegocioServiceImpl(final VentaRepository ventaRepository,
			final VentaEfectivoRepository ventaEfectivoRepository,
			final VentaTarjetaRepository ventaTarjetaRepository,
			final ClienteService clienteService,
			final PrendaService prendaService,
			final ItemService itemService) {
		this.ventaRepository = ventaRepository;
		this.ventaEfectivoRepository = ventaEfectivoRepository;
		this.ventaTarjetaRepository = ventaTarjetaRepository;
		this.clienteService = clienteService;
		this.prendaService = prendaService;
		this.itemService = itemService;

	}

	@Override
	public Venta findById(Long id) throws BusinessException {
		LOGGER.debug("Busqueda de una venta por ID");
		
		Optional<Venta> itemOptional = ventaRepository.findById(id);
		if (itemOptional.isPresent()) {
			return itemOptional.get();
		}
		
		throw new BusinessException("No se encotró la venta por el id: " + id);
	}

	@Override
	public List<Venta> list() {
		LOGGER.debug("Listado de todas las ventas");

		return ventaRepository.findAll();
	}

	@Override
	public Page<Venta> list(Pageable pageable) {
		
		LOGGER.debug("Listado de todas las ventas por páginas");
		LOGGER.debug("Pageable: offset: " + pageable.getOffset() + ", pageSize: " + pageable.getPageSize() + " and pageNumber: " + pageable.getPageNumber());
		
		return ventaRepository.findAll(pageable);
	}

	@Override
	public long count() {
		return ventaRepository.count();
	}

	private Venta getVenta(Long ventaId) throws BusinessException {
		Optional<Venta> ventaOptional = ventaRepository.findById(ventaId);
		if (ventaOptional.isPresent()) {
			return ventaOptional.get();
		} else {
			throw new BusinessException("Venta no encotrado para el id: " + ventaId);
		}
	}
	
	private List<Item> getItems(List<Item> requestItems) throws BusinessException {
		List<Item> items = new ArrayList<Item>();
		for (Item requestItem : requestItems) {
			
			Prenda prenda = getPrenda(requestItem);
			Item item = Item.builder()
					.cantidad(requestItem.getCantidad())
					.prenda(prenda)
					.build();
			items.add(item);
		}
		return items;
	}
	
	private Prenda getPrenda(Item requestItem) throws BusinessException {
		if (requestItem.getPrenda().getId() != null) {
			
			return prendaService.findById(requestItem.getPrenda().getId());
			
		} else {
			throw new BusinessException("La Prenda es obligatoria");
		}
	}


	private Item getItem(Long id) throws BusinessException {
		
		return itemService.findById(id);

	}


	private Cliente getCliente(Long id) throws BusinessException {

		return clienteService.findById(id);
	
	}

}
