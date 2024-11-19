package com.projecao.fabricadesoftwarecrm.repository;

import com.projecao.fabricadesoftwarecrm.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}
