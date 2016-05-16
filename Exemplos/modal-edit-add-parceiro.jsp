<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal Parceiros da Coleta-->
<div class="miniTdv modal fade" id="myModalAddParceiro" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabelAddParceiro"
	aria-hidden="true">
	<div class="miniTdv modal-dialog">
		<div class="miniTdv modal-content">
			<div class="miniTdv modal-header" style="background-color:#f49b17;">
				<button type="button" class="miniTdv close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="miniTdv sr-only">Close</span>
				</button>
				<h4 class="miniTdv modal-title" id="myModalLabelAddParceiro">
					 <span class="miniTdv coltitleModol" style="color:white"><i class="miniTdv glyphicon glyphicon-plus"></i> Adicionar Parceiro</span> <small
						class="miniTdv cicloTitleModal"></small>
				</h4>
			</div>
			<div class="miniTdv modal-body">
				
				<div class="miniTdv ">
					<div class="miniTdv row"> 
						<label class="miniTdv radio-inline"><input id="tpPesquisaParceiroC" type="radio" name="tpPesquisaParceiro" value="cpf" > CNPJ/CPF
						</label>
						<label class="miniTdv radio-inline"><input id="tpPesquisaParceiroR" type="radio" name="tpPesquisaParceiro" value="rzSocial"> Razão Social
						</label>
					</div>
					<br>
					<div class="miniTdv row">
						<div class="miniTdv col-sm-4" id="campo">
							<input type="text" id="campoPesquisaParceiro" class="miniTdv form-control" disabled>
						</div>
						<div class="miniTdv col-sm-2">
							<button id="id_modal_pesq_parceiro"class="miniTdv btn btn-default btn-xs" disabled><i class="miniTdv glyphicon glyphicon-search"></i></button>
						</div>
						<div class="miniTdv col-sm-4">
							<select id="cbTpParceiro" size="1" name="cbTpParceiro" class="miniTdv form-control" required>
								<option selected value="">-----------------</option>
								<option value="CT">Transportador Envolvido na Coleta</option>
								<option value="CC">Local Da Coleta diferente do Remetente</option>
								<option value="CE">Local Da Entrega diferente do Destinatário</option>
							</select>
						</div>
					</div>
				</div>
				
				<br>
				<div style="width:100%; height:90px; overflow: auto;" class="miniTdv panel panel-default">
					<table class="miniTdv table table-bordered table-responsive table-condensed">
						<thead>
							<th>CNPJ/CPF</th>
							<th>Razão Social</th>
						</thead>
						<tbody id="id_table_pesq_cnpj_parc">
								
						</tbody>
					</table>
				</div>
				<br>
				<div style="width:100%; height:90px; overflow: auto;" id="tpEndTable" class="miniTdv panel panel-default" hidden>
						<table class="miniTdv table table-bordered table-responsive table-condensed">
							<thead>
								<th>CNPJ/CPF</th>
								<th>Tp. End.</th>
								<th>Cidade</th>
								<th>CEP</th>
								<th>Endereço</th>
								<th>Complemento</th>
								<th class="miniTdv text-center"><i class="miniTdv glyphicon glyphicon-tasks"></i></th>
							</thead>
							<tbody id="id_table_parc_tp_end">
									
							</tbody>
						</table>
					</div>
				<button id="id_seleciona_parceiro" type="button" class="miniTdv btn btn-primary btn-xs"><i class="miniTdv glyphicon glyphicon-circle-arrow-down"></i><br>Selecionar</button>
				<a class="miniTdv btn btn-danger btn-xs"  href="javascript:hideModalAddParceiros();"><i class="miniTdv glyphicon glyphicon-remove-circle"></i><br>Cancelar</a>		
			</div>
<!-- 		<div class="miniTdv modal-footer">
				
			</div> -->
		</div>
	</div>
</div>