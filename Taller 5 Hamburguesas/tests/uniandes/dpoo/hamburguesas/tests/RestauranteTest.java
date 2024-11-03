package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
    private Restaurante restaurante;

    @BeforeEach
    public void setUp() throws Exception {
        restaurante = new Restaurante();
    }

    @Test
    public void testGetPedidoEnCurso() throws Exception {
        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso no es correcto");
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso no es correcto");
        assertEquals("Juan", restaurante.getPedidoEnCurso().getNombreCliente(), "El pedido en curso no es correcto");
    }

    @Test
    public void testIniciarPedido() throws Exception {
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> restaurante.iniciarPedido("Pedro", "Calle 456"));
        assertEquals("Juan", restaurante.getPedidoEnCurso().getNombreCliente(), "El pedido en curso no es correcto");
    }

    @Test
    public void testCerrarYGuardarPedido() throws Exception {
        assertThrows(NoHayPedidoEnCursoException.class, () -> restaurante.cerrarYGuardarPedido());
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso no es correcto");
        restaurante.cerrarYGuardarPedido();
        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso no es correcto");
    }

    @Test
    public void testCargarInformacionRestaurante() throws Exception {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoProductosMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");

        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoProductosMenu, archivoCombos);
        assertEquals(15, restaurante.getIngredientes().size(), "El número de ingredientes no es correcto");
        assertEquals(22, restaurante.getMenuBase().size(), "El número de productos de menú no es correcto");
        assertEquals(4, restaurante.getMenuCombos().size(), "El número de combos no es correcto");
    }

    @Test
    void testFallaCargaInformacionIngrediente() throws Exception {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoProductosMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");
        restaurante.getIngredientes().add(new Ingrediente("lechuga", 1000));
        assertThrows(IngredienteRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoProductosMenu, archivoCombos));
    }

    @Test
    void testFallaCargaInformacionMenu() throws Exception {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoProductosMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");
        restaurante.getMenuBase().add(new ProductoMenu("corral", 14000));
        assertThrows(ProductoRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoProductosMenu, archivoCombos));
    }

    @Test
    void testFallaCargaInformacionCombos() throws Exception {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoProductosMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");
        ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
        productos.add(new ProductoMenu("corral", 14000));
        productos.add(new ProductoMenu("papas medianas", 5500));
        productos.add(new ProductoMenu("gaseosa", 5000));
        restaurante.getMenuCombos().add(new Combo("combo corral", 0.1, productos));
        assertThrows(ProductoRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoProductosMenu, archivoCombos));
    }

}
