package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
    private ArrayList<Combo> combos;

    @BeforeEach
    void setUp() throws Exception {
        ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
        productos.add(new ProductoMenu("Hamburguesa", 20000));
        productos.add(new ProductoMenu("Perro", 15000));
        productos.add(new ProductoMenu("Papas", 5000));
        productos.add(new ProductoMenu("Gaseosa", 3000));
        String nombre = "Combo 1";
        double descuento = 0.1;
        Combo combo = new Combo(nombre, descuento, productos);
        combos = new ArrayList<Combo>();
        combos.add(combo);
        productos = new ArrayList<ProductoMenu>();
        productos.add(new ProductoMenu("Hamburguesa", 20000));
        productos.add(new ProductoMenu("Papas", 5000));
        productos.add(new ProductoMenu("Gaseosa", 3000));
        nombre = "Combo 2";
        descuento = 0.15;
        combo = new Combo(nombre, descuento, productos);
        combos.add(combo);
    }

    @Test
    void testGetNombre() throws Exception {
        assertEquals("Combo 1", combos.get(0).getNombre(), "El nombre del combo no es correcto");
        assertEquals("Combo 2", combos.get(1).getNombre(), "El nombre del combo no es correcto");
    }

    @Test
    void testGetPrecio() throws Exception {
        assertEquals(38700, combos.get(0).getPrecio(), "El precio del combo no es correcto");
        assertEquals(25500, combos.get(1).getPrecio(), "El precio del combo no es correcto");
    }

    @Test
    void testGenerarTextoFactura() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("Combo Combo 1\n");
        sb.append(" Descuento: 0.1\n");
        sb.append("            38700\n");
        assertEquals(sb.toString(), combos.get(0).generarTextoFactura(),
                "El texto de la factura no es correcto para Combo 1");

        sb = new StringBuffer();
        sb.append("Combo Combo 2\n");
        sb.append(" Descuento: 0.15\n");
        sb.append("            25500\n");
        assertEquals(sb.toString(), combos.get(1).generarTextoFactura(),
                "El texto de la factura no es correcto para Combo 2");
    }
}
