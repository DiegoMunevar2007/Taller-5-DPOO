package uniandes.dpoo.hamburguesas.tests;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
public class ProductoMenuTest {
	private ProductoMenu producto1;
	
	@BeforeEach
	void setUp() throws Exception{
		producto1 = new ProductoMenu("Hamburguesa",20000);
	}
	 @AfterEach
	void tearDown( ) throws Exception{
	
	 }
	@Test
	void testGetNombre() throws Exception{
		assertEquals("Hamburguesa", producto1.getNombre(), "El nombre del producto no es el esperado");
	}
	@Test
	void testGetPrecio() throws Exception{
		assertEquals(20000,producto1.getPrecio(),"El precio del producto no es correcto");
	}
	@Test
	void testGenerarTextoFactura() throws Exception{
		assertEquals("Hamburguesa"+"\n"+"            "+"20000\n",producto1.generarTextoFactura(),"La factura generada no es correcta");
	}
	
}
