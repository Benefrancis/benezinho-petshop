package br.com.fiap.petshop.domain.entity.animal;

import br.com.fiap.petshop.domain.entity.Sexo;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import br.com.fiap.petshop.infra.security.entity.PessoaFisica;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "TB_GATO")
@DiscriminatorValue("GATO")
public class Gato extends Animal {

    public Gato() {
        super( "GATO" );
    }


    public Gato(Long id, String nome, Sexo sexo, LocalDate nascimento, String raca, String descricao, String observacao, PessoaFisica dono) {
        super( id, nome, sexo, nascimento, raca, descricao, observacao, "GATO", dono );
    }

    @Override
    public String toString() {
        return "Gato{} " + super.toString();
    }
}
