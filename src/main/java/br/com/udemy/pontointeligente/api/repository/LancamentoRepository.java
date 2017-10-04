package br.com.udemy.pontointeligente.api.repository;


import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;

@Transactional(readOnly=true)
@NamedQueries({
	@NamedQuery(name="LancamentoRepository.findByFuncionarioId",
			query="select l from Lancamento l where l.funcionario.id = :funcionarioId")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);
	
	Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable pageable);

}
