package com.devsenior.nmanja.interfaces;

import com.devsenior.nmanja.model.Loan;

public interface LoanRepository {

    void saveLoan(Loan loan);
    Loan findById(String id);

}
