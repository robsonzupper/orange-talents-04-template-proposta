package br.com.zupacademy.robson.proposta.models.proposal;

import java.math.BigDecimal;
import javax.validation.constraints.*;
import br.com.zupacademy.robson.proposta.config.validation.ValidCpfOrCnpj;

public class ProposalRequest {

    @NotBlank
    @ValidCpfOrCnpj
    private String document;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Positive
    @NotNull
    private BigDecimal salary;

    public ProposalRequest(String document, String email, String name,
            String address, BigDecimal salary) {

        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal convert() {
        return new Proposal(document, email, name, address, salary);
    }

    public String getDocument() {
        return document;
    }
}
