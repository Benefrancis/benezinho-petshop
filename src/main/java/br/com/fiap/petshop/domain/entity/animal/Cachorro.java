package br.com.fiap.petshop.domain.entity.animal;

import br.com.fiap.petshop.domain.entity.Sexo;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import br.com.fiap.petshop.infra.security.entity.Pessoa;
import br.com.fiap.petshop.infra.security.entity.PessoaFisica;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "TB_CACHORRO")
@DiscriminatorValue( "CACHORRO" )
public class Cachorro extends Animal{

    public Cachorro() {
        super("CACHORRO");
    }



    public Cachorro(Long id, String nome, Sexo sexo, LocalDate nascimento, String raca, String descricao, String observacao, PessoaFisica dono) {
        super(id, nome, sexo, nascimento, raca, descricao, observacao, "CACHORRO", dono);
    }

    @Override
    public String toString() {
        return "Cachorro{} " + super.toString();
    }
}
