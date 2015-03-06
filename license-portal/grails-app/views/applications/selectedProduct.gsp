<%@ page contentType="text/html;charset=UTF-8" %>
<g:form class="form-verticle">
    <div class="row">
    <table class="table table-striped table-condensed">
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Hosts Licensed</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Calculation</th>
            </tr>
        </thead>    
        <tbody>
            <g:each in="${productsList}" status="i" var="productInstance">
                <input name="hostQuantity" value="${productInstance.licenseQuantity}" type="hidden"/>
                <input name="productId" value="${productInstance.id}" type="hidden"/>
                <input name="price" value="${productInstance.price}"  type="hidden"/>   
                <tr>
                    <td>
                        ${productInstance.name}
                    </td>
                    <td>
                        ${productInstance.licenseQuantity}
                    </td>
                    <td>
                        <span id="price-${productInstance.id}">${productInstance.price}</span>
                    </td>
                    <td>
                        <g:if test="${productInstance.baseProduct == null}">
                            <input name="quantity" type="number" min="0" value="1" readonly="" class="form-control"/>
                        </g:if>
                        <g:if test="${productInstance.baseProduct != null}">
                            <input name="quantity" type="number" min="0" value="0" class="form-control" onkeyup="product.calculate(this, ${productInstance.id})"/>
                        </g:if>
                    </td>
                    <td>
                        <g:if test="${productInstance.baseProduct == null}">
                             <span id="total-${productInstance.id}">${1 * productInstance.price}</span>
                        </g:if>
                        <g:if test="${productInstance.baseProduct != null}">
                             <span id="total-${productInstance.id}">0</span>
                        </g:if>
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>
    </div>
    <div class="row">
        <label class="col-sm-3 control-label">Month</label>
        <div class="col-sm-3">
            <select class="form-control" name="licenseExpiryMonth" id="licenseExpiryMonth">
                <option value="3">Quarterly</option>
                <option value="6">Half Yearly</option>
                <option value="12">Annualy</option>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <g:actionSubmit class="btn btn-primary pull-right" value="buy" action="buyLicense" />
        </div>
    </div>
    <div class="row">
        <p>I would like to give a <g:link action="freeTrial" params="[productId: baseProduct.id]">free trial</g:link></p>
    </div>
</g:form>
