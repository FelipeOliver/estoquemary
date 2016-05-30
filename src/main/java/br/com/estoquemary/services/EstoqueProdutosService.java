package br.com.estoquemary.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.models.ProdutosVenda;
import br.com.estoquemary.repositories.EstoqueProdutoRepository;
import br.com.estoquemary.repositories.ProdutosPedidoRepository;
import br.com.estoquemary.repositories.ProdutosVendaRepository;

@Service
public class EstoqueProdutosService {

	@Autowired
	private EstoqueProdutoRepository estoqueProdutoRepository;
	@Autowired
	private ProdutosPedidoRepository produtosPedidoRepository;
	@Autowired
	private ProdutosVendaRepository produtosVendaReposiTory;
	
	public void insereProdutosEstoque(List<ProdutosPedido> produtos){
		try{
//			for(int i = 0; i < produtos.size(); i++){
//				EstoqueProdutos estoque = estoqueProdutoRepository.findByProduto(produtos.get(i).getProduto());
//	
//				estoque.setQntdEstoque(estoque.getQntdEstoque() + produtos.get(i).getQntdProdutos());
//				estoque.setQntdComprada(estoque.getQntdComprada() + produtos.get(i).getQntdProdutos());
//	
//				Double valorTotal = (produtos.get(i).getValorPago() * produtos.get(i).getQntdProdutos());
//				estoque.setValorTotal(estoque.getValorTotal() + valorTotal);
//				
//				Double valorMedio = estoque.getValorMedio();
//				if(valorMedio > 0)
//					valorMedio = valorTotal / estoque.getQntdComprada();
//				else
//					valorMedio = produtos.get(i).getValorPago();
//				estoque.setValorMedio(valorMedio);
//		
//				estoqueProdutoRepository.save(estoque);
//			}
			for(int i = 0; i < produtos.size(); i++){
				
				EstoqueProdutos estoque = estoqueProdutoRepository.findByProduto(produtos.get(i).getProduto());
				
				List<ProdutosPedido> produtosPedido = this.produtosPedidoRepository.findByProduto(produtos.get(i).getProduto());
				int quantidadeComprada = 0;
				double valorComprado = 0;
				for(int j = 0; j < produtosPedido.size(); j++){
					quantidadeComprada = quantidadeComprada + produtosPedido.get(j).getQntdProdutos();
					valorComprado = valorComprado + (produtosPedido.get(j).getValorPago()* produtosPedido.get(j).getQntdProdutos());
				}
				
				List<ProdutosVenda> produtosVenda = this.produtosVendaReposiTory.findByProduto(produtos.get(i).getProduto());
				int quantidadeVendida = 0;
				for(int j = 0; j < produtosVenda.size(); j++){
					quantidadeVendida = quantidadeVendida + produtosVenda.get(j).getQntdProdutos();
				}
				
				estoque.setQntdEstoque(quantidadeComprada - quantidadeVendida);
				estoque.setQntdComprada(quantidadeComprada);
				estoque.setValorTotal(valorComprado);
				estoque.setValorMedio(valorComprado / quantidadeComprada);
		
				estoqueProdutoRepository.save(estoque);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<EstoqueProdutos> findAll() {
		return (List<EstoqueProdutos>) estoqueProdutoRepository.findAll();
	}
	
	public List<Produto> findByQntdEstoque(){
		List<EstoqueProdutos> list = estoqueProdutoRepository.findByQntdEstoqueIsGreaterThan(0);
		List<Produto> lp = new ArrayList<Produto>();
		for(int i = 0; i < list.size();i++){
			lp.add(list.get(i).getProduto());
		}
		return lp;
	}

	public void removeEstoqueByCodProduto(Produto produto) {
		System.out.println(produto);
		this.estoqueProdutoRepository.deleteByProduto(produto);
	}

	public EstoqueProdutos findByProduto(Produto produto) {
		return this.estoqueProdutoRepository.findByProduto(produto);
	}

	public void delete(EstoqueProdutos estoque) {
		this.estoqueProdutoRepository.delete(estoque);
	}
}
