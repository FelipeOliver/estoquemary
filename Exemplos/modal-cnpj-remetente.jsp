<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal Parceiros da Coleta-->
<div class="modal fade" id="myModalCnpjRemetente" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabelCnpjRemetente"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#f49b17;">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabelCnpjRemetente">
					<span class="coltitleModol" style="color:white"><i class="glyphicon glyphicon-user"></i> Remetente da Coleta</span> <small
						class="cicloTitleModal"></small>
				</h4>
			</div>
			<div class="modal-body">
			
				<div class="miniTdv ">
					<div class="miniTdv row"> 
						<label class="miniTdv radio-inline"><input id="tpPesquisaRemetenteC" type="radio" name="tpPesquisaRemetente" value="cpf" > CNPJ/CPF
						</label>
						<label class="miniTdv radio-inline"><input id="tpPesquisaRemetenteR" type="radio" name="tpPesquisaRemetente" value="rzSocial"> Razão Social
						</label>
					</div>
					<br>
					<div class="row">
						<div class="miniTdv col-sm-3" id="campoRemetente">
							<input type="text" id="campoPesquisaRemetente" class="miniTdv form-control" disabled>
						</div>
						<div class="miniTdv col-sm-1">
							<button id="id_modal_pesq_remetente"class="miniTdv btn btn-default btn-xs" disabled><i class="glyphicon glyphicon-search"></i></button>
						</div>
					</div>
				</div>
				
				<br>
				<div style="width:100%; height:90px; overflow: auto;" class="miniTdv panel panel-default">
					<table class="table table-bordered table-responsive table-condensed">
						<thead>
							<th>CNPJ/CPF</th>
							<th>Razão Social</th>
						</thead>
						<tbody id="id_table_pesq_cnpj_remet">
								
						</tbody>
					</table>
				</div>
				
				<!-- <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>  -->
				<button id="id_sel_remetente" type="button" class="miniTdv btn btn-primary btn-xs"><i class="miniTdv glyphicon glyphicon-circle-arrow-down"></i><br>Selecionar</button>
				<a class="miniTdv btn btn-danger btn-xs"  href="javascript:hideModalCnpjRemetente();"><i class="miniTdv glyphicon glyphicon-remove-circle"></i><br>Cancelar</a>
			</div>
		</div>
	</div>
</div>