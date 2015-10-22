<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="user" />
	<div>
		Olá ${user.name}
	</div>
</security:authorize>

	<section class="container middle">
		<h2 id="cart-title">Seu carrinho de compras</h2>
		<table id="cart-table">
			<colgroup>
				<col class="item-col">
				<col class="item-price-col">
				<col class="item-quantity-col">
				<col class="line-price-col">
				<col class="delete-col">
			</colgroup>
			<thead>
				<tr>
					<th class="cart-img-col"></th>
					<th width="65%">Item</th>
					<th width="10%">Preço</th>
					<th width="10%">Quantidade</th>
					<th width="10%">Total</th>
					<th width="5%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${shoppingCart.list}" var="item">
					<tr>
						<td class="cart-img-col"><img src=""
							alt="${item.product.title}" /></td>
						<td class="item-title">${item.product.title}-
							${item.bookType}</td>
						<td class="numeric-cell">${item.price}</td>
						<td class="quantity-input-cell"><input type="number" min="0"
							readonly="readonly" value="${shoppingCart.getQuantity(item)}"></td>
						<td class="numeric-cell">${shoppingCart.getTotal(item)}</td>
						<td class="remove-item"><form:form method="post"
								action="${spring:mvcUrl('SCC#remove').arg(0,item.product.id).arg(1,item.bookType).build()}">
								<input type="image"
									src="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/excluir.png?58522"
									alt="Excluir" title="Excluir" />
							</form:form></td>
					</tr>
				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<td colspan="2"><form:form
							action="/housecode/payment/checkout" method="post">
							<input type="submit" class="checkout" name="checkout"
								value="Finalizar compra " id="checkout" />
						</form:form></td>
					<td class="numeric-cell">${shoppingCart.total}</td>
					<td></td>
				</tr>
			</tfoot>
		</table>

		</section>
