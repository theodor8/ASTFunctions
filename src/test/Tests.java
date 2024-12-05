package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import function.*;
import function.visitor.EvalVisitor;


public class Tests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }
    
    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    @Test
    void testNullary() {
        assertEquals(14, new Con(14).getValue());
        assertEquals(new Con(12), new Con(12));
        assertNotEquals(new Con(12), new Con(13));
        assertNotEquals(new Con(12), new Var());
        assertEquals(new Var(), new Var());
        assertNotEquals(new Var(), new Con(12));
    }

    @Test
    void testUnary() {
        assertEquals(new Con(10), new Cos(new Con(10)).getArg());
        assertEquals(new Con(10), new Sin(new Con(10)).getArg());
        assertEquals(new Sin(new Con(3)), new Sin(new Con(3)));
        assertNotEquals(new Sin(new Con(4)), new Sin(new Con(3)));
        assertNotEquals(new Sin(new Con(4)), new Cos(new Con(4)));
        assertEquals(new Neg(new Con(11)), new Neg(new Con(11)));
        assertEquals(new Neg(new Neg(new Con(-18))), new Neg(new Neg(new Con(-18))));
        assertNotEquals(new Neg(new Con(11)), new Neg(new Neg(new Con(11))));
        assertNotEquals(new Con(11), new Neg(new Neg(new Con(11))));
        assertEquals(new Sin(new Cos(new Con(2))), new Sin(new Cos(new Con(2))));
        assertNotEquals(new Sin(new Cos(new Con(2))), new Sin(new Cos(new Con(3))));
        assertNotEquals(new Sin(new Cos(new Con(2))), new Sin(new Sin(new Con(2))));
        assertNotEquals(new Sin(new Cos(new Con(2))), new Cos(new Cos(new Con(2))));
    }

    @Test
    void testBinary() {
        assertEquals(new Mul(new Con(10), new Con(10)), new Mul(new Con(10), new Con(10)));
        assertNotEquals(new Mul(new Con(10), new Con(10)), new Mul(new Con(10), new Con(11)));
        assertNotEquals(new Mul(new Con(10), new Con(10)), new Mul(new Con(10), new Var()));
        assertNotEquals(new Mul(new Con(10), new Var()), new Mul(new Con(10), new Con(10)));
    }

    @Test
    void testEval() {
        EvalVisitor ev = new EvalVisitor();

        assertEquals(new Con(7), new Neg(new Neg(new Con(7))).accept(ev));
        assertEquals(new Con(-5), new Neg(new Con(5)).accept(ev));
        assertEquals(new Con(-5), new Neg(new Neg(new Neg(new Con(5)))).accept(ev));
        assertEquals(new Con(-10), new Neg(new Neg(new Neg(new Neg(new Con(-10))))).accept(ev));
        
        assertEquals(new Con(-12), new Mul(new Con(-3), new Con(4)).accept(ev));
        assertEquals(new Mul(new Con(-3), new Sin(new Var())), new Mul(new Con(-3), new Sin(new Var())).accept(ev));
        assertEquals(new Con(0), new Mul(new Con(0), new Con(0)).accept(ev));
        assertEquals(new Con(7), new Add(new Con(3), new Con(4)).accept(ev));
        assertEquals(new Con(-6), new Sub(new Con(10), new Con(16)).accept(ev));
        assertEquals(new Con(4), new Div(new Con(12), new Con(3)).accept(ev));

        assertEquals(new Con(0), new Mul(new Con(0), new Var()).accept(ev));
        assertEquals(new Con(0), new Mul(new Var(), new Con(0)).accept(ev));
        assertEquals(new Var(), new Mul(new Con(1), new Var()).accept(ev));
        assertEquals(new Var(), new Mul(new Var(), new Con(1)).accept(ev));
        assertEquals(new Neg(new Var()), new Mul(new Con(-1), new Var()).accept(ev));
        assertEquals(new Neg(new Var()), new Mul(new Var(), new Con(-1)).accept(ev));
        assertEquals(new Var(), new Add(new Con(0), new Var()).accept(ev));
        assertEquals(new Var(), new Add(new Var(), new Con(0)).accept(ev));
        assertEquals(new Var(), new Sub(new Var(), new Con(0)).accept(ev));
        assertEquals(new Neg(new Var()), new Sub(new Con(0), new Var()).accept(ev));
        assertEquals(new Var(), new Div(new Var(), new Con(1)).accept(ev));
        assertEquals(new Neg(new Var()), new Div(new Var(), new Con(-1)).accept(ev));
        assertEquals(new Con(0), new Div(new Con(0), new Var()).accept(ev));
        assertThrows(ArithmeticException.class, () -> new Div(new Con(1), new Con(0)).accept(ev));
        assertThrows(ArithmeticException.class, () -> new Div(new Var(), new Con(0)).accept(ev));

    }

    @Test
    void testDerivative() {
        
    }


}
