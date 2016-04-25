package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.repositories.EstoqueProdutoRepository;

@Service
public class EstoqueProdutosService {

	@Autowired
	private EstoqueProdutoRepository estoqueProdutoRepository;
	
	public void insereProdutosEstoque(List<ProdutosPedido> produtos){
		try{
			for(int i = 0; i < produtos.size(); i++){
				EstoqueProdutos estoque = estoqueProdutoRepository.findByProduto(produtos.get(i).getProduto());
	
				estoque.setQntd_estoque(estoque.getQntd_estoque() + produtos.get(i).getQntd_produtos());
				estoque.setQntd_comprada(estoque.getQntd_comprada() + produtos.get(i).getQntd_produtos());
	
				Double valorMedio = estoque.getValor_medio();
				if(valorMedio > 0)
					valorMedio = (valorMedio + produtos.get(i).getValor_pago()) /2;
				else
					valorMedio = produtos.get(i).getValor_pago();
				estoque.setValor_medio(valorMedio);
	
				Double valorTotal = (produtos.get(i).getValor_pago() * produtos.get(i).getQntd_produtos());
				estoque.setValor_total(estoque.getValor_total() + valorTotal);
	
				estoqueProdutoRepository.save(estoque);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	public void baixaProdutosEstoque(List<ProdutosPedido> produtos){
//		try{
//			for(int i = 0; i < produtos.size(); i++){
//				EstoqueProdutos estoque = estoqueProdutoRepository.findByProduto(produtos.get(i).getProduto());
//	
//				estoque.setQntd_estoque(estoque.getQntd_estoque() + produtos.get(i).getQntd_produtos());
//				estoque.setQntd_comprada(estoque.getQntd_comprada() + produtos.get(i).getQntd_produtos());
//	
//				Double valorMedio = estoque.getValor_medio();
//				if(valorMedio > 0)
//					valorMedio = (valorMedio + produtos.get(i).getValor_pago()) /2;
//				else
//					valorMedio = produtos.get(i).getValor_pago();
//				estoque.setValor_medio(valorMedio);
//	
//				Double valorTotal = (produtos.get(i).getValor_pago() * produtos.get(i).getQntd_produtos());
//				estoque.setValor_total(estoque.getValor_total() + valorTotal);
//	
//				estoqueProdutoRepository.save(estoque);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
