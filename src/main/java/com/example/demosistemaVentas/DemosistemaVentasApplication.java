package com.example.demosistemaVentas;

import com.example.demosistemaVentas.Entidades.*;
import com.example.demosistemaVentas.Otros.Estado;
import com.example.demosistemaVentas.Otros.FormaPago;
import com.example.demosistemaVentas.Otros.TipoEnvio;
import com.example.demosistemaVentas.Otros.TipoProducto;
import com.example.demosistemaVentas.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.jsf.FacesContextUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class DemosistemaVentasApplication {
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemosistemaVentasApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(){
	return args -> {
			System.out.println("Funcionando");

		//Creando un único rubro en este caso
		Rubro rubro = Rubro.builder()
				.denominacion("Gastronomía")
				.build();

		//Creando producto
		Producto producto1 = Producto.builder()
				.tipo(TipoProducto.Manufacturado)
				.tiempoEstimadoCocina(2)
				.denominacion("Factura con pastelera")
				.precioVenta(120.00)
				.StockActual(50)
				.StockMinimo(20)
				.unidadMedida("Unidad")
				.foto("https://tse3.mm.bing.net/th?id=OIP.lbAzxQoA6ffsFw-KAu0CigHaD4&pid=Api&P=0&h=180")
				.receta("https://razafolklorica.com/como-hacer-facturas-caseras-de-crema-pastelera/")
				.build();


		Producto producto2 = Producto.builder()
				.tipo(TipoProducto.Manufacturado)
				.tiempoEstimadoCocina(2)
				.denominacion("Factura con membrillo")
				.precioVenta(130.00)
				.StockActual(70)
				.StockMinimo(20)
				.unidadMedida("Unidad")
				.foto("https://tse2.mm.bing.net/th?id=OIP.wlFtB7vq1RvyV2hAfzApKgHaHa&pid=Api&P=0&h=180")
				.receta("ookpad.com/ar/recetas/15015271-facturas-rellenas-de-dulce-de-membrillo")
				.build();


		//Relación producto con rubro
		rubro.agregarProducto(producto1);
		rubro.agregarProducto(producto2);;
		//Guardando el objeto rubro a la base de Datos
		rubroRepository.save(rubro);
		//Guardar objeto producto a la base de datos
		productoRepository.save(producto1);
		productoRepository.save(producto2);



		//Creando detalle de pedido
		DetallePedido detallePedido1 = DetallePedido.builder()
				.Cantidad(15)
				.subtotal(767.55)
				.build();

		DetallePedido detallePedido2 = DetallePedido.builder()
				.Cantidad(9)
				.subtotal(7521.362)
				.build();

			//Creando Pedidos
			Pedido pedido1 = Pedido.builder()
					.fecha("05/05/2022")
					.estado(Estado.Preparado)
					.horaEstimadaEntrega(new Date(122, 9, 7))
					.tipoEnvio(TipoEnvio.Retira)
					.total(5478.85)
					.build();

			Pedido pedido2 = Pedido.builder()
					.fecha("18/11/2022")
					.estado(Estado.Entregado)
					.horaEstimadaEntrega(new Date(123, 7, 3))
					.tipoEnvio(TipoEnvio.Delivery)
					.total(2345.85)
					.build();
		//Agregando detalle pedido a pedido
		pedido1.agregarDetalle(detallePedido1);
		pedido2.agregarDetalle(detallePedido2);
		//Creando factura
		Factura factura1 = Factura.builder()
				.fecha(new Date(122, 9, 7, 12, 30, 06))
				.numero(1)
				.descuento(1000.00)
				.formaPago(FormaPago.Efectivo)
				.total(4478.85)
				.build();
		Factura factura2 = Factura.builder()
				.fecha(new Date(122, 9, 7, 16, 55, 28))
				.numero(2)
				.formaPago(FormaPago.Transferencia)
				.descuento(345.85)
				.total(2000.00)
				.build();

		//Relación pedido con factura
		pedido1.setFactura(factura1);
		pedido2.setFactura(factura2);

		//Creando Usuario
		Usuario usuario1 = Usuario.builder()
				.nombre("Julieta Zabala")
				.password("Juli1707")
				.rol("Cliente")
				.build();

		Usuario usuario2 = Usuario.builder()
				.nombre("Paola Fernandez")
				.password("p@Ol41980")
				.rol("Cliente")
				.build();


		//Relecionando Usuario con pedido
		usuario1.agregarPedido(pedido1);
		usuario2.agregarPedido(pedido2);
			//Creando cliente
			Cliente cliente1 = Cliente.builder()
					.nombre("Julieta")
					.apellido("Zabala")
					.telefono("2617034164")
					.email("juliizabala1707@gmail.com")
					.build();

			Cliente cliente2 = Cliente.builder()
					.nombre("Paola")
					.apellido("Fernandez")
					.telefono("2616673735")
					.email("paoFer25@gmail.com")
					.build();


			//Agregando pedido a cliente
			cliente1.agregarPedido(pedido1);
			cliente2.agregarPedido(pedido2);
			//Creando domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Carlos Gardel")
					.numero(5274)
					.localidad("Guaymallén")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Severo del Castillo")
					.numero(9879)
					.localidad("Guaymallén")
					.build();

			//Agregando un domicilio a cliente
			domicilio1.setCliente(cliente1);
			domicilio2.setCliente(cliente2);
			//Agregando un domicilio a producto
			domicilio1.PedidosDe(pedido1);
			domicilio2.PedidosDe(pedido2);


		//Guardar objetos domicilios en base de datos
		domicilioRepository.save(domicilio1);
		domicilioRepository.save(domicilio2);

		//Guardando el objeto usuario a la base de datos
		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);

		//Guardando pedido en base de datos
		pedidoRepository.save(pedido1);
		pedidoRepository.save(pedido2);


		//Guardando cliente a la base de datps
		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);

		//Guardando objeto factura en la base de datos
		facturaRepository.save(factura1);
		facturaRepository.save(factura2);


		//Guardar objeto de detalle pedido
		detallePedidoRepository.save(detallePedido1);
		detallePedidoRepository.save(detallePedido2);


		Producto productoRecuperado = productoRepository.findById(producto1.getId()).orElse(null);
		if (productoRecuperado != null){
			System.out.println("Producto 1");
			System.out.println("Tipo: "+ productoRecuperado.getTipo());
			System.out.println("Tiempo estimado cocina: " + productoRecuperado.getTiempoEstimadoCocina());
			System.out.println("Denominacion: "+ productoRecuperado.getDenominacion());
			System.out.println("Precio Venta: "+ productoRecuperado.getPrecioVenta());
			System.out.println("Precio Compra: "+ productoRecuperado.getPrecioCompra());
			System.out.println("Stock minimo: " + productoRecuperado.getStockMinimo());
			System.out.println("Stock actual: " + productoRecuperado.getStockActual());
			System.out.println("Unidad de medida: " + productoRecuperado.getUnidadMedida());
			System.out.println("Foto: " + productoRecuperado.getFoto());
			System.out.println("Receta: "+productoRecuperado.getReceta());
		}
		Producto productoRecuperado2 = productoRepository.findById(producto2.getId()).orElse(null);
		if (productoRecuperado2 != null){
			System.out.println("Producto 2");
			System.out.println("Tipo: "+ productoRecuperado2.getTipo());
			System.out.println("Tiempo estimado cocina: " + productoRecuperado2.getTiempoEstimadoCocina());
			System.out.println("Denominacion: "+ productoRecuperado2.getDenominacion());
			System.out.println("Precio Venta: "+ productoRecuperado2.getPrecioVenta());
			System.out.println("Precio Compra: "+ productoRecuperado2.getPrecioCompra());
			System.out.println("Stock minimo: " + productoRecuperado2.getStockMinimo());
			System.out.println("Stock actual: " + productoRecuperado2.getStockActual());
			System.out.println("Unidad de medida: " + productoRecuperado2.getUnidadMedida());
			System.out.println("Foto: " + productoRecuperado2.getFoto());
			System.out.println("Receta: "+productoRecuperado2.getReceta());
		}

		Usuario usuarioRecuperado = usuarioRepository.findById(usuario1.getId()).orElse(null);

		if (usuarioRecuperado != null) {
			System.out.println("Usuario 1");
			System.out.println("Nombre: " + usuarioRecuperado.getNombre());
			System.out.println("Password: " + usuarioRecuperado.getPassword());
			System.out.println("Rol: " + usuarioRecuperado.getRol());
			System.out.println("Pedidos: " + usuarioRecuperado.getPedidos());
		}
		Usuario usuarioRecuperado2 = usuarioRepository.findById(usuario1.getId()).orElse(null);

		if (usuarioRecuperado2 != null) {
			System.out.println("Usuario 1");
			System.out.println("Nombre: " + usuarioRecuperado2.getNombre());
			System.out.println("Password: " + usuarioRecuperado2.getPassword());
			System.out.println("Rol: " + usuarioRecuperado2.getRol());
			System.out.println("Pedidos: " + usuarioRecuperado2.getPedidos());
		}

		Pedido pedidoRecuperado = pedidoRepository.findById(pedido1.getId()).orElse(null);

		if (pedidoRecuperado != null) {
			System.out.println("Pedido 1");
			System.out.println("Fecha: " + pedidoRecuperado.getFecha());
			System.out.println("Estado: " + pedidoRecuperado.getEstado());
			System.out.println("Hora aprox entrega: " + pedidoRecuperado.getHoraEstimadaEntrega());
			System.out.println("Tipo de envio: " + pedidoRecuperado.getTipoEnvio());
			System.out.println("Total: " + pedidoRecuperado.getTotal());
			System.out.println("Factura: "+ pedidoRecuperado.getFactura());
			System.out.println("Detalle Pedido: " + pedidoRecuperado.getPedidos());
		}

		Pedido pedidoRecuperado2 = pedidoRepository.findById(pedido2.getId()).orElse(null);

		if (pedidoRecuperado2 != null) {
			System.out.println("Pedido 2");
			System.out.println("Fecha: " + pedidoRecuperado2.getFecha());
			System.out.println("Estado: " + pedidoRecuperado2.getEstado());
			System.out.println("Hora aprox entrega: " + pedidoRecuperado2.getHoraEstimadaEntrega());
			System.out.println("Tipo de envio: " + pedidoRecuperado2.getTipoEnvio());
			System.out.println("Total: " + pedidoRecuperado2.getTotal());
			System.out.println("Factura: "+ pedidoRecuperado2.getFactura());
			System.out.println("Detalle Pedido: " + pedidoRecuperado2.getPedidos());
		}

		Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);

		if (clienteRecuperado != null) {
			System.out.println("Cliente 1");
			System.out.println("Nombre: " + clienteRecuperado.getNombre());
			System.out.println("Apellido: " + clienteRecuperado.getApellido());
			System.out.println("Telefono: " + clienteRecuperado.getTelefono());
			System.out.println("Email: " + clienteRecuperado.getEmail());
			System.out.println("Pedidos" + clienteRecuperado.getPedidos());
		}
		Cliente clienteRecuperado2 = clienteRepository.findById(cliente2.getId()).orElse(null);

		if (clienteRecuperado2 != null) {
			System.out.println("Cliente 2");
			System.out.println("Nombre: " + clienteRecuperado2.getNombre());
			System.out.println("Apellido: " + clienteRecuperado2.getApellido());
			System.out.println("Telefono: " + clienteRecuperado2.getTelefono());
			System.out.println("Email: " + clienteRecuperado2.getEmail());
			System.out.println("Pedidos" + clienteRecuperado2.getPedidos());

		}

		Factura facturaRecuperado = facturaRepository.findById(factura1.getId()).orElse(null);

		if (facturaRecuperado != null) {
			System.out.println("Factura 1");
			System.out.println("Fecha: " + facturaRecuperado.getFecha());
			System.out.println("Numero: " + facturaRecuperado.getNumero());
			System.out.println("Descuento: " + facturaRecuperado.getDescuento());
			System.out.println("Forma de pago: " + facturaRecuperado.getFormaPago());
			System.out.println("Total: " + facturaRecuperado.getTotal());
		}
		Factura facturaRecuperado2 = facturaRepository.findById(factura2.getId()).orElse(null);

		if (facturaRecuperado2 != null) {
			System.out.println("Factura 1");
			System.out.println("Fecha: " + facturaRecuperado2.getFecha());
			System.out.println("Numero: " + facturaRecuperado2.getNumero());
			System.out.println("Descuento: " + facturaRecuperado2.getDescuento());
			System.out.println("Forma de pago: " + facturaRecuperado2.getFormaPago());
			System.out.println("Total: " + facturaRecuperado2.getTotal());
		}
		DetallePedido detallePedidoRecuperado = detallePedidoRepository.findById(detallePedido1.getId()).orElse(null);

		if (detallePedidoRecuperado != null) {
			System.out.println("Detalle Pedido 1");
			System.out.println("Cantidad: " + detallePedidoRecuperado.getCantidad());
			System.out.println("Prpducto: " + detallePedidoRecuperado.getProductos());
			System.out.println("SubTotal: " + detallePedidoRecuperado.getSubtotal());
		}

		DetallePedido detallePedidoRecuperado2 = detallePedidoRepository.findById(detallePedido2.getId()).orElse(null);

		if (detallePedidoRecuperado2 != null) {
			System.out.println("Detalle Pedido 1");
			System.out.println("Cantidad: " + detallePedidoRecuperado2.getCantidad());
			System.out.println("Prpducto: " + detallePedidoRecuperado2.getProductos());
			System.out.println("SubTotal: " + detallePedidoRecuperado2.getSubtotal());
		}
		Domicilio domicilioRecuperado = domicilioRepository.findById(domicilio1.getId()).orElse(null);

		if (domicilioRecuperado != null) {
			System.out.println("Domicilio 1");
			System.out.println("Calle: " + domicilioRecuperado.getCalle());
			System.out.println("Numero: " + domicilioRecuperado.getNumero());
			System.out.println("Localidad: " + domicilioRecuperado.getLocalidad());
		}
		Domicilio domicilioRecuperado2 = domicilioRepository.findById(domicilio1.getId()).orElse(null);

		if (domicilioRecuperado2 != null) {
			System.out.println("Domicilio 1");
			System.out.println("Calle: " + domicilioRecuperado2.getCalle());
			System.out.println("Numero: " + domicilioRecuperado2.getNumero());
			System.out.println("Localidad: " + domicilioRecuperado2.getLocalidad());
		}


	};

    }
}

