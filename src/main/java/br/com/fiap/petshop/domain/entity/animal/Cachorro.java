package br.com.fiap.petshop.domain.entity.animal;

import br.com.fiap.petshop.domain.entity.Sexo;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import br.com.fiap.petshop.infra.security.entity.Pessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "TB_CACHORRO")
@DiscriminatorValue("C")
public class Cachorro extends Animal{
    public Cachorro() {
    }

    public Cachorro(Long id, String nome, Sexo sexo, LocalDate nascimento, String raca, String descricao, String observacao, Pessoa dono, Set<Servico> servicos) {
        super(id, nome, sexo, nascimento, raca, descricao, observacao, dono, servicos);
    }

    @Override
    public String toString() {
        return "Cachorro{} " + super.toString();
    }
}
