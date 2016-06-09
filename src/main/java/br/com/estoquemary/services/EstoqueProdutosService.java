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
			for(int i = 0; i < produtos.size(); i++){
				this.atualizaEstoque(produtos.get(i).getProduto());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void retiraProdutosEstoque(List<ProdutosVenda> produtos){
		try{
			for(int i = 0; i < produtos.size(); i++){
				this.atualizaEstoque(produtos.get(i).getProduto());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<EstoqueProdutos> findAll() {
		return (List<EstoqueProdutos>) estoqueProdutoRepository.findAll();
	}
	
	public List<Produto> findByQntdEstoqueReturnProduto(){
		List<EstoqueProdutos> list = estoqueProdutoRepository.findByQntdEstoqueIsGreaterThan(0);
		List<Produto> lp = new ArrayList<Produto>();
		for(int i = 0; i < list.size();i++){
			lp.add(list.get(i).getProduto());
		}
		return lp;
	}
	
	public List<EstoqueProdutos> findByQntdEstoqueReturnEstoque(){
		List<EstoqueProdutos> list = estoqueProdutoRepository.findByQntdEstoqueIsGreaterThan(0);
		return list;
	}
	
	public List<EstoqueProdutos> findByQntdEstoqueAndQntdCompradaReturnEstoque(){
		List<EstoqueProdutos> list = estoqueProdutoRepository.findByQntdEstoqueIsLessThanEqualAndQntdCompradaIsGreaterThan(0, 0);
		return list;
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

	public void atualizaEstoque(Produto produto) {
		EstoqueProdutos estoque = estoqueProdutoRepository.findByProduto(produto);
		
		List<ProdutosPedido> produtosPedido = this.produtosPedidoRepository.findByProduto(produto);
		int quantidadeComprada = 0;
		double valorComprado = 0;
		for(int j = 0; j < produtosPedido.size(); j++){
			quantidadeComprada = quantidadeComprada + produtosPedido.get(j).getQntdProdutos();
			valorComprado = valorComprado + (produtosPedido.get(j).getValorPago()* produtosPedido.get(j).getQntdProdutos());
		}
		
		List<ProdutosVenda> produtosVenda = this.produtosVendaReposiTory.findByProduto(produto);
		int quantidadeVendida = 0;
		double valorVendido = 0;
		for(int j = 0; j < produtosVenda.size(); j++){
			quantidadeVendida = quantidadeVendida + produtosVenda.get(j).getQntdProdutos();
			valorVendido = valorVendido + (produtosVenda.get(j).getValorVendido()*produtosVenda.get(j).getQntdProdutos());
		}
		int a;
		estoque.setQntdEstoque(quantidadeComprada - quantidadeVendida);
		estoque.setQntdComprada(quantidadeComprada);
		estoque.setValorTotal(valorComprado);
		estoque.setQntdVendida(quantidadeVendida);
		estoque.setValorVendido(valorVendido);
		a = (quantidadeVendida == 0? 1 :quantidadeVendida);
		estoque.setValorMedioVendido(valorVendido / a);
		System.out.println(valorVendido / a);
		a = (quantidadeComprada == 0? 1:quantidadeComprada);
		estoque.setValorMedio(valorComprado / a);
		
		estoqueProdutoRepository.save(estoque);
	}

	public List<EstoqueProdutos> findByQntdEstoqueNaoComprado() {
		return this.estoqueProdutoRepository.findByQntdEstoqueEquals(0);
	}
}
