<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal Parceiros da Coleta-->
<div class="miniTdv modal fade" id="myModalParceiros" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabelParceiros"
	aria-hidden="true">
	<div class="miniTdv modal-dialog">
		<div class="miniTdv modal-content">
			<div class="miniTdv modal-header" style="background-color:#f49b17;">
				<!-- <button type="button" class="miniTdv close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="miniTdv sr-only">Close</span>
				</button> -->
				<h4 class="miniTdv modal-title" id="myModalLabelParceiros">
					<span class="miniTdv coltitleModol" style="color:white"><i class="miniTdv glyphicon glyphicon-user"></i> Parceiros da Coleta</span> <small
						class="miniTdv cicloTitleModal"></small>
				</h4>
			</div>
			<div class="miniTdv modal-body">
				
				<div class="miniTdv col-sm-10">
					<div class="miniTdv row">
						<div class="miniTdv col-sm-2">
							<label>Razão Social:</label>
						</div>
						<div class="miniTdv col-sm-7">
							<input id="id_razaoSocial_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
					</div>
					
					<div class="miniTdv row">
						<div class="miniTdv col-sm-2" >
							<label>Tp End:</label>
						</div>
						<div class="miniTdv col-sm-1">
							<input id="id_tpEnd_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
						<div class="miniTdv col-sm-2" style="text-align:right;">
							<label>Município:</label>
						</div>
						<div class="miniTdv col-sm-4">
							<input id="id_cidade_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
					</div>
					
					<div class="miniTdv row">
						<div class="miniTdv col-sm-2">
							<label>Localidade:</label>
						</div>
						<div class="miniTdv col-sm-2">
							<input id="id_localidade_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
						<div class="miniTdv col-sm-1" style="text-align:right;">
							<label>UF:</label>
						</div>
						<div class="miniTdv col-sm-1">
							<input id="id_uf_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
						<div class="miniTdv col-sm-1" style="text-align:right;">
							<label>CEP:</label>
						</div>
						<div class="miniTdv col-sm-2">
							<input id="id_cep_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
					</div>
					
					<div class="miniTdv row">
						<div class="miniTdv col-sm-2">
							<label>Endereço:</label>
						</div>
						<div class="miniTdv col-sm-7">
							<input id="id_endereco_parceiros" type="text" class="miniTdv form-control" disabled>
						</div>
					</div>
				</div>
				<br><br><br><br><br>	
				<table class="miniTdv table table-bordered table-responsive table-condensed">
					<thead>
						<th>Coleta</th>
						<th>Ciclo</th>
						<th>CNPJ/CPF</th>
						<th>Tp. End.</th>
						<th>Tipo de Parceiro</th>
						<th>Descrição</th>
						<th class="miniTdv text-center"><i class="miniTdv glyphicon glyphicon-tasks"></i></th>
					</thead>
					<tbody id="id_table_parceiros">
							
					</tbody>
				</table>
				
				<!-- <button type="button" class="miniTdv btn btn-default"><i class="miniTdv glyphicon glyphicon-search"></i></button>  -->
				<button id="id_add_parceiro" type="button" class="miniTdv btn btn-primary btn-xs" disabled><i class="miniTdv glyphicon glyphicon-plus"></i><br>Adicionar</button>
				<a class="miniTdv btn btn-danger btn-xs"  href="javascript:hideModalParceiros();"><i class="miniTdv glyphicon glyphicon-remove-circle"></i><br>Cancelar</a>
			</div>
		</div>
	</div>
</div>