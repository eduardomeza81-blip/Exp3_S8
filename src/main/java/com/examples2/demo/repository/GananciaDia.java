package com.examples2.demo.repository;

import java.math.BigDecimal;
import java.sql.Date; // <- para mapear TRUNC(fecha) de Oracle

public interface GananciaDia {
    Date getDia();
    BigDecimal getTotal();
}
