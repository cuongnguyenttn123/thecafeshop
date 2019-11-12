<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
      
      <div class="form-group">  
        <input id="categoryproductid" type="text" 
          <c:if test = "${categoryproduct.getCategoryproductid()!=null}">
              disabled
          </c:if>
          value= '<c:out value="${categoryproduct.getCategoryproductid()}" />' 
          class="form-control" placeholder="Mã loại"/>
      </div>
      <div class="form-group">
          <input id="name" type="text" value= '<c:out value="${categoryproduct.getName()}" />' class="form-control" placeholder="Tên loại"/>
      </div>
        <c:if test = "${categoryproduct.getCategoryproductid()==null}">
            <button id="btnSave" type="button" class="btn btn-success mr-2">
              Thêm
            </button>
        </c:if>
        <c:if test = "${categoryproduct.getCategoryproductid()!=null}">
            <button id="btnUpdate" type="button" class="btn btn-success mr-2">
              Cập nhật
            </button>
        </c:if>
      <button id="btnClear" class="btn btn-light">Làm mới</button>
<script src="../resouces/ajax-jquery/category-product-edit.js"></script>