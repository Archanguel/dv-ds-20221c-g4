package ar.edu.davinci.dvds20221cg4.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="negocios")

//Configuración de lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Negocio implements Serializable {

	
	private static final long serialVersionUID = 9002003896900479460L;

	// Configuración por JPA de la PK de la tabla
	@Id
	// Configuración por JPA de la manera en que se generan los IDs autogenerados en MySQL
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	// Configuración por JPA del nombre de la columna
	@Column(name = "ngc_id")
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "negocio", cascade = CascadeType.PERSIST, orphanRemoval = true   )
	@JsonIgnore
	private List<Venta> ventas;

	@Column(name = "ngc_ganancia")
	private BigDecimal ganancia;
	
    public BigDecimal calcularGananciaPorDia(Date dia) {
    	return ventas.stream().filter(venta -> venta.getFecha() == dia).map(Venta::importeFinal).reduce(BigDecimal::add).get();
    }
    
    public BigDecimal calcularGananciaTotal() {
		if(ventas.stream().map(Venta::importeFinal).reduce(BigDecimal::add).isPresent()) {
			return ventas.stream().map(Venta::importeFinal).reduce(BigDecimal::add).get();
		} else {
			return BigDecimal.ZERO;
		}
	};
}
