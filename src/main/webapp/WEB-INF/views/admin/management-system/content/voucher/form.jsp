<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
      
<c:if test = "${voucher.getVoucherid()!=null}">
      <div class="form-group">  
                <input id="voucherid" type="text" 
                    disabled
                value= '<c:out value="${voucher.getVoucherid()}" />' 
                class="form-control" placeholder="Mã voucher"/>
      </div>
    </c:if> 
      <div class="form-group">
          <input id="name" type="text" value= '<c:out value="${voucher.getName()}" />' class="form-control" placeholder="Tên voucher"/>
      </div>
       <div class="form-group">
          <input id="startdatetime" 
            <c:if test = "${temb == true}">
                disabled
            </c:if> 
            type="date" value= '<c:out value="${voucher.getStartdatetime()}" />' class="form-control" placeholder="Ngày bắt đầu"/>
      </div>
      <div class="form-group">
         <input id="enddate" type="date" value= '<c:out value="${voucher.getEnddate()}" />' class="form-control" placeholder="Ngày kết thúc"/>
     </div>
     <div class="form-group">
        <input id="number" type="number" value= '<c:out value="${voucher.getNumber()}" />' class="form-control" placeholder="Số lượng phát"/>
    </div>
    <c:if test = "${voucher.getVoucherid()!=null}">
        <div class="form-group">
        <input id="count" disabled type="number" value= '<c:out value="${voucher.getCount()}" />' class="form-control" placeholder="Vourcher còn lại"/>
        </div>
    </c:if>
    <div class="form-group">
        <input id="discount" 
            <c:if test = "${temb == true}">
                    disabled
            </c:if> 
        type="text" value= '<c:out value="${voucher.getDiscount()}" />' class="form-control" placeholder="Giảm giá"/>
    </div>
        <c:if test = "${voucher.getVoucherid()==null}">
            <button id="btnSave" type="button" class="btn btn-success mr-2">
              Thêm
            </button>
        </c:if>
        <c:if test = "${voucher.getVoucherid()!=null}">
            <button id="btnUpdate" type="button" class="btn btn-success mr-2">
              Cập nhật
            </button>
        </c:if>
      <button id="btnClear" class="btn btn-light">Làm mới</button>
<script src="../resouces/ajax-jquery/voucher/voucher-edit.js"></script>