package ar.edu.davinci.dvds20221cg4.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="negocios")

//Configuración de lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Negocio implements Serializable {

	
	private static final long serialVersionUID = 9112683896901479466L;

	// Configuración por JPA de la PK de la tabla
	@Id
	// Configuración por JPA de la manera en que se generan los IDs autogenerados en MySQL
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	// Configuración por JPA del nombre de la columna
	@Column(name = "ngc_id")
	private Long id;

	@ManyToOne(targetEntity = Venta.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	//@JoinColumn(name="vta_id", referencedColumnName="vta_id", nullable = false)
	private List<Venta> ventas;

	@Column(name = "ngc_ganancia")
	private BigDecimal ganancia;

    public BigDecimal calcularGananciaPorDia(Date dia) {
    	return ventas.stream().filter(venta -> venta.getFecha() == dia).map(Venta::importeFinal).reduce(BigDecimal::add).get();
    }
}
