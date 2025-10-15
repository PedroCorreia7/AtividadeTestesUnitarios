package com.example.biblioteca;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LivroTest.class,
        UsuarioTest.class,
        EmprestimoTest.class
})
public class AllTestsSuite {
    // Nada aqui: as anotações definem a suíte.
}